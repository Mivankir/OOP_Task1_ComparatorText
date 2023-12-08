import java.io.*;
import java.util.Comparator;

/**
 * Класс для чтения файла
 */
public class TextFile {

    private final String content;
    public TextFile(String filePath) throws IOException {
        this.content = readContent(filePath);
    }

    public String getContent() {
        return content;
    }

    /**
     * Метод для чтения файла
     * @param filePath - принимаемый файл для чтения
     */
    private String readContent(String filePath) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        if (checkNullBuffer(bufferedReader)) throw new FileNotFoundException();

        StringBuilder stringBuilder = new StringBuilder();
        try  {
            String line;
            // считываем информацию файла 1 раз и если не null, то записываем его
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            // обработка ошибки(Может быть любая)
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stringBuilder.toString();
    }

    protected boolean checkNullBuffer(BufferedReader bufferedReader) {
        return bufferedReader == null;
    }
}
