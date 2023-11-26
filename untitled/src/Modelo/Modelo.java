package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Modelo implements IObservable {
    private Tablero tablero;
    Jugador jugadorBlanco;
    Jugador jugadorNegro;
    //private ArrayList <Modelo.Observer> observadores;
    private List<IObserver> observers = new ArrayList<>();



    public Modelo() {
        Jugador jugadorBlanco = new Jugador(Color.BLANCAS);
        Jugador jugadorNegro = new Jugador(Color.NEGRAS);



        List<Ficha> FichasParaElTablero = new ArrayList<>();
        FichasParaElTablero.addAll(jugadorNegro.FichasConPosiciones());
        FichasParaElTablero.addAll(jugadorBlanco.FichasConPosiciones());
        Tablero tablero = new Tablero(FichasParaElTablero, jugadorBlanco, jugadorNegro);
        tablero = new Tablero(tablero.getFichas_del_Tablero(), jugadorBlanco, jugadorNegro);
        this.jugadorBlanco = jugadorBlanco;
        this.jugadorNegro = jugadorNegro;
        this.tablero = tablero;

    }

    public Tablero getTablero() {
        return tablero;
    }





    public  Jugador turno(){
        return (tablero.getTurno() == Color.BLANCAS) ? jugadorBlanco : jugadorNegro;
    }
    public boolean MRealizarMovimientos(Jugador jugador, int origen, int dado1, int dado2) {
        return tablero.RealizarMovimientos(jugador,origen,dado1, dado2, dado1 + dado2);
    }

    public boolean MrealizarMovimientosSeparados(Jugador jugador, int origen1, int origen2, int dado1, int dado2) {
        return tablero.RealizarMovimientosSeparados(jugador, origen1, origen2, dado1, dado2);
    }

    //Mostramos la barra.
   public ArrayList<String> MostrarTableroBarra(Jugador jugador){

       return jugador.MostrarFichasBarra();

   }

   //Mostrar Cajones de Jugador;
    public ArrayList<String> MostrarCajonesAmbiosJugadores(Jugador jugador1, Jugador jugador2){
        ArrayList<String> resultado = new ArrayList<>();
        ArrayList<String> CajonNegro = new ArrayList();
        ArrayList<String> CajonBlanca = new ArrayList();

        // Mostrar cajón de jugador1
        resultado.add("Cajón de " + jugadorBlanco + ":");
        ArrayList<String> cajonJugador1 = jugador1.MostrarCajonFichas();
        resultado.addAll(cajonJugador1);

        // Mostrar cajón de jugador2
        resultado.add("Cajón de " + jugadorBlanco + ":");
        ArrayList<String> cajonJugador2 = jugador2.MostrarCajonFichas();
        resultado.addAll(cajonJugador2);

        return resultado;


    }






/*
   public int CantidadFichasBarra(Jugador jugador){
       jugador.Cantidad_Fichas_Barra();
   }



 */


    public boolean HayfichasEnLaBarraDeColor(Jugador jugador, Color jugadorColor){

        return jugador.EstaEnBarra(jugadorColor);
    }

    public void  MostrarFichasBarra(List<Ficha> barra) {
       // System.out.println("Fichas en la Barra:");
        notifyObservers("NOTIFICADOR: Fichas en la barra " );
        for (Ficha Fbarra : barra) {
            //  System.out.println("Posición: " + Fbarra.getPosicion() + ", Modelo.Jugador: " + Fbarra.getJugador());
            notifyObservers("NOTIFICADOR: Posición: " + Fbarra.getPosicion() + ", Modelo.Jugador: " + Fbarra.getJugador());

        }
    }

    public int Cantidad_Fichas_Barra(Jugador jugador){
       return jugador.Cantidad_Fichas_Barra();
    }

    public Color Jcolor(Jugador jugadorTurno){
        notifyObservers("NOTIFICADOR: Turno del jugador " + jugadorTurno.getColor());
        return  jugadorTurno.getColor();
    }

    public Color colorJugador(Jugador jugadorTurno){
        return  jugadorTurno.getColor();
    }

    /*
    public Color Notificar_turno(Jugador jugadorTurno){
        notifyObservers("Turno del jugador:: " + turno());
        return  jugadorTurno.getColor();
    }

     */

    public Jugador McambiarTurno() {

        notifyObservers("NOTIFICADOR: Se hizo un cambio de turno");
        return tablero.cambiarTurno();
    }

    /*
    public void MDibujarTablero(){

        notifyObservers(tablero.dibujarTablero());
    }

     */
    public boolean hayMovimientosSeparados(Jugador jugador, int dado1, int dado2) {
        return tablero.HayMovimientosSeparados(jugador, dado1, dado2);
    }

    public int Lanzar_Dadoo(Jugador jugador, Dado dado){
        return jugador.Lanzar_Dado(dado);
    }




    public  boolean TodasFichasCajon(Jugador jugador){
        if (jugador.TodasFichasEnCajon()){
            notifyObservers("NOTIFICADOR: ¡JUGADOR:  " + jugador.getColor() + " HA GANADO!");
            return true;
        }
        return false;

    }


    //FUNCIONAAA
    /*
    public String VerSiFuncion(){
        String s;
        StringBuilder tableroRepresentation = new StringBuilder();



        // Muestra el estado actual del tablero en la consola.
        for (int i = 0; i < tablero.getPosiciones().length; i++) {
            tableroRepresentation.append("Punto ").append(i + 1).append(": ");
            if (tablero.getPosiciones()[i] > 0) {
                // Imprime fichas blancas.
                for (int j = 0; j < tablero.getPosiciones()[i]; j++) {
                    tableroRepresentation.append("B");
                }
            } else if (tablero.getPosiciones()[i] < 0) {
                // Imprime fichas negras.
                for (int j = 0; j < -tablero.getPosiciones()[i]; j++) {
                    tableroRepresentation.append("N");
                }
            }
            tableroRepresentation.append("\n"); // Salto de línea después de cada punto.
        }




      //  s= "fefe";
        s=tableroRepresentation.toString();
        return s;
    }
 */

    //FUNCIONA MOSTRAR EL TABLERO;
    public String llamarTablero(){
        String k;
        k = tablero.dibujarTableroo();
        notifyObservers("Tablero: "+"\n" +  k);
        return k;

    }



    public void MDibujarTableroConsalo(){
            tablero.dibujarTablero();
    }



    public int[] PosicionTablero(){
       return tablero.getPosiciones();
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }



}