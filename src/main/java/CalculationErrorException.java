public class CalculationErrorException extends Exception{

    private static final String CALCULATION_ERROR = "Calculation Error.";

    public CalculationErrorException(){
        super(CALCULATION_ERROR);
    }
}
