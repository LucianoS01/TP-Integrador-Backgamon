package Modelo;
import java.util.Observer;

public interface IObservable {
    void AgregarObservable(IObserver  observer);
    void RemoverObservable(IObserver  observer);
    void NotificadorObservable(String message);
}