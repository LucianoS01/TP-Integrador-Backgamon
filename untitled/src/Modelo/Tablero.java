package Modelo;

import java.util.List;

public class Tablero {
    private int [] posiciones;
    private Color turno;
    private  List<Ficha> fichas_del_Tablero;

    private List <Jugador> jugadores;

    private Jugador JugadorBlanco;
    private Jugador JugadorNegro;

    boolean comio;



    public Tablero(List<Ficha> fichas_del_Tablero, Jugador jugadorBlanco, Jugador jugadorNegro) {

        posiciones = new int[24];
        this.fichas_del_Tablero = fichas_del_Tablero;
        turno = Color.BLANCAS;

        this.JugadorBlanco = jugadorBlanco;
        this.JugadorNegro = jugadorNegro;

        comio = false;
    }

    public int[] getPosiciones() {
        return posiciones;
    }

    public List<Ficha> getFichas_del_Tablero() {
        return fichas_del_Tablero;
    }

    public void mostrarTablero() {
        // Muestra el estado actual del tablero en la consola.
        // Puedes personalizar la representación gráfica según tus necesidades.
        for (int i = 0; i < posiciones.length; i++) {
            System.out.print("Punto " + (i + 1) + ": ");
            if (posiciones[i] > 0) {
                // Imprime fichas blancas.
                for (int j = 0; j < posiciones[i]; j++) {
                    System.out.print("B");
                }
            } else if (posiciones[i] < 0) {
                // Imprime fichas negras.
                for (int j = 0; j < -posiciones[i]; j++) {
                    System.out.print("N");
                }
            }
            System.out.println(); // Salto de línea después de cada punto.
        }


    }
    public void actualizarTablero(List<Ficha> fichasConPosiciones) {
        // Reinicia el tablero (borra todas las fichas actuales).
        for (int i = 0; i < posiciones.length; i++) {
            posiciones[i] = 0;
        }

        // Coloca las fichas en el tablero según las posiciones de la lista.
        for (Ficha ficha : fichasConPosiciones) {
            int posicion = ficha.getPosicion();
            if (posicion >= 1 && posicion <= posiciones.length) {
                posiciones[posicion - 1] += (ficha.getJugador() == Color.BLANCAS) ? 1 : -1;
            }
        }
    }


    public  boolean RealizarMovimientos(Jugador jugador, int origen,int dado1, int dado2, int SumaDados){
        // Busca la ficha en el punto de origen.
        Ficha ficha = null;
        for (Ficha f : fichas_del_Tablero) {
            if (f.getPosicion() == origen) {
                ficha = f;
                break;
            }
        }
        boolean EntroCajon = false;
        if (ficha != null) {
            // Verificamos que la ficha que selecciono el usuario no sea del reival.
            if (ficha.getJugador() != jugador.getColor()) {
                System.out.println("La ficha seleccionada no pertenece al jugador actual.");
                return false;
            }
            int destino = calcularDestino(origen, dado1, dado2, jugador.getColor());

           // int destino = SumaDados;

            // Verifica si el movimiento es válido.
            if (esMovimientoValido(jugador,origen,destino, dado1, dado2)) {
                // Verifica si hay una ficha del oponente en el destino.
                if (PuedeComer(destino, jugador.getColor())) {
                    Ficha fichaAComer = null;
                    Color ColorFicha = null;
                    int posicionFicha = 0;
                    for (Ficha f : fichas_del_Tablero) {
                        if (f.getPosicion() == destino) {
                            fichaAComer = f;
                            posicionFicha = fichaAComer.getPosicion();
                            ColorFicha = fichaAComer.getJugador();
                            break;
                        }
                    }
                    if (fichaAComer != null) {
                        jugador.getBarra().add(fichaAComer);
                        fichas_del_Tablero.remove(fichaAComer);
                    }
                    ficha.mover(dado1 + dado2);
                    //comio = true;
                    //Comioo(ColorFicha);
                    System.out.println("Modelo.Ficha  " + posicionFicha + "Del jugador color " + fichaAComer.getJugador() + " enviada a la barra.");
                } else {
                    // Realiza el movimiento normal.
                    ficha.mover(dado1 + dado2);
                    // Avisa cuando se agrega al cajón!!.
                    if ((ficha.getPosicion() == 25 || ficha.getPosicion() == 0) && jugador.getColor() == Color.NEGRAS) {
                        // Modelo.Ficha negra ha salido del tablero.
                        jugador.getCajonDeFichas().add(ficha);
                        jugador.getFichas().remove(ficha);
                        System.out.println("Se agregó una ficha al cajón de Negras.");

                    } else if ((ficha.getPosicion() == 25 || ficha.getPosicion() == 0) && jugador.getColor() == Color.BLANCAS) {
                        // Modelo.Ficha blanca ha salido del tablero.
                        jugador.getCajonDeFichas().add(ficha);
                        jugador.getFichas().remove(ficha);
                        System.out.println("Se agregó una ficha al cajón de Blancas.");
                    } else {
                        System.out.println("Movimiento exitoso desde el punto " + origen + " al punto " + ficha.getPosicion() + ".");
                    }
                    jugador.MostrarFichasBarra();
                    System.out.println("La cantidad de fichas en las barras son: " + jugador.Cantidad_Fichas_Barra());
                    System.out.println("las fichas que estan en la barra son: ");
                    jugador.MostrarFichasBarra();
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




    public Color  Comioo (Color color){
        return color;
    }



    public String obtenerRepresentacionTablero() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < posiciones.length; i++) {
            stringBuilder.append("Punto ").append(i + 1).append(": ");

            if (posiciones[i] > 0) {
                for (int j = 0; j < posiciones[i]; j++) {
                    stringBuilder.append("B");
                }
            } else if (posiciones[i] < 0) {
                for (int j = 0; j < -posiciones[i]; j++) {
                    stringBuilder.append("N");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    public String dibujarTablero() {
        // Reinicia el tablero (borra todas las fichas actuales).
        for (int i = 0; i < posiciones.length; i++) {
            posiciones[i] = 0;
        }


        // Coloca las fichas en el tablero según las posiciones de las fichas de ambos jugadores.
        for (Ficha ficha : fichas_del_Tablero) {
            int posicion = ficha.getPosicion();
            if (posicion >= 1 && posicion <= posiciones.length) {
                posiciones[posicion - 1] += (ficha.getJugador() == Color.BLANCAS) ? 1 : -1;
            }
        }

        int l = 1;
        // Muestra el estado actual del tablero en la consola.
        for (int i = 0; i < posiciones.length; i++) {

            if (i == 6 || i == 12  || i == 25 || i == 18 ){
                System.out.println("Cuadrante: "+ l);
                l = l + 1;
            }
            System.out.print("Punto " + (i + 1) + ": ");
            if (posiciones[i] > 0) {
                // Imprime fichas blancas.
                for (int j = 0; j < posiciones[i]; j++) {
                    System.out.print("B");
                }
            } else if (posiciones[i] < 0) {
                // Imprime fichas negras.
                for (int j = 0; j < -posiciones[i]; j++) {
                    System.out.print("N");
                }
            }

            System.out.println(); // Salto de línea después de cada punto.
        }
        System.out.println("Cuadrante: "+ l);
        return null;
    }

    public String dibujarTableroo() {

        String s;
        // Reinicia el tablero (borra todas las fichas actuales).
        for (int i = 0; i < posiciones.length; i++) {
            posiciones[i] = 0;
        }

        // Coloca las fichas en el tablero según las posiciones de las fichas de ambos jugadores.
        for (Ficha ficha : fichas_del_Tablero) {
            int posicion = ficha.getPosicion();
            if (posicion >= 1 && posicion <= posiciones.length) {
                posiciones[posicion - 1] += (ficha.getJugador() == Color.BLANCAS) ? 1 : -1;
            }
        }
        int l = 1;
        // Construye la representación del tablero como una cadena.
        StringBuilder tableroString = new StringBuilder();
        tableroString.append("******************************* " + "\n");
        for (int i = 0; i < posiciones.length; i++) {
            if (i == 6 || i == 12  || i == 18 ){
                tableroString.append("* * * Cuadrante: "+ l + "\n");
                l = l + 1;
            }
            tableroString.append("Punto ").append(i + 1).append(": ");
            if (posiciones[i] > 0) {
                // Agrega fichas blancas a la cadena.
                for (int j = 0; j < posiciones[i]; j++) {
                    tableroString.append("B");
                }
            } else if (posiciones[i] < 0) {
                // Agrega fichas negras a la cadena.
                for (int j = 0; j < -posiciones[i]; j++) {
                    tableroString.append("N");
                }
            }
            tableroString.append("\n"); // Salto de línea después de cada punto.
        }
        tableroString.append("* * * Cuadrante: "+ l + "\n");
        tableroString.append("******************************* ");
        // Retorna la cadena representando el tablero.
       // return tableroString.toString();

        s= tableroString.toString();
        return s;
    }





    public Jugador cambiarTurno(){
        if (turno == Color.BLANCAS){
            turno = Color.NEGRAS;
            return JugadorNegro;
        }
        else{
            turno = Color.BLANCAS;
            return JugadorBlanco;
        }

    }

    public Color getTurno() {
        return turno;
    }


    public void mostrarFichasEnTablero() {
        System.out.println("Fichas en el tablero:");
        for (Ficha ficha : fichas_del_Tablero) {
            System.out.println("Posición: " + ficha.getPosicion() + ", Modelo.Jugador: " + ficha.getJugador());
        }
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

        for (Ficha ficha : fichas_del_Tablero) {
            if ( posicion == ficha.getPosicion() && ficha.getJugador() == colorrival){
                CanidadDeFichas = CanidadDeFichas + 1;
            }

            if (CanidadDeFichas == 1){
                return true;
            }
        }
        return false ;
    }
    /*/
    public boolean CasilleroOcupado(int posicion, Color colorficha){
        Color colorrival;
        int CanidadDeFichas = 0;
        if (colorficha == Color.BLANCAS){
            colorrival = Color.NEGRAS;
        }
        else{
            colorrival = Color.BLANCAS;
        }

        for (Ficha ficha : fichas_del_Tablero) {
            if ( posicion == ficha.getPosicion() && ficha.getJugador() == colorrival){
               CanidadDeFichas = CanidadDeFichas + 1;
               if (CanidadDeFichas > 1){
                   return true;
               }

            }
        }
        return false ;
    }


     */

    public boolean CasilleroOcupado(int posicion, Color colorficha) {
        Color colorrival;
        int CantidadDeFichas = 0;

        if (colorficha == Color.BLANCAS) {
            colorrival = Color.NEGRAS;
        } else {
            colorrival = Color.BLANCAS;
        }

        int CantidadFichasColorPropio = 0;

        for (Ficha ficha : fichas_del_Tablero) {
            if (posicion == ficha.getPosicion()) {
                if (ficha.getJugador() == colorrival) {
                    CantidadDeFichas++;
                    if (CantidadDeFichas > 1) {
                        return true;
                    }
                }

                if (ficha.getJugador() == colorficha) {
                    CantidadFichasColorPropio++;
                }
            }
        }

        // Condición adicional para verificar si hay más de 5 fichas propias en la posición.
        if (CantidadFichasColorPropio >= 5) {
            return true;
        }

        return false;
    }
    public boolean esMovimientoValido(Jugador jugador, int origen,int destino,int dado1, int dado2) {
        // Verificar que el destino esté dentro del rango de puntos del tablero.
        if (origen < 0 || origen > 25) {
            return false;
        }
        if (CasilleroOcupado(destino,jugador.getColor())){
            System.out.println("El casillero esta ocupadito  por el rivarl: ");
            return false;
        }
        //Mayor a 5 posiciones;
       if ( MayoyA5Posiciones(destino,jugador.getColor())){
           System.out.println(" Error Ya pasaron las 5 Posiciones: ");
           return false;
       }
        int PosicionFinal;
        if (jugador.getColor() == Color.NEGRAS){
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

    private boolean movimiento_Valido(int PosicionFinal){
        return PosicionFinal >= 0 && PosicionFinal <= 25;
    }

    public boolean MayoyA5Posiciones(int posicion, Color colorficha){
        int CanidadDeFichas = 0;
        for (Ficha ficha : fichas_del_Tablero) {
            if ( posicion == ficha.getPosicion() && ficha.getJugador() == colorficha){
                CanidadDeFichas = CanidadDeFichas + 1;
                if (CanidadDeFichas > 5){
                    return true;
                }
            }
        }
        return false ;

    }


    //Calcula el destino pero con la suima de los 2 dados.
    public int calcularDestino(int origen, int dado1, int dado2, Color colorDelJugador) {
        int sumaDeDados = dado1 + dado2;
        if (colorDelJugador == Color.BLANCAS){
            return  origen - sumaDeDados;
        }else {
            return  origen + sumaDeDados;
        }
    }
  //Calcula el destino con la 1 dado por separado.
    public int calcularDestinoSeparados(int origen, int dado, Color colorDelJugador) {
        if (colorDelJugador == Color.BLANCAS){
            return  origen - dado;
        }else {
            return  origen + dado;
        }
    }



    public boolean HayMovimientosSeparados(Jugador jugador, int dado1, int dado2) {
        List<Ficha> fichas = jugador.getFichas();
        // Itera sobre todas las combinaciones posibles de fichas y movimientos
        for (Ficha ficha1 : fichas) {
            for (Ficha ficha2 : fichas) {
                if (ficha1 != ficha2) {
                    // Verifica si al menos uno de los movimientos es válido
                    if (esMovimientoValido(jugador, ficha1.getPosicion(), calcularDestino(ficha1.getPosicion(), dado1, dado2, jugador.getColor()), dado1, dado2)
                            || esMovimientoValido(jugador, ficha2.getPosicion(), calcularDestino(ficha2.getPosicion(), dado1, dado2, jugador.getColor()), dado1, dado2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
    public boolean RealizarMovimientosSeparados(Jugador jugador, int origen1, int origen2, int dado1, int dado2) {
        Ficha ficha1 = null;
        Ficha ficha2 = null;

        // Busca las fichas en los puntos de origen.
        for (Ficha f : fichas_del_Tablero) {
            if (f.getPosicion() == origen1) {
                ficha1 = f;
            } else if (f.getPosicion() == origen2) {
                ficha2 = f;
            }
        }

        // Verifica si las fichas existen y si los movimientos son válidos.
        if (ficha1 != null && ficha2 != null) {
            int destino1 = calcularDestino(origen1, dado1, dado2, jugador.getColor());
            int destino2 = calcularDestino(origen2, dado1, dado2, jugador.getColor());

            // Verifica que ambos movimientos sean válidos.
            if (esMovimientoValido(jugador, origen1, destino1, dado1, dado2) && esMovimientoValido(jugador, origen2, destino2, dado1, dado2)) {
                // Realiza los movimientos.
                ficha1.mover(dado1);
                ficha2.mover(dado2);

                // Muestra información sobre los movimientos realizados.
                System.out.println("Movimiento exitoso desde el punto " + origen1 + " al punto " + ficha1.getPosicion() + ".");
                System.out.println("Movimiento exitoso desde el punto " + origen2 + " al punto " + ficha2.getPosicion() + ".");



                return true;
            } else {
                System.out.println("Movimientos inválidos desde los puntos " + origen1 + " y " + origen2 + ".");
                return false;
            }
        } else {
            System.out.println("No se encontraron fichas en los puntos de origen " + origen1 + " y/o " + origen2 + ".");
            return false;
        }
    }


     */


    public boolean RealizarMovimientosSeparados(Jugador jugador, int origen1, int origen2, int dado1, int dado2) {
        boolean comio = false;
        Ficha ficha1 = null;
        Ficha ficha2 = null;
        // Busca las fichas en los puntos de origen.
        for (Ficha f : fichas_del_Tablero) {
            if (f.getPosicion() == origen1) {
                ficha1 = f;
            } else if (f.getPosicion() == origen2) {
                ficha2 = f;
            }
        }
        // Verifica si las fichas existen y si los movimientos son válidos.
        if (ficha1 != null && ficha2 != null) {

            // Verificamos que la ficha que selecciono el usuario no sea del reival.
            if (ficha1.getJugador() != jugador.getColor() || ficha2.getJugador() != jugador.getColor() ) {
                System.out.println("La ficha seleccionada no pertenece al jugador actual.");
                return false;
            }
            int destino1 = calcularDestinoSeparados(origen1, dado1,  jugador.getColor());
            int destino2 = calcularDestinoSeparados(origen2, dado2, jugador.getColor());
            // Verifica que ambos movimientos sean válidos.
            if (esMovimientoValido(jugador, origen1, destino1, dado1, dado2) && esMovimientoValido(jugador, origen2, destino2, dado1, dado2)) {
                // Realiza los movimientos.
                ficha1.mover(dado1);
                ficha2.mover(dado2);
                // Verifica si hay fichas del oponente en los destinos.
                if (PuedeComer(destino1, jugador.getColor())) {
                    ComerFicha(destino1, jugador);

                }
                if (PuedeComer(destino2, jugador.getColor())) {
                    ComerFicha(destino2, jugador);
                }
                // Muestra información sobre los movimientos realizados.
                System.out.println("Movimiento exitoso desde el punto " + origen1 + " al punto " + ficha1.getPosicion() + ".");
                System.out.println("Movimiento exitoso desde el punto " + origen2 + " al punto " + ficha2.getPosicion() + ".");
                return true;
            } else {
                System.out.println("Movimientos inválidos desde los puntos " + origen1 + " y " + origen2 + ".");
                return false;
            }
        } else {
            System.out.println("No se encontraron fichas en los puntos de origen " + origen1 + " y/o " + origen2 + ".");
            return false;
        }
    }

    private void ComerFicha(int destino, Jugador jugador) {
        Ficha fichaAComer = null;
        int posicionFicha = 0;
        for (Ficha f : fichas_del_Tablero) {
            if (f.getPosicion() == destino) {
                fichaAComer = f;
                posicionFicha = fichaAComer.getPosicion();
                Color ColorFicha = fichaAComer.getJugador();
                break;
            }
        }
        if (fichaAComer != null) {
            jugador.getBarra().add(fichaAComer);
            fichas_del_Tablero.remove(fichaAComer);
        }
        System.out.println("Ficha en el punto " + posicionFicha + " del jugador " + jugador.getColor() + " enviada a la barra.");
    }

}
