package Controlador;

import Modelo.*;
import Vista.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControladorF {
    Tablero tablero;
    Jugador jugador;
    Vista vista;


    public ControladorF(Tablero tablero) {
        this.tablero = tablero;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public boolean realizarMovimiento(Jugador jugador, int origen, int dado1, int dado2) {
        return tablero.RealizarMovimientos(jugador, origen, dado1, dado2, dado1 + dado2);
    }

    public boolean realizarMovimientosSeparados(Jugador jugador, int origen1, int origen2, int dado1, int dado2) {
        return tablero.RealizarMovimientosSeparados(jugador, origen1, origen2, dado1, dado2);
    }

    public void cambiarTurno() {
        tablero.cambiarTurno();
    }

    public boolean hayMovimientosSeparados(Jugador jugador, int dado1, int dado2) {
        return tablero.HayMovimientosSeparados(jugador, dado1, dado2);
    }

    public int  Cantidad_Fichas_Barra(){
        return jugador.Cantidad_Fichas_Barra();
    }


    public String procesarString(String inputString) {

        return inputString;
    }

}
