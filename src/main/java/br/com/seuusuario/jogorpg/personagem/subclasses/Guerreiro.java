package br.jogo.personagens.subclasses;

import br.jogo.personagens.Hero;
import br.jogo.personagens.Player;

public class Guerreiro extends Hero{
    private int furia;

    public Guerreiro(String nome, int hp, int ataque, int defesa, int destreza, int velocidade, int experiencia, int mana, int furia){
        super(nome, hp, ataque, defesa, destreza, velocidade, velocidade, experiencia, mana);
        this.furia = furia;
    }

    public int getFuria(){
        return furia;
    }

    public void setFuria(int furia){
        this.furia = furia;
    }

    @Override
    public void realizarAtaque(Player alvoPlayer){
        // Lógica simples de ataque físico: dano = ataque - defesa do alvo
        int dano = this.ataque - alvoPlayer.getDefesa();
        if (dano < 0) dano = 0;
        alvoPlayer.setHp(alvoPlayer.getHp() - dano);

        System.out.println(this.nome + "ataca" + alvoPlayer.getNome() + " causando" + dano + " de dano!");
    }

    @Override
    public void usarHabilidadeEspecial(Player alvoPlayer){
        if (this.furia >= 10){
            int danoEspecial = (int)(this.ataque * 1.5);
            this.furia -= 10;
            alvoPlayer.setHp(alvoPlayer.getHp() - danoEspecial);
            System.out.println(this.nome + " usa Golpe de Fúria em " + alvoPlayer.getNome() + " causando " + danoEspecial + " de dano!");
        } else {
            System.out.println(this.nome + " não tem fúria suficiente para usar a habilidade especial.");
        }
    }

    @Override
    protected int getHp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHp'");
    }
}
