package br.jogo.core;

import br.jogo.personagens.Player;
import br.jogo.personagens.Hero;
import br.jogo.personagens.Monster;
import br.jogo.core.Game;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class Turno {
    private int rodada;
    private List<Player> jogadores;
    private List<Player> ordemTurno;
    private int acoesRealizadas;

    public Turno(int rodada, List<Player> jogadores, Game game) {
        this.rodada = rodada;
        this.jogadores = jogadores;
        this.ordemTurno = ordenarPorVelocidade();
        this.acoesRealizadas = 0;
    }

    public void iniciarTurno(Game game) {
        acoesRealizadas = 0;
        System.out.println("===========================================");
        System.out.printf("          %d° rodada iniciada\n", rodada);
        System.out.println("===========================================");

        while (!verificarFimdoTurno()) {
            Player jogador = ordemTurno.get(acoesRealizadas);
            System.out.println("\n" + jogador.getNome() + " está agindo...");

            if (jogador instanceof Hero) {
                // Heroi escolhe um monstro vivo para atacar
                Monster alvo = game.getMonstros().stream()
                    .filter(m -> m.getHp() > 0)
                    .findFirst()
                    .orElse(null);

                if (alvo != null) {
                    jogador.realizarAtaque(alvo);
                }
            } else if (jogador instanceof Monster) {
                // Monstro escolhe um herói vivo para atacar
                Hero alvo = game.getHerois().stream()
                    .filter(h -> h.getHp() > 0)
                    .findFirst()
                    .orElse(null);

                if (alvo != null) {
                    jogador.realizarAtaque(alvo);
                }
            }

            // Exibe as estatísticas após a ação do jogador
            System.out.println("\nStatus após ação:");
            for (Map.Entry<String, Player> entry : game.getEstatisticasBatalha().entrySet()) {
                Player p = entry.getValue();
                System.out.println(p.getNome() + " - HP: " + p.getHp() + ", Velocidade: " + p.getVelocidade());
            }

            acoesRealizadas++;

            if (verificarFimdoTurno()) {
                System.out.println("\nTurno concluído.\n");
            }
        }
    }

    private List<Player> ordenarPorVelocidade() {
        List<Player> ordenada = new ArrayList<>(jogadores);
        ordenada.sort(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getVelocidade(), p1.getVelocidade());
            }
        });
        return ordenada;
    }

    public boolean verificarFimdoTurno() {
        return acoesRealizadas >= ordemTurno.size();
    }

    public List<Player> getOrdemTurno() {
        System.out.println("A ordem dos ataques dos jogadores é:");
        for (Player p : ordemTurno) {
            System.out.println(p.getNome());
        }
        return ordemTurno;
    }

}
