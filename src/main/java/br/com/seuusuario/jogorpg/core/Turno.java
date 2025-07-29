package nucleo;

import Player.Player;
import Player.Hero;
import log.Log;
import enums.TipoLog;
import enums.ResultadoAtaque;
import core.SistemaCombate;

import java.util.*;

public class Turno {
    private int rodada;
    private List<Player> jogadores;
    private List<Player> ordemTurno;
    private int acoesRealizadas;

    public Turno(int rodada, List<Player> jogadores) {
        this.rodada = rodada;
        this.jogadores = jogadores;
        this.ordemTurno = ordenarPorVelocidade();
        this.acoesRealizadas = 0;
    }

    public void iniciarTurno() {
        acoesRealizadas = 0;
        System.out.println("===========================================");
        System.out.printf("          %d° rodada iniciada\n", rodada++);
        System.out.println("===========================================");

        while (!verificarFimTurno()) {
            Player jogador = ordemTurno.get(acoesRealizadas);

            if (jogador.getHp() <= 0) {
                acoesRealizadas++;
                continue;
            }

            if (jogador instanceof Hero) {
                executarAcaoJogador(jogador);
            } else {
                processarAcoesMonstros(jogador);
            }

            acoesRealizadas++;
        }

        System.out.println("Turno concluído.");
    }

    public void executarAcaoJogador(Player heroi) {
//        Player alvo = null;         // TODO: quando a lógica de IA para escolha do alvo for implementada, substituir este bloco comentado
//        for (Player p : jogadores) {
//            if (!(p instanceof Hero) && p.getHp() > 0) {
//                alvo = p;
//                break;
//            }
//        }

        if (alvo != null) {
            ResultadoAtaque resultado = SistemaCombate.calcularResultadoAtaque(heroi, alvo);
            int dano = SistemaCombate.calcularDano(heroi, alvo, resultado);
            SistemaCombate.aplicarDano(alvo, dano);

            String msg = heroi.getNome() + " atacou " + alvo.getNome()
                    + " e " + resultado.name().toLowerCase().replace('_', ' ')
                    + ", causando " + dano + " de dano.";
            new Log(msg, TipoLog.COMBATE, heroi).salvar();

            if (alvo.getHp() == 0) {
                new Log(alvo.getNome() + " foi derrotado!", TipoLog.MORTE, alvo).salvar();
            }
        } else {
            System.out.println(heroi.getNome() + " não encontrou nenhum monstro vivo.");
        }
    }

    public void processarAcoesMonstros(Player monstro) {
        // TODO: lógica de IA feita pelo Aldrey
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

    public boolean verificarFimTurno() {
        return acoesRealizadas >= ordemTurno.size();
    }

    public List<Player> getOrdemTurno() {
        System.out.println("A ordem dos ataques dos hérois é: " );
        for(Player ordemTurno : ordemTurno){
            System.out.println(ordemTurno.getNome());}
        return ordemTurno;
    }
}
