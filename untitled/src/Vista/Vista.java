package Vista;

import Controlador.Controlador;
import Modelo.Dado;
import Modelo.Jugador;
import Modelo.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Scanner;

import Modelo.IObserver;

public class Vista extends JFrame implements IObserver {
     Tablero tablero;
    private Controlador controlador;
    private JTextArea textArea;
    private JButton button;
    private JTextArea texto;
     JTextArea textoTablero;  // Nuevo campo para la referencia al JTextArea
    private JTextField textField;
    Jugador jugador;
    Dado dado;

    private PrintStream outputPrintStream;
    public Vista(Controlador control) {
        this.controlador = control;
        initializeUI();
    }


    private void initializeUI() {
        setTitle(" JUEGO Backgammon ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 800));
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para dar a Empezar el juego.
        JButton buttonBienvenida = new JButton("Empezar a Jugar");
        buttonBienvenida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.iniciarJuego();
            }
        });
        add(buttonBienvenida, BorderLayout.NORTH);

        // Botón para tirar dados
        JButton buttonTirarDados = new JButton("Tirar Dados");
        buttonTirarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.LanzarDaditosY_Emxezar();
            }
        });
        add(buttonTirarDados, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

       // textField = new JTextField();
       // add(textField, BorderLayout.SOUTH);

       // JButton buttonAgregarTexto = new JButton("Agregar Texto");
       // add(buttonAgregarTexto, BorderLayout.EAST);
        /*
        // Acción del botón
        buttonAgregarTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acciones relacionadas con el botón
                agregarTexto();
            }
        });
         */

        /*
        // Botón para cambiar turno
        JButton buttonCambiarTurno = new JButton("Cambiar Turno");
        buttonCambiarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.Cambiarturnito();
            }
        });
        add(buttonCambiarTurno, BorderLayout.NORTH);
         */

    }


    public void mostrarMensaje(String mensaje) {
        textArea.append(mensaje + "\n");
    }

    public void mostrarError(String error) {
        textArea.append("Error: " + error + "\n");
    }

    //Elije una origen mediante de Imput.
    public int obtenerOrigenDesdeInput() {
        String input = JOptionPane.showInputDialog(this, "Ingrese el origen:");
        if (input != null && !input.isEmpty()) {
            try {
                //hago un Scroll hacia abajo.
                // Scroleo_haciaAbajo();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                mostrarError("Por favor, ingrese un número válido.");
                //hago un Scroll hacia abajo.
                // Scroleo_haciaAbajo();
            }
        } else {
            mostrarError("No se ingresó un origen válido.");
            //hago un Scroll hacia abajo.
            //Scroleo_haciaAbajo();
        }
        //hago un Scroll hacia abajo.
        //Scroleo_haciaAbajo();
        return -1; // Valor por defecto si hay un error
    }


//Elije una opcion mediante de Imput.
    public int elegirOpcion(String jugadorC, int Dado,int  dado2) {
        String[] opciones = {"Realizar movimiento", "Realizar movimientos separados", "Salir"};
        int opcionSeleccionada = JOptionPane.showOptionDialog(this, "Seleccione una opción:", " SU TURNO :  "+  jugadorC + " Dado 1: "+Dado +" Dado 2:  "+dado2, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        // Ajustamos el índice de la opción (Java Swing devuelve -1 si se cierra la ventana).
        if (opcionSeleccionada == -1) {
            // Si se cierra la ventana, asumimos que el usuario quiere salir.
            System.exit(0);
        } else {
            // Ajustamos el índice de la opción si es diferente de -1.
            opcionSeleccionada++;
            if (opciones[opcionSeleccionada - 1].equals("Salir")) {
                // Cerramos la aplicación.
                System.exit(0);
            }
        }
        return opcionSeleccionada;
    }






    @Override
    public void update(String message) {
        textArea.append(message + "\n");

        // Hacer que el scroll baje automáticamente
        int length = textArea.getDocument().getLength();
        textArea.setCaretPosition(length);
        System.out.println("Vista actualizada: " + message);
    }

















    // Funciones adicionales según sea necesario

    public String agregarTexto5() {
        String textoIngresado = textField.getText();
        if (!textoIngresado.isEmpty()) {
            textArea.append(textoIngresado + "\n");
            textField.setText("");
        }
        return textoIngresado;
    }
    public String agregarTexto() {
        String textoIngresado = textField.getText();
        textArea.append("Texto ingresado: " + textoIngresado + "\n");
        textField.setText("");
        return textoIngresado;
    }

    public int obtenerOrigenDesdeTextField() {
        String textoIngresado = textField.getText();
        if (!textoIngresado.isEmpty()) {
            try {
                int origen = Integer.parseInt(textoIngresado);
                return origen;
            } catch (NumberFormatException e) {
                // Si hay un error al convertir, muestra un mensaje de error.
                JOptionPane.showMessageDialog(this, "Error: Ingrese un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Muestra un mensaje de error si no se ingresó ningún texto.
            JOptionPane.showMessageDialog(this, "Error: No se ingresó un origen válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1; // Valor por defecto si hay un error
    }

    public int agregarTexto3() {
        String textoIngresado = textField.getText();
        int numero = 49;
        numero = Integer.parseInt(textoIngresado);
        // Limpia el campo de texto después de agregar el número.
        textField.setText("");
        return numero;
    }




    public void Bienvenida(){
        System.out.println("****************************************************************************** ");
        System.out.println("                      BIENVENIDO AL JUEGO BACKGAMON " );
        textArea.append("****************************************************************************** " + "\n");
        textArea.append("                      BIENVENIDO AL JUEGO BACKGAMON                        " + "\n");
       // controlador.CVerSiFuncion();
    }

    public void Final(){
        System.out.println("******************************************************************************");
        System.out.println("                      FIN DEL  JUEGO BACKGAMON " );
        System.out.println("******************************************************************************");


        textArea.append("******************************************************************************");
        textArea.append("                      FIN DEL  JUEGO BACKGAMON " );
        textArea.append("******************************************************************************");

    }

    //Muestra el mensaje en la consola.
    public void mostrarMensajeC(String mensaje) {
      System.out.println(mensaje);
    }

    //Muestra el mensaje ERROR en la consola.
    public void mostrarErrorC(String error) {
       // textArea.append("Error: " + error + "\n");
        System.out.println("Error: " + error + "\n");
    }



    public int elegirOpcionConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menú:");
        System.out.println("1. Realizar movimiento");
        System.out.println("2. Realizar movimientos separados");
        System.out.println("4. Salir");

        int opcion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print("Seleccione una opción: ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= 3) {
                    entradaValida = true;
                } else {
                    System.out.println("Opción no válida. Intente nuevamente.");
                }
            } else {
                scanner.nextLine(); // Limpiar el búfer de entrada
                System.out.println("Entrada no válida. Intente nuevamente.");
            }
        }

        return opcion;
    }
    public int obtenerOrigenDesdeConsola() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Ingrese el origen: ");
            String input = scanner.nextLine();

            if (!input.isEmpty()) {
                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    mostrarError("Por favor, ingrese un número válido.");
                }
            } else {
                mostrarError("No se ingresó un origen válido.");
            }
        }
    }




    public void setOutputPrintStream(PrintStream outputPrintStream) {
        this.outputPrintStream = outputPrintStream;
    }



    public void ValorCambiado(String valor){
        textArea.append(valor + "\n");
    }




}
