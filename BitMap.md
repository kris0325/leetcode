import java.io.*;
import java.util.*;

public class TopKWords {
    private static final int MAX_WORD_LENGTH = 16;
    private static final int MAX_FILE_SIZE = 1024 * 1024; // 1MB
    private static final int TOP_K = 100;
    private static final int NUM_FILES = 5000;

    public static void main(String[] args) throws IOException {
        String inputFile = "input.txt"; // 1GB大小的输入文件
        String outputDir = "output/";

        // 第一步:将输入文件中的词按照hash值分散到5000个小文件中
        splitInputFileByHash(inputFile, outputDir, NUM_FILES);

        // 第二步:统计每个小文件中出现频率最高的100个词
        List<Map<String, Integer>> topKWordsList = processSmallFiles(outputDir, NUM_FILES);

        // 第三步:合并所有小文件的结果,得到最终的top 100个词
        Map<String, Integer> finalTopKWords = mergeTopKWords(topKWordsList);
        printTopKWords(finalTopKWords);
    }

    private static void splitInputFileByHash(String inputFile, String outputDir, int numFiles) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            List<BufferedWriter> writers = new ArrayList<>();
            for (int i = 0; i < numFiles; i++) {
                writers.add(getBufferedWriter(outputDir, i));
            }

            while ((line = reader.readLine()) != null) {
                int hashValue = Math.abs(line.hashCode()) % numFiles;
                writers.get(hashValue).write(line);
                writers.get(hashValue).newLine();
            }

            for (BufferedWriter writer : writers) {
                writer.close();
            }
        }
    }

    private static List<Map<String, Integer>> processSmallFiles(String outputDir, int numFiles) throws IOException {
        List<Map<String, Integer>> topKWordsList = new ArrayList<>();

        for (int i = 0; i < numFiles; i++) {
            String fileName = outputDir + "file_" + i + ".txt";
            Map<String, Integer> topKWords = getTopKWordsFromFile(fileName);
            topKWordsList.add(topKWords);
        }

        return topKWordsList;
    }

    private static Map<String, Integer> getTopKWordsFromFile(String fileName) throws IOException {
        Map<String, Integer> wordFrequency = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordFrequency.merge(line, 1, Integer::sum);
            }
        }

        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            if (minHeap.size() < TOP_K) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }

        Map<String, Integer> topKWords = new HashMap<>();
        while (!minHeap.isEmpty()) {
            Map.Entry<String, Integer> entry = minHeap.poll();
            topKWords.put(entry.getKey(), entry.getValue());
        }

        return topKWords;
    }

    private static Map<String, Integer> mergeTopKWords(List<Map<String, Integer>> topKWordsList) {
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for (Map<String, Integer> topKWords : topKWordsList) {
            for (Map.Entry<String, Integer> entry : topKWords.entrySet()) {
                if (minHeap.size() < TOP_K) {
                    minHeap.offer(entry);
                } else if (entry.getValue() > minHeap.peek().getValue()) {
                    minHeap.poll();
                    minHeap.offer(entry);
                }
            }
        }

        Map<String, Integer> finalTopKWords = new HashMap<>();
        while (!minHeap.isEmpty()) {
            Map.Entry<String, Integer> entry = minHeap.poll();
            finalTopKWords.put(entry.getKey(), entry.getValue());
        }

        return finalTopKWords;
    }

    private static void printTopKWords(Map<String, Integer> topKWords) {
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(topKWords.entrySet());
        sortedWords.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println("Top 100 words:");
        for (int i = 0; i < TOP_K; i++) {
            System.out.println(sortedWords.get(i).getKey() + ": " + sortedWords.get(i).getValue());
        }
    }

    private static BufferedWriter getBufferedWriter(String outputDir, int fileIndex) throws IOException {
        File file = new File(outputDir + "file_" + fileIndex + ".txt");
        return new BufferedWriter(new FileWriter(file));
    }
}