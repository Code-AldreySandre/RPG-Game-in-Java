package br.jogo.personagens.subclasses;

import br.jogo.personagens.Hero;
import br.jogo.personagens.Player;
import ia.AdaptiveAI;
import logs.Log;
import logs.TipoLog;

public class Guerreiro extends Hero{
    private int furia;
    private IAAdaptativa ia;

    public Guerreiro(String nome, int hp, int ataque, int defesa, int destreza, int velocidade,
                     int experiencia, int mana, int furia){
        super(nome, hp, ataque, defesa, destreza, velocidade, velocidade, experiencia, mana);
        this.furia = furia;
        this.ia = new IAAdaptativa();
    }

    public int getFuria(){
        return furia;
    }

    public void setFuria(int furia){
        this.furia = furia;
    }

    @Override
    public void realizarAtaque(Player alvoPlayer){
        int dano = this.ataque - alvoPlayer.getDefesa();
        if (dano < 0) dano = 0;
        alvoPlayer.setHp(alvoPlayer.getHp() - dano);

        System.out.println(this.nome + " ataca " + alvoPlayer.getNome() + " causando " + dano + " de dano!");

        // Log da ação
        Log log = new Log(this.nome + " realizou ataque básico causando " + dano + " de dano em " + alvoPlayer.getNome(), TipoLog.ACAO, this);
        log.salvar();

        ia.registrarAcao("realizarAtaque", dano);
    }

    @Override
    public void usarHabilidadeEspecial(Player alvoPlayer){
        if (this.furia >= 10){
            int danoEspecial = (int)(this.ataque * 1.5);
            this.furia -= 10;
            alvoPlayer.setHp(alvoPlayer.getHp() - danoEspecial);

            System.out.println(this.nome + " usa Golpe de Fúria em " + alvoPlayer.getNome() + " causando " + danoEspecial + " de dano!");

            // Log da ação especial
            Log log = new Log(this.nome + " usou Golpe de Fúria causando " + danoEspecial + " de dano em " + alvoPlayer.getNome(), TipoLog.ACAO, this);
            log.salvar();

            ia.registrarAcao("usarHabilidadeEspecial", danoEspecial);
        } else {
            System.out.println(this.nome + " não tem fúria suficiente para usar a habilidade especial.");

            // Log da falha
            Log log = new Log(this.nome + " tentou usar Golpe de Fúria, mas não tinha fúria suficiente.", TipoLog.ACAO, this);
            log.salvar();
        }
    }

    @Override
    protected int getHp() {
        return this.hp;
    }
}
