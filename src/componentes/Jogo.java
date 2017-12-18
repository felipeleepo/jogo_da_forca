package componentes;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Jogo extends JPanel {

    
    private String[] listaPalavras = {"PETROLEO", "PESTANA", "PESTILENTO", "PETELECO", "REBOQUE", "CADAFALSO", "CADEIRA",
        "COLA", "REBENTO", "DEFUMADO", "DISCURSO", "ELETRODOMESTICO", "ELETRONICA", "ENGRENAGEM", "ESFOMEADO",
        "FERRALHEIRO", "FERROVIA", "FERTIL", "FORTALEZA", "FORTIFICANTE", "OFICINA", "ORNAMENTO", "PALAVRA",};

    private char[] letrasAlfabeto = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private char[] letrasNaoSelecionadas = new char[26];
    private Palavra palavra;
    private char[] letrasAdivinhadas_X_Posicao;
    private int tentativasErradas;
    String resultado;

    public Jogo() {       
        start();
    }

    public void start() {
        // Inicializa vetor das letrasNaoSelecionadas contendo inicialmente
        // todas as letras disponiveis
        for (int i = 0; i < letrasNaoSelecionadas.length; i++) {
            letrasNaoSelecionadas[i] = letrasAlfabeto[i];
        }

        // Sorteia palavra do jogo
        palavra = new Palavra(sorteiaPalavra());

        // Inicializa vetor das letras ja adivinhadas com todas as posicoes
        // iguais a "0"
        letrasAdivinhadas_X_Posicao = new char[palavra.getSize()];
        for (int i = 0; i < letrasAdivinhadas_X_Posicao.length; i++) {
            letrasAdivinhadas_X_Posicao[i] = '0';
        }

        // Inicializa variavel referente ao numero de tentativas erradas do
        // usuario
        tentativasErradas = 0;

        // Inicializa variaveis referentes a saida de fim de um jogo
        resultado = "";

        // Chamada ao metodo paint necessaria para o reinicio do jogo
        repaint();
    }

    // Sorteia palavra em jogo entre aquelas da lista
    public char[] sorteiaPalavra() {
        int indicePalavra;
        String palavraSorteada;

        // Sorteia indice da palavra
        indicePalavra = (int) (Math.random() * listaPalavras.length);

        // Seleciona a palavra correspondente ao indice sorteado no array de
        // palavras
        palavraSorteada = listaPalavras[indicePalavra];

        // Retorna palavra sorteada
        return palavraSorteada.toCharArray();
    }

    // Verifica se o palpite e correto ou incorreto e realiza acoes referentes
    public void verificaPalpite(char letra_teclada) {
        // Remove letra do vetor de letras nao utilizadas
        boolean achouLetra = false;
        int indiceBusca = 0;
        while (!achouLetra && indiceBusca < letrasNaoSelecionadas.length) {
            if (letrasNaoSelecionadas[indiceBusca] == letra_teclada) {
                letrasNaoSelecionadas[indiceBusca] = '0';
                achouLetra = true;
            }
            indiceBusca++;
        }

        // Caso a letra nao seja encontrada, o que indica que esta ja foi
        // utilizada, o metodo e abortado
        if (!achouLetra) {
            return;
        }

        // Verifica palpite percorrendo o vetor correspondente as letras da
        // palavra
        boolean palpiteCorreto = false;
        for (int i = 0; i < palavra.getSize(); i++) {
            if (palavra.getLetra(i) == letra_teclada) {
                letrasAdivinhadas_X_Posicao[i] = letra_teclada;
                palpiteCorreto = true;
            }
        }
        // Caso a o palpite seja errado, incrementa contador de tentativas
        // erradas
        if (palpiteCorreto == false) {
            tentativasErradas++;
        }

        // Invoca metodo que verifica termino do jogo
        verificaFimJogo();

        // Redesenha a tela com as novas configuracoes
        repaint();
    }

    // Verifica fim do jogo, derrota ou vitoria, e armazena resultado
    public void verificaFimJogo() {

        if (tentativasErradas >= 6) {
            resultado = "Palavra errada , Continue tentando ate descubrir a palavra =D";
        } // Checa vitoria
        else {
            boolean temZero = false;
            for (int i = 0; i < letrasAdivinhadas_X_Posicao.length; i++) {
                if (letrasAdivinhadas_X_Posicao[i] == '0') {
                    temZero = true;
                }
            }
            if (!temZero) {
                resultado = "Feito , Palavra encontrada +1 ponto para você";
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        // Seleciona cor do fundo
        setBackground(Color.white);

        // Desenha forca
        g.fillRect(20, 20, 110, 10);
        g.fillRect(20, 20, 10, 185);
        g.fillRect(122, 30, 8, 22);
        g.fillRect(10, 195, 30, 10);

        // Desenha cabeca, olhos e boca
        if (tentativasErradas > 0) {
            g.fillRect(113, 52, 25, 28);
            g.fillRect(122, 80, 8, 7);
            g.setColor(Color.white);
            g.fillRect(118, 58, 4, 5);
            g.fillRect(129, 58, 4, 5);
            g.fillRect(121, 70, 9, 4);
            g.setColor(Color.black);
        }

        // Desenha corpo
        if (tentativasErradas > 1) {
            g.fillRect(106, 87, 39, 50);
        }

        // Desenha braco esquerdo
        if (tentativasErradas > 2) {
            g.fillRect(102, 87, 6, 6);
            g.fillRect(98, 93, 12, 6);
            g.fillRect(92, 99, 12, 6);
            g.fillRect(87, 105, 11, 10);
            g.fillRect(92, 115, 12, 6);
            g.fillRect(98, 121, 12, 6);
        }

        // Desenha braco direito
        if (tentativasErradas > 3) {
            g.fillRect(141, 87, 6, 6);
            g.fillRect(142, 93, 12, 6);
            g.fillRect(147, 99, 12, 6);
            g.fillRect(152, 105, 11, 10);
            g.fillRect(147, 115, 12, 6);
            g.fillRect(142, 121, 12, 6);
        }

        // Desenha perna esquerda
        if (tentativasErradas > 4) {
            g.fillRect(106, 137, 14, 13);
            g.fillRect(106, 150, 10, 10);
            g.fillRect(106, 160, 7, 10);
            g.fillRect(100, 170, 13, 5);
        }

        // Desenha perna direita
        if (tentativasErradas > 5) {
            g.fillRect(131, 137, 14, 13);
            g.fillRect(135, 150, 10, 13);
            g.fillRect(138, 160, 7, 10);
            g.fillRect(138, 170, 13, 5);
        }
        // Desenha tracos referentes as letras da palavra escondida e as letras
        // encontradas em suas respectivas posicoes
        for (int i = 0; i < palavra.getSize(); i++) {
            if (letrasAdivinhadas_X_Posicao[i] != '0') {
                g.drawString(String.valueOf(letrasAdivinhadas_X_Posicao[i]), 191 + i * 30, 52);
            }
            g.fillRect(190 + i * 30, 58, 20, 4);
        }

        // Desenha letras ainda nao selecionadas
        int x_LetrasNaoUtilizadas = 190;
        int i;
        for (i = 0; i < letrasNaoSelecionadas.length / 2; i++) {
            if (letrasNaoSelecionadas[i] != '0') {
                g.drawString(String.valueOf(letrasNaoSelecionadas[i]), x_LetrasNaoUtilizadas, 137);
            }
            x_LetrasNaoUtilizadas += 25;
        }
        for (x_LetrasNaoUtilizadas = 192; i < letrasNaoSelecionadas.length; i++) {
            if (letrasNaoSelecionadas[i] != '0') {
                g.drawString(String.valueOf(letrasNaoSelecionadas[i]), x_LetrasNaoUtilizadas, 160);
            }
            x_LetrasNaoUtilizadas += 25;
        }

        // Desenha mensagem de pressionamento de tecla
        g.drawString("--== Tecle a letra que corresponde a seu palpite ==--", 165, 190);

        // Desenha mensagem relativa ao fim do jogo (vitoria ou derrota)
        g.drawString(resultado, 200, 220);
    }

}
