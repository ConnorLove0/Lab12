package cpsc2150.banking;

import cpsc2150.banking.controllers.*;
import cpsc2150.banking.views.*;

/**
 * This class holds main to run the program.
 * @author Connor Love
 * @since 4/5/2023
 */
public class MortgageApp {
    public static void main(String [] args) {
        IMortgageView view = new MortgageView();
        IMortgageController controller = new MortgageController(view);
        view.setController(controller);
        controller.submitApplication();
    }
}
