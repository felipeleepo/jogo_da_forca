package componentes;

import javax.swing.JFrame;

public class Tela extends JFrame{

    private Jogo jogo;
    private Teclado teclado;
    
    public Tela() {
        super("Jogo da forca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        jogo = new Jogo();
        add(jogo);
        teclado = new Teclado(jogo);
        addKeyListener(teclado);
        setSize(700, 320);
    } 
}
