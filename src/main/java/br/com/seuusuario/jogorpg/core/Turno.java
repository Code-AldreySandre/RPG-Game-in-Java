package nucleo;
import Player.Player;
import Player.Hero;
import monstros.Monster;

import java.util.*;


public class Turno {
    private int rodada;
    public List<Player> jogadores;
    private List<Player> ordemTurno;
    private int acoesRealizadas;

    public Turno(int rodada, List<Player> jogadores, Game game) {
        this.rodada = rodada;
        this.jogadores = jogadores;
        this.ordemTurno = ordenarPorVelocidade();
        this.acoesRealizadas = 0;

        List<Hero> herois = game.getHerois();
        List<Monster> monstros = game.getMonstros();
    }

    public void iniciarTurno(Game game) {
        acoesRealizadas = 0;
        System.out.println("===========================================");
        System.out.printf("          %d° rodada iniciada\n", rodada++);
        System.out.println("===========================================");

        while (!verificarFimdoTurno()) {
            Player jogador = ordemTurno.get(acoesRealizadas); // Jogador que vai agir no turno
            System.out.println("\n");
            System.out.println(jogador.getNome() + " está agindo...");

            // Verifica o tipo do jogador (Hero ou Monster)
            if (jogador instanceof Hero) {
                // Herói ataca um monstro
                for (Monster monstro : game.getMonstros()) {
                    if (monstro.getHp() > 0) {
                        int dano = 10;
                        monstro.setHp(monstro.getHp() - dano);
                        System.out.println(jogador.getNome() + " atacou " + monstro.getNome() + " causando " + dano + " de dano\n!");
                        break; // Sai do loop após atacar o primeiro monstro
                    }
                }
            } else if (jogador instanceof Monster) {
                // Monstro ataca um herói
                for (Hero heroi : game.getHerois()) {
                    if (heroi.getHp() > 0) {
                        int dano = 10;
                        heroi.setHp(heroi.getHp() - dano);
                        System.out.println(jogador.getNome() + " atacou " + heroi.getNome() + " causando " + dano + " de dano!");
                        break; // Sai do loop após atacar o primeiro herói
                    }
                }
            }

            // Exibe as estatísticas após a ação do jogador
            for (Map.Entry<String, Player> entry : game.getEstatisticasBatalha().entrySet()) {
                Player p = entry.getValue();
                System.out.println(p.getNome() + " - HP: " + p.getHp() + ", Velocidade: " + p.getVelocidade());
            }

            acoesRealizadas++;  // Incrementa o contador de ações realizadas

            // Verifica se todos os jogadores já agiram
            if (verificarFimdoTurno()) {
                System.out.println("Turno concluído.");
            }
        }
    }

    public List<Player> ordenarPorVelocidade() {
        List<Player> ordenada = new ArrayList<>(jogadores);
        ordenada.sort(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getVelocidade(), p1.getVelocidade());
            }
        });
        return ordenada;
    }

    public void executarAcaoJogador() {
        //Irá entrar o código do Aldrey

    }

    public boolean verificarFimdoTurno() {
        return acoesRealizadas >= ordemTurno.size();

    }

    public List<Player> getOrdemTurno() {
        System.out.println("A ordem dos ataques dos hérois é: ");
        for (Player ordemTurno : ordemTurno) {
            System.out.println(ordemTurno.getNome());
        }
        return ordemTurno;
    }

    public void processarAcoesMonstros() {
        //Irá entrar o código do Aldrey

    }
}
