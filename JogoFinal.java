import java.util.Scanner;

public class JogoFinal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UI.limparTela();
        UI.imprimirCabecalho(" CRIACAO DE PERSONAGEM ");
        System.out.print("  Nome do Heroi: ");
        String nome = sc.nextLine();

        System.out.println("\n  Classes Disponiveis:");
        System.out.println("  [1] Guerreiro (Equilibrado, mais vida)");
        System.out.println("  [2] Mago (Dano alto, menos vida)");
        System.out.print("  Escolha: ");

        Combatente heroi = (sc.nextLine().equals("2")) ? new Mago(nome) : new Guerreiro(nome);

        Equipe timeHerois = new Equipe();
        timeHerois.adicionar(heroi);

        Equipe timeMonstros = new Equipe();
        timeMonstros.adicionar(new Monstro("Orc Batedor", 60, 15, "Orc", new PocaoCura()));
        timeMonstros.adicionar(new Monstro("Chefe Orc", 150, 30, "Boss", new PergaminhoForca()));

        BatalhaVisual jogo = new BatalhaVisual(timeHerois, timeMonstros);
        jogo.iniciar();
        sc.close();
    }
}