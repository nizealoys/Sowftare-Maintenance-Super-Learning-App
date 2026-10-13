package core.observer;

public interface SubjectController {
    void addObserver(ObserverView observer);
    void removeObserver(ObserverView observer);
    void notifyObserver(String input);
}
