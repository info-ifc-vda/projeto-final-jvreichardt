class Cronometro {
    private long inicio;
    public void iniciar() { this.inicio = System.currentTimeMillis(); }
    public double parar() { return (System.currentTimeMillis() - inicio) / 1000.0; }
}