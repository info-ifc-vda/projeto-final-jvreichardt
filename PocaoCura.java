class PocaoCura extends Item {
    public PocaoCura() { super("Poção de Vida"); }
    @Override
    public void aplicar(Combatente alvo) {
        System.out.println("   [+] " + alvo.getNome() + " recuperou 25 HP!");
        alvo.curar(25);
    }
}