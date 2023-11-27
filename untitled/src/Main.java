// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import Controlador.Controlador;
import Modelo.*;
import Vista.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Main {
    public static void main(String[] args) {
        Modelo model = new Modelo();
        Controlador control = new Controlador(model);
        Vista v = new  Vista(control);
        control.setVista(v);
        model.addObserver(v);
       // control.Jugar();
       String S= "3";
       int k = Integer.parseInt(S);
       System.out.println(k);
    }
}