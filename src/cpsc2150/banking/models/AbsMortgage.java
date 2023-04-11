
package cpsc2150.banking.models;

/**
 * This class overrides toString() to provide a string representation for mortgage objects.
 * @author Connor Love
 * @since 4/5/2023
 */
public abstract class AbsMortgage implements IMortgage {

    /**
     * This method overrides toString() to provide a string
     * representation for mortgage objects.
     *
     * @pre [interestRate and payment have been calculated]
     * @post toString = [ The string value of the loan, or "Loan was not approved" ]
     */
    @Override
    public String toString() {
        String str = "";
        if (loanApproved()) {
            str += "Principal Amount: $" + getPrincipal() + "\n";
            str += "Interest Rate: " + (getRate() * 100) + "%\n";
            str += "Term: " + getYears() + " years\n";
            str += "Monthly Payment: $" + getPayment() + "\n";
        } else {
            str += "Loan was not approved\n";
        }

        return str;
    }
}
