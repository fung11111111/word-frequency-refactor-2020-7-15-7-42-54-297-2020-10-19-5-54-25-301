import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String LINE_FEED = "\n";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> sortedWordFrequencies = getCalculatedWordFrequencies(sentence).stream()
                    .sorted(Comparator.comparing(WordFrequency::getCount).reversed())
                    .collect(Collectors.toList());
            return buildWordFrequencyResult(sortedWordFrequencies);
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    public String buildWordFrequencyResult(List<WordFrequency> wordFrequencies) {
        StringJoiner wordFrequencyResultJoiner = new StringJoiner(LINE_FEED);
        wordFrequencies.stream()
                .forEach(word -> wordFrequencyResultJoiner.add(buildJointWordsToLine(word)));
        return wordFrequencyResultJoiner.toString();
    }

    public String buildJointWordsToLine(WordFrequency word) {
        return String.format("%s %d", word.getWord(), word.getCount());
    }

    public List<WordFrequency> getCalculatedWordFrequencies(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE_REGEX));
        return words.stream()
                .distinct()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .collect(Collectors.toList());
    }
}
