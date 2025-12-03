import java.util.ArrayList;
import java.util.List;
class Equipe {
    private List<Combatente> membros = new ArrayList<>();
    public void adicionar(Combatente c) { membros.add(c); }
    public boolean perdeu() { for(Combatente c : membros) if(c.estaVivo()) return false; return true; }
    public Combatente getLutadorAtivo() { for(Combatente c : membros) if(c.estaVivo()) return c; return null; }
}