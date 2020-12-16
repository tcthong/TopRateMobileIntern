package toprate.mobileintern;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        FileHandler fileHandler = new FileHandler();

        //Các ký tự không thuộc từ a đến z, A đến Z, 0 đến 9, và các dấu ' hoặc - mà trước và sau nó đều là chữ cái
        //(ví dụ từ i'm hoặc can't hoặc good-looking,... thì các ký tự ' hoặc - thì trước và sau nó đều là các chữ cái sẽ không được
        // dùng làm ký tự phân cách từ)
        //thì đều coi là ký tự phân cách dùng để tách từ.
        String regex = "((?<![a-zA-Z])('|-)|('|-)(?![a-zA-Z])|[^a-zA-Z\\d'-])";
        String filePath = "BaiTap.txt";

        Map<String, Integer> wordWithOccurrences = null;
        try {
            wordWithOccurrences = fileHandler.countWordOccurrenceByRegex(filePath, regex);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        if (wordWithOccurrences != null) {

            printOccurrencesCount(wordWithOccurrences);

            printWordWithOccurrence(wordWithOccurrences);

            printWordInDescendingOccurrence(fileHandler);

            searchWord(fileHandler);
        }
    }

    //In ra tổng tất cả số từ tách được
    private static void printOccurrencesCount(Map<String, Integer> wordWithOccurrences) {
        System.out.println("-------------------------------------------------------------------------");
        int wordCount = 0;
        for (String word : wordWithOccurrences.keySet()) {
            wordCount += wordWithOccurrences.get(word);
        }
        System.out.println("The number of words: " + wordCount);
    }

    //In ra từng từ với số lần xuất hiện của nó
    private static void printWordWithOccurrence(Map<String, Integer> wordWithOccurrences) {
        System.out.println();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Words and number of occurrences of each word:");
        for (String word : wordWithOccurrences.keySet()) {
            System.out.println(word + " - " + wordWithOccurrences.get(word));
        }
    }

    //In ra danh sách từ với số lần xuất hiện của nó theo thứ tự giảm dần
    private static void printWordInDescendingOccurrence(FileHandler fileHandler) {
        System.out.println();
        System.out.println("-------------------------------------------------------------------------");
        List<Map.Entry<String, Integer>> sortedWords = fileHandler.sortWordsInDescending();
        if (sortedWords.isEmpty()) {
            System.out.println("Not found");
        }

        System.out.println("Words and the number of occurrences of each word in descending order:");
        //In ra màn hình các từ và số lần xuất hiện của nó theo thứ tự giảm dần số lần xuất hiện.
        for (Map.Entry<String, Integer> wordEntry : sortedWords) {
            System.out.println(wordEntry.getKey() + " - " + wordEntry.getValue());
        }
    }

    //Tìm kiếm theo từ khóa
    private static void searchWord(FileHandler fileHandler) {
        Scanner sc = new Scanner(System.in);

        int top;
        do {
            System.out.println();
            System.out.println("-------------------------------------------------------------------------");
            System.out.print("Input keyword: ");
            String keyword = sc.nextLine();
            System.out.print("Top: ");
            top = sc.nextInt();
            sc.nextLine();
            Map<String, Integer> topWords = fileHandler.searchWordsInTop(keyword, top);
            for (String word : topWords.keySet()) {
                System.out.println(word + " - " + topWords.get(word));
            }
        } while (top > 0);

        sc.close();
    }
}
