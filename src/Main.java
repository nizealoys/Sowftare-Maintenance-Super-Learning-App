package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.core.controller.CLI;
import src.core.model.CompositeBuilder;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        CompositeBuilder builder = CompositeBuilder.getInstance();
        CLI cli = new CLI();
        // French french = French.getInstance();

        // System.out.println(french.getQAList());
            try{
                cli.command();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
}
