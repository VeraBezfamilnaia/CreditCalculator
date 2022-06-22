import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

public class LoanCalculatorTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForCalcLoanPayment")
    public void calcLoanPaymentTest(boolean isAnnuityLoanPayment, double expected) {
        double loan = 5000;
        double percent = 10;
        int loanTerm = 5;

        LoanCalculator loanCalculator = Mockito.mock(LoanCalculator.class);
        Mockito.when(loanCalculator.calcLoanPayment(loan, percent, loanTerm, loan, isAnnuityLoanPayment))
                .thenReturn(expected);

        double actual = new LoanCalculator().calcLoanPayment(loan, percent, loanTerm, loan, isAnnuityLoanPayment);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideArgumentsForCalcLoanPayment() {
        return Stream.of(Arguments.of(true, 1025.14), Arguments.of(false, 1041.10));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalcTotalAmount")
    public void calcTotalLoanAmountTest(boolean isAnnuityLoanPayment, double expected) {
        double loan = 5000;
        double percent = 10;

        LoanCalculator loanCalculator = Mockito.mock(LoanCalculator.class);
        Mockito.when(loanCalculator.calcTotalLoanAmount(loan, percent, isAnnuityLoanPayment))
                .thenReturn(expected);

        double actual = new LoanCalculator().calcTotalLoanAmount(loan, percent, isAnnuityLoanPayment);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideArgumentsForCalcTotalAmount() {
        return Stream.of(Arguments.of(true, 5126), Arguments.of(false, 5125));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalcOverpayment")
    public void calcOverpayment(boolean isAnnuityLoanPayment, double expected) {
        double loan = 5000;
        double percent = 10;

        LoanCalculator loanCalculator = Mockito.mock(LoanCalculator.class);
        Mockito.when(loanCalculator.calcOverpayment(loan, percent, isAnnuityLoanPayment))
                .thenReturn(expected);

        double actual = new LoanCalculator().calcOverpayment(loan, percent, isAnnuityLoanPayment);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideArgumentsForCalcOverpayment() {
        return Stream.of(Arguments.of(true, 126), Arguments.of(false, 125));
    }
}
