package src.core.view.states;

import java.util.ArrayList;
import javax.swing.*;
import src.core.model.Composite;
import src.core.model.Feature;
import src.core.model.Iterator;
import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.course.Course;
import src.feature.course.CourseContent;
import src.feature.course.subfeature.method.Methode;

public class CourseState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;
    ArrayList<Feature> availableCourse;
    int courseSlot = 0;

    public CourseState(Viewer viewer) {
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public Methode getCourseData(String courseName) {
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

    public void getCourseAvailable() {
        Iterator<Feature> it = Composite.getInstance().createIterator();
        Course course = null;

        while (it.hasNext()) {
            Feature f = it.next();
            if (f instanceof Course c) {
                course = c;
            }
        }

        if (course == null) {
            System.err.println("Course introuvable ");
            return;
        }
        this.availableCourse = course.getCourseList();
        this.courseSlot = course.getCourseSlot();
        System.out.print(this.availableCourse + "\n");
        System.out.print(this.courseSlot + "\n");
    }

    public void accessToCourse(String courseName) {
        if (this.availableCourse.size() < this.courseSlot || this.availableCourse.contains(viewer.controller.getComposite().getFeature(courseName))) {
            if (!this.availableCourse.contains(viewer.controller.getComposite().getFeature(courseName))) {
                String[] activation = new String[]{courseName};
                String[] desactivateArgs = new String[0];
                viewer.controller.activate(desactivateArgs, activation);
            }
            viewer.getState(Viewer.Page.METHOD, MethodState.class).setData(getCourseData(courseName));
            viewer.getState(Viewer.Page.METHOD, MethodState.class).setName(courseName);
            viewer.goTo(Viewer.Page.METHOD);
        } else {
            JOptionPane.showMessageDialog(
                    this.frame,
                    "Vous avez atteint le nombre maximum de cours.",
                    "Limite atteinte",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void build() {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });

        this.buttonTemps.add(main);

        JButton cookingbutton = new JButton("Cooking");
        cookingbutton.addActionListener((actionEvent) -> {
            accessToCourse("Cooking");
        });
        this.buttonTemps.add(cookingbutton);

        JButton englishbutton = new JButton("English");
        englishbutton.addActionListener((actionEvent) -> {
            accessToCourse("English");
        });
        this.buttonTemps.add(englishbutton);

        JButton frenchbutton = new JButton("French");
        frenchbutton.addActionListener((actionEvent) -> {
            accessToCourse("French");
        });
        this.buttonTemps.add(frenchbutton);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).getData();
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).infoPanel();
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
