package main;

import componentes.Tela;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tela gui = new Tela();
                gui.setVisible(true);
            }
        });
    }
}
