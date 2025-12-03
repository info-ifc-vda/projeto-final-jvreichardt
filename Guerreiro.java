import java.util.Random;

class Guerreiro extends Combatente {
    private String[] palavrasAtk = { "CORTAR", "GOLPE", "ESPADA", "BATIDA", "ATAQUE","SOCO"};

    public Guerreiro(String nome) {
        super(nome, 120, 11, 5);
    }

    @Override
    public String[] getAsciiArt() {
        return new String[] { "  /o/  [GUERREIRO]", "  [|]  (Nv.1)", "  / \\  " };
    }

    @Override
    public String getNomeHabilidade() {
        return "ESCUDADA";
    }

    @Override
    public String getPalavraAtaque() {
        return palavrasAtk[new Random().nextInt(palavrasAtk.length)];
    }

    @Override
    public String getPalavraEspecial() {
        return "IMPACTO";
    }
}
