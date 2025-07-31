import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestorPalavras {
    private List<String> palavras;
    private String palavraAtual;

    public GestorPalavras() {
        palavras = new ArrayList<>();
        inicializarPalavras();
    }

    public void inicializarPalavras() {
        String[] palavrasHardcoded = {
                "PROGRAMACAO", "COMPUTADOR", "ALGORITMO", "VARIAVEL",
                "FUNCAO", "CLASSE", "OBJETO", "METODO", "ARRAY",
                "CICLO", "CONDICAO", "ELEFANTE", "GIRAFA", "TIGRE", "LEAO"
        };
        for (String palavra : palavrasHardcoded) {
            palavras.add(palavra);
        }
    }

    public String escolherPalavraAleatoria() {
        Random random = new Random();
        int index = random.nextInt(palavras.size());
        palavraAtual = palavras.get(index);
        return palavraAtual;
    }

    public int obterTotalPalavras() {
        return palavras.size();
    }

    public String getPalavraAtual() {
        return palavraAtual;
    }

    public void setPalavraAtual(String palavraAtual) {
        this.palavraAtual = palavraAtual;
    }
}