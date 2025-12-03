import java.util.Scanner;

class BatalhaVisual {
    private Equipe herois;
    private Equipe monstros;
    private Scanner scanner;
    private Cronometro timer;

    public BatalhaVisual(Equipe herois, Equipe monstros) {
        this.herois = herois;
        this.monstros = monstros;
        this.scanner = new Scanner(System.in);
        this.timer = new Cronometro();
    }

    public void iniciar() {
        UI.limparTela();
        UI.imprimirCabecalho(" TYPE KOMBAT ");
        System.out.println("  1. Escolha sua ação.");
        System.out.println("  2. Digite a palavra RAPIDAMENTE.");
        System.out.println("  3. Sobreviva.");
        System.out.println("\n  [Pressione ENTER para começar]");
        scanner.nextLine();

        while (!herois.perdeu() && !monstros.perdeu()) {
            Combatente heroi = herois.getLutadorAtivo();
            Combatente monstro = monstros.getLutadorAtivo();

            UI.limparTela();
            renderizarHUD(heroi, monstro);

            System.out.println("\n  >> SUA VEZ! O que vai fazer?");
            System.out.println("  [1] ATAQUE BÁSICO   (Palavra curta)");
            String cdTxt = (heroi.getCooldown() > 0) ? "[Recarga: " + heroi.getCooldown() + "]" : "[PRONTO!]";
            System.out.println("  [2] " + heroi.getNomeHabilidade() + " " + cdTxt);
            System.out.println("  [3] DEFENDER        (Recupera HP + Proteção)");
            System.out.print("\n  > Escolha: ");

            int escolha = 1;
            try {
                escolha = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
            }

            System.out.println("\n" + new String(new char[80]).replace("\0", "="));
            boolean atacou = false;

            if (escolha == 3) {
                System.out.println("  [🛡️] Você assumiu posição defensiva!");
                heroi.setPosturaDefensiva(true);
                heroi.curar(10);
            } else {
                String tipo = (escolha == 2) ? "ESPECIAL" : "ATAQUE";
                String palavra = (escolha == 2) ? heroi.getPalavraEspecial() : heroi.getPalavraAtaque();

                if (escolha == 2 && heroi.getCooldown() > 0) {
                    System.out.println("  [X] Habilidade em recarga! Você tropeçou.");
                } else {
                    double tempo = desafio(tipo, palavra);
                    if (tempo >= 0) {
                        int dano = (escolha == 2) ? heroi.calcularDanoEspecial(tempo) : heroi.calcularDanoAtaque(tempo);
                        if (escolha == 2)
                            heroi.resetarCooldown();
                        if (dano > 0)
                            monstro.receberDano(dano);
                        else
                            System.out.println("  [X] Ataque falhou (Lento demais)!");
                    } else {
                        System.out.println("  [X] Você errou a palavra!");
                    }
                    atacou = true;
                }
            }
            if (atacou)
                heroi.reduzirCooldown();

            if (!monstro.estaVivo()) {
                System.out.println("\n  [☠️] INIMIGO DERROTADO!");
                Item loot = ((Monstro) monstro).getLoot();
                if (loot != null)
                    heroi.receberItem(loot);
                esperar(2500);
                continue;
            }

            esperar(1500);

            if (monstro.estaVivo()) {
                System.out.println("\n" + new String(new char[80]).replace("\0", "="));
                System.out.println("  [!!!] " + monstro.getNome() + " ESTÁ ATACANDO!");

                double tempo = desafio("DEFESA", BancoPalavras.getDefesa());
                int danoBase = 20;

                if (tempo < 0) {
                    System.out.println("  [!!!] ERRO CRÍTICO NA DEFESA!");
                    heroi.receberDano(danoBase + 10);
                } else if (tempo < 2.0) {
                    System.out.println("  [✨] DEFESA PERFEITA!");
                    heroi.receberDano(danoBase / 4);
                } else {
                    System.out.println("  [🛡️] Bloqueio parcial...");
                    heroi.receberDano(danoBase / 2);
                }
            }
            esperar(2000);
        }

        UI.limparTela();
        if (monstros.perdeu())
            UI.imprimirCabecalho("  VITÓRIA! VOCÊ É O CAMPEÃO  ");
        else
            UI.imprimirCabecalho("  GAME OVER...  ");
    }

    private double desafio(String tipo, String palavra) {
        System.out.println("  DIGITE: >> " + palavra + " <<");
        System.out.print("  > ");
        timer.iniciar();
        String input = scanner.nextLine();
        double tempo = timer.parar();

        if (!input.equalsIgnoreCase(palavra))
            return -1.0;

        String feedback = (tempo < 1.8) ? "EXCELENTE!" : (tempo < 3.0) ? "BOM" : "LENTO";
        System.out.printf("  [Tempo: %.2fs] %s\n", tempo, feedback);
        return tempo;
    }

    private void renderizarHUD(Combatente h, Combatente m) {
        UI.imprimirCabecalho("BATALHA EM ANDAMENTO");
        String[] artH = h.getAsciiArt();
        String[] artM = m.getAsciiArt();

        System.out.printf("  %-35s %35s\n", h.getNome(), m.getNome());

        String barraH = UI.gerarBarraVida(h.getVida(), h.getVidaMaxima());
        String barraM = UI.gerarBarraVida(m.getVida(), m.getVidaMaxima());

        System.out.printf("  %-35s     VS     %35s\n\n", barraH, barraM);

        for (int i = 0; i < 3; i++) {
            System.out.printf("  %-35s %35s\n", artH[i], artM[i]);
        }
        UI.desenharSeparador();
    }

    private void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }
}