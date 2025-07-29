package br.jogo.personagens;

public abstract class Player {
    protected String nome;
    protected int hp;
    protected int ataque;
    protected int defesa;
    protected int destreza;
    protected int velocidade;
    protected int nivel;

    public Player(String nome, int hp, int ataque, int defesa,int destreza,  int velocidade, int nivel){
        this.nome = nome;
        this.hp = hp;
        this.ataque = ataque;
        this.defesa = defesa;
        this.destreza = destreza;
        this.velocidade = velocidade;
        this.nivel = nivel;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public int getAtaque(){
        return ataque;
    }

    public int setAtaque(int ataque){
        return this.ataque = ataque;
    }

    public int getDefesa(){
        return defesa;
    }

    public int setDefesa(int defesa){
        return this.defesa = defesa;
    }

    public int getVelocidade(){
        return velocidade;
    }

    public int setVelocidade(int velocidade){
        return this.velocidade = velocidade;
    }

    public int getNivel(){
        return nivel;
    }

    public void setNivel(int nivel){
        this.nivel = nivel;
    }

    public abstract void realizarAtaque(Player alvo);
    
    public static Player gerarPlayerAleatorio(){
        throw new UnsupportedOperationException("Implementar geração aleatória nas subclasses.");
    }

    @Override
    public String toString(){
        return nome + " (HP: " + hp + ", Atk: " + ataque + ", Def: " +  defesa + ", Des: " + destreza + ", Vel: " + velocidade + ", Nível: " + nivel + ")";
    }

    protected abstract int getHp();
}

