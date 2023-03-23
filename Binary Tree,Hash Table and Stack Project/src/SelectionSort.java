public class SelectionSort {
    // değiş tokuş fonksiyonu
    void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void sort(int dizi[]) {
        int n = dizi.length;

        for (int i = 0; i < n; i++) {
            int minimumIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (dizi[j] < dizi[minimumIndex]) {
                    minimumIndex = j;
                }
            }
            swap(dizi, minimumIndex, i);
        }
    }
}

