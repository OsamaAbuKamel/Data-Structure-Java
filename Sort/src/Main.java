import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        int[] integers = { 3, 5, 6, 2, 1, 4,7,8,11,77,55 };
        // // Using Bubble Sort
        // System.out.println("------------------Bubble Sort------------------");
        // BubbleSort.bubble(integers);
        // for (int i : integers) {
        //     System.out.print(i + "\t");
        // }
        // // Using Insertion Sort
        // System.out.println("\n------------------Insertion Sort------------------");
        // InsertionSort.insertionSort(integers);
        // for (int i : integers) {
        //     System.out.print(i + "\t");
        // }
        // // Using Selection Sort
        // System.out.println("\n------------------Selection Sort------------------");
        // SelectionSort.selection(integers);
        // for (int i : integers) {
        //     System.out.print(i + "\t");
        // }
        // Using Merge Sort
        // System.out.println("\n------------------Merge Sort------------------");
        // MergeSort.mergeSort(integers);
        // for (int i : integers) {
        //     System.out.print(i + "\t");
        // }

        // Using Radix Sort
        // System.out.println("\n------------------Radix Sort------------------");
        // RadixSort.radixSort(integers);
        // for (int i : integers) {
        //     System.out.print(i + "\t");
        // }

        System.out.println("\n------------------Quick Sort------------------");
        QuickSort.quickSort(integers);
        for (int i : integers) {
            System.out.print(i + "\t");
        }


    }
}