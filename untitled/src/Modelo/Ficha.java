package Modelo;

public class Ficha {
    private int posicion;
    private Color jugador;

    public Ficha(Color jugador) {
        this.posicion = -1;
        this.jugador = jugador;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Color getJugador() {
        return jugador;
    }

    public boolean mover(int lugares){
        if (posicion == -1){
            return false;
        }

        int PosicionFinal;
        if (jugador == Color.NEGRAS){
            PosicionFinal = posicion + lugares;
        }else {
            PosicionFinal = posicion - lugares;
        }

        if (movimiento_Valido(PosicionFinal)){
            posicion =PosicionFinal;
            return true;
        }
        else{
            System.out.println("Movimiento inválidoo. No se puede mover la ficha a la posición " + PosicionFinal + ".");
            return false;
        }
    }


    private boolean movimiento_Valido(int PosicionFinal){
        return PosicionFinal >= 0 && PosicionFinal <= 25;
    }


}
