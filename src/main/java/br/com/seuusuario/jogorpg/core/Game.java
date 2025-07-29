package nucleo;
import Player.Hero;
import Player.Player;
import enums.Dificuldade;
import enums.EstadoJogo;
import logs.Log;
import monstros.Monster;

import java.util.ArrayList;

import java.util.List;

public class Game {
    private List<Hero>  herois; // Mago, clérigo, ladino e guerreiro
    private List<Monster>   monstros;
    private Dificuldade dificuldade; //Fácil, médio, dificíl
    private List<Log> logs;
    private Turno turnoAtual;
    private EstadoJogo estado; // NÃO_INICIADO, EM_ANDAMENTO, PAUSADO, TERMINADO

    public Game(int quantidadeDeTurnos ){
        this.herois = new ArrayList<>();
        this.monstros = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.estado = EstadoJogo.NAO_INICIADO;
    }

    public void iniciarJogo(Dificuldade dificuldade){
        this.dificuldade =  dificuldade;
        gerarPersonagens();
        estado = EstadoJogo.EM_ANDAMENTO;
    }

    public void terminarJogo(){
        this.estado = EstadoJogo.TERMINADO;
        System.out.println("\n===== JOGO ENCERRADO =====");
        exibirResultado();
    }

    public void pausarJogo() {
        if (this.estado == EstadoJogo.EM_ANDAMENTO) {
            this.estado = EstadoJogo.PAUSADO;
            System.out.println("\n[JOGO PAUSADO]");
        } else {
            System.out.println("O jogo não está em andamento e não pode ser pausado.");
        }
    }

    public void gerenciarTurnos(){
        int numeroTurno = 1;

        while(estado == EstadoJogo.EM_ANDAMENTO){
            System.out.println("\n===== TURNO " + numeroTurno + " =====");
            List<Player> personagemsVivos = new ArrayList<>();
            for(Hero heroi : herois)
                if (heroi.getHp() > 0){personagemsVivos.add(heroi);}
            for(Monster monstro : monstros)
                if(monstro.getHp() > 0){personagemsVivos.add(monstro);}
            turnoAtual = new Turno(numeroTurno, personagemsVivos);
            turnoAtual.iniciarTurno();

            atualizarEstatisticas();

            if(verificarFimJogo()){
                terminarJogo();
                break;
            }
            numeroTurno++;


        }

    }

    public boolean verificarFimJogo() {
        boolean heroisVivos = false;
        boolean monstrosVivos = false;

        for (int i = 0; i < herois.size(); i++) {
            if (herois.get(i).getHp() > 0) {
                heroisVivos = true;
                break;
            }
        }

        for (int i = 0; i < monstros.size(); i++) {
            if (monstros.get(i).getHp() > 0) {
                monstrosVivos = true;
                break;
            }
        }

        // Se algum grupo não tiver ninguém vivo, o jogo termina
        return !heroisVivos || !monstrosVivos;
    }

    public void exibirResultado(){
        int heroisVivos = 0;
        int monstrosVivos = 0;

        for(int i= 0 ; herois.size() > 0 ; i++){
            if(herois.get(i).getHp() > 0 ){
                heroisVivos++;
            }

        }
        for(int i = 0; monstros.size() > 0; i++ ){
            if(monstros.get(i).getHp() > 0 ){
                monstrosVivos++;
            }
        }
        System.out.println("\n===== RESULTADO FINAL =====");
        if (heroisVivos > 0 && monstrosVivos == 0) {
            System.out.println("Heróis venceram!");
        } else if (monstrosVivos > 0 && heroisVivos == 0) {
            System.out.println("Monstros venceram!");
        } else {
            System.out.println("⚔Empate!");
        }

        System.out.println("\n===== LOGS DA BATALHA =====");
        for (int i = 0; i < logs.size(); i++) {
            logs.get(i).salvar();
        }

    }

    public void gerarMonstrosConformeDificuldade(){
        // código do Aldrey
    }


    private void gerarPersonagens() {
        // Código do Aldrey
    }


    public void atualizarEstatisticas(){
        int heroisVivos = 0;
        int monstrosVivos = 0;

        for (int i = 0; i < herois.size(); i++) {
            if (herois.get(i).getHp() > 0) {
                heroisVivos++;
            }
        }

        for (int i = 0; i < monstros.size(); i++) {
            if (monstros.get(i).getHp() > 0) {
                monstrosVivos++;
            }
        }

        System.out.println("Status Atual:");
        System.out.println("Heróis vivos: " + heroisVivos);
        System.out.println("Monstros vivos: " + monstrosVivos);

    }
    public List<Log> getLogs(){
       return logs;
  }
}
