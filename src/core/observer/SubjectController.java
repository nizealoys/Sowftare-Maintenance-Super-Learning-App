package core.observer;

public interface SubjectController {
    void addObserver(core.observer.ObserverView observer);
    void removeObserver(core.observer.ObserverView observer);
    void notifyObserver(String input);
}
