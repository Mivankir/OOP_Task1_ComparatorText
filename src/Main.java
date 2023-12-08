import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        TextFile file1 = new TextFile("D:/test/file1.txt");
        TextFile file2 = new TextFile("D:/test/file2.txt");

        TextFileComparator comparator = new TextFileComparator();
        int result = comparator.compare(file1, file2);

        if (result == 0) {
            System.out.println("Файлы идентичны");
        } else {
            System.out.println("Файлы не идентичны");
        }

        String resultLeftAndRight = comparator.equalsLeftAndRight(file1, file2);
        System.out.println(resultLeftAndRight);

    }
}