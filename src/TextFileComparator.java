import java.util.Arrays;
import java.util.Comparator;


/**
 * Класс для сравнения двух текстовых файлов
 */
public class TextFileComparator implements Comparator<TextFile> {


    /**
     * Метод для сравнения двух текстовых файлов
     * @param file1 первый файл для сравнения
     * @param file2 второй файл для сравнения
     */
    @Override
    public int compare(TextFile file1, TextFile file2) {
        String content1 = file1.getContent();
        String content2 = file2.getContent();

        if (content1.equals(content2)) {
            return 0; //файлы идентичны
        } else {
            return 1; //файлы не идентичны
        }
    }

    public String equalsLeftAndRight(TextFile file1, TextFile file2) {
        StringBuilder sb = new StringBuilder();

        String[] words1 = new String[]{file1.getContent()};
        String[] words2 = new String[]{file2.getContent()};

        for (String word1 : words1) {
            if (!Arrays.asList(words2).contains(word1)) {
                sb.append("Слово отсутствует во втором файле: ").append(word1).append("\n");
                sb.append("Первый файл: ").append(Arrays.toString(words1)).append("\n");
                sb.append("Второй файл: ").append(Arrays.toString(words2)).append("\n");
            }
        }

        for (String word2 : words2) {
            if (!Arrays.asList(words1).contains(word2)) {
                sb.append("Слово отсутствует в первом файле: ").append(word2).append("\n");
                sb.append("Первый файл: ").append(Arrays.toString(words1)).append("\n");
                sb.append("Второй файл: ").append(Arrays.toString(words2)).append("\n");
            }
        }

        return sb.toString();
    }
}
