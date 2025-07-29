package br.jogo.ia;

public class AdaptiveAI {
    private int dificuldadeAtual;
    private int turnos;

    public AdaptiveAI() {
        this.dificuldadeAtual = 1;
        this.turnos = 0;
    }

    public void registrarTurno() {
        turnos++;
        if (turnos % 3 == 0) {
            dificuldadeAtual++;
        }
    }

    public int getDificuldadeAtual() {
        return dificuldadeAtual;
    }

    public void reset() {
        this.dificuldadeAtual = 1;
        this.turnos = 0;
    }
}
