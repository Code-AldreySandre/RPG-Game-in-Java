package logs;

import Player.Player;
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
        System.out.printf("[%s][%s] %s [%s]%n", timestamp, tipo, mensagem, jogadorRelacionado.getNome());
    }

    // Getters e setters omitidos para brevidade
}

