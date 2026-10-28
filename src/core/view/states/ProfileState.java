package src.core.view.states;

import java.util.ArrayList;
import javax.swing.*;
import src.core.model.Composite;
import src.core.model.Feature;
import src.core.model.Iterator;
import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.achievement.Achievement;
import src.feature.achievement.AchievementElement;
import src.feature.premium.Premium;

public class ProfileState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    JLabel premiumLabel = new JLabel();
    ArrayList<JButton> buttonTemps;
    Boolean premiumActive;
    ArrayList<AchievementElement> achievements = new ArrayList<>();

    public ProfileState(Viewer viewer) {
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public void getData() {
        //Code généré par IA
        Iterator<Feature> it = Composite.getInstance().createIterator();
        Premium premium = null;
        Achievement achievement = null;

        while (it.hasNext()) {
            Feature f = it.next();
            if (f instanceof Premium p) {
                premium = p;
            } else if (f instanceof Achievement a) {
                achievement = a;
            }
        }

        if (premium == null) {
            System.err.println("premium introuvable");
            return;
        }
        this.premiumActive = premium.isActive();

        if (achievement == null) {
            System.err.println("achievement introuvable");
            return;
        }
        this.achievements = achievement.getAchievementElements();
    }

    public void premiumPanel() {
        String text = new String("Premium: " + this.premiumActive + "\n              ");

        for (AchievementElement ae : this.achievements) {
            text += ae.getName() + ": " + ae.getDescription() + ": " + ae.isAchieved() + "\n           ";
        }
        this.premiumLabel.setText(text);
    }

    private void build() {

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
