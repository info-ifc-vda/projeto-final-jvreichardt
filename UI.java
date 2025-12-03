class UI {
    public static void limparTela() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    public static String gerarBarraVida(int atual, int max) {
        int tamanhoTotal = 15;
        double porcentagem = (double) atual / max;
        int preenchido = (int) (tamanhoTotal * porcentagem);
        
        StringBuilder sb = new StringBuilder();
        sb.append("HP: [");
        for (int i = 0; i < tamanhoTotal; i++) {
            if (i < preenchido) sb.append("#");
            else sb.append("-");
        }
        sb.append("] " + atual + "/" + max);
        return sb.toString();
    }

    public static void desenharSeparador() {
        System.out.println("+--------------------------------------------------------------------------------+");
    }
    
    public static void imprimirCabecalho(String texto) {
        desenharSeparador();
        int larguraTotal = 80;
        int espacos = (larguraTotal - texto.length() - 2) / 2;
        String margem = new String(new char[espacos]).replace("\0", " ");
        System.out.println("|" + margem + texto + margem + (texto.length() % 2 != 0 ? " " : "") + "|");
        desenharSeparador();
    }
}