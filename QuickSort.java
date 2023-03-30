package AISD;

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
            System.out.println("Количество итераций: " + quickSort(array, 0, array.length-1));
//            long finish = System.nanoTime();
//            long resultTime = finish-start;
//            System.out.println("Время работы алгоритма: " + resultTime/1000);

            System.out.println("Отсортированный массив - " + Arrays.toString(array));
        }
    }

    public static int quickSort(int[] arr, int low, int high){
        if(arr.length == 0){
            return 0; //завершить выполнение если длина массива равна 0
        }
        if(low >= high){
            return 0; //завершить выполнение, если нечего делить
        }

        //выбираем опорный элемент
        int middleIndex = low + (high-low)/2;
        int opora = arr[middleIndex];
        int countIterators = 0;

        // делим на подмассивы, элементы в одном из которых больше опорного элемента, а в другом меньше
        int i = low;
        int j = high;
        while(i <= j){
            while(arr[i] < opora){ // двигаем указатель слева, пока элемент слева меньше опорного элемента
                i++;
                countIterators++;
            }
            while(arr[j] > opora){ // двигаем указатель справа, пока элемент справа больше опорного элемента
                j--;
                countIterators++;
            }
            if(i <= j){ //меняем местами
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
                countIterators++;
            }
        }

        //вызываем рекурсию для сортировки левой и правой части
        if(low < j){
            countIterators += quickSort(arr, low, j);
        }

        if(high > i){
            countIterators += quickSort(arr, i, high);
        }

        return countIterators;

    }
}
