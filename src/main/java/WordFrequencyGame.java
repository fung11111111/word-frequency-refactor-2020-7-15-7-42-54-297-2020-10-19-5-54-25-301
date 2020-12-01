import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String LINE_FEED = "\n";

    public String getResult(String sentence) throws CalculationErrorException {
        try {
            List<WordFrequency> wordFrequencies = getCalculatedWordFrequencies(splitSentenceToWords(sentence));
            return buildWordFrequencyResult(sortWordFrequenciesWithDescending(wordFrequencies));
        } catch (Exception exception) {
            throw new CalculationErrorException();
        }
    }

    private String buildWordFrequencyResult(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(word -> buildJointWordsToLine(word))
                .collect(Collectors.joining(LINE_FEED));
    }

    private String buildJointWordsToLine(WordFrequency wordFrequency) {
        return String.format("%s %d", wordFrequency.getWord(), wordFrequency.getCount());
    }

    private List<String> splitSentenceToWords(String sentence) {
        return Arrays.asList(sentence.split(WHITE_SPACE_REGEX));
    }

    private List<WordFrequency> getCalculatedWordFrequencies(List<String> words) {
        return words.stream()
                .distinct()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .collect(Collectors.toList());
    }

    private List<WordFrequency> sortWordFrequenciesWithDescending(List<WordFrequency> wordFrequencies){
        return wordFrequencies.stream()
                .sorted(Comparator.comparing(WordFrequency::getCount).reversed())
                .collect(Collectors.toList());
    }
}
