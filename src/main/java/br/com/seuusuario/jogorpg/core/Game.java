package br.jogo.core;

import br.jogo.personagens.Player;
import br.jogo.personagens.Hero;
import br.jogo.personagens.Monster;
import br.jogo.personagens.subclasses.Mago;
import br.jogo.personagens.subclasses.Ladino;
import br.jogo.personagens.subclasses.Guerreiro;
import br.jogo.personagens.subclasses.Clerigo;

import br.jogo.ia.IAAdaptativa;  // Import da IA adaptativa

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Game {
    private List<Hero> herois; // Mago, clérigo, ladino e guerreiro
    private List<Monster> monstros;
    private Dificuldade dificuldade; // Fácil, médio, difícil
    private List<Log> logs;
    private Turno turnoAtual;
    private EstadoJogo estado; // NÃO_INICIADO, EM_ANDAMENTO, PAUSADO, TERMINADO
    private IAAdaptativa iaAdaptativa;

    public Game(int quantidadeDeTurnos) {
        this.herois = new ArrayList<>();
        this.monstros = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.estado = EstadoJogo.NAO_INICIADO;
        this.iaAdaptativa = new IAAdaptativa();
    }

    public void iniciarJogo(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
        gerarPersonagens();
        gerarMonstrosConformeDificuldade(1);  // Inicializa monstros no turno 1
        estado = EstadoJogo.EM_ANDAMENTO;
    }

    public void terminarJogo() {
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

    public void gerenciarTurnos() {
        int numeroTurno = 1;

        while (estado == EstadoJogo.EM_ANDAMENTO) {
            System.out.println("\n===== TURNO " + numeroTurno + " =====");

            // Atualiza monstros conforme a IA a cada turno (opcional: pode gerar novos monstros ou ajustar existentes)
            gerarMonstrosConformeDificuldade(numeroTurno);

            List<Player> personagensVivos = new ArrayList<>();
            for (Hero heroi : herois)
                if (heroi.getHp() > 0) {
                    personagensVivos.add(heroi);
                }
            for (Monster monstro : monstros)
                if (monstro.getHp() > 0) {
                    personagensVivos.add(monstro);
                }

            turnoAtual = new Turno(numeroTurno, personagensVivos, this);
            turnoAtual.iniciarTurno(this);

            atualizarEstatisticas();

            if (verificarFimJogo()) {
                terminarJogo();
                break;
            }

            numeroTurno++;
        }
    }

    public boolean verificarFimJogo() {
        boolean heroisVivos = false;
        boolean monstrosVivos = false;

        for (Hero heroi : herois) {
            if (heroi.getHp() > 0) {
                heroisVivos = true;
                break;
            }
        }

        for (Monster monstro : monstros) {
            if (monstro.getHp() > 0) {
                monstrosVivos = true;
                break;
            }
        }

        return !heroisVivos || !monstrosVivos;
    }

    public void exibirResultado() {
        int heroisVivos = 0;
        int monstrosVivos = 0;

        for (Hero heroi : herois) {
            if (heroi.getHp() > 0) {
                heroisVivos++;
            }
        }
        for (Monster monstro : monstros) {
            if (monstro.getHp() > 0) {
                monstrosVivos++;
            }
        }
        System.out.println("\n===== RESULTADO FINAL =====");
        if (heroisVivos > 0 && monstrosVivos == 0) {
            System.out.println(" Heróis venceram!");
        } else if (monstrosVivos > 0 && heroisVivos == 0) {
            System.out.println(" Monstros venceram!");
        } else {
            System.out.println(" Empate!");
        }

        System.out.println("\n===== LOGS DA BATALHA =====");
        for (Log log : logs) {
            log.salvar();
        }
    }

    // Atualiza ou gera monstros conforme a dificuldade e turno usando IA adaptativa
    public void gerarMonstrosConformeDificuldade(int turnoAtual) {
        monstros.clear(); // limpa monstros antigos
        int quantidade = 3 + turnoAtual; // Exemplo: mais monstros conforme o turno

        for (int i = 0; i < quantidade; i++) {
            Monster monstro = iaAdaptativa.gerarMonstroAdaptativo(turnoAtual);
            monstros.add(monstro);
        }
    }

    private void gerarPersonagens() {
        // Exemplo simples de criação dos heróis (você pode adaptar)
        herois.add(new Mago("Gandalf", 100, 25, 10, 20, 15, 1, 0, 100, List.of("Bola de Fogo", "Raio")));
        herois.add(new Ladino("Garrett", 90, 20, 12, 25, 20, 1, 0, 50, 30, List.of("Veneno"), List.of("Armadilha")));
        herois.add(new Guerreiro("Aragorn", 120, 30, 20, 15, 10, 1, 0, 0, 40));
        herois.add(new Clerigo("Elrond", 110, 15, 15, 18, 12, 1, 0, 80, List.of("Cura", "Benção")));
    }

    public void atualizarEstatisticas() {
        int heroisVivos = 0;
        int monstrosVivos = 0;

        for (Hero heroi : herois) {
            if (heroi.getHp() > 0) {
                heroisVivos++;
            }
        }

        for (Monster monstro : monstros) {
            if (monstro.getHp() > 0) {
                monstrosVivos++;
            }
        }

        System.out.println("Status Atual:");
        System.out.println("Heróis vivos: " + heroisVivos);
        System.out.println("Monstros vivos: " + monstrosVivos);
    }

    public Map<String, Player> getEstatisticasBatalha() {
        Map<String, Player> estatisticas = new HashMap<>();

        for (Hero heroi : herois) {
            estatisticas.put(heroi.getNome(), heroi);
        }

        for (Monster monstro : monstros) {
            estatisticas.put(monstro.getNome(), monstro);
        }

        return estatisticas;
    }

    public List<Hero> getHerois() {
        return herois;
    }

    public List<Monster> getMonstros() {
        return monstros;
    }
}
