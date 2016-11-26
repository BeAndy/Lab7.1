import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrew on 11/26/2016.
 */
public class Runner {

    public static void main(String[] args) {
        List<String> listOfWords = getListWords(getListStrings("input.txt"));
        List<String> words=getWords(listOfWords);
        List<String> numbers=getNumbers(listOfWords);
        Collections.sort(numbers, (first, second) -> (Double.parseDouble(first) > Double.parseDouble(second) ? 1 : 0));
        Collections.sort(words, (first, second) -> (first.compareToIgnoreCase(second)));
        writeData("output2.txt",words.iterator());
        writeData("output1.txt",numbers.iterator());
    }

    public static List<String> getListStrings(String way) {
        List<String> list = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(way)));
            String currentDirectory = reader.readLine();
            while (currentDirectory != null) {
                list.add(currentDirectory);
                currentDirectory = reader.readLine();
            }
            reader.close();
        } catch (IOException exception) {
            exception.getLocalizedMessage();
        }
        return list;
    }

    public static List<String> getListWords(List<String> listStrings) {
        List<String> words = new LinkedList<>();
        Pattern pattern = Pattern.compile("[^\\s.,;]+");
        for (String s : listStrings) {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                words.add(matcher.group());
            }
        }
        return words;
    }

    public static List<String> getNumbers(List<String> words) {
        List<String> numbers = new LinkedList<>();
        Pattern patternFirstTask = Pattern.compile("\\d+");
        for (String currentWord : words) {
            if (patternFirstTask.matcher(currentWord).matches()) {
                numbers.add(currentWord);
            }
        }
        System.out.println("Number of elements in the first task: " + numbers.size());
        return numbers;

    }
    public static List<String> getWords(List<String> words) {
        List<String> names = new LinkedList<>();
        Pattern patternSecondTask = Pattern.compile("[^\\d\\s,.;]+");
        for (String currentWord : words) {
            if (patternSecondTask.matcher(currentWord).matches()) {
                names.add(currentWord);
            }
        }
        System.out.println("Number of elements in the second task: " + names.size());
        return names;

    }


    private static void writeData(String way, Iterator<String> iterator) {
        try {
            FileWriter writer = new FileWriter(new File(way));
            while (iterator.hasNext()) {
                writer.write(iterator.next());
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException exception) {
            exception.getLocalizedMessage();
        }
    }

}