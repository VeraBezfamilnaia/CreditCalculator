import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import paymentTypes.AnnuityLoanPayment;
import paymentTypes.DifferentiatedLoanPayment;
import paymentTypes.LoanPayment;

import java.util.stream.Stream;

public class LoanCalculatorTest {
    private static final LoanPayment annLoanPayment = new AnnuityLoanPayment();
    private static final LoanPayment diffLoanPayment = new DifferentiatedLoanPayment();

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalcLoanPayment")
    public void calcLoanPaymentTest(LoanPayment typeLoanPayment, double expected) {
        double loan = 5000;
        double percent = 10;
        int loanTerm = 5;

        LoanCalculator loanCalculator = Mockito.mock(LoanCalculator.class);
        Mockito.when(loanCalculator.calcLoanPayment(loan, percent, loanTerm, loan, typeLoanPayment))
                .thenReturn(expected);

        double actual = new LoanCalculator().calcLoanPayment(loan, percent, loanTerm, loan, typeLoanPayment);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideArgumentsForCalcLoanPayment() {
        return Stream.of(Arguments.of(annLoanPayment, 1025.14), Arguments.of(diffLoanPayment, 1041.10));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalcTotalAmount")
    public void calcTotalLoanAmountTest(LoanPayment typeLoanPayment, double expected) {
        double loan = 5000;
        double percent = 10;

        LoanCalculator loanCalculator = Mockito.mock(LoanCalculator.class);
        Mockito.when(loanCalculator.calcTotalLoanAmount(loan, percent, typeLoanPayment))
                .thenReturn(expected);

        double actual = new LoanCalculator().calcTotalLoanAmount(loan, percent, typeLoanPayment);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideArgumentsForCalcTotalAmount() {
        return Stream.of(Arguments.of(annLoanPayment, 5126), Arguments.of(diffLoanPayment, 5125));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalcOverpayment")
    public void calcOverpayment(LoanPayment typeLoanPayment, double expected) {
        double loan = 5000;
        double percent = 10;

        LoanCalculator loanCalculator = Mockito.mock(LoanCalculator.class);
        Mockito.when(loanCalculator.calcOverpayment(loan, percent, typeLoanPayment))
                .thenReturn(expected);

        double actual = new LoanCalculator().calcOverpayment(loan, percent, typeLoanPayment);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideArgumentsForCalcOverpayment() {
        return Stream.of(Arguments.of(annLoanPayment, 126), Arguments.of(diffLoanPayment, 125));
    }
}
