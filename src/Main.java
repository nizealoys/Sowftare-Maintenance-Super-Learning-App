package src;

import java.io.FileNotFoundException;
import src.core.controller.Controller;
import src.core.controller.CLI;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        CLI cli = new CLI();
        String[] desactivateArgs = new String[0];
        String[] activateArgs = new String[]{"SuperLearningApp", "Method", "Course", "Type", "Language"};
        cli.getController().activate(desactivateArgs, activateArgs);

        cli.command();
    }
}
