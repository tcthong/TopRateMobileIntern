package toprate.mobileintern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileHandler {

    //Map mà trong đó mỗi phần tử có key là từ tách được từ tập tin và value là số lần xuất hiện của nó.
    private Map<String, Integer> wordWithOccurrences;

    //Danh sách chứa các từ và số lần xuất hiện của từ đó được sắp xếp giảm dần theo số lần xuất hiện.
    private List<Map.Entry<String, Integer>> sortedWordsInDescending;

    /**
     * Đọc tệp tin có đường dẫn filePath và tách chuỗi đọc được thành các từ theo regex và đếm số lần xuất hiện của các từ đã tách được
     *
     * @param filePath đường dẫn tới tệp tin
     * @param regex biểu thức chính quy muốn sử dụng để tách chuỗi đọc được từ tệp tin thành các từ.
     *
     * @return map mà trong đó mỗi phần tử có key là từ tách được từ tập tin và value là số lần xuất hiện của nó.
     *
     * @throws IOException khi lỗi I/O xảy ra
     *
     **/
    public Map<String, Integer> countWordOccurrenceByRegex(String filePath, String regex) throws IOException {
        sortedWordsInDescending = null;
        wordWithOccurrences = new HashMap<>();
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        String prefix = "prefix ";
        String postfix = " postfix";

        while (line != null) {
            StringBuilder tempStr = new StringBuilder();
            //Thêm prefix và postfix vào chuỗi đang đọc được để hàm split không xử lý sai khi ký tự phân cách ở đầu hoặc cuối chuỗi
            tempStr.append(prefix);
            tempStr.append(line);
            tempStr.append(postfix);

            line = tempStr.toString().replaceAll(regex, " ");
            String[] words = line.split("\\s+");

            //Phải bỏ từ đầu tiên và từ cuối cùng vì ta chỉ thêm vào để hàm split không xử lý sai
            //chứ thực chất chúng không nằm trong chuỗi đọc được.
            for (int i = 1, n = words.length; i < n - 1; i++) {
                if (wordWithOccurrences.containsKey(words[i])) {
                    wordWithOccurrences.put(words[i], wordWithOccurrences.get(words[i]) + 1);
                } else {
                    wordWithOccurrences.put(words[i], 1);
                }
            }

            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        fileReader.close();
        return wordWithOccurrences;
    }

    /**
     * Sắp xếp danh sách các từ với số lần xuất hiện của nó theo thứ tự tăng dần số lần xuất hiện
     *
     * @return một danh sách các phần tử của wordWithOccurences được sắp xếp giảm dần theo số lần xuất hiện của từ.
     *         danh sách trống khi countWordOccurrenceByRegex(filePath, regex) chưa được gọi.
     *
     **/
    public List<Map.Entry<String, Integer>> sortWordsInDescending() {
        if (wordWithOccurrences == null) {
            return Collections.emptyList();
        }

        if (sortedWordsInDescending != null) {
            return sortedWordsInDescending;
        }


        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordWithOccurrences.entrySet());
        sortedWords.sort(new Comparator<Map.Entry<String, Integer>>() {
            //So sánh 2 phần tử theo số lần xuất hiện của từ.
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        this.sortedWordsInDescending = sortedWords;

        return sortedWords;
    }


    /**
     * Tìm kiếm top phần tử có số lần xuất hiện nhiều nhất trong wordWithOccurrences với từ bắt đầu bằng keyword
     *
     * @param keyword là từ cần tìm kiếm
     * @param top số lượng từ với số lượng xuất hiện của nó theo thứ tự giảm dần
     *
     * @return một danh sách các phần tử của wordWithOccurences được sắp xếp giảm dần theo số lần xuất hiện của từ.
     *         danh sách trống khi countWordOccurrenceByRegex(filePath, regex) chưa được gọi.
     *         null khi wordWithOccurences null.
     **/
    public Map<String, Integer> searchWordsInTop(String keyword, int top) {
        if (wordWithOccurrences == null) {
            return Collections.emptyMap();
        }

        if (sortedWordsInDescending == null) {
            sortedWordsInDescending = sortWordsInDescending();
        }

        //Sử dụng LinkedHashMap() để giữ nguyên thứ tự các phần tử được thêm vào (tức là các từ với số lần xuất hiện
        //theo thứ tự giảm dần)
        Map<String, Integer> result = new LinkedHashMap<>();
        int temp = 0;

        long startTime = System.currentTimeMillis();
        for (Map.Entry<String, Integer> wordEntry : sortedWordsInDescending) {
            if (temp == top) {
                break;
            }

            String currentWord = wordEntry.getKey();
            int occurrences = wordEntry.getValue();

            if (currentWord.startsWith(keyword)) {
                temp++;
                result.put(currentWord, occurrences);
            }
        }
        System.out.println("Take time to search: " + (System.currentTimeMillis() - startTime));

        return result;
    }
}
