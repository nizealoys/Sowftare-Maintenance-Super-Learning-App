package src.core.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import src.core.controller.Controller;
import src.core.view.states.*;

public final class Viewer {

    public Controller controller;

    public enum Page {
        MAIN, COURSES, COIN, SHOP, METHOD, PROFILE, EXERCICES, LESSONS, TOPIC, DIFFICULTY, WIP
    }
    private final Map<Page, ViewState> states = new EnumMap<>(Page.class);
    private ViewState currentState;
    public JFrame frame;
    public JPanel panel;
    // String userInput;
    //JButton buttonTemp;

    // Controller observer;
    public Viewer() throws IOException {
        this.controller = Controller.getInstance();
        // Create window with title "application" that stop the program when closed
        this.frame = new JFrame("SUPER LEARNING APP: COOKING EDITION");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Creating a panel that will hold the buttons
        this.panel = new JPanel();

        // Setup frame with the panel
        this.frame.add(panel);
        this.frame.setSize(1920, 1080);
        //this.frame.setSize(200, 200);
        //this.frame.setVisible(true); // show the window
        //Composite c = controller.getComposite();
        // System.out.println("\n controller = " + controller);
        // Thread.dumpStack();
        this.InitAllStates();
        this.goTo(Page.MAIN);

        // this.observer = observer;
    }

    private void InitAllStates() throws IOException {
        states.put(Page.MAIN, new MainState(this));
        states.put(Page.COURSES, new CourseState(this));
        states.put(Page.SHOP, new ShopState(this));
        //states.put(Page.COIN, new CoinState(this));
        states.put(Page.METHOD, new MethodState(this));
        states.put(Page.PROFILE, new ProfileState(this));
        states.put(Page.LESSONS, new LessonState(this));
        states.put(Page.TOPIC, new TopicState(this));
        states.put(Page.EXERCICES, new ExerciceState(this));
        states.put(Page.DIFFICULTY, new DifficultyState(this));
        states.put(Page.WIP, new WIPState(this));
    }

    public <T extends ViewState> T getState(Page page, Class<T> type) {
        return type.cast(states.get(page));
    }

    public void goTo(Page page) {
        panel.removeAll();
        this.currentState = states.get(page);
        this.currentState.handleRequest();
    }

    public JLabel loadPicture(String path) {
        File f = new File(path);
        try {
            BufferedImage img = ImageIO.read(f);
            if (img != null) {
                return new JLabel(new ImageIcon(img));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JLabel("Image introuvable");
    }

    public boolean show() {
        if (frame.isVisible()) {
            return false;
        }
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
        return true;
    }

    public boolean hide() {
        if (!frame.isVisible()) {
            return false;
        }
        SwingUtilities.invokeLater(() -> frame.setVisible(false));
        return true;
    }

    public void close() {
        SwingUtilities.invokeLater(frame::dispose);
    }
}
