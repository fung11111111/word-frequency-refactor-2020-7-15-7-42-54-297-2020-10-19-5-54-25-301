import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String LINE_FEED = "\n";

    public String getResult(String sentence) throws CalculationErrorException {
        try {
            List<WordFrequency> sortedWordFrequencies = getSortedCalculatedWordFrequencies(splitSentenceToWords(sentence));
            return buildWordFrequencyResult(sortedWordFrequencies);
        } catch (Exception exception) {
            throw new CalculationErrorException();
        }
    }

    public String buildWordFrequencyResult(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(word -> buildJointWordsToLine(word))
                .collect(Collectors.joining(LINE_FEED));
    }

    public String buildJointWordsToLine(WordFrequency wordFrequency) {
        return String.format("%s %d", wordFrequency.getWord(), wordFrequency.getCount());
    }

    public List<String> splitSentenceToWords(String sentence) {
        return Arrays.asList(sentence.split(WHITE_SPACE_REGEX));
    }

    public List<WordFrequency> getSortedCalculatedWordFrequencies(List<String> words) {
        return words.stream()
                .distinct()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .sorted(Comparator.comparing(WordFrequency::getCount).reversed())
                .collect(Collectors.toList());
    }
}
