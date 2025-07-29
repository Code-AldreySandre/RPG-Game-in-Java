package br.com.seuusuario.jogorpg.core;

import br.com.seuusuario.jogorpg.enums.ResultadoAtaque;
import br.com.seuusuario.jogorpg.personagem.Player;
import java.util.Random;

public class SistemaCombate {
    private static final Random random = new Random();

    public static ResultadoAtaque calcularResultadoAtaque(Player atacante, Player alvo) {
        if (atacante == null || alvo == null) {
            throw new IllegalArgumentException("Atacante e alvo não podem ser nulos.");
        }

        int chance = random.nextInt(100);
        int destreza = atacante.getDestreza();

        if (chance < destreza / 5) {
            return ResultadoAtaque.ERROU;
        } else if (chance < destreza) {
            return ResultadoAtaque.ACERTOU;
        } else {
            return ResultadoAtaque.CRITICAL_HIT;
        }
    }

    public static int calcularDano(Player atacante, Player alvo, ResultadoAtaque resultado) {
        if (atacante == null || alvo == null || resultado == null) {
            throw new IllegalArgumentException("Argumentos nulos não são permitidos.");
        }

        int danoBase = Math.max(0, atacante.getAtaque() - alvo.getDefesa());

        switch (resultado) {
            case ERROU:
                return 0;
            case CRITICAL_HIT:
                return danoBase * 2;
            case ACERTOU:
            default:
                return danoBase;
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
