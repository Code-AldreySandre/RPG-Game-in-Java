package br.jogo.personagens;

import java.util.Random;

import br.jogo.ia.AdaptiveAI;

public class Monster extends Player {
    private static final AdaptiveAI ia = new AdaptiveAI();
    
    public Monster(String nome, int hp, int ataque, int defesa, int destreza, int velocidade, int nivel) {
        super(nome, hp, ataque, defesa, destreza, velocidade, nivel);
    }

    @Override
    public void realizarAtaque(Player alvoPlayer) {
        ResultadoAtaque resultado = SistemaCombate.calcularResultadoAtaque(this, alvoPlayer);
        int dano = SistemaCombate.calcularDano(this, alvoPlayer, resultado);
        SistemaCombate.aplicarDano(alvoPlayer, dano);

        String mensagem;
        switch (resultado) {
            case ERROU:
                mensagem = this.nome + " tentou atacar " + alvoPlayer.getNome() + ", mas errou!";
                break;
            case CRITICAL_HIT:
                mensagem = this.nome + " desferiu um GOLPE CRÍTICO em " + alvoPlayer.getNome()
                        + ", causando " + dano + " de dano!";
                break;
            case ACERTOU:
            default:
                mensagem = this.nome + " atacou " + alvoPlayer.getNome() + " causando " + dano + " de dano.";
                break;
        }

        System.out.println(mensagem);

        Log log = new Log(mensagem, TipoLog.COMBATE, this);
        log.salvar();
    }

    public static Monster gerarMonstroAdaptativo(int turno) {
        Random rand = new Random();

        // Obtem parâmetros ajustados pela IA adaptativa
        AdaptiveAI.MonsterStats stats = ia.gerarAtributosAdaptativos(turno);

        String nome = "Monstro" + rand.nextInt(1000);

        return new Monster(nome, stats.hp(), stats.ataque(), stats.defesa(), stats.destreza(), stats.velocidade(), stats.nivel());
    }

    public static Monster gerarMonstroAleatorio() {
        Random rand = new Random();

        String nome = "Monstro" + rand.nextInt(1000);
        int hp = 50 + rand.nextInt(51);          // 50 a 100
        int ataque = 10 + rand.nextInt(11);      // 10 a 20
        int defesa = 5 + rand.nextInt(6);        // 5 a 10
        int destreza = 10 + rand.nextInt(21);    // 10 a 30
        int velocidade = 5 + rand.nextInt(11);   // 5 a 15
        int nivel = 1 + rand.nextInt(5);         // 1 a 5

        return new Monster(nome, hp, ataque, defesa, destreza, velocidade, nivel);
    }

    public boolean estaVivo() {
        return this.hp > 0;
    }

    @Override
    public String toString() {
        return "Monstro " + super.toString();
    }

    @Override
    protected int getHp() {
        return this.hp;
    }
}
