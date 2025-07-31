import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JogoForca {
    public String palavraSecreta;
    public char[] palavraRevelada;
    public List<Character> letrasTentadas;
    public int tentativasRestantes;
    private boolean jogoTerminado;
    private boolean jogadorVenceu;
    private GestorPalavras gestorPalavras;
    private String horaInicio;
    private String horaFim;

    public JogoForca(GestorPalavras gestorPalavras) {
        this.gestorPalavras = gestorPalavras;
        inicializarJogo();
    }

    public void inicializarJogo() {
        palavraSecreta = gestorPalavras.escolherPalavraAleatoria();
        palavraRevelada = new char[palavraSecreta.length()];
        for (int i = 0; i < palavraRevelada.length; i++) {
            palavraRevelada[i] = '_';
        }
        letrasTentadas = new ArrayList<>();
        tentativasRestantes = 6;
        jogoTerminado = false;
        jogadorVenceu = false;
        horaInicio = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public void processarTentativa(char letra) {
        letra = Character.toUpperCase(letra);
        if (letrasTentadas.contains(letra) || jogoTerminado) return;

        letrasTentadas.add(letra);

        if (verificarLetra(letra)) {
            revelarLetras(letra);
        } else {
            tentativasRestantes--;
        }

        verificarVitoria();
        verificarDerrota();
    }

    public boolean verificarLetra(char letra) {
        return palavraSecreta.indexOf(letra) >= 0;
    }

    public void revelarLetras(char letra) {
        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letra) {
                palavraRevelada[i] = letra;
            }
        }
    }

    public void verificarVitoria() {
        if (new String(palavraRevelada).equals(palavraSecreta)) {
            jogadorVenceu = true;
            jogoTerminado = true;
            horaFim = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
    }

    public void verificarDerrota() {
        if (tentativasRestantes <= 0) {
            jogadorVenceu = false;
            jogoTerminado = true;
            horaFim = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
    }

    public void mostrarEstado() {
        System.out.println("Palavra: " + new String(palavraRevelada));
        System.out.println("Tentativas restantes: " + tentativasRestantes);
        System.out.println("Letras tentadas: " + letrasTentadas);
    }

    public void mostrarForca() {
        int erros = 6 - tentativasRestantes;
        System.out.println("+---+");
        System.out.println("|   |");
        System.out.println("|   " + (erros >= 1 ? "O" : ""));
        System.out.println("|  " + (erros >= 3 ? "/" : " ") + (erros >= 2 ? "|" : "") + (erros >= 4 ? "\\" : ""));
        System.out.println("|  " + (erros >= 5 ? "/" : "") + (erros >= 6 ? " \\" : ""));
        System.out.println("|");
        System.out.println("=========");
    }

    public int obterLetrasCorretas() {
        int count = 0;
        for (char letra : letrasTentadas) {
            if (palavraSecreta.indexOf(letra) >= 0) count++;
        }
        return count;
    }

    public int obterLetrasErradas() {
        int count = 0;
        for (char letra : letrasTentadas) {
            if (palavraSecreta.indexOf(letra) < 0) count++;
        }
        return count;
    }

    public boolean isJogoTerminado() {
        return jogoTerminado;
    }

    public boolean isJogadorVenceu() {
        return jogadorVenceu;
    }

    public String getPalavraSecreta() {
        return palavraSecreta;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public String getPalavraRevelada() {
        return new String(palavraRevelada);
    }
}