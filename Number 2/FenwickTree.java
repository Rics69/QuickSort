import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FenwickTree {

    private int[] tree;

    public FenwickTree(int size) {
        tree = new int[size + 1];
    }

    public int add(int index, int value) {
        index++;
        int count = 0;
        while (index < tree.length) {
            tree[index] += value;
            index += index & -index;
            count++;
        }
        return count;
    }

    public int delete(int index, int value) {
        return add(index, -value);
    }

    public int sum(int index) {
        index++;
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & -index;
        }
        return sum;
    }

    public int search(int target) {
        int left = 0;
        int right = tree.length - 1;
        int result = -1;
        int count = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midSum = sum(mid);
            count++;

            if (midSum == target) {
                result = mid;
                break;
            } else if (midSum < target) {
                left = mid + 1;
                count++;
            } else {
                result = mid;
                right = mid - 1;
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] randomInt = new int[10001];
        List<Long> timeAdd = new ArrayList<>(); //массив с временем в наносекундах для каждого добавления
        List<Integer> countAdd = new ArrayList<>(); //массив с количеством итерации для каждого добавления
        List<Long> timeSearch = new ArrayList<>(); //массив с временем в наносекундах для каждого поиска
        List<Integer> countSearch = new ArrayList<>(); //массив с количеством итерации для каждого поиска
        List<Long> timeDelete = new ArrayList<>(); //массив с временем в наносекундах для каждого удаления
        List<Integer> countDelete = new ArrayList<>(); //массив с количеством итерации для каждого удаления


        for (int i = 0; i < randomInt.length; i++) {
            randomInt[i] = (int) (Math.random()*1000);
        }


        AISD.FenwickTree tree = new AISD.FenwickTree(randomInt.length);
        for (int i = 0; i < randomInt.length; i++) {
            long start = System.nanoTime(); //для просчитывания времени работы добавление
            int count = tree.add(i, randomInt[i]);
            long finish = System.nanoTime();
            countAdd.add(count);
            long resultTime = finish-start;
            timeAdd.add(resultTime);
        }

        for (int i = 0; i < 100; i++) {
            int randomIndex = (int)(Math.random()*10000);
            long start = System.nanoTime(); //для просчитывания времени работы поиска
            int count = tree.search(randomInt[randomIndex]);
            long finish = System.nanoTime();
            countSearch.add(count);
            long resultTime = finish-start;
            timeSearch.add(resultTime);
        }

        for (int i = 0; i < 1000; i++) {
            int randomIndex = (int)(Math.random()*10000);
            long start = System.nanoTime(); //для просчитывания времени работы удаления
            int count = tree.delete(randomIndex, randomInt[randomIndex]);
            long finish = System.nanoTime();
            countDelete.add(count);
            long resultTime = finish-start;
            timeDelete.add(resultTime);
        }

        //расчеты для добавления
        int sumTimeAdd = (int)timeAdd.stream().mapToDouble(x -> x).sum();
        int srZnachTimeAdd = sumTimeAdd/timeAdd.size(); //среднее значение времени добавления элементов в дерево Фенвика
        int sumCountAdd = (int)countAdd.stream().mapToDouble(x -> x).sum();
        int srZnachCountAdd = sumCountAdd/countAdd.size(); //среднее значение количества итерации для добавления элементов в дерево Фенвика

        //рассчеты для поиска
        int sumTimeSearch = (int)timeSearch.stream().mapToDouble(x -> x).sum();
        int srZnachTimeSearch = sumTimeSearch/timeSearch.size(); //среднее значение времени поиска элементов в дереве Фенвика
        int sumCountSearch = (int)countSearch.stream().mapToDouble(x -> x).sum();
        int srZnachCountSearch = sumCountSearch/countSearch.size(); //среднее значение количества итерации для поиска элементов в дереве Фенвика

        //рассчеты для удаления
        int sumTimeDelete = (int)timeDelete.stream().mapToDouble(x -> x).sum();
        int srZnachTimeDelete = sumTimeDelete/timeDelete.size(); //среднее значение времени удаления элементов в дерево Фенвика
        int sumCountDelete = (int)countDelete.stream().mapToDouble(x -> x).sum();
        int srZnachCountDelete = sumCountDelete/countDelete.size(); //среднее значение количества итерации для удаления элементов в дерево Фенвика

        System.out.println(Arrays.toString(randomInt)); //вывод изначального массива
        System.out.println(Arrays.toString(tree.tree)); //вывод дерева Фенвика
        //массив с добавлением
        System.out.println(Arrays.toString(timeAdd.toArray()));
        System.out.println(Arrays.toString(countAdd.toArray()));
        System.out.println("Среднее время добавления элементов: " + srZnachTimeAdd);
        System.out.println("Среднее количество итерации для добавления элементов: " + srZnachCountAdd);

        //массив с поиском
        System.out.println(Arrays.toString(timeSearch.toArray()));
        System.out.println(Arrays.toString(countSearch.toArray()));
        System.out.println("Среднее время поиска элементов: " + srZnachTimeSearch);
        System.out.println("Среднее количество итерации для поиска элементов: " + srZnachCountSearch);

        //массив с удалением
        System.out.println(Arrays.toString(timeDelete.toArray()));
        System.out.println(Arrays.toString(countDelete.toArray()));
        System.out.println("Среднее время удаления элементов: " + srZnachTimeDelete);
        System.out.println("Среднее количество итерации для удаления элементов: " + srZnachCountDelete);

    }
}