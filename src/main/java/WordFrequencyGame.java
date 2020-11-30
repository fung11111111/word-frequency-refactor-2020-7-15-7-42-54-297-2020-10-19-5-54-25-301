import java.util.*;

public class WordFrequencyGame {

    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final int COUNT_ONE_WORD = 1;
    private static final String WORD_FREQUENCY_RESULT_PLUS_1 = " 1";

    public String getResult(String sentence) {

        if (sentence.split(WHITE_SPACE_REGEX).length == COUNT_ONE_WORD) {
            return sentence + WORD_FREQUENCY_RESULT_PLUS_1;
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(WHITE_SPACE_REGEX);

                List<WordFrequency> inputList = new ArrayList<>();
                for (String word : words) {
                    WordFrequency input = new WordFrequency(word, COUNT_ONE_WORD);
                    inputList.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> map = getListMap(inputList);

                List<WordFrequency> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : map.entrySet()) {
                    WordFrequency input = new WordFrequency(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((firstWord, secondWord) -> secondWord.getCount() - firstWord.getCount());

                StringJoiner wordFrequencyResultJoiner = new StringJoiner("\n");
                for (WordFrequency w : inputList) {
                    String s = w.getWord() + " " + w.getCount();
                    wordFrequencyResultJoiner.add(s);
                }
                return wordFrequencyResultJoiner.toString();
            } catch (Exception exception) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> inputList) {
        Map<String, List<WordFrequency>> wordCountMapping = new HashMap<>();
        for (WordFrequency input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
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
