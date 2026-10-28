package src;

import java.io.FileNotFoundException;
import src.core.controller.Controller;
import src.core.model.Composite;
import src.core.model.CompositeBuilder;
import src.feature.course.subfeature.type.language.English;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        CompositeBuilder compositeBuilder = CompositeBuilder.getInstance();
        Composite composite = compositeBuilder.getComposite();
        English english = (English) composite.getFeature("English");
        System.out.println("English is active: " + english.isActive());
        Controller cli = new Controller();
        String[] desactivateArgs = new String[0];
        String[] activateArgs = new String[]{"SuperLearningApp", "Premium", "Method", "Course", "Type", "Language", "English", "French"};
        cli.activate(desactivateArgs, activateArgs);

        cli.command();
    }
}
