import java.util.*;

public class TesteSorts {
    static final int[] TAMANHOS = {800000, 1000000};
    static final int REPETICOES = 10;

    public static void main(String[] args) {
        System.out.println("Iniciando testes de performance dos algoritmos de ordenação\n");

        for (int tamanho : TAMANHOS) {
            System.out.println("==== TAMANHO DO VETOR: " + tamanho + " ====");

            // Testes com inteiros
            testarAlgoritmoInteiros("BubbleSort", tamanho, TesteSorts::bubbleSort);
            testarAlgoritmoInteiros("InsertionSort", tamanho, TesteSorts::insertionSort);
            testarAlgoritmoInteiros("SelectionSort", tamanho, TesteSorts::selectionSort);
            testarAlgoritmoInteiros("QuickSort", tamanho, TesteSorts::quickSort);
            testarAlgoritmoInteiros("MergeSort", tamanho, TesteSorts::mergeSort);
            testarAlgoritmoInteiros("HeapSort", tamanho, TesteSorts::heapSort);

            // Testes com TAD
            // (Você deve ter os algoritmos adaptados para arrays de `MeuItem`)
        }
    }

    private static void testarAlgoritmoInteiros(String nome, int tamanho, OrdenadorInt ordenador) {
        System.out.println("\nAlgoritmo: " + nome);

        // Aleatório
        medirDesempenho(nome, "Aleatório", tamanho, ordenador, false);
        // Inverso
        medirDesempenho(nome, "Inverso", tamanho, ordenador, true);
    }

    private static void medirDesempenho(String nome, String tipo, int tamanho, OrdenadorInt ordenador, boolean inverso) {
        long[] tempos = new long[REPETICOES];

        for (int i = 0; i < REPETICOES; i++) {
            int[] vetor = gerarVetorAleatorio(tamanho);
            if (inverso) vetor = inverterVetor(vetor);
            int[] copia = vetor.clone();

            long inicio = System.nanoTime();
            ordenador.ordenar(copia);
            long fim = System.nanoTime();

            tempos[i] = fim - inicio;
        }

        double media = Arrays.stream(tempos).average().getAsDouble() / 1_000_000.0;
        double desvio = calcularDesvioPadrao(tempos) / 1_000_000.0;

        System.out.printf("  Entrada: %-9s | Média: %.3f ms | Desvio Padrão: %.3f ms%n", tipo, media, desvio);
    }

    private static int[] gerarVetorAleatorio(int tamanho) {
        Random rand = new Random();
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = rand.nextInt(100_000);
        }
        return vetor;
    }

    private static int[] inverterVetor(int[] vetor) {
        int[] invertido = vetor.clone();
        Arrays.sort(invertido);
        for (int i = 0, j = invertido.length - 1; i < j; i++, j--) {
            int temp = invertido[i];
            invertido[i] = invertido[j];
            invertido[j] = temp;
        }
        return invertido;
    }

    private static double calcularDesvioPadrao(long[] tempos) {
        double media = Arrays.stream(tempos).average().orElse(0);
        double soma = 0;
        for (long tempo : tempos) {
            soma += Math.pow(tempo - media, 2);
        }
        return Math.sqrt(soma / tempos.length);
    }

    // Funções que chamam os algoritmos
    private static void bubbleSort(int[] arr) { BubbleSort.sort(arr); }
    private static void insertionSort(int[] arr) { InsertionSort.sort(arr); }
    private static void selectionSort(int[] arr) { SelectionSort.sort(arr); }
    private static void quickSort(int[] arr) { QuickSort.sort(arr, 0, arr.length - 1); }
    private static void mergeSort(int[] arr) { MergeSort.sort(arr, 0, arr.length - 1); }
    private static void heapSort(int[] arr) { HeapSort.sort(arr); }

    interface OrdenadorInt {
        void ordenar(int[] arr);
    }
}
