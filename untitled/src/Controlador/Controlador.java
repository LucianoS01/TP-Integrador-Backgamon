package Controlador;

import Modelo.*;
import Vista.*;
import java.util.Scanner;

public class Controlador {

    public Tablero tablero;
    private Jugador jugadorBlanco;
    private Jugador jugadorNegro;
    private Vista vista;
    private Vista vista2;
    Modelo modelo;

    private Jugador jugadorTurno;

    public Controlador(Modelo modelo) {
        this.tablero = tablero;
        this.jugadorBlanco = jugadorBlanco;
        this.jugadorNegro = jugadorNegro;
        this.vista = vista;
        this.vista2 = vista2;
        this.modelo = modelo;
        Jugador jugadorTurno = modelo.turno();
        this.jugadorTurno = jugadorTurno;

    }
    public void setVista(Vista vista) {
        this.vista = vista;
    }
    public void iniciarJuego() {
        vista.Bienvenida();
        //jugadorTurno = modelo.turno();
        modelo.turno();
        vista.mostrarMensaje("Turno del jugador " + jugadorTurno.getColor());
        DibujarTablero();
        modelo.MostrarFichasBarra(jugadorTurno.getBarra());
        vista.mostrarMensaje("La cantidad de fichas que hay en la barra del jugador "+ " son:  " + jugadorTurno.getColor() + "  "+ modelo.Cantidad_Fichas_Barra(jugadorTurno));
       // actualizarVista();
    }
    public void Cambiarturnito() {
      //  modelo.turno();
        cambiarTurno();
        actualizarVista();
    }


    public void actualizarVista() {
        DibujarTablero();
       // vista.mostrarMensaje("Turno del jugador " + jugadorTurno.getColor());
    }
    public void LanzarDaditosY_Emxezar(){
        vista.mostrarMensaje("Turno del jugador " + jugadorTurno.getColor());
        modelo.turno();

        Dado dado = new Dado();
        int dado1 = 0;
        int dado2 = 0;
        dado1 = modelo.Lanzar_Dadoo(jugadorTurno, dado);
        dado2 = modelo.Lanzar_Dadoo(jugadorTurno, dado);
        boolean movimientoExitoso = false;
        //Si hay dobles:
        if (dado1 == dado2){
            dado1 = dado1 * 2;
            dado2 = dado2 * 2;
            vista.mostrarMensaje("Hubo Dobles se multiplican los dados!!"+ "\n");
        }
        vista.mostrarMensaje("El jugador : " + ColorDelJugadorTurno(jugadorTurno)  + ", Lanzo: " + dado1);
        vista.mostrarMensaje("El jugador : " + ColorDelJugadorTurno(jugadorTurno)  + ", Lanzo: " + dado2);
        vista.mostrarMensajeC("Dado 1: " + dado1 + ", Dado 2: " + dado2);
      //  vista.mostrarMensaje("Dado 1: " + dado1 + ", Dado 2: " + dado2);

        int opcion = vista.elegirOpcion(ColorDelJugadorTurno(jugadorTurno) ,dado1,dado2);
        int origen;
        if ((opcion == 1 ) || !modelo.hayMovimientosSeparados(jugadorTurno, dado1, dado2)) {
            vista.mostrarMensajeC("Origen: ");
            vista.mostrarMensaje("Origen: ");
            origen = vista.obtenerOrigenDesdeInput();
            vista.mostrarMensaje(" Se ha ingresador la poscion de origen : " + origen);
            int sumaDados = dado1 + dado2;
            movimientoExitoso = realizarMovimiento(jugadorTurno, origen, dado1, dado2);
        } else if (opcion == 2 )  {
           // vista.mostrarMensajeC("Origen Dado 1: ");
            vista.mostrarMensaje(" Ingrese el Origen  para el Dado 1: ");
            int origenDado1 = vista.obtenerOrigenDesdeInput();
            vista.mostrarMensaje(" Se ha ingresador la poscion de origen : " + origenDado1);
            vista.mostrarMensaje(" Ingrese el Origen  para el Dado 2: ");
            int origenDado2 = vista.obtenerOrigenDesdeInput();
            vista.mostrarMensaje(" Se ha ingresador la poscion de origen : " + origenDado2);
            movimientoExitoso = realizarMovimientosSeparados(jugadorTurno, origenDado1, origenDado2, dado1, dado2);
        } else {
            vista.mostrarMensajeC("Opción no válida. Se sumarán los dados por defecto.");
            // System.out.println("Opción no válida. Se sumarán los dados por defecto.");
            // vista.mostrarMensaje(" VUELVA INGRESAR Origen: ");
            //  cadena = vista.agregarTexto5();
            // origen = vista.obtenerOrigenDesdeInput();
            //  int sumaDados = dado1 + dado2;
            // movimientoExitoso = realizarMovimiento(jugadorTurno, origen, dado1, dado2);
        }

        if (!movimientoExitoso) {
            vista.mostrarError("ERROR Movimiento inválido. Intenta de nuevo.");
            vista.mostrarErrorC("ERROR Movimiento inválido. Intenta de nuevo.");
        }
        else {
            modelo.MostrarFichasBarra(jugadorTurno.getBarra());
            vista.mostrarMensaje("La cantidad de fichas que hay en la barra son:  " + modelo.Cantidad_Fichas_Barra(jugadorTurno));
            vista.mostrarMensaje("La caja del Jugador " + Jcolor(jugadorTurno) + " contiene: " + jugadorTurno.getfichasEncajon());
            vista.mostrarMensajeC("La caja del Jugador " + Jcolor(jugadorTurno) + " contiene: " + jugadorTurno.getfichasEncajon());
           // vista.mostrarMensaje("Se cambio el turno ");
            Cambiarturnito();
            //DibujarTablero();
        }
        if (modelo.TodasFichasCajon(jugadorTurno)) {
            vista.Final();
        }
       // MostrarFichasBarra(jugadorTurno);

    }


    //---------------------------------------------------------------------------------

    //Jugar funciona pero es un gameloop.
    public void Jugar() {
        vista.Bienvenida();
        Scanner scanner = new Scanner(System.in);
        Dado dado = new Dado();
        while (true) {
            Jugador jugadorTurno = modelo.turno();
            DibujarTablero();
            modelo.MDibujarTableroConsalo();
            System.out.println("Turno del jugador " + jugadorTurno.getColor());
            Jcolor(jugadorTurno);
            //Este Jugador tiene fichas en la barra;
            if (VerificaJugadorTieneFichaEnBarra(jugadorTurno, Jcolor(jugadorTurno))){
                vista.mostrarMensaje("No puede mover Fichas hasta que las saque de ahi: ");
            }
            boolean movimientoExitoso = false;
            int dado1 = 0;
            int dado2 = 0;
            do {
                // Solo lanzamos nuevos dados si el movimiento anterior fue inválido
                if (!movimientoExitoso) {
                    dado1 = modelo.Lanzar_Dadoo(jugadorTurno, dado);
                    dado2 = modelo.Lanzar_Dadoo(jugadorTurno, dado);

                    //Si hay dobles:
                    if (dado1 == dado2){
                        dado1 = dado1 *2;
                        dado2 = dado2 * 2;
                        vista.mostrarMensaje("Hubo Dobles se multiplican los dados!!"+ "\n");
                    }
                    vista.mostrarMensaje("El jugador : " + ColorDelJugadorTurno(jugadorTurno)  + ", Lanzo: " + dado1);
                    vista.mostrarMensaje("El jugador : " + ColorDelJugadorTurno(jugadorTurno)  + ", Lanzo: " + dado2);
                    vista.mostrarMensajeC("Dado 1: " + dado1 + ", Dado 2: " + dado2);
                    vista.mostrarMensaje("Dado 1: " + dado1 + ", Dado 2: " + dado2);
                }
               // int opcion = vista.elegirOpcion();
               // int opcion = vista.elegirOpcionConsola();
                String cadena = vista.agregarTexto5();
                int opcion = vista.elegirOpcion(ColorDelJugadorTurno(jugadorTurno) ,dado1,dado2);
                //hago un Scroll hacia abajo.
                //vista.Scroleo_haciaAbajo();
                int origen;
                if ((opcion == 1 ) || !modelo.hayMovimientosSeparados(jugadorTurno, dado1, dado2)) {
                    //System.out.println("Origen: ");
                    vista.mostrarMensajeC("Origen: ");
                    vista.mostrarMensaje("Origen: ");
                    cadena = vista.agregarTexto5();
                    origen = vista.obtenerOrigenDesdeInput();
                    //hago un Scroll hacia abajo.
                 //   vista.Scroleo_haciaAbajo();
                    vista.mostrarMensaje(" Se ha ingresador la poscion de origen : " + origen);
                    int sumaDados = dado1 + dado2;
                    movimientoExitoso = realizarMovimiento(jugadorTurno, origen, dado1, dado2);
                } else if (opcion == 2 )  {
                    vista.mostrarMensajeC("Origen Dado 1: ");
                    vista.mostrarMensaje(" Ingrese el Origen  para el Dado 1: ");
                    cadena = vista.agregarTexto5();
                    int origenDado1 = vista.obtenerOrigenDesdeInput();
                    //hago un Scroll hacia abajo.
                 //   vista.Scroleo_haciaAbajo();
                    vista.mostrarMensaje(" Se ha ingresador la poscion de origen : " + origenDado1);
                  //  vista.mostrarMensaje("Origen Dado 2: ");
                    cadena = vista.agregarTexto5();
                    vista.mostrarMensajeC("Origen Dado 2: ");
                    vista.mostrarMensaje(" Ingrese el Origen  para el Dado 1: ");
                    int origenDado2 = vista.obtenerOrigenDesdeInput();
                    //hago un Scroll hacia abajo.
                 //   vista.Scroleo_haciaAbajo();
                    vista.mostrarMensaje(" Se ha ingresador la poscion de origen : " + origenDado2);
                    movimientoExitoso = realizarMovimientosSeparados(jugadorTurno, origenDado1, origenDado2, dado1, dado2);
                } else {
                    vista.mostrarMensajeC("Opción no válida. Se sumarán los dados por defecto.");
                   // System.out.println("Opción no válida. Se sumarán los dados por defecto.");
                   // vista.mostrarMensaje(" VUELVA INGRESAR Origen: ");
                  //  cadena = vista.agregarTexto5();
                   // origen = vista.obtenerOrigenDesdeInput();
                  //  int sumaDados = dado1 + dado2;
                   // movimientoExitoso = realizarMovimiento(jugadorTurno, origen, dado1, dado2);
                }
               // vista.dibujarTableroV();
                modelo.MDibujarTableroConsalo();
                MostrarFichasBarra(jugadorTurno);
                DibujarTablero();
                //modelo.MDibujarTablero();
               // vista.dibujarTableroV();

                if (!movimientoExitoso) {
                    vista.mostrarError("ERROR Movimiento inválido. Intenta de nuevo.");
                    vista.mostrarErrorC("ERROR Movimiento inválido. Intenta de nuevo.");
                }
                //Muestra las Fichas que estan en la barra:
               // modelo.MostrarTablerBarra(jugadorTurno);
                modelo.MostrarFichasBarra(jugadorTurno.getBarra());
                vista.mostrarMensaje("La cantidad de fichas que hay en la barra son:  " + modelo.Cantidad_Fichas_Barra(jugadorTurno));
                vista.mostrarMensaje("La caja del Jugador " + Jcolor(jugadorTurno) + " contiene: " + jugadorTurno.getfichasEncajon());
                vista.mostrarMensajeC("La caja del Jugador " + Jcolor(jugadorTurno) + " contiene: " + jugadorTurno.getfichasEncajon());
            } while (!movimientoExitoso);

            if (!modelo.TodasFichasCajon(jugadorTurno)) {
                break;
            }
            // Cambiar el turno.
            cambiarTurno();
        }
        //scanner.close();
        vista.Final();
    }


    public boolean VerificaJugadorTieneFichaEnBarra(Jugador jugador, Color color){
        return modelo.HayfichasEnLaBarraDeColor(jugador,color);
    }

    public int validarOpcion(String opcionIngresada) {
        if ("1".equals(opcionIngresada) || "2".equals(opcionIngresada)) {
            // La cadena es "1" o "2", por lo que puedes convertirla a un entero y retornarla.
            return Integer.parseInt(opcionIngresada);
        } else {
            // La cadena no es "1" ni "2", muestra un mensaje de error o realiza otra acción apropiada.
            vista.mostrarError("Por favor, ingrese una opción válida (1 o 2).");
            return -1; // Puedes retornar un valor por defecto o indicar un error, según tu lógica.
        }
    }

    public Color Jcolor( Jugador jugador){

        return modelo.Jcolor(jugador);
    }

    public String ColorDelJugadorTurno(Jugador jugador){
       String S;
       S = modelo.colorJugador(jugador).toString();
       return S;
    }
    public boolean TOdasFichasEncaJon(Jugador jugadorr){
        return modelo.TodasFichasCajon(jugadorr);
    }

    public boolean realizarMovimiento(Jugador jugador, int origen, int dado1, int dado2) {
        return modelo.MRealizarMovimientos(jugador, origen, dado1, dado2);
    }

    public boolean realizarMovimientosSeparados(Jugador jugador, int origen1, int origen2, int dado1, int dado2) {
        return modelo.MrealizarMovimientosSeparados(jugador, origen1, origen2, dado1, dado2);
    }
    public String LLevarString (String inputString) {

        return inputString;
    }
    public void cambiarTurno() {
       jugadorTurno =  modelo.McambiarTurno();
    }

    public void MostrarFichasBarra(Jugador juga){
        vista.mostrarMensaje("MuestaBarraDeFichasComidas:  ");
        for (String FichasBarra :  modelo.MostrarTableroBarra(juga)) {
            System.out.println(FichasBarra);
            vista.mostrarMensaje(FichasBarra);
        }

    }
    public void MostrarCajonesAmbiosJugadores_(Jugador j1, Jugador j2){
        vista.mostrarMensaje("Muestra Los 2 cajones del tablero:  ");
        for (String FichasCajon :  modelo.MostrarCajonesAmbiosJugadores(j1,j2)) {
            System.out.println(FichasCajon);
            vista.mostrarMensaje(FichasCajon);
        }
    }


    public void DibujarTablero(){
        modelo.llamarTablero();
    }


}