public class MergeSort {
    public static void sort(int[] arr, int esq, int dir) {
        if (esq < dir) {
            // Evita overflow: (esq + dir) pode ultrapassar o limite de int
            int meio = esq + (dir - esq) / 2;
            sort(arr, esq, meio);
            sort(arr, meio + 1, dir);
            merge(arr, esq, meio, dir);
        }
    }

    private static void merge(int[] arr, int esq, int meio, int dir) {
        int n1 = meio - esq + 1;
        int n2 = dir - meio;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, esq, L, 0, n1);
        System.arraycopy(arr, meio + 1, R, 0, n2);

        int i = 0, j = 0, k = esq;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}
