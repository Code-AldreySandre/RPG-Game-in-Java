package br.jogo.personagens;

import java.util.Random;

public class Monster extends Player {

    public Monster(String nome, int hp, int ataque, int defesa, int destreza, int velocidade, int nivel) {
        super(nome, hp, ataque, defesa, destreza, velocidade, nivel);
    }

    @Override
    public void realizarAtaque(Player alvoPlayer) {
        Random rand = new Random();

        int chanceAcerto = this.destreza + rand.nextInt(100);
        if (chanceAcerto > 50) {  // Exemplo simples de acerto
            int dano = this.ataque - alvoPlayer.getDefesa();
            dano = dano > 0 ? dano : 1; // Dano mínimo 1
            alvoPlayer.setHp(alvoPlayer.getHp() - dano);
            System.out.println(this.nome + " atacou " + alvoPlayer.getNome() + " causando " + dano + " de dano.");
        } else {
            System.out.println(this.nome + " errou o ataque em " + alvoPlayer.getNome() + ".");
        }
    }

    @Override
    protected int getHp() {
        return this.hp;
    }

    public boolean estaVivo() {
        return this.hp > 0;
    }

    public static Monster gerarMonstroAleatorio() {
        Random rand = new Random();

        String nome = "Monstro" + (rand.nextInt(1000));
        int hp = 50 + rand.nextInt(51);          // 50 a 100
        int ataque = 10 + rand.nextInt(11);      // 10 a 20
        int defesa = 5 + rand.nextInt(6);        // 5 a 10
        int destreza = 10 + rand.nextInt(21);    // 10 a 30
        int velocidade = 5 + rand.nextInt(11);   // 5 a 15
        int nivel = 1 + rand.nextInt(5);          // 1 a 5

        return new Monster(nome, hp, ataque, defesa, destreza, velocidade, nivel);
    }

    @Override
    public String toString() {
        return "Monstro " + super.toString();
    }
}
