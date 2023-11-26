package Modelo;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Backgammon {
    private Tablero tablero;
    private Jugador jugadorBlanco;
    private Jugador jugadorNegro;

    Controlador controlador;

    public Backgammon(Controlador controlador) {
        jugadorBlanco = new Jugador(Color.BLANCAS);
        jugadorNegro = new Jugador(Color.NEGRAS);
        this.controlador = controlador;

    }


    public void Jugar() {
        JFrame frame;


        Scanner scanner = new Scanner(System.in);
        Dado dado = new Dado();


        List<Ficha> FichasParaElTablero = new ArrayList<>();
        FichasParaElTablero.addAll(jugadorNegro.FichasConPosiciones());
        FichasParaElTablero.addAll(jugadorBlanco.FichasConPosiciones());
        Tablero tablero = new Tablero(FichasParaElTablero, jugadorBlanco, jugadorNegro);



        // tablero.dibujarTablero2();
       //  tablero.dibujarTablero();

        frame = new JFrame("Titulo Ventana");
        frame.setVisible(true);
        frame.setSize(400,1000);
      //  frame.add(tablero.dibujarTablero(), BorderLayout.CENTER);

        while (true) {
            Jugador jugadorTurno;
            if (tablero.getTurno() == Color.BLANCAS) {
                jugadorTurno = jugadorBlanco;
            } else {
                jugadorTurno = jugadorNegro;
            }

            System.out.println("Turno del jugador " + jugadorTurno.getColor());

            boolean movimientoExitoso = false;
            while (!movimientoExitoso) {

                int dado1 = jugadorTurno.Lanzar_Dado(dado);
                int dado2 = jugadorTurno.Lanzar_Dado(dado);

               // System.out.println("Modelo.Dado 1: " + dado1);
               // System.out.println("Modelo.Dado 2: " + dado2);

                System.out.print("¿Cómo deseas tirar los dados? \n");
                System.out.println("1. Sumar Modelo.Dado 1 y Modelo.Dado 2");
                System.out.println("2. Tirar Modelo.Dado 1 y Modelo.Dado 2 por separado");
                int opcion = scanner.nextInt();

                int origen;
                if (opcion == 1 || !tablero.HayMovimientosSeparados(jugadorTurno, dado1, dado2)) {
               // if (opcion == 1) {
                    System.out.print("Origen: ");
                    origen = scanner.nextInt();
                    int sumaDados = dado1 + dado2;
                    movimientoExitoso = tablero.RealizarMovimientos(jugadorTurno, origen, dado1,dado2,sumaDados);
                } else if (opcion == 2) {
                    System.out.print("Origen Modelo.Dado 1: ");
                    int origenDado1 = scanner.nextInt();
                    System.out.print("Origen Modelo.Dado 2: ");
                    int origenDado2 = scanner.nextInt();
                    movimientoExitoso = tablero.RealizarMovimientosSeparados(jugadorTurno, origenDado1, origenDado2, dado1, dado2);
                } else {
                    System.out.println("Opción no válida. Se sumarán los dados por defecto.");
                    System.out.print("Origen: ");
                    origen = scanner.nextInt();
                    int sumaDados = dado1 + dado2;
                    movimientoExitoso = tablero.RealizarMovimientos(jugadorTurno, origen, dado1,dado2,sumaDados);
                }

                tablero.dibujarTablero();

                if (!movimientoExitoso) {
                    System.out.println("ERROR Movimiento inválido. Intenta de nuevo.");
                }

                System.out.println("La caja del Modelo.Jugador " + jugadorTurno.getColor() + " contiene: " + jugadorTurno.getfichasEncajon());
            }

            if (jugadorTurno.TodasFichasEnCajon()) {
                System.out.println("¡Modelo.Jugador " + jugadorTurno.getColor() + " ha ganado!");
                break;
            }

            scanner.nextLine(); // Limpiar el búfer de entrada.

            // Cambiar el turno.
            tablero.cambiarTurno();
        }
        scanner.close();
    }
}