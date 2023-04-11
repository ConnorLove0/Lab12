package cpsc2150.banking.models;
import java.util.*;
import java.lang.*;

/**
 * This class calculates the APR
 * @author Connor Love
 * @since 3/3/2023
 *
 * @invariant BASERATE <= APR
 *
 * @Correspondence IMortgage interface provides the constant comparison values
 *              and method declarations for the implementation to determine
 *              the correct APR/Rate depending on the time period.
 */

public class Mortgage extends AbsMortgage implements IMortgage {
    //Annual Percentage Rate
    private double APR;
    //Payment: R - The monthly payment on the mortgage
    private double Payment;
    //Rate: R - the interest rate per monthly period on the loan
    private double Rate;
    //Customer: - Every loan must have an associated customer
    ICustomer Cus;
    //DebtToIncomeRatio: R - The ratio of the customer's total monthly debt payments (including mortgage) to their monthly income
    private double DebtToIncomeRatio;
    //Principal: R - The amount of the loan
    private double Principal;
    //NumberOfPayments: Z - total number of payments the customer will make on the loan
    private int NumberOfPayments;
    //PercentDown : R - The percent of the house cost covered by the down payment
    private double PercentDown;
    /**
     * This constructor takes in the values of the total cost of the Home, the down payment on the loan,
     * the number of years they will take to repay the loan and the customer, and updates the customer name, APR,
     * Principal, PercentDown, NumberOfPayments, Rate, Payment and DebtToIncomeRatio according to the approved loan
     * agreement parameters
     *
     * @pre costOfHome = #costOfHome AND downPayment = #downPayment AND numOfYears = #numOfYears AND customer = #customer
     *
     * @param costOfHome The total cost of the Home
     * @param downPayment The down payment on the loan
     * @param numOfYears The number of years they will take to repay the loan
     * @param customer The customer associated with the loan
     *
     * @post
     *      Cus = #customer AND APR = #APR AND Principal = #Principal AND PercentDown = #PercentDown AND
     *      NumberOfPayments = #NumberOfPayments AND Rate = #Rate AND Payment = #Payment
     *      AND DebtToIncomeRatio = #DebtToIncomeRatio
     */
    public Mortgage(double costOfHome, double downPayment, int numOfYears, ICustomer customer) {
        //Sets Customer Cus Object = new Customer customer
        Cus = customer;
        //Sets APR = BASERATE (.025)
        APR = BASERATE;
        //Sets Principal equal to the CostOfHome - downPayment
        Principal = costOfHome - downPayment;
        //Sets PercentDown equal to downPayment divided by costOfHome
        PercentDown = (downPayment / costOfHome);
        //Sets NumberOfPayments equal to numOfYears * MONTHS_IN_YEAR (12)
        NumberOfPayments = numOfYears * MONTHS_IN_YEAR;

        //if numOfYears is less than 30
        if (numOfYears < MAX_YEARS) {
            //Add 0.5% to the APR
            APR += GOODRATEADD;
        }
        //else if numOfYears is greater than or equal to 30
        else {
            //Add 1% to the APR
            APR += NORMALRATEADD;
        }

        //if PercentDown is less than 20%
        if (PercentDown < PREFERRED_PERCENT_DOWN) {
            //Add 0.5% to the APR
            APR += GOODRATEADD;
        }
        else {
            APR += 0;
        }

        //if customer's credit score is less than 500
        if (Cus.getCreditScore() < BADCREDIT) {
            //Add 10% to the APR
            APR += VERYBADRATEADD;
        }
        //else if customer's credit score is greater than 500 but less than 600
        else if ((Cus.getCreditScore() >= BADCREDIT) && (Cus.getCreditScore() < FAIRCREDIT)) {
            //Add 5% to the APR
            APR += BADRATEADD;
        }
        //else if customer's credit score is greater than 600 but less than 700
        else if ((Cus.getCreditScore() >= FAIRCREDIT) && (Cus.getCreditScore() < GOODCREDIT)) {
            //Add 1% to the APR
            APR += NORMALRATEADD;
        }
        //else if customer's credit score is greater than 700 but less than 750
        else if ((Cus.getCreditScore() >= GOODCREDIT) && (Cus.getCreditScore() < GREATCREDIT)) {
            //Add 0.5% to the APR
            APR += GOODRATEADD;
        }
        //else, if customer's credit score is greater than 750 but less than 850
        else if ((Cus.getCreditScore() >= GREATCREDIT) && (Cus.getCreditScore() <= Cus.MAX_CREDIT_SCORE)) {
            //Add 0% to the APR
            APR += 0;
        }
        //Updates Rate using the updated APR
        Rate = APR / MONTHS_IN_YEAR;
        //Sets Payment value using the payment formula with updated parameters
        Payment = (Rate * Principal) / (1 - Math.pow((1 + Rate), -(NumberOfPayments)));
        //Sets The Debt to income ratio = debt for the month or year/ Gross income for the month or year
        DebtToIncomeRatio = ((Payment + Cus.getMonthlyDebtPayments()) / (Cus.getIncome() / MONTHS_IN_YEAR));
    }

    /**
     * This method checks to see if the mortgage loan has been approved or not.
     *
     * @return true if the loan is approved, false otherwise.
     *
     * @post loanApproved iff (Rate*12 < RATE_TOO_HIGH AND PercentDown >= MIN_PERCENT_DOWN AND DebtToIncomeRatio <= DTOITOOHIGH) AND
     *          Payment = #Payment AND Rate = #Rate AND Customer = #Customer AND DebtToIncomeRatio = #DebtToIncomeRatio AND
     *          Principal = #Principal AND NumberOfPayments = #NumberOfPayments AND PercentDown = #PercentDown
     */
    public boolean loanApproved() {
        boolean isApproved = false;
        if (((APR) < RATETOOHIGH) && ((PercentDown) >= (MIN_PERCENT_DOWN)) && ((DebtToIncomeRatio) <= (DTOITOOHIGH))) {
            isApproved = true;
        }
        return isApproved;
    }
    /**
     * This method returns the amount that must be paid per month for the mortgage loan.
     *
     * @return the monthly payment on the loan
     *
     * @post getPayment = Payment AND
     *          Payment = #Payment AND Rate = #Rate AND Customer = #Customer AND DebtToIncomeRatio = #DebtToIncomeRatio AND
     *          Principal = #Principal AND NumberOfPayments = #NumberOfPayments AND PercentDown = #PercentDown
     */
    public double getPayment() {
        return Payment;
    }

    /**
     * This method returns the interest rate (APR).
     *
     * @return the interest rate (APR) for this customer
     *
     * @post getRate = Rate * 12 AND
     *          Payment = #Payment AND Rate = #Rate AND Customer = #Customer AND DebtToIncomeRatio = #DebtToIncomeRatio AND
     *          Principal = #Principal AND NumberOfPayments = #NumberOfPayments AND PercentDown = #PercentDown
     */
    public double getRate(){
        return (Rate * MONTHS_IN_YEAR);
    }

    /**
     * This method returns the customer's principal amount for the mortgage loan.
     *
     * @return the principal amount of the loan
     *
     * @post getPrincipal = Principal AND
     *          Payment = #Payment AND Rate = #Rate AND Customer = #Customer AND DebtToIncomeRatio = #DebtToIncomeRatio AND
     *          Principal = #Principal AND NumberOfPayments = #NumberOfPayments AND PercentDown = #PercentDown
     */
    public double getPrincipal(){
        return Principal;
    }

    /**
     * This method returns the amount of years the customer applied the mortgage loan for.
     *
     * @return the number of years the loan is for
     *
     * @post getYears = Years AND
     *          Payment = #Payment AND Rate = #Rate AND Customer = #Customer AND DebtToIncomeRatio = #DebtToIncomeRatio AND
     *          Principal = #Principal AND NumberOfPayments = #NumberOfPayments AND PercentDown = #PercentDown
     */
    public int getYears() {
        return (NumberOfPayments / MONTHS_IN_YEAR);
    }
}
