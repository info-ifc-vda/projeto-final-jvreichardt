import java.util.*;
class BancoPalavras {
    public static String getDefesa() {
        String[] w = {"BLOQUEIO", "ESQUIVA", "SAIR", "DEFESA"};
        return w[new Random().nextInt(w.length)];
    }
}