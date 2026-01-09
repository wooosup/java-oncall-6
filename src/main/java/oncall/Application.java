package oncall;

import oncall.controller.OncallController;

public class Application {
    public static void main(String[] args) {

        OncallController controller = new OncallController();
        controller.run();
    }
}
