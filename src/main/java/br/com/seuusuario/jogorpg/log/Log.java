package logs;

import br.jogo.personagens.Player;
import enums.TipoLog;
import java.time.LocalDateTime;

public class Log {
    private String mensagem;
    private LocalDateTime timestamp;
    private TipoLog tipo;
    private Player jogadorRelacionado;

    public Log(String mensagem, TipoLog tipo, Player jogadorRelacionado) {
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.jogadorRelacionado = jogadorRelacionado;
        this.timestamp = LocalDateTime.now();
    }

    public void salvar() {
        String nomeJogador = (jogadorRelacionado != null) ? jogadorRelacionado.getNome() : "N/A";
        System.out.printf("[%s][%s] %s [%s]%n", timestamp, tipo, mensagem, nomeJogador);
    }

    // Método estático para criar logs específicos da IA adaptativa
    public static Log criarLogIA(String mensagem, Player jogadorRelacionado) {
        return new Log("[IA_ADAPTATIVA] " + mensagem, TipoLog.INFO, jogadorRelacionado);
    }

    // Getters e setters (omitidos para brevidade, adicione se precisar)
}

