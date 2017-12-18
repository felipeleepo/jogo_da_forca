package componentes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    private Jogo jogo;
    public Teclado(Jogo j){
        jogo = j;
    }
    // Captura teclas pressionadas
    public boolean keyDown(int key) {
        // Captura de teclas no decorrer de uma secao de jogo
        // Letras maiusculas - Retorna tecla
        if (key >= 65 && key <= 90) {
            jogo.verificaPalpite((char) key);
            return true;
        }
        // Letras minusculas - Converte para maiuscula e retorna tecla
        if (key >= 97 && key <= 122) {
            jogo.verificaPalpite((char) (key - 32));
            return true;
        }
        // Pressionamento de tecla invalida
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyDown(e.getKeyCode());
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
