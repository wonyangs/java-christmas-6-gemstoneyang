package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.ControllerFactory;
import christmas.controller.PlannerController;

public class Application {
    public static void main(String[] args) {
        PlannerController controller = ControllerFactory.createPlannerController();

        controller.run();
        Console.close();
    }
}
