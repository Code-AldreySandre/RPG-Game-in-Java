package nucleo;
import Player.Player;
import Player.Hero;

import java.util.*;


public class Turno {
    private int rodada;
    public List<Player> jogadores;
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


        while (!verificarFimdoTurno()) {
            Player jogador = ordemTurno.get(acoesRealizadas);

            // Aqui irá entrar a ordenação e execução dos ataques
            System.out.println(jogador.getNome() + " está agindo...");

            // Após executar a ação:
            acoesRealizadas++;
        }

        System.out.println("Turno concluído.");

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

    public boolean verificarFimdoTurno(){
        return acoesRealizadas >= ordemTurno.size();

    }
    public List<Player> getOrdemTurno(){
        System.out.println("A ordem dos ataques dos hérois é: " );
        for(Player ordemTurno : ordemTurno){
            System.out.println(ordemTurno.getNome());}
        return ordemTurno;
    }

    public void processarAcoesMonstros(){
        //Irá entrar o código do Aldrey

    }


}



