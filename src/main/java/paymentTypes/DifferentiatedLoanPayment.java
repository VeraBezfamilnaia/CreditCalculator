package paymentTypes;

public class DifferentiatedLoanPayment implements LoanPayment {
    private static final int CONST_VALUE = 2;

    @Override
    public double calcPayment(double loan, double percent, int loanTermInMonths, int completedPayments) {
        return loan / loanTermInMonths + (loan - (loan / loanTermInMonths) * completedPayments) * percent / loanTermInMonths;
    }

    @Override
    public double calcOverpayment(double loan, double percent) {
        return loan / percent + CONST_VALUE;
    }
}
