abstract class Combatente {
    protected String nome;
    protected int vida, vidaMaxima;
    protected int forca, defesa;
    protected int cooldownEspecial = 0;
    protected boolean emPosturaDefensiva = false;

    public Combatente(String nome, int vida, int forca, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.forca = forca;
        this.defesa = defesa;
    }

    public abstract String[] getAsciiArt();

    public abstract String getPalavraAtaque();

    public abstract String getPalavraEspecial();

    public abstract String getNomeHabilidade();

    public int calcularDanoAtaque(double tempo) {
        if (tempo > 3.0)
            return 0;
        else if (tempo < 1.8)
            return (int) (this.forca * 1.5);
        return this.forca;
    }

    public int calcularDanoEspecial(double tempo) {
        if (tempo > 4.0)
            return 0;
        return (int) (this.forca * 2.5);
    }

    public void receberDano(int danoBruto) {
        int reducao = this.defesa;
        if (emPosturaDefensiva)
            reducao *= 2.5f;

        int danoFinal = Math.max(0, danoBruto - reducao);
        this.vida -= danoFinal;
        System.out.println("   -> " + this.nome + " sofreu " + danoFinal + " de dano.");
        this.emPosturaDefensiva = false;
    }

    public void reduzirCooldown() {
        if (cooldownEspecial > 0)
            cooldownEspecial--;
    }

    public void resetarCooldown() {
        this.cooldownEspecial = 3;
    }

    public void curar(int qtd) {
        this.vida = Math.min(vidaMaxima, vida + qtd);
    }

    public void aumentarForca(int qtd) {
        this.forca += qtd;
    }

    public void setPosturaDefensiva(boolean b) {
        this.emPosturaDefensiva = b;
    }

    public void receberItem(Item item) {
        item.aplicar(this);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return Math.max(0, vida);
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getCooldown() {
        return cooldownEspecial;
    }
}