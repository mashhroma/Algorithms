import java.util.Random;

public class HeapSort {

    public static void main(String args[])
    {
        int arr[] = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(0,100);
        }

        System.out.print("Неотсортированный массив: ");
        printArray(arr);
 
        HeapSort ob = new HeapSort();
        ob.sort(arr);
 
        System.out.print("Отсортированный массив: ");
        printArray(arr);
    }

    static void printArray(int arr[]) {
        for (int i : arr) {
            System.out.print(i + " ");
        }   
        System.out.println();
    }
    
    public void sort(int arr[]) {
        int n = arr.length;
        int mid = arr.length / 2 - 1;
 
        // Первоначальное перестроение массива с помощью кучи
        for (int i = mid; i >= 0; i--)
            heapify(arr, n, i);
 
        // Корень переносим в конец, перестраиваем массив с помощью кучи и так делаем N раз
        for (int i = n - 1; i >= 0; i--) {
            // Переместить текущий корень в конец
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
            // Вызвать функцию построения кучи
            heapify(arr, i, 0);
        }
    }
 
    // Чтобы сгруппировать поддерево, корневое с узлом i, это индекс в arr[]. n - это размер кучи
    void heapify(int arr[], int n, int i) {
        int largest = i; // наибольший элемент - наш корень
        int l = 2 * i + 1; // левый ребенок = 2*i + 1
        int r = 2 * i + 2; // правый ребенок = 2*i + 2
 
        // если левый ребенок больше корня
        if (l < n && arr[l] > arr[largest])
            largest = l;
 
        // если правый ребенок больше, чем наибольший на данный момент
        if (r < n && arr[r] > arr[largest])
            largest = r;
 
        // если наибольший - не корень
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Рекурсивно опускаемся вниз для построения кучи на нижних уровнях
            heapify(arr, n, largest);
        }
    }
}