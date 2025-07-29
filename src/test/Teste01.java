import enums.Dificuldade;
import monstros.Monster;
import nucleo.*;
import Player.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("       BEM-VINDO AO RPG TURNOS      ");
        System.out.println("====================================");
        System.out.println("Escolha a dificuldade:");
        System.out.println("1 - Fácil");
        System.out.println("2 - Médio");
        System.out.println("3 - Difícil");
        System.out.print("Opção: ");

        int escolha = scanner.nextInt();
        Dificuldade dificuldadeSelecionada;

        switch (escolha) {
            case 1:
                dificuldadeSelecionada = Dificuldade.FACIL;
                break;
            case 2:
                dificuldadeSelecionada = Dificuldade.MEDIO;
                break;
            case 3:
                dificuldadeSelecionada = Dificuldade.DIFICIL;
                break;
            default:
                System.out.println("Opção inválida. Iniciando em modo Fácil.");
                dificuldadeSelecionada = Dificuldade.FACIL;
        }

        // Cria o jogo
        Game game = new Game(10);  // Número de turnos como exemplo
        game.iniciarJogo(dificuldadeSelecionada);  // Inicializa o jogo

        // Adiciona heróis manualmente (exemplo)
        game.getHerois().add(new Hero("Guerreiro", 100, 20, "Guerreiro", 0, 10));
        game.getHerois().add(new Hero("Clérigo", 90, 18, "Clérigo", 0, 15));

        // Adiciona monstros manualmente (exemplo)
        game.getMonstros().add(new Monster("Goblin", 50, 10, 5, 5, 22));
        game.getMonstros().add(new Monster("Orc", 120, 15, 10, 8, 12));

        // Executa os turnos
        game.gerenciarTurnos();  // Não é necessário passar 'game' como parâmetro

        scanner.close();
    }
}
