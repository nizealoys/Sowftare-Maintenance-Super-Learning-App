package src.core.view.states;

import java.util.ArrayList;
import javax.swing.*;
import src.core.model.Composite;
import src.core.model.Feature;
import src.core.model.Iterator;
import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.course.CourseContent;
import src.feature.course.subfeature.method.Methode;

public class CourseState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;

    public CourseState(Viewer viewer) {
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public Methode getCourseData(String courseName) {
        //Code généré par IA
        Iterator<Feature> it = Composite.getInstance().createIterator();
        CourseContent course = null;
        Methode methode = null;

        while (it.hasNext()) {
            Feature f = it.next();
            if (f instanceof Methode m) {
                methode = m;
            }
            if (f instanceof CourseContent c && c.getName().equalsIgnoreCase(courseName)) {
                course = c;
            }
        }

        if (methode == null || course == null) {
            System.err.println("Cours introuvable : " + courseName);
            return null;
        }
        methode.setCourseData(course);
        return methode;
    }

    @Override
    public void build() {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });

        this.buttonTemps.add(main);

        JButton cookingbutton = new JButton("Cooking");
        cookingbutton.addActionListener((actionEvent) -> {
            //viewer.getState(Viewer.Page.METHOD, MethodState.class).setData(getCourseData("Cooking"));
            viewer.getState(Viewer.Page.METHOD, MethodState.class).setName("Cooking");
            viewer.goTo(Viewer.Page.METHOD);
        });
        this.buttonTemps.add(cookingbutton);

        JButton englishbutton = new JButton("English");
        englishbutton.addActionListener((actionEvent) -> {
            viewer.getState(Viewer.Page.METHOD, MethodState.class).setData(getCourseData("English"));
            viewer.getState(Viewer.Page.METHOD, MethodState.class).setName("English");
            viewer.goTo(Viewer.Page.METHOD);
        });
        this.buttonTemps.add(englishbutton);

        JButton frenchbutton = new JButton("French");
        frenchbutton.addActionListener((actionEvent) -> {
            viewer.getState(Viewer.Page.METHOD, MethodState.class).setData(getCourseData("French"));
            viewer.getState(Viewer.Page.METHOD, MethodState.class).setName("French");
            viewer.goTo(Viewer.Page.METHOD);
        });
        this.buttonTemps.add(frenchbutton);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.PROFILE);
        });
        this.buttonTemps.add(profilbutton);
    }

    /**
     * public Methode getContent(String Course){
     * this.viewer.model.getComposite().createIterator(); }
     */
    @Override
    public void handleRequest() {
        for (JButton b : this.buttonTemps) {
            this.panel.add(b);
        }
        this.frame.repaint();
        this.frame.revalidate();
    }
}
