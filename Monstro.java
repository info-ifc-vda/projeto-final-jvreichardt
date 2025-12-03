class Monstro extends Combatente {
    private String tipo;
    private Item loot;

    public Monstro(String nome, int vida, int forca, String tipo, Item loot) {
        super(nome, vida, forca, 5);
        this.tipo = tipo;
        this.loot = loot;
    }

    public Item getLoot() {
        return loot;
    }

    @Override
    public String[] getAsciiArt() {

        if (tipo.equals("Orc")) {
            return new String[] {
                    " (o_o) [ORC]   ",
                    " /|\\ (Inimigo) ",
                    " / \\           "
            };
        }

        return new String[] {
                "  {O}  [BOSS]   ",
                "  /|\\  (Perigo) ",
                "  / \\           "
        };
    }

    public String getPalavraAtaque() {
        return "";
    }

    public String getPalavraEspecial() {
        return "";
    }

    public String getNomeHabilidade() {
        return "";
    }
}