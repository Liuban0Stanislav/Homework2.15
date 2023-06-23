public class Main {
    public static void main(String[] args) {
        IntegerList stringList = new IntegerListImpl();
        stringList.add(1);
        stringList.add(3);
        stringList.add(4);
        stringList.add(5);
        System.out.println(stringList);
        System.out.println();

        stringList.add(1, 2);
        System.out.println(stringList);
        System.out.println();

        stringList.remove(4);
        System.out.println(stringList);

        for (int i = 0; i < 20; i++) {
            stringList.add(stringList.get(stringList.size() - 1) + 1);
        }
        System.out.println("stringList = " + stringList);
    }
}