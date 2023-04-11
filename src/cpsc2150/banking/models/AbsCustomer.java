package cpsc2150.banking.models;

/**
 * This class uses toString() override to provide a string representation for customer objects.
 * @author Connor Love
 * @since 4/5/2023
 */
public abstract class AbsCustomer implements ICustomer {

    // Every customer will have an IMortgage Loan
    // Protected so the child classes can access it
    protected IMortgage loan;

    /**
     * This method overrides toString() to provide a string
     * representation for customer objects.
     *
     * @return a string representation of the customer including loan details
     *
     * @post toString = [ A string representation of the customer ]
     */
    @Override
    public String toString() {
        String str = "";
        str += "Name: " + getName() + "\n";
        str += "Income: $" + getIncome() + "\n";
        str += "Credit Score: " + getCreditScore() + "\n";
        str += "Monthly Debt: $" + getMonthlyDebtPayments() + "\n";
        str += "Mortgage info:";
        if (appliedForLoan()) {
            str += loan.toString();
        }
        return str;
    }
}
