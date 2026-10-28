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

    public void getCourseAvailable() {
        //Code généré par IA
        Iterator<Feature> it = Composite.getInstance().createIterator();
        Course course = null;

        while (it.hasNext()) {
            Feature f = it.next();
            if (f instanceof Course c) {
                course = c;
            }
        }

        if (course == null) {
            System.err.println("Course introuvable " );
        }
        this.availableCourse = course.getCourseList();
        this.courseSlot = course.getCourseSlot();
        System.out.print(this.availableCourse + "\n");
        System.out.print(this.courseSlot + "\n");
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
            if(this.availableCourse.size() < this.courseSlot || this.availableCourse.contains(viewer.controller.getComposite().getFeature("English"))) {
                viewer.getState(Viewer.Page.METHOD, MethodState.class).setData(getCourseData("English"));
                viewer.getState(Viewer.Page.METHOD, MethodState.class).setName("English");
                this.availableCourse.add(viewer.controller.getComposite().getFeature("English"));
                String[] activation = new String[]{"English"};
                String[] desactivateArgs = new String[0];
                viewer.controller.activate(desactivateArgs, activation);
                this.courseSlot++;
                viewer.goTo(Viewer.Page.METHOD);
            }else {
                JOptionPane.showMessageDialog(
                        this.frame,
                        "Vous avez atteint le nombre maximum de cours.",
                        "Limite atteinte",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
        this.buttonTemps.add(englishbutton);

        JButton frenchbutton = new JButton("French");
        frenchbutton.addActionListener((actionEvent) -> {
            if(this.availableCourse.size() < this.courseSlot || this.availableCourse.contains(viewer.controller.getComposite().getFeature("French"))){
                viewer.getState(Viewer.Page.METHOD, MethodState.class).setData(getCourseData("French"));
                viewer.getState(Viewer.Page.METHOD, MethodState.class).setName("French");
                String[] activation = new String[]{"French"};
                String[] desactivateArgs = new String[0];
                viewer.controller.activate(desactivateArgs, activation);
                this.availableCourse.add(viewer.controller.getComposite().getFeature("French"));
                this.courseSlot++;
                viewer.goTo(Viewer.Page.METHOD);
            }else{
                JOptionPane.showMessageDialog(
                        this.frame,
                        "Vous avez atteint le nombre maximum de cours.",
                        "Limite atteinte",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
        this.buttonTemps.add(frenchbutton);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).getPremiumState();
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).premiumPanel();
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
