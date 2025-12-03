import java.util.Random;

class Mago extends Combatente {
    private String[] palavrasAtk = { "FOGO", "GELO", "LUZ", "RAIO", "AR" };

    public Mago(String nome) {
        super(nome, 80, 18, 3);
    }

    @Override
    public String[] getAsciiArt() {
        return new String[] { "  <|>  [MAGO]", "  /|\\  (Nv.1)", "  / \\  " };
    }

    @Override
    public String getNomeHabilidade() {
        return "EXPLOSAO";
    }

    @Override
    public String getPalavraAtaque() {
        return palavrasAtk[new Random().nextInt(palavrasAtk.length)];
    }

    @Override
    public String getPalavraEspecial() {
        return "METEORO";
    }
}