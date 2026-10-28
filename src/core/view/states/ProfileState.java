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
import src.feature.premium.Premium;

public class ProfileState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    JLabel premiumLabel = new JLabel();
    ArrayList<JButton> buttonTemps;

    public ProfileState(Viewer viewer) {
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public Boolean getPremiumState() {
        //Code généré par IA
        Iterator<Feature> it = Composite.getInstance().createIterator();
        Premium premium = null;

        while (it.hasNext()) {
            Feature f = it.next();
            if (f instanceof Premium p) {
                premium = p;
            }
        }

        if (premium == null) {
            System.err.println("premium introuvable");
            return null;
        }
        System.out.print(premium.isActive());
        return premium.isActive();
    }

    public void premiumPanel(){
        this.premiumLabel.setText("Premium: " + this.getPremiumState());
        System.out.print("je suis passé: " + this.getPremiumState());
    }

    @Override
    public void build() {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });

        this.buttonTemps.add(main);


    }

    @Override
    public void handleRequest() {
        for (JButton b : this.buttonTemps) {
            this.panel.add(b);
        }
        this.panel.add(this.premiumLabel);
        this.frame.repaint();
        this.frame.revalidate();
    }
}
