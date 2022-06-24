package paymentTypes;

public interface LoanPayment {
    double calcPayment(double loan, double percent, int loanTermInMonths, int completedPayments);

    default double calcTotalLoanAmount(double loan, double percent) {
        return loan + calcOverpayment(loan, percent);
    }

    double calcOverpayment(double loan, double percent);
}
