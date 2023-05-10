import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("./Test.txt"));
        while(scanner.hasNextLine()){
            int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println("Исходный массив - " + Arrays.toString(array));
            System.out.println("Размер массива - " + array.length);
//            long start = System.nanoTime(); //для просчитывания времени работы сортировки
            quickSort(array, 0, array.length-1);
//            long finish = System.nanoTime();
//            long resultTime = finish-start;
//            System.out.println("Время работы алгоритма: " + resultTime/1000);

            System.out.println("Отсортированный массив - " + Arrays.toString(array));
        }
    }

    public static void quickSort(int[] arr, int low, int high){
        if(arr.length == 0){
            return; //завершить выполнение если длина массива равна 0
        }
        if(low >= high){
            return; //завершить выполнение, если нечего делить
        }

        //выбираем опорный элемент
        int middleIndex = low + (high-low)/2;
        int opora = arr[middleIndex];

        // делим на подмассивы, элементы в одном из которых больше опорного элемента, а в другом меньше
        int i = low;
        int j = high;
        while(i <= j){
            while(arr[i] < opora){ // двигаем указатель слева, пока элемент слева меньше опорного элемента
                i++;
            }
            while(arr[j] > opora){ // двигаем указатель справа, пока элемент справа больше опорного элемента
                j--;
            }
            if(i <= j){ //меняем местами
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }

        //вызываем рекурсию для сортировки левой и правой части
        if(low < j){
            quickSort(arr, low, j);
        }

        if(high > i){
            quickSort(arr, i, high);
        }

    }
}