import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class WordFrequencyGameTest {

    @Test
    public void should_get_the_1_when_input_the() throws Exception, CalculationErrorException {
        //Given
        String sentence = "the";
        String expectResult = "the 1";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_words() throws Exception, CalculationErrorException {
        //Given
        String sentence = "the is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_spaces() throws Exception, CalculationErrorException {
        //Given
        String sentence = "the      is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_enter() throws Exception, CalculationErrorException {
        //Given
        String sentence = "the   \n   is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_same_words_with_sorted() throws Exception, CalculationErrorException {
        //Given
        String sentence = "the the is";
        String expectResult = "the 2\nis 1";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_sorted_with_count_descending() throws Exception {
        //Given
        String sentence = "the is is";
        String expectResult = "is 2\nthe 1";
    }

    @Test
    public void should_throw_calculation_error_exception_given_null_sentence() {
        //Given
        String sentence = null;

        WordFrequencyGame game = new WordFrequencyGame();

        //When
        CalculationErrorException calculationErrorException = assertThrows(CalculationErrorException.class, () -> {
            game.getResult(sentence);
        });

        //Then
        assertEquals("Calculation Error.", calculationErrorException.getLocalizedMessage());
    }

    private void validate_Input_words_process_to_expected_word(String inputStr, String expectResult) throws CalculationErrorException {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String result = game.getResult(inputStr);
        //Then  result
        assertEquals(expectResult, result);
    }
}
