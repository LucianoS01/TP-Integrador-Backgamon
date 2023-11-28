package Vista;



import Controlador.Controlador;
import Modelo.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

public class VistaJugadorNegro extends JFrame implements IObserver {
    private JTextArea textArea;
    private JButton button;
    private JTextField textField;

    Controlador control;
    private PrintStream outputPrintStream;


    public VistaJugadorNegro(Controlador control) {

        this.control = control;

        initializeUI();
    }
    private void initializeUI() {
        setTitle("Backgammon Game - Jugador Negro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        textField = new JTextField();
        add(textField, BorderLayout.SOUTH);

        JButton buttonAgregarTexto = new JButton("Agregar Texto");
        add(buttonAgregarTexto, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Acción del botón
        buttonAgregarTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarTexto();
            }
        });
    }

    public String agregarTexto() {
        String textoIngresado = textField.getText();
        textArea.append("Texto ingresado: " + textoIngresado + "\n");
        textField.setText("");
        return textoIngresado;
    }

    @Override
    public void Actualizar(String message) {
        textArea.append(message + "\n");

        // Hacer que el scroll baje automáticamente
        int length = textArea.getDocument().getLength();
        textArea.setCaretPosition(length);

        System.out.println("Vista Jugador Negro actualizada: " + message);
    }

    public void setOutputPrintStream(PrintStream outputPrintStream) {
        this.outputPrintStream = outputPrintStream;
    }
}