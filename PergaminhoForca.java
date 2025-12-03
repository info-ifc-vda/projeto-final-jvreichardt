class PergaminhoForca extends Item {
    public PergaminhoForca() { super("Pergaminho de Poder"); }
    @Override
    public void aplicar(Combatente alvo) {
        System.out.println("   [+] " + alvo.getNome() + " sentiu sua força aumentar!");
        alvo.aumentarForca(10);
    }
}