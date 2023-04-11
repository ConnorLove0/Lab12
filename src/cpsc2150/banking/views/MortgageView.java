
package cpsc2150.banking.views;
import cpsc2150.banking.controllers.*;

import java.util.*;

/**
 * This class displays the Mortgage view using user's input
 * @author Connor Love
 * @since 4/5/2023
 * @Defines
 *      IMortgageController controller - The controller object being used
 */
public class MortgageView implements IMortgageView {
    Scanner sc = new Scanner(System.in);
    IMortgageController controller;
    String input = "";

    /**
     * Mortgage View constructor with no parameters
     */
    public MortgageView(){}

    /**
     * This method sets the controller for the mortgage view
     * @param c the Controller object to set
     * @post the controller is now set
     */
    @Override
    public void setController(IMortgageController c) {
        controller = c;
    }

    /**
     * This method returns the cost of the house.
     * @post The cost of the house is returned.
     * @return The cost of the house
     */
    @Override
    public double getHouseCost() {
        return sc.nextDouble();
    }
    /**
     * This method returns the down payment amount.
     * @post The down payment amount is returned.
     * @return The down payment amount
     */
    @Override
    public double getDownPayment() {
        return sc.nextDouble();
    }
    /**
     * This method returns the total number of years for the mortgage.
     * @post The total number of years for the mortgage is returned.
     * @return The total number of years for the mortgage.
     */
    @Override
    public int getYears() {
        return sc.nextInt();
    }
    /**
     * This method returns the monthly debt amount.
     * @post The monthly debt amount is returned.
     * @return The monthly debt amount.
     */
    @Override
    public double getMonthlyDebt(){
        return sc.nextDouble();
    }

    /**
     * This method returns customer's yearly income.
     * @post The customer's yearly income is returned.
     * @return The customer's yearly income.
     */
    @Override
    public double getYearlyIncome(){
        return sc.nextDouble();
    }

    /**
     * This method returns customer's credit score.
     * @post The customer's credit score is returned.
     * @return The customer's credit score.
     */
    @Override
    public int getCreditScore(){
        return sc.nextInt();
    }

    /**
     * This method prints a message to the user.
     * @param s The message to print to the user.
     * @post The message is printed to the user.
     * @return The message that is to be printed to the user using the command line.
     */
    @Override
    public void printToUser(String s){
        System.out.println(s);
    }

    /**
     * This method displays the monthly payment amount
     * @param p the monthly payment amount for the loan
     * @post The monthly payment amount for the loan is returned
     * @return The monthly payment amount
     */
    @Override
    public void displayPayment(double p){
        System.out.println("Monthly Payment: " + p);
    }

    /**
     * This method displays the monthly payment amount.
     * @param r The (APR) interest rate for the loan.
     * @post The (APR) interest rate for the loan is returned.
     * @return "Interest Rate" + The (APR) interest rate for the loan.
     */
    @Override
    public void displayRate(double r) {
        System.out.println("Interest Rate: " + r);
    }

    /**
     * This method displays whether or not the loan is approved.
     * @param a Whether or not the mortgage application was approved.
     * @post A message is returned indicating whether or not the loan is approved.
     * @return whether or not the loan is approved.
     */
    @Override
    public void displayApproved(boolean a) {
        System.out.println("Approved: " + a);
    }

    /**
     * This method asks the user if they would like to apply for another mortgage.
     * @pre [A mortgage has already been created]
     * @post If true, another mortgage application will be created, else the application will exit
     * @return True, if the customer wants to apply for another loan, else false.
     */
    @Override
    public boolean getAnotherMortgage(){
        boolean anotherMortgage = false;
        System.out.println("Would you like to apply for another mortgage? Y/N");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        char uI = input.charAt(0);

        while(uI != 'Y' && uI != 'y' && uI != 'N' && uI != 'n') {
            System.out.println("Invalid Input: please enter Y/N");
            System.out.println("Would you like to apply for another mortgage? Y/N");
            input = sc.nextLine();
            uI = input.charAt(0);
        }

        if(uI == 'Y' || uI == 'y') {
            anotherMortgage = true;
        }
        return anotherMortgage;
    }

    /**
     * This method asks the user if they would like to apply for another under a different name.
     * @pre [A mortgage has already been created.]
     * @post If true, A new mortgage will be created, else the program will be exited.
     * @return True if the user would like to apply for another under a different name, false otherwise.
     */
    @Override
    public boolean getAnotherCustomer() {
        boolean anotherCustomer = false;
        System.out.println("Would you like to consider another customer? Y/N");
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        char uI = input.charAt(0);

        while(uI != 'Y' && uI != 'y' && uI != 'N' && uI != 'n') {
            System.out.println("Invalid Input: please enter Y/N");
            System.out.println("Would you like to consider another customer? Y/N");
            input = sc.nextLine();
            uI = input.charAt(0);
        }

        if(uI == 'Y' || uI == 'y') {
            anotherCustomer = true;
        }
        return anotherCustomer;
    }

    /**
     * This method returns the name of the customer.
     * @pre [A mortgage has already been created.]
     * @post The name of the customer is returned.
     * @return The name of the customer.
     */
    @Override
    public String getName(){
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().trim();
        return name;
    }
}
