package src;

import java.io.FileNotFoundException;
import src.core.controller.CLI;
import src.core.model.CompositeBuilder;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        CompositeBuilder builder = CompositeBuilder.getInstance();
        CLI cli = new CLI();
        // French french = French.getInstance();

        // System.out.println(french.getQAList());
        cli.command();   // lance la boucle console
    }
}
