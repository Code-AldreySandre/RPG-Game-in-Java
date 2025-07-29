package br.jogo.personagens;

public abstract class Hero extends Player {
    protected int experiencia;
    protected int mana;

    public Hero(String nome, int hp, int ataque, int defesa, int destreza, int velocidade, int nivel, int experiencia, int mana){
        super(nome, hp, ataque, defesa, destreza, velocidade, nivel);
        this.experiencia = experiencia;
        this.mana = mana;
    }

    public int getExperiencia(){
        return experiencia;
    }

    public void setExperiencia(int experiencia){
        this.experiencia = experiencia;
    }

    public void setMana(int mana){
        this.mana = mana;
    }

    // Heróis terão habilidades especiais, ,mas como isso depende da subclasse, deixamos abstrato
    public abstract void usarHabilidadeEspecial(Player alvoPlayer);
}
