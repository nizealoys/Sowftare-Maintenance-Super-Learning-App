package src.core.view.states;

import java.util.ArrayList;
import javax.swing.*;
import src.core.model.Composite;
import src.core.model.Feature;
import src.core.model.Iterator;
import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.premium.Premium;

public class ShopState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;

    public ShopState(Viewer viewer) {
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public Boolean getPremiumState() {
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
        return premium.isActive();
    }

    private void build() {

        JButton mainbutton = new JButton("Main Page");
        mainbutton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });
        this.buttonTemps.add(mainbutton);

        //this.buttonTemps.add(new JButton("Course slot"));
        //this.buttonTemps.add(new JButton("Cosmetics"));
        //this.buttonTemps.add(new JButton("Early access"));
        //this.buttonTemps.add(new JButton("Ad free"));
        JButton activateButton = new JButton("Activate Premium");
        activateButton.addActionListener((actionEvent) -> {
            if (!this.getPremiumState()) {
                String[] activation = new String[]{"Premium"};
                String[] desactivateArgs = new String[0];
                viewer.controller.activate(desactivateArgs, activation);
                JOptionPane.showMessageDialog(
                        this.frame,
                        "Vous avez activé premium \n vous pouvez maintenant avoir accès à tous les cours",
                        "Premium activated",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        this.frame,
                        "Vous avez déjà activé Premium.",
                        "Premium already activated",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
        this.buttonTemps.add(activateButton);

        JButton desactivateButton = new JButton("Desactivate Premium");
        desactivateButton.addActionListener((actionEvent) -> {
            if (this.getPremiumState()) {
                String[] desactivateArgs = new String[]{"Premium"};
                String[] activation = new String[0];
                viewer.controller.activate(desactivateArgs, activation);
                JOptionPane.showMessageDialog(
                        this.frame,
                        "Vous avez désactivé premium",
                        "Premium desactivated",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        this.frame,
                        "Vous avez déjà desactivé Premium.",
                        "Premium already desactivated",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
        this.buttonTemps.add(desactivateButton);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).getData();
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).infoPanel();
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
