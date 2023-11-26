package Modelo;
import java.util.Observer;

public interface IObservable {
    void addObserver(IObserver  observer);
    void removeObserver(IObserver  observer);
    void notifyObservers(String message);
}