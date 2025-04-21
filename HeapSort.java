public class HeapSort {
    public static void sort(int[] arr) {
        int n = arr.length;

        // Constrói o heap (reorganiza o array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Extrai elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && arr[esq] > arr[maior])
            maior = esq;

        if (dir < n && arr[dir] > arr[maior])
            maior = dir;

        if (maior != i) {
            int swap = arr[i];
            arr[i] = arr[maior];
            arr[maior] = swap;

            heapify(arr, n, maior);
        }
    }
}
