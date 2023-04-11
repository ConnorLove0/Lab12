package cpsc2150.banking.controllers;
import cpsc2150.banking.models.*;
import cpsc2150.banking.views.*;

/**
 * This class implements IMortgageController. The controller uses the views to ask the user for input,
 * and passes the information to them to receive back a response. It then uses the views to display the
 * necessary output.
 *
 * @author Connor Love
 * @since 4/5/2023
 *
 * @Defines:
 *      IMortgageView controlView - the view object being used
 */
public class MortgageController implements IMortgageController {
    private IMortgageView controlView;

    /**
     * Constructor that sets the view object to use
     * @pre [The view object has been created]
     * @param view the view object to use
     * @post controlView is set to the view object
     */
    public MortgageController(IMortgageView view) {
        controlView = view;
    }

    /**
     * This method submits the application request. The controller asks for all the
     * information needed, and then sends the information of to the model classes. If any part of the information given is invalid
     * The user will be prompted to enter the information again.
     * @post The application is returned. If approved, The necessary data will be returned,
     * else a message will be outputted, that states the mortgage was not approved.
     */
    @Override
    public void submitApplication() {
        boolean startOver = true;
        boolean doAgain = true;
        String name;
        double yearlyIncome;
        boolean newMortgage;
        boolean newCustomer;

        //While a new customer wants to start a new mortgage application
        while (startOver) {
            doAgain = true;
            //Ask the customer for their name
            controlView.printToUser("What's your name?");
            //Stores the input
            name = controlView.getName();
            //Asks the user for their yearly income
            controlView.printToUser("How much is your yearly income?");
            //Stores the input
            yearlyIncome = controlView.getYearlyIncome();

            //While the customer's yearly income is less than 0
            while (yearlyIncome < 0) {
                //Error message is outputted
                controlView.printToUser("Income must be greater than 0.");
                //Asks again for the customer's yearly income
                controlView.printToUser("How much is your yearly income?");
                //Stores the input
                yearlyIncome = controlView.getYearlyIncome();
            }

            //Monthly Debt
            //Asks the user for their monthly debt payments
            controlView.printToUser("How much are your monthly debt payments?");
            //Stores the input
            double monthlyDebt = controlView.getMonthlyDebt();

            //While the customer's input is less than 0
            while(monthlyDebt < 0) {
                //Error message is outputted.
                controlView.printToUser("Debt must be greater than or equal to 0.");
                //Asks the user again for their monthly debt payments
                controlView.printToUser("How much are your monthly debt payments?");
                //Stores the input
                monthlyDebt = controlView.getMonthlyDebt();
            }

            //CreditScore
            //Aks the user for their credit score
            controlView.printToUser("What is your credit score?");
            //Stores the input
            int creditScore = controlView.getCreditScore();
            //While the user's input is less than 0 or greater than 850
            while (creditScore < 0 || creditScore > 850) {
                //Error message is outputted.
                controlView.printToUser("Credit Score must be greater than 0 and less than 850");
                //Asks the user again for their credit score
                controlView.printToUser("What is your credit score?");
                //Stores the input
                creditScore = controlView.getCreditScore();
            }
            //While the customer wants to apply for a mortgage
            while (doAgain) {
                //House Cost
                //Asks the user for the house cost
                controlView.printToUser("How much does the house cost?");
                //Stores the input
                double houseCost = controlView.getHouseCost();

                //While the user input for the house cost is less than 0
                while (houseCost < 0) {
                    //Error message is outputted.
                    controlView.printToUser("Cost must be greater than 0.");
                    //Asks the user again for the cost of the house
                    controlView.printToUser("How much does the house cost?");
                    //Stores the input
                    houseCost = controlView.getHouseCost();
                }

                //Down Payment
                //Asks the user for the down payment for the house
                controlView.printToUser("How much is the down payment?");
                //Stores the input
                double downPayment = controlView.getDownPayment();

                //While the user's input is less than 0 or greater than the cost of the house.
                while (downPayment < 0 || downPayment >= houseCost) {
                    //Error message is outputted.
                    System.out.println("Down Payment must be greater than 0 and less than the cost of the house.");
                    //Asks the user again for the down payment
                    System.out.println("How much is the down payment?");
                    //Stores the input
                    downPayment = controlView.getDownPayment();
                }
                //Years
                //Ask the user for the number of years to pay off the mortgage
                System.out.println("How many years?");
                //Stores the input
                int years = controlView.getYears();

                //While the user's input for the number of years to pay off the mortgage is less than 0
                while (years < 0) {
                    //Errors message is outputted.
                    System.out.println("Years must be greater than 0.");
                    //Ask the user again for the number of years to pay off the mortgage
                    System.out.println("How many years?");
                    //Stores the input
                    years = controlView.getYears();
                }
                //Sets startOver and doAgain to false
                startOver = false;
                doAgain = false;

                //Output
                //Sets ICustomer object customer to a new customer with the resources given by the user
                ICustomer customer = new Customer(monthlyDebt, yearlyIncome, creditScore, name);
                //Sets IMortgage object mortgage to a new Mortgage with the resources given by the user
                IMortgage mortgage = new Mortgage(houseCost, downPayment, years, customer);
                //Stores the ICustomer object customer into customerOutput in string format
                String customerOutput = customer.toString();
                //Prints the customerOutput to the user
                controlView.printToUser(customerOutput);
                //Stores the IMortgage object Mortgage into mortgageOutput in string format
                String mortgageOutput = mortgage.toString();
                //Prints the mortgageOutput to the user
                controlView.printToUser(mortgageOutput);
                //Ask the user if the would like to apply for another mortgage under the same name
                newMortgage = controlView.getAnotherMortgage();

                //If the customer wants to apply for another mortgage
                if (newMortgage) {
                    //sets doAgain to true
                    doAgain = true;
                }
                //If the customer does not want to apply for another mortgage
                else {
                    //Asks the user if they want to apply for a mortgage as a new customer
                    newCustomer = controlView.getAnotherCustomer();
                    //If the customer wants to apply for another mortgage as a new customer
                    if (newCustomer) {
                        //Sets startOver to true
                        startOver = true;
                    }
                    //Else, the program is exited.
                    else {
                        System.exit(0);
                    }
                }
            }

        }
    }
}
