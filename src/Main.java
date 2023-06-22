import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StringList stringList = new StringListImpl();
        stringList.add("первый");
        stringList.add("третий");
        stringList.add("четвертый");
        stringList.add("пятый");
        System.out.println(stringList.toString());
        System.out.println();

        stringList.add(1, "второй");
        System.out.println(stringList.toString());
        System.out.println();

        stringList.remove("пятый");
        System.out.println(stringList.toString());

    }
}