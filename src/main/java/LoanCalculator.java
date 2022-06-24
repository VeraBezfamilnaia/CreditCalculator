import paymentTypes.LoanPayment;

public class LoanCalculator {
    public double calcLoanPayment(double loan, double percent, int loanTermInMonths, int completedPayments,
                                  LoanPayment typeLoanPayment) {
        return typeLoanPayment.calcPayment(loan, percent, loanTermInMonths, completedPayments);
    }

    public double calcTotalLoanAmount(double loan, double percent, LoanPayment typeLoanPayment) {
        return typeLoanPayment.calcTotalLoanAmount(loan, percent);
    }

    public double calcOverpayment(double loan, double percent, LoanPayment typeLoanPayment) {
        return typeLoanPayment.calcOverpayment(loan, percent);
    }
}
