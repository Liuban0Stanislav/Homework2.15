import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        /**задаю размер массива*/
        int sizeArr = 100_000;

        /**создаю объекты*/
        IntegerList integerList1 = new IntegerListImpl();
        Random r = new Random();

        /**заполняю массив случайными числами от 1 до 1 000 000 включительно*/
        for (int i = 0; i < sizeArr; i++) {
            integerList1.add(r.nextInt(1_000_000) + 1);
        }
        System.out.println("stringList = " + integerList1);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        /**создаю копии исходного массива*/
        IntegerList integerList2 = new IntegerListImpl();
        IntegerList integerList3 = new IntegerListImpl();
        integerList1.integerListCopy(integerList2);
        integerList1.integerListCopy(integerList3);

        /**выполняется поиск элемента и замер времени для разных способов сортировки*/
        long start1 = System.currentTimeMillis();
        System.out.println(integerList1.contains(56,"Bubbles"));
        System.out.println(System.currentTimeMillis() - start1);

        long start2 = System.currentTimeMillis();
        System.out.println(integerList1.contains(56,"Selection"));
        System.out.println(System.currentTimeMillis() - start2);

        long start3 = System.currentTimeMillis();
        System.out.println(integerList1.contains(56,"Inspection"));
        System.out.println(System.currentTimeMillis() - start3);
    }
}

