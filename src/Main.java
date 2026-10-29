package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.core.controller.CLI;

public class Main {

    public static void main(String[] args) throws IOException {
        CLI cli = new CLI();
        String[] desactivateArgs = new String[0];
        String[] activateArgs = new String[]{"SuperLearningApp", "Method", "Course", "Type", "Language"};
        cli.getController().activate(desactivateArgs, activateArgs);

        cli.command();   // lance la boucle console
    }
}
