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
        double loan = 50000;
        double percent = 10;
        int loanTerm = 12;
        int completedPayments = 0;

        LoanCalculator loanCalculator = Mockito.mock(LoanCalculator.class);
        Mockito.when(loanCalculator.calcLoanPayment(loan, percent, loanTerm, completedPayments, typeLoanPayment))
                .thenReturn(expected);

        double actual = new LoanCalculator().calcLoanPayment(loan, percent, loanTerm, completedPayments, typeLoanPayment);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideArgumentsForCalcLoanPayment() {
        return Stream.of(Arguments.of(annLoanPayment, 4583.333333333334), Arguments.of(diffLoanPayment, 45833.33333333333));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalcTotalAmount")
    public void calcTotalLoanAmountTest(LoanPayment typeLoanPayment, double expected) {
        double loan = 50000;
        double percent = 10;

        LoanCalculator loanCalculator = Mockito.mock(LoanCalculator.class);
        Mockito.when(loanCalculator.calcTotalLoanAmount(loan, percent, typeLoanPayment))
                .thenReturn(expected);

        double actual = new LoanCalculator().calcTotalLoanAmount(loan, percent, typeLoanPayment);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideArgumentsForCalcTotalAmount() {
        return Stream.of(Arguments.of(annLoanPayment, 55000), Arguments.of(diffLoanPayment, 55002));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalcOverpayment")
    public void calcOverpayment(LoanPayment typeLoanPayment, double expected) {
        double loan = 50000;
        double percent = 10;

        LoanCalculator loanCalculator = Mockito.mock(LoanCalculator.class);
        Mockito.when(loanCalculator.calcOverpayment(loan, percent, typeLoanPayment))
                .thenReturn(expected);

        double actual = new LoanCalculator().calcOverpayment(loan, percent, typeLoanPayment);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> provideArgumentsForCalcOverpayment() {
        return Stream.of(Arguments.of(annLoanPayment, 5000), Arguments.of(diffLoanPayment, 5002));
    }
}
