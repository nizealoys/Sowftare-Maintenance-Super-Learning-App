package src.core.view.states;

import java.util.ArrayList;
import javax.swing.*;
import src.core.model.Feature;
import src.core.model.Iterator;
import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.course.subfeature.method.Methode;
import src.feature.course.subfeature.type.language.English;

public class MethodState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;
    English english = English.getInstance();

    public MethodState(Viewer viewer) {
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    @Override
    public void build() {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });

        this.buttonTemps.add(main);

        this.buttonTemps.add(new JButton("Workshop"));

        JButton exercices = new JButton("Exercices");
        exercices.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.EXERCICES);
        });
        this.buttonTemps.add(exercices);

        JButton lessons = new JButton("Lessons");
        exercices.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.LESSONS);
            Iterator<Feature> it = viewer.model.getComposite().createIterator();
            while (it.hasNext()) {
                Feature f = it.next();
                if (f instanceof Methode methode) {
                    methode.setCourseData(english);
                    break;
                }
            }
        });
        this.buttonTemps.add(lessons);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.PROFILE);
        });
        this.buttonTemps.add(profilbutton);
    }

    @Override
    public void handleRequest() {
        for (JButton b : this.buttonTemps) {
            this.panel.add(b);
        }
        this.frame.repaint();
        this.frame.revalidate();
    }
}
