import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    public static void sort(int[] arr, int inicio, int fim) {
        if (inicio < fim) {
            int pivoIndex = randomizedPartition(arr, inicio, fim);
            sort(arr, inicio, pivoIndex - 1);
            sort(arr, pivoIndex + 1, fim);
        }
    }

    private static int randomizedPartition(int[] arr, int inicio, int fim) {
        int pivoIndex = random.nextInt(fim - inicio + 1) + inicio;
        swap(arr, pivoIndex, fim); // coloca o pivô no fim (posição tradicional)
        return partition(arr, inicio, fim);
    }

    private static int partition(int[] arr, int inicio, int fim) {
        int pivo = arr[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (arr[j] <= pivo) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, fim);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
