package br.jogo.personagens.subclasses;

import br.jogo.personagens.Hero;
import br.jogo.personagens.Player;

import java.util.List;
public class Clerigo  extends Hero{
    private List<String> feicoesDivinas;

    public Clerigo(String nome, int hp, int ataque, int defesa, int destreza, int velocidade, int nivel, int experiencia, int mana, List<String> feicoesDivinas){
        super(nome, hp, ataque, defesa, destreza, velocidade, nivel, experiencia, mana);
        this.feicoesDivinas = feicoesDivinas;
    }

    public List<String> getFeicoesDivinas(){
        return feicoesDivinas;
    }

    public void setFeicoesDivinas(List<String> feicoesDivinas) {
        this.feicoesDivinas = feicoesDivinas;
    }

    @Override
    public void usarHabilidadeEspecial(Player aliado) {
        if (this.mana >= 15){
            int cura = (int)(this.ataque * 1.5);
            this.mana -= 15;
            aliado.setHp(aliado.getHp() + cura);

            System.out.println(this.nome + " invoca uma benção sobre " + aliado.getNome() + ", curando " + cura + " de HP!");
        } else {
            System.out.println(this.nome + " não tem mana suficiente para curar.");
        }
    }

    @Override
    public void realizarAtaque(Player alvoPlayer) {
        // Ataque básio reduzido, pois o clérigo é suporte 
         int dano = (int) (this.ataque * 0.8) - alvoPlayer.getDefesa();
        if (dano < 0) dano = 0;
        alvoPlayer.setHp(alvoPlayer.getHp() - dano);

        System.out.println(this.nome + " golpeia " + alvoPlayer.getNome() + " com sua maça sagrada causando " + dano + " de dano!");

    }

    // Método exclusivo do clérigo
    public void reviver(Player aliado) {
        if (this.mana >= 30 && aliado.getHp() <= 0) {
            aliado.setHp(40); // revive com 40 de HP
            this.mana -= 30;

            System.out.println(this.nome + " usa poder divino para reviver " + aliado.getNome() + " com 40 de HP!");
        } else {
            System.out.println(this.nome + " não pode reviver " + aliado.getNome() + " (sem mana ou ele ainda está vivo).");
        }
    }

    @Override
    protected int getHp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHp'");
    }
    
}
