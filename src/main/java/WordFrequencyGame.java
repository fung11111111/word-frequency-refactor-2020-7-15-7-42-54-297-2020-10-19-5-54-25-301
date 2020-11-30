import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final int COUNT_ONE_WORD = 1;
    private static final String WORD_FREQUENCY_RESULT_PLUS_1 = " 1";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            Map<String, List<WordFrequency>> wordCountMapping = getListMap(getWordFrequencies(sentence));

            List<WordFrequency> list = new ArrayList<>();
            for (Map.Entry<String, List<WordFrequency>> entry : wordCountMapping.entrySet()) {
                WordFrequency input = new WordFrequency(entry.getKey(), entry.getValue().size());
                list.add(input);
            }
            list.sort((firstWord, secondWord) -> secondWord.getCount() - firstWord.getCount());

            return buildWordFrequencyResult(list);
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    public String buildWordFrequencyResult(List<WordFrequency> inputList) {
        StringJoiner wordFrequencyResultJoiner = new StringJoiner("\n");
        for (WordFrequency word : inputList) {
            wordFrequencyResultJoiner.add(buildJointWordsToLine(word));
        }
        return wordFrequencyResultJoiner.toString();
    }

    public String buildJointWordsToLine(WordFrequency word) {
        return String.format("%s %d", word.getWord(), word.getCount());
    }

    public List<WordFrequency> getWordFrequencies(String sentence) {
        String[] words = sentence.split(WHITE_SPACE_REGEX);
        return Arrays.asList(words).stream()
                .map(word -> new WordFrequency(word, COUNT_ONE_WORD))
                .collect(Collectors.toList());
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> inputList) {
        Map<String, List<WordFrequency>> wordCountMapping = new HashMap<>();
        for (WordFrequency input : inputList) {
            if (!wordCountMapping.containsKey(input.getWord())) {
                ArrayList words = new ArrayList<>();
                words.add(input);
                wordCountMapping.put(input.getWord(), words);
            } else {
                wordCountMapping.get(input.getWord()).add(input);
            }
        }
        return wordCountMapping;
    }
}
