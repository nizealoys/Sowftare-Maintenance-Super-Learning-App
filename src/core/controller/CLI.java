package src.core.controller;

import java.util.Arrays;
import java.util.Scanner;

public class CLI {

    private final Controller controller;

    public CLI() {
        controller = Controller.getInstance();
    }

    public String instructions(int i) {

        System.out.print("\n List of Instructions: \n");

        String instruction = "";
        instruction += "ENABLE: SHOWS ALL UI VIEW OF THE USERS (IF THERE IS NOT USER, ENABLE WILL CREATE ONE) \n";
        instruction += "DISABLE: HIDE ALL UI VIEW OF THE USERS \n";
        instruction += "MODEL: SHOW THE CURRENT USER MODEL \n";
        instruction += "STOP: SHUT DOWN THE CONSOLE \n";
        instruction += "LOG: PRINT THE LOG \n";

        return instruction;
    }

    public void command() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print(instructions(0));
            String line = in.nextLine();
            switch (line) {
                case "ENABLE" -> {
                    controller.enableUIView();
                    System.out.print("New View enable \n");
                }
                case "DISABLE" -> {
                    controller.disableUIView();
                    System.out.print("View disable \n");
                }

                case "MODEL" ->
                    System.out.print(controller.getComposite().toString() + '\n');

                case "STOP" -> {
                    in.close();
                    System.exit(0);
                }

                case "LOG" ->
                    System.out.print(Arrays.toString(controller.getStateAsLog()));

                default ->
                    System.out.println("Unknown command " + line);

            }
        }
    }

    public Controller getController() {
        return controller;
    }
}
