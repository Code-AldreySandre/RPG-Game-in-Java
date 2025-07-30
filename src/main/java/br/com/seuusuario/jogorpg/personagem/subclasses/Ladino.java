package br.jogo.personagens.subclasses;

import br.jogo.personagens.Hero;
import br.jogo.personagens.Player;
import ia.AdaptiveAI;
import logs.Log;
import logs.TipoLog;

import java.util.List;

public class Ladino extends Hero {
    private int furtividade;
    private List<String> venenos;
    private List<String> armadilhas;
    private IAAdaptativa ia;  // IA para ajustar dificuldade / estratégias

    public Ladino(String nome, int hp, int ataque, int defesa, int destreza, int velocidade,
                  int nivel, int experiencia, int mana, int furtividade,
                  List<String> venenos, List<String> armadilhas) {
        super(nome, hp, ataque, defesa, destreza, velocidade, nivel, experiencia, mana);
        this.furtividade = furtividade;
        this.venenos = venenos;
        this.armadilhas = armadilhas;
        this.ia = new IAAdaptativa();
    }

    public int getFurtividade() {
        return furtividade;
    }

    public void setFurtividade(int furtividade) {
        this.furtividade = furtividade;
    }

    public List<String> getVenenos() {
        return venenos;
    }

    public void setVenenos(List<String> venenos) {
        this.venenos = venenos;
    }

    public List<String> getArmadilhas() {
        return armadilhas;
    }

    public void setArmadilhas(List<String> armadilhas) {
        this.armadilhas = armadilhas;
    }

    @Override
    public void realizarAtaque(Player alvoPlayer) {
        ResultadoAtaque resultado = SistemaCombate.calcularResultadoAtaque(this, alvoPlayer);
        boolean ataqueFurtivo = Math.random() < (this.furtividade * 0.015); // 1.5% por ponto de furtividade
        int dano = SistemaCombate.calcularDano(this, alvoPlayer, resultado);

        String mensagem;

        if (resultado == ResultadoAtaque.ERROU) {
            mensagem = this.nome + " errou o ataque contra " + alvoPlayer.getNome() + "!";
        } else {
            if (ataqueFurtivo) {
                dano *= 3;
                mensagem = this.nome + " executa um ATAQUE FURTIVO em " + alvoPlayer.getNome() + " causando " + dano + " de dano!";
            } else if (resultado == ResultadoAtaque.CRITICAL_HIT) {
                mensagem = this.nome + " realiza um GOLPE CRÍTICO em " + alvoPlayer.getNome() + ", causando " + dano + " de dano!";
            } else {
                mensagem = this.nome + " ataca " + alvoPlayer.getNome() + " causando " + dano + " de dano.";
            }

            SistemaCombate.aplicarDano(alvoPlayer, dano);
        }

        System.out.println(mensagem);
        new Log(mensagem, TipoLog.COMBATE, this).salvar();
        ia.registrarAcao("realizarAtaque", dano);
    }


    @Override
    public void usarHabilidadeEspecial(Player alvo) {
        if (this.mana >= 10 && !venenos.isEmpty()) {
            this.mana -= 10;
            String veneno = venenos.get(0); // simplificação
            int dano = (int)(alvo.getHp() * 0.05); // 5% do HP atual por turno (simulado)

            alvo.setHp(alvo.getHp() - dano);

            System.out.println(this.nome + " aplica o veneno " + veneno + " em " + alvo.getNome() + ", causando " + dano + " de dano contínuo!");

            // Log da ação
            Log log = new Log(this.nome + " usou veneno " + veneno + " causando " + dano + " de dano em " + alvo.getNome(), TipoLog.ACAO, this);
            log.salvar();

            ia.registrarAcao("usarHabilidadeEspecial", dano);
        } else {
            System.out.println(this.nome + " não possui mana ou venenos disponíveis.");

            // Log da falha
            Log log = new Log(this.nome + " tentou usar veneno, mas não possui mana ou venenos.", TipoLog.ACAO, this);
            log.salvar();
        }
    }

    // Método extra: colocar armadilha
    public void sabotar(Player alvoPlayer) {
        if (!armadilhas.isEmpty()) {
            String armadilha = armadilhas.get(0); // exemplo simples
            int dano = this.ataque * 2;

            SistemaCombate.aplicarDano(alvoPlayer, dano);

            System.out.println(this.nome + " sabota " + alvoPlayer.getNome() + " com " + armadilha + ", causando " + dano + " de dano!");

            Log log = new Log(this.nome + " usou armadilha " + armadilha + " causando " + dano + " de dano em " + alvoPlayer.getNome(), TipoLog.ACAO, this);
            log.salvar();

            ia.registrarAcao("sabotar", dano);
        } else {
            System.out.println(this.nome + " não possui armadilhas para usar.");
            Log log = new Log(this.nome + " tentou usar armadilha, mas não possui.", TipoLog.ACAO, this);
            log.salvar();
        }
    }

    @Override
    protected int getHp() {
        return this.hp;
    }
}
