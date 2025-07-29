package br.com.seuusuario.jogorpg.core;

import br.com.seuusuario.jogorpg.enums.Dificuldade;
import br.com.seuusuario.jogorpg.personagem.Player;

public class ConfiguracaoJogo {

    public static void aplicarDificuldade(Dificuldade dificuldade, Player jogador) {
        switch (dificuldade) {
            case FACIL:
                System.out.println("Modo Fácil selecionado: +30 de vida, +5 de ataque.");
                jogador.setVida(jogador.getVida() + 30);
                jogador.setAtaque(jogador.getAtaque() + 5);
                break;
            case MEDIA:
                System.out.println("Modo Médio selecionado: sem modificações.");
                // Sem modificações
                break;
            case DIFICIL:
                System.out.println("Modo Difícil selecionado: -20 de vida.");
                jogador.setVida(jogador.getVida() - 20);
                break;
            case INSANA:
                System.out.println("Modo Insana selecionado: -40 de vida, -5 de ataque.");
                jogador.setVida(jogador.getVida() - 40);
                jogador.setAtaque(jogador.getAtaque() - 5);
                break;
        }
    }
}
