package src.core.observer;

public interface ObserverView {

    int update(String[] deactivations, String[] activations);

    void show();    // afficher la fenêtre

    void hide();    // masquer la fenêtre

    void close();   // détruire la fenêtre
}
