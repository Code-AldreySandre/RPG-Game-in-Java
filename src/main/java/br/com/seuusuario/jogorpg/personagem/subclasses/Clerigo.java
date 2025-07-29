package br.jogo.personagens.subclasses;

import br.jogo.personagens.Hero;
import br.jogo.personagens.Player;
import ia.AdaptiveAI;
import logs.Log;
import logs.TipoLog;

import java.util.List;

public class Clerigo extends Hero {
    private List<String> feicoesDivinas;
    private AdaptiveAI ia;

    public Clerigo(String nome, int hp, int ataque, int defesa, int destreza, int velocidade, int nivel,
                   int experiencia, int mana, List<String> feicoesDivinas) {
        super(nome, hp, ataque, defesa, destreza, velocidade, nivel, experiencia, mana);
        this.feicoesDivinas = feicoesDivinas;
        this.ia = new AdaptiveAI();
    }

    public List<String> getFeicoesDivinas() {
        return feicoesDivinas;
    }

    public void setFeicoesDivinas(List<String> feicoesDivinas) {
        this.feicoesDivinas = feicoesDivinas;
    }

    @Override
    public void usarHabilidadeEspecial(Player aliado) {
        if (this.mana >= 15) {
            int cura = (int) (this.ataque * 1.5);
            this.mana -= 15;
            aliado.setHp(aliado.getHp() + cura);

            System.out.println(this.nome + " invoca uma benção sobre " + aliado.getNome() + ", curando " + cura + " de HP!");

            Log log = new Log(this.nome + " usou benção curativa em " + aliado.getNome() + " curando " + cura + " de HP.", TipoLog.ACAO, this);
            log.salvar();

            ia.registrarAcao("usarHabilidadeEspecial", cura);
        } else {
            System.out.println(this.nome + " não tem mana suficiente para curar.");

            Log log = new Log(this.nome + " tentou usar benção curativa mas não tinha mana suficiente.", TipoLog.ACAO, this);
            log.salvar();
        }
    }

    @Override
    public void realizarAtaque(Player alvoPlayer) {
        ResultadoAtaque resultado = SistemaCombate.calcularResultadoAtaque(this, alvoPlayer);
        int dano = SistemaCombate.calcularDano(this, alvoPlayer, resultado);
        SistemaCombate.aplicarDano(alvoPlayer, dano);

        System.out.println(this.nome + " golpeia " + alvoPlayer.getNome() + " com sua maça sagrada causando " + dano + " de dano!");

        Log log = new Log(this.nome + " realizou ataque básico causando " + dano + " de dano em " + alvoPlayer.getNome(), TipoLog.ACAO, this);
        log.salvar();

        ia.registrarAcao("realizarAtaque", dano);
    }

    // Método exclusivo do clérigo
    public void reviver(Player aliado) {
        if (this.mana >= 30 && aliado.getHp() <= 0) {
            aliado.setHp(40); // revive com 40 de HP
            this.mana -= 30;

            System.out.println(this.nome + " usa poder divino para reviver " + aliado.getNome() + " com 40 de HP!");

            Log log = new Log(this.nome + " reviveu " + aliado.getNome() + " com 40 de HP.", TipoLog.ACAO, this);
            log.salvar();

            ia.registrarAcao("reviver", 40);
        } else {
            System.out.println(this.nome + " não pode reviver " + aliado.getNome() + " (sem mana ou ele ainda está vivo).");

            Log log = new Log(this.nome + " tentou reviver " + aliado.getNome() + " mas falhou.", TipoLog.ACAO, this);
            log.salvar();
        }
    }

    @Override
    protected int getHp() {
        return this.hp;
    }
}
