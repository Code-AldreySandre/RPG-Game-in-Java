package br.jogo.personagens;

import java.util.List;

public class Mago extends Hero{
    private List<String> feiticos;

    public Mago(String nome, int hp, int ataque, int defesa, int destreza, int velocidade, int nivel, int experiencia, int mana, List<String> feiticos){
        super(nome, hp, ataque, defesa, destreza, velocidade, nivel, experiencia, mana);
        this.feiticos = feiticos;
    }

    public void setFeiticos(List<String> feiticos) {
        this.feiticos = feiticos;
    }
    

    @Override
    public void usarHabilidadeEspecial(Player alvoPlayer) {
      if (this.mana >= 20 && !feiticos.isEmpty()){
        String feitiço = feiticos.get(0); // por agora, pega o primeiro
        int danoEspecial = this.ataque + 15;
        this.mana -= 20;
        alvoPlayer.setHp(alvoPlayer.getHp() - danoEspecial);

         System.out.println(this.nome + " usa o feitiço " + feitiço + " em " + alvoPlayer.getNome() + " causando " + danoEspecial + " de dano mágico!");
        } else {
            System.out.println(this.nome + " não tem mana suficiente ou não possui feitiços.");
        }
    }

    @Override
    public void realizarAtaque(Player alvoPlayer) {
       // Ataque mágico: ignora parte da defesa
       int dano = (int) (this.ataque * 1.2) - (alvoPlayer.getDefesa() / 2);
       if (dano < 0) dano = 0;
       alvoPlayer.setHp(alvoPlayer.getHp() - dano);

       System.out.println(this.nome + "Lnça um feitiço básico em " + alvoPlayer.getNome() + "causando " + dano + " de dado!");
    }

    @Override
    protected int getHp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHp'");
    }
    
}
