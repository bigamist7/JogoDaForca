import java.util.Scanner;

public class Main {
    private Scanner scanner;
    private JogoForca jogoForca;
    private GestorPalavras gestorPalavras;
    private boolean continuar;
    private int jogosNestaSessao;
    private int vitoriasNestaSessao;

    public Main() {
        scanner = new Scanner(System.in);
        gestorPalavras = new GestorPalavras();
        continuar = true;
        jogosNestaSessao = 0;
        vitoriasNestaSessao = 0;
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.inicio();
    }

    public void inicio() {
        System.out.println("=== JOGO DA FORCA ===");
        System.out.println("Bem-vindo ao Jogo da Forca do João Pinto!");
        System.out.println("A carregar a lista de palavras...");
        System.out.println(gestorPalavras.obterTotalPalavras() + " palavras disponíveis.");
        System.out.println("Sistema inicializado!");

        while (continuar) {
            mostrarMenuPrincipal();
            int opcao = obterOpcao();
            processarOpcao(opcao);
        }

        fim();
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Novo Jogo");
        System.out.println("2. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public int obterOpcao() {
        String entrada = scanner.nextLine();
        while (!validarEntrada(entrada)) {
            System.out.print("Entrada inválida. Tente novamente: ");
            entrada = scanner.nextLine();
        }
        return Integer.parseInt(entrada);
    }

    public boolean validarEntrada(String entrada) {
        return entrada.equals("1") || entrada.equals("2");
    }

    public void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                executarJogo();
                break;
            case 2:
                continuar = false;
                break;
        }
    }

    public void executarJogo() {
        System.out.println("\n=== INICIANDO NOVO JOGO ===");
        System.out.println("A escolher palavra aleatória...");
        jogoForca = new JogoForca(gestorPalavras);
        jogosNestaSessao++;

        System.out.println("Palavra selecionada! (" + jogoForca.getPalavraSecreta().length() + " letras)");
        System.out.println("Regras:");
        System.out.println("- Adivinhe a palavra letra por letra");
        System.out.println("- Você tem 6 tentativas erradas");
        System.out.println("Boa sorte!");

        while (!jogoForca.isJogoTerminado()) {
            System.out.println("\n=== ESTADO DO JOGO ===");
            System.out.println("Palavra: " + jogoForca.getPalavraRevelada().replace("", " ").trim());
            System.out.println("Tentativas restantes: " + jogoForca.tentativasRestantes);
            System.out.println("Letras tentadas: " + (jogoForca.letrasTentadas.isEmpty() ? "(nenhuma)" : jogoForca.letrasTentadas));
            System.out.println("Hora de início: " + jogoForca.getHoraInicio());
            jogoForca.mostrarForca();

            System.out.print("Digite uma letra: ");
            String entrada = scanner.nextLine().toUpperCase();

            if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
                System.out.println("Entrada inválida. Digite apenas uma letra.");
                continue;
            }

            char letra = entrada.charAt(0);
            boolean antes = jogoForca.getPalavraRevelada().contains(String.valueOf(letra));
            jogoForca.processarTentativa(letra);
            boolean depois = jogoForca.getPalavraRevelada().contains(String.valueOf(letra));

            if (antes == depois) {
                System.out.println("A letra '" + letra + "' não existe na palavra.");
            } else {
                System.out.println("Boa! A letra '" + letra + "' existe na palavra!");
            }
        }

        mostrarResultadoFinal();
        perguntarNovoJogo();
    }

    public void mostrarResultadoFinal() {
        if (jogoForca.isJogadorVenceu()) {
            System.out.println("\n=== PARABÉNS! VOCÊ VENCEU! ===");
        } else {
            System.out.println("\n=== GAME OVER ===");
        }

        System.out.println("Palavra completa: " + jogoForca.getPalavraSecreta());
        System.out.println("=== ESTATÍSTICAS DO JOGO ===");
        System.out.println("Palavra: " + jogoForca.getPalavraSecreta());
        System.out.println("Letras tentadas: " + jogoForca.letrasTentadas);
        System.out.println("Tentativas corretas: " + jogoForca.obterLetrasCorretas());
        System.out.println("Tentativas erradas: " + jogoForca.obterLetrasErradas());
        System.out.println("Resultado: " + (jogoForca.isJogadorVenceu() ? "VITÓRIA" : "DERROTA"));
        System.out.println("Hora de início: " + jogoForca.getHoraInicio());
        System.out.println("Hora de fim: " + jogoForca.getHoraFim());

        if (!jogoForca.isJogadorVenceu()) {
            System.out.println("A palavra era: " + jogoForca.getPalavraSecreta());
        }

        if (jogoForca.isJogadorVenceu()) vitoriasNestaSessao++;
    }

    public void perguntarNovoJogo() {
        System.out.print("\nQuer jogar novamente? (S/N): ");
        String resposta = scanner.nextLine().toUpperCase();
        if (!resposta.equals("S")) {
            continuar = false;
        }
    }

    public void fim() {
        System.out.println("\nA sair do programa...");
        System.out.println("Obrigado por jogar o Jogo da Forca do João Pinto!");
        System.out.println("Estatísticas da sessão:");
        System.out.println("- Jogos: " + jogosNestaSessao);
        System.out.println("- Vitórias: " + vitoriasNestaSessao);
        System.out.println("- Última palavra: " + jogoForca.getPalavraSecreta() + " (" + (jogoForca.isJogadorVenceu() ? "VITÓRIA" : "DERROTA") + ")");
        System.out.println("Desenvolvido por: JoãoPinto");
        System.out.println("Sistema encerrado.");
    }
}