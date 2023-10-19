import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FindLongestFirstAndSecondCompoundWord {
    public static void main(String[] args) {
        String fileName = "Input_02.txt";
        TrieNode root = new TrieNode((char) 0);

        String longestCompoundWord = "";
        String secondLongestCompoundWord = "";

        LinkedList<WordSuffix> wordSuffixes = new LinkedList<>();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            String word;

            while ((word = bufferReader.readLine()) != null) {
                List<String> prefixes = root.getAllPrefixes(word);
                for (String prefix : prefixes) {
                    wordSuffixes.add(new WordSuffix(word, word.substring(prefix.length())));
                }
                root.insertWord(word);
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        }

        while (!wordSuffixes.isEmpty()) {
            WordSuffix wordsuffix = wordSuffixes.remove();
            String word = wordsuffix.word;
            String suffix = wordsuffix.suffix;

            if (root.contains(suffix)) {
                if (word.length() > longestCompoundWord.length()) {
                    secondLongestCompoundWord = longestCompoundWord;
                    longestCompoundWord = word;
                } else if (word.length() > secondLongestCompoundWord.length()) {
                    secondLongestCompoundWord = word;
                }
            } else {
                List<String> prefixes = root.getAllPrefixes(suffix);
                for (String prefix : prefixes) {
                    wordSuffixes.add(new WordSuffix(word, suffix.substring(prefix.length())));
                }
            }
        }

        System.out.println("Longest Compound Word: " + longestCompoundWord);
        System.out.println("Second Longest Compound Word: " + secondLongestCompoundWord);
    }
}

class WordSuffix {
    public WordSuffix(String w, String s) {
        this.word = w;
        this.suffix = s;
    }

    public String word;
    public String suffix;
}

class TrieNode {
    private char value;
    private boolean isEndOfWord;
    private TrieNode[] children;

    public TrieNode(char value) {
        this.value = value;
        this.isEndOfWord = false;
        this.children = new TrieNode[26]; 
    }

    public void insertWord(String word) {
        TrieNode current = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode(c);
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    public boolean contains(String word) {
        TrieNode current = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }

    public List<String> getAllPrefixes(String word) {
        List<String> prefixes = new ArrayList<>();
        TrieNode current = this;
        int i = 0;
        while (i < word.length()) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (current.children[index] == null) {
                break;
            }
            current = current.children[index];
            if (current.isEndOfWord) {
                prefixes.add(word.substring(0, i + 1));
            }
            i++;
        }
        return prefixes;
    }
}
