package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private Color color;
   private List<Ficha> fichas;
   private List<Ficha> CajonDeFichas;
   private List<Ficha> barra;


    public List<Ficha> getCajonDeFichas() {
        return CajonDeFichas;
    }

    /*
            Modelo.Jugador de Blancas (Modelo.Color.BLANCAS):

                    2 fichas en el punto 24
                    5 fichas en el punto 13
                    3 fichas en el punto 8
                    5 fichas en el punto 6
            Modelo.Jugador de Negras (Modelo.Color.NEGRAS):

                    2 fichas en el punto 1
                    5 fichas en el punto 12
                    3 fichas en el punto 17
                    5 fichas en el punto 19
        */
    public Jugador(Color color) {
        this.color = color;
        this.fichas = new ArrayList<>();
        this.CajonDeFichas = new ArrayList<>();
        this.barra = new ArrayList<>();


        if (color == Color.BLANCAS) {
            // Inicializa las fichas blancas en las posiciones iniciales.
            // Agrega 2 fichas en el punto 24.
            for (int i = 0; i < 2; i++) {
                Ficha ficha = new Ficha(color);
                ficha.setPosicion(24);
                fichas.add(ficha);
            }
            // Agrega 5 fichas en el punto 13.
            for (int i = 0; i < 5; i++) {
                Ficha ficha = new Ficha(color);
                ficha.setPosicion(13);
                fichas.add(ficha);
            }
            // Agrega 3 fichas en el punto 8.
            for (int i = 0; i < 3; i++) {
                Ficha ficha = new Ficha(color);
                ficha.setPosicion(8);
                fichas.add(ficha);
            }
            // Agrega 5 fichas en el punto 6.
            for (int i = 0; i < 5; i++) {
                Ficha ficha = new Ficha(color);
                ficha.setPosicion(6);
                fichas.add(ficha);
            }
        } else {
            // Inicializa las fichas negras en las posiciones iniciales.
            // Agrega 2 fichas en el punto 1.
            for (int i = 0; i < 2; i++) {
                Ficha ficha = new Ficha(color);
                ficha.setPosicion(1);
                fichas.add(ficha);
            }
            // Agrega 5 fichas en el punto 12.
            for (int i = 0; i < 5; i++) {
                Ficha ficha = new Ficha(color);
                ficha.setPosicion(12);
                fichas.add(ficha);
            }
            // Agrega 3 fichas en el punto 17.
            for (int i = 0; i < 3; i++) {
                Ficha ficha = new Ficha(color);
                ficha.setPosicion(17);
                fichas.add(ficha);
            }
            // Agrega 5 fichas en el punto 19.
            for (int i = 0; i < 5; i++) {
                Ficha ficha = new Ficha(color);
                ficha.setPosicion(19);
                fichas.add(ficha);
            }

        }




/*
        if (color == Color.NEGRAS){
            for (int i = 8; i< 10; i++){
                    Ficha ficha = new Ficha(Color.NEGRAS);
                    ficha.setPosicion(i);
                    fichas.add(ficha);
              //  fichas.add(ficha);
            }
        }
        else {
            for (int i = 4; i< 5; i++){
                    Ficha ficha = new Ficha(Color.BLANCAS);
                    ficha.setPosicion(i);
                    fichas.add(ficha);

            }
        }


 */


    }

    public Color getColor() {
        return color;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public List<Ficha> getBarra() {
        return barra;
    }

    public int Lanzar_Dado(Dado dado){
        int resultado = dado.tirar();
        System.out.println("El jugador " + color + " lanzó un " + resultado);
        return resultado;
    }

    public void Mostrar_fichas_por_color_y_las_posiciones(){
        //creamos un array
        int [] posicionFichas = new int[24];


        System.out.println("Fichas del jugador " + color + ":");


        int i;
        for (Ficha ficha : fichas) {
            if (ficha.getJugador() == color && (ficha.getPosicion() != 25 || ficha.getPosicion() != 0) ) {
               // System.out.println("Modelo.Ficha en posición " + ficha.getPosicion());
                i = ficha.getPosicion() - 1 ;
              //  i = ficha.getPosicion() - 1 ;
                posicionFichas[i] = posicionFichas[i] + 1 ;
            }
        }

        for (int l = 0; l <posicionFichas.length; l++){
            if (posicionFichas[l] != 0){
                System.out.println( "La cantidad de fichas en la  posicion " +  (l + 1) +" es " + posicionFichas[l] + " ");
            }
        }


    }

/*
    public boolean RealizarMovimiento(int origen, int dado1, int dado2) {
        // Busca la ficha en el punto de origen.
        Modelo.Ficha ficha = null;
        for (Modelo.Ficha f : fichas) {
            if (f.getPosicion() == origen) {
                ficha = f;
                break;
            }
        }

        if (ficha != null) {
            // Verifica si el movimiento es válido.
            if (esMovimientoValido(origen, dado1, dado2)) {
                // Calcula la posición de destino.
                int destino = calcularDestino(origen, dado1, dado2, color);

                // Verifica si hay una ficha del oponente en el destino.
                if (PuedeComer(destino, color)) {
                    ficha.setPosicion(destino);
                    // Coloca la ficha en la barra.
                    barra.add(ficha);
                    fichas.remove(ficha);
                    System.out.println("Modelo.Ficha " + ficha + "Del jugador color "  + ficha.getJugador()+ " enviada a la barra.");
                    System.out.println("La cantidad de fichas en las barras son: " + Cantidad_Fichas_Barra() );
                } else {
                    // Realiza el movimiento normal.
                    ficha.mover(dado1 + dado2);

                    // Avisa cuando se agrega al cajón!!.
                    if ((ficha.getPosicion() == 25 || ficha.getPosicion() == 0) && color == Modelo.Color.NEGRAS) {
                        // Modelo.Ficha negra ha salido del tablero.
                        CajonDeFichas.add(ficha);
                        fichas.remove(ficha);
                        System.out.println("Se agregó una ficha al cajón de Negras.");
                    } else if ((ficha.getPosicion() == 25 || ficha.getPosicion() == 0) && color == Modelo.Color.BLANCAS) {
                        // Modelo.Ficha blanca ha salido del tablero.
                        CajonDeFichas.add(ficha);
                        fichas.remove(ficha);
                        System.out.println("Se agregó una ficha al cajón de Blancas.");
                    } else {
                        System.out.println("Movimiento exitoso desde el punto " + origen + " al punto " + ficha.getPosicion() + ".");
                    }
                }
                return true;
            } else {
                System.out.println("Movimiento inválido desde el punto " + origen + ".");
                return false;
            }
        } else {
            System.out.println("No hay fichas en el punto de origen " + origen + ".");
            return false;
        }
    }



 */

   /*
    public boolean esMovimientoValido(int origen,int dado1, int dado2) {


        // Verificar que el destino esté dentro del rango de puntos del tablero.
        if (origen < 0 || origen > 25) {
            return false;
        }

        if (CasilleroOcupado(calcularDestino(origen, dado1,dado2,color),color)){
            System.out.println("El casillero esta ocupado por el rivarl: ");
            return false;

        }

        int PosicionFinal;
        if (getColor() == Modelo.Color.NEGRAS){
            PosicionFinal = origen + (dado1 + dado2);
        }else {
            PosicionFinal = origen - (dado1 + dado2);
        }

        if (movimiento_Valido(PosicionFinal)){
            return true;
        }
        else{
            return false;
        }

        // Si todas las validaciones pasan, el movimiento es válido.

    }


    */

    public int  Cantidad_Fichas_Barra(){
            return barra.size();
    }

    private boolean movimiento_Valido(int PosicionFinal){
        return PosicionFinal >= 0 && PosicionFinal <= 25;
    }

    public boolean PuedeComer(int posicion, Color colorficha){
        Color colorrival;
        int CanidadDeFichas = 0;
        if (colorficha == Color.BLANCAS){
            colorrival = Color.NEGRAS;
        }
        else{
            colorrival = Color.BLANCAS;
        }

        for (Ficha ficha : fichas) {
            if ( posicion == ficha.getPosicion() && ficha.getJugador() == colorrival){
                CanidadDeFichas = CanidadDeFichas + 1;
            }

            if (CanidadDeFichas == 1){
                return true;
            }
        }
        return false ;
    }


    public boolean CasilleroOcupado(int posicion, Color colorficha){
        /*
        Modelo.Color colorrival;
        int CanidadDeFichas = 0;
        if (colorficha == Modelo.Color.BLANCAS){
            colorrival = Modelo.Color.NEGRAS;
        }
        else{
            colorrival = Modelo.Color.BLANCAS;
        }

        for (Modelo.Ficha ficha : fichas) {
            if ( posicion == ficha.getPosicion() && ficha.getJugador() == colorrival){
               CanidadDeFichas = CanidadDeFichas + 1;
               if (CanidadDeFichas > 1){
                   return true;
               }

            }
        }
        return false ;



         */
        int cantidadFichasOponente = 0;
        Color colorOponente = (colorficha == Color.BLANCAS) ? Color.NEGRAS : Color.BLANCAS;
        for (Ficha ficha : fichas) {
            if (posicion == ficha.getPosicion() && ficha.getJugador().equals(colorOponente)) {
                cantidadFichasOponente++;
            }
        }

        return cantidadFichasOponente == 1;
    }


    private int posicionInicialSegunColor(Color color ) {
        // Implementa la lógica para determinar la posición inicial de cada ficha
        // según el color del jugador y el número de ficha.
        // Esto puede variar según las reglas específicas del juego de Modelo.Backgammon.
        // Aquí se muestra una implementación simplificada.
        if (color == Color.NEGRAS) {
            return  1;
        } else {
            return 24;
        }
    }

    public List<Ficha> FichasConPosiciones() {
        List<Ficha> fichasConPosiciones = new ArrayList<>();
        for (Ficha ficha : fichas) {
            fichasConPosiciones.add(ficha); // Agrega la ficha al nuevo ArrayList
        }
        return fichasConPosiciones;
    }



    //Verifica si Ya tiene todas las fichas las fichas dentro del cajon
    public boolean TodasFichasEnCajon(){
        if (14 == CajonDeFichas.size()){
            return true;
        }
        else return false;
    }

    public int getfichasEncajon(){
        return CajonDeFichas.size();
    }

/*
    public int calcularDestino(int origen, int dado1, int dado2, Modelo.Color colorDelJugador) {
        int sumaDeDados = dado1 + dado2;

        if (colorDelJugador == Modelo.Color.BLANCAS){
             return  origen - sumaDeDados;

        }else {
            return  origen + sumaDeDados;
        }
    }


 */

   public  boolean EstaEnBarra(Color coloFichita){
       for (Ficha ficha : barra) {
           if (ficha.getJugador() == coloFichita) {
               return true;
           }
       }
       return false;
   }


   //Mostramos cajon de fichas de cada color, y mostramos la barra.


    public  ArrayList<String>  MostrarFichasBarra() {
        ArrayList<String> CargarFichasBarraAMostrar = new ArrayList();
        System.out.println("Fichas en la Barra:");
        for (Ficha Fbarra : barra) {
            String infoFicha =("Posición: " + Fbarra.getPosicion() + ", Jugador: " + Fbarra.getJugador());
            System.out.println("Posición: " + Fbarra.getPosicion() + ", Jugador: " + Fbarra.getJugador());
            CargarFichasBarraAMostrar.add(infoFicha) ;
        }


        return CargarFichasBarraAMostrar;
    }

    public  ArrayList<String>  MostrarCajonFichas() {
        ArrayList<String> CargarFichasBarraAMostrar = new ArrayList();
        System.out.println("Fichas en el cajon:");
        for (Ficha Fbarra : CajonDeFichas) {
            String infoFicha =("Posición: " + Fbarra.getPosicion() + ", Jugador: " + Fbarra.getJugador());
            System.out.println("Posición: " + Fbarra.getPosicion() + ", Jugador: " + Fbarra.getJugador());
            CargarFichasBarraAMostrar.add(infoFicha) ;
        }


        return CargarFichasBarraAMostrar;
    }



}
