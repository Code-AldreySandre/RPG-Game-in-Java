package br.com.seuusuario.jogorpg.core;

import br.com.seuusuario.jogorpg.enums.ResultadoAtaque;
import br.com.seuusuario.jogorpg.personagem.Player;
import br.com.seuusuario.jogorpg.ia.AdaptiveAI;  // Import da IA

import java.util.Random;

public class SistemaCombate {
    private static final Random random = new Random();

    // Referência para a IA adaptativa (pode ser setada pelo Game)
    private static AdaptiveAI adaptiveAI;

    // Método para configurar a IA no Sistema de Combate
    public static void setAdaptiveAI(AdaptiveAI ai) {
        adaptiveAI = ai;
    }

    public static ResultadoAtaque calcularResultadoAtaque(Player atacante, Player alvo) {
        if (atacante == null || alvo == null) {
            throw new IllegalArgumentException("Atacante e alvo não podem ser nulos.");
        }

        int destreza = atacante.getDestreza();

        // Obtem ajustes da IA (exemplo: IA pode ajustar chance de erro e crítico)
        double ajusteErro = 0.0;
        double ajusteCritico = 0.0;
        if (adaptiveAI != null) {
            ajusteErro = adaptiveAI.ajustarChanceErro(atacante, alvo);
            ajusteCritico = adaptiveAI.ajustarChanceCritico(atacante, alvo);
        }

        double chanceErro = Math.max(0.05, 0.3 - (destreza / 200.0) + ajusteErro);       // minimo 5%
        double chanceCritico = Math.min(0.5, destreza / 150.0 + ajusteCritico);           // maximo 50%
        double chanceAcerto = 1.0 - chanceErro - chanceCritico;

        double sorte = random.nextDouble(); // entre 0.0 e 1.0

        if (sorte < chanceErro) {
            return ResultadoAtaque.ERROU;
        } else if (sorte < chanceErro + chanceAcerto) {
            return ResultadoAtaque.ACERTOU;
        } else {
            return ResultadoAtaque.CRITICAL_HIT;
        }
    }

    public static int calcularDano(Player atacante, Player alvo, ResultadoAtaque resultado) {
        if (atacante == null || alvo == null || resultado == null) {
            throw new IllegalArgumentException("Argumentos nulos não são permitidos.");
        }

        int ataque = atacante.getAtaque();
        int defesa = alvo.getDefesa();

        // Ajustes da IA para dano (pode aumentar ou diminuir dano conforme dificuldade)
        int ajusteDano = 0;
        if (adaptiveAI != null) {
            ajusteDano = adaptiveAI.ajustarDano(atacante, alvo, resultado);
        }

        int danoBase = Math.max(0, ataque - (defesa / 2)) + ajusteDano; // defesa reduz metade do dano

        switch (resultado) {
            case ERROU:
                return 0;
            case CRITICAL_HIT:
                return Math.max(2, danoBase * 2); // crítico dobra o dano, mínimo 2
            case ACERTOU:
            default:
                return Math.max(1, danoBase); // acerto normal, mínimo 1
        }
    }

    public static void aplicarDano(Player alvo, int dano) {
        if (alvo == null) {
            throw new IllegalArgumentException("O alvo não pode ser nulo.");
        }
        if (dano < 0) {
            throw new IllegalArgumentException("O dano não pode ser negativo.");
        }

        int novoHp = Math.max(0, alvo.getHp() - dano);
        alvo.setHp(novoHp);
    }
}
