package paymentTypes;

public class AnnuityLoanPayment implements LoanPayment {
    @Override
    public double calcPayment(double loan, double percent, int loanTermInMonths, int completedPayments) {
        return loan / loanTermInMonths + calcOverpayment(loan, percent) / loanTermInMonths;
    }

    @Override
    public double calcOverpayment(double loan, double percent) {
        return loan / percent;
    }
}
