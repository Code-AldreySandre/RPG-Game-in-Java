package br.jogo.personagens.subclasses;

import br.jogo.personagens.Hero;
import br.jogo.personagens.Player;
import logs.Log;
import logs.TipoLog;
import ia.AdaptiveAI;  

import java.util.List;

public class Mago extends Hero {
    private List<String> feiticos;
    private IAAdaptativa ia;  // referência à IA para ajustar estratégia
    
    public Mago(String nome, int hp, int ataque, int defesa, int destreza, int velocidade, int nivel, int experiencia, int mana, List<String> feiticos){
        super(nome, hp, ataque, defesa, destreza, velocidade, nivel, experiencia, mana);
        this.feiticos = feiticos;
        this.ia = new IAAdaptativa();
    }

    public void setFeiticos(List<String> feiticos) {
        this.feiticos = feiticos;
    }

    @Override
    public void usarHabilidadeEspecial(Player alvoPlayer) {
        if (this.mana >= 20 && !feiticos.isEmpty()) {
            String feitiço = feiticos.get(0); // por enquanto, pega o primeiro
            int danoEspecial = this.ataque + 15;
            this.mana -= 20;
            alvoPlayer.setHp(alvoPlayer.getHp() - danoEspecial);

            System.out.println(this.nome + " usa o feitiço " + feitiço + " em " + alvoPlayer.getNome() + " causando " + danoEspecial + " de dano mágico!");

            // Log da ação
            Log log = new Log(this.nome + " usou habilidade especial " + feitiço + " em " + alvoPlayer.getNome() + " causando " + danoEspecial + " de dano mágico.", TipoLog.ACAO, this);
            log.salvar();

            // Atualiza IA adaptativa
            ia.registrarAcao("usarHabilidadeEspecial", danoEspecial);
        } else {
            System.out.println(this.nome + " não tem mana suficiente ou não possui feitiços.");
            
            // Log da falha
            Log log = new Log(this.nome + " tentou usar habilidade especial, mas falhou por falta de mana ou feitiços.", TipoLog.ACAO, this);
            log.salvar();
        }
    }

    @Override
    public void realizarAtaque(Player alvoPlayer) {
        int dano = (int) (this.ataque * 1.2) - (alvoPlayer.getDefesa() / 2);
        if (dano < 0) dano = 0;
        alvoPlayer.setHp(alvoPlayer.getHp() - dano);

        System.out.println(this.nome + " lança um feitiço básico em " + alvoPlayer.getNome() + " causando " + dano + " de dano!");

        // Log da ação
        Log log = new Log(this.nome + " realizou ataque básico causando " + dano + " de dano em " + alvoPlayer.getNome(), TipoLog.ACAO, this);
        log.salvar();

        // Atualiza IA adaptativa
        ia.registrarAcao("realizarAtaque", dano);
    }

    @Override
    protected int getHp() {
        // pode implementar ou usar diretamente o atributo protegido da superclasse
        return this.hp;
    }
}
