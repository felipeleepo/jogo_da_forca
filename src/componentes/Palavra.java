package componentes;

public class Palavra {
    private String PalavraSorteada = null;

    public Palavra(char[] s){
        String p = "";
        for(int i = 0; i < s.length; i++){
            p += s[i];
        }
        PalavraSorteada = p;
    }
    
    public int getSize(){
        return PalavraSorteada.length();
    }
    
    public char getLetra(int i){
        char[] p = PalavraSorteada.toCharArray();
        return p[i];
    }
    
}
