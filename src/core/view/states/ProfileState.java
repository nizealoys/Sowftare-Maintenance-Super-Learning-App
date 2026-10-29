package src.core.view.states;

import java.awt.*;
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
        Iterator<Feature> it = Composite.getInstance().createIterator();
        Premium premium = null;
        Achievement achievement = null;

        while (it.hasNext()) {
            Feature f = it.next();
            switch (f) {
                case Premium p ->
                    premium = p;
                case Achievement a ->
                    achievement = a;
                default -> {
                }
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

    public JPanel infoPanel() {

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(new JLabel("Premium: " + this.premiumActive));

        content.add(new JLabel("Achievement: "));
        for (AchievementElement ae : this.achievements) {
            if(ae.isAchieved()){
                JLabel ach = new JLabel(ae.getName() + " : " + ae.getDescription() + " : " + "ACHIEVED");
                content.add(ach);
            }
            else{
                JLabel ach = new JLabel(ae.getName() + " : " + ae.getDescription() + " : " + "NOT ACHIEVED");
                ach.setForeground(Color.GRAY);
                content.add(ach);
            }
        }
        return content;
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

        JPanel content = infoPanel();

        JPanel buttons = new JPanel();
        for (JButton b : this.buttonTemps) {
            buttons.add(b);
        }

        JPanel root = new JPanel(new BorderLayout());
        root.add(buttons, BorderLayout.NORTH);  // boutons en haut
        root.add(content, BorderLayout.CENTER); // infos en dessous

        this.panel.add(root);
        this.frame.repaint();
        this.frame.revalidate();
    }
}
