import Alternativa.RepositorioArrayList;
import modelo.Avenger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel Principal;
    private JTabbedPane tabbedPane1;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtMision;
    private JSpinner spinPeligro;
    private JSpinner spinPago;
    private JButton btnRegistrar;
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblMision;
    private JLabel lblPeligro;
    private JLabel lblPago;
    private JTextArea textArea1;
    private JTextField txtIDbuscar;
    private JButton btnBuscar;
    private JButton btnListar;
    private JTextField txtNombreModificar;
    private JTextField txtMisionModificar;
    private JSpinner spinPeligroModificar;
    private JSpinner spinPagoModificar;
    private JButton btnModificar;
    private JLabel lblSeleccionado;
    private JButton mostrarButton;
    RepositorioArrayList repo = new RepositorioArrayList();
    Avenger aux;

    public Ventana() {
        SpinnerNumberModel modelo1 = new SpinnerNumberModel(1, 1, 5, 1);
        spinPeligro.setModel(modelo1);
        SpinnerNumberModel modelo2 = new SpinnerNumberModel(0.0,0.0, null, 1.0);
        spinPago.setModel(modelo2);
        spinPeligroModificar.setModel(modelo1);
        spinPagoModificar.setModel(modelo2);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = txtId.getText();
                String nombre = txtNombre.getText();
                String mision = txtMision.getText();
                if(id.isEmpty() || nombre.isEmpty() || mision.isEmpty()) {
                    JOptionPane.showMessageDialog(Principal, "Por favor, complete todos los campos.");
                    return;
                }
                int peligro = (Integer) spinPeligro.getValue();
                double pagoMensual = (Double) spinPago.getValue();

                Avenger avenger = new Avenger(id, nombre, mision, peligro, pagoMensual);
                boolean registrado = repo.registrar(avenger);
                aux = avenger;
                if (registrado) {
                    JOptionPane.showMessageDialog(Principal, "Avenger registrado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(Principal, "Error al registrar el Avenger. ID duplicado o datos inválidos.");
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                for (Avenger avenger : repo.listar()) {
                    sb.append(avenger).append("\n");
                }
                textArea1.setText(sb.toString());
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIDbuscar.getText();
                if(id.isEmpty()) {
                    JOptionPane.showMessageDialog(Principal, "Por favor, ingrese un ID para buscar.");
                    return;
                }
                Avenger avenger = repo.buscarPorId(id).orElse(null);
                if (avenger != null) {
                    textArea1.setText(avenger.toString());
                } else {
                    textArea1.setText("Avenger no encontrado.");
                }
                lblSeleccionado.setText(avenger != null ? "Avenger seleccionado: " + avenger.getId() : "Avenger no encontrado.");
                aux = avenger;
            }
        });
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aux == null) {
                    JOptionPane.showMessageDialog(Principal, "No hay un Avenger seleccionado para modificar.");
                    return;
                }
                String nombre = txtNombreModificar.getText();
                String mision = txtMisionModificar.getText();
                if(nombre.isEmpty() || mision.isEmpty()) {
                    JOptionPane.showMessageDialog(Principal, "Por favor, complete todos los campos de modificación.");
                    return;
                }
                int peligro = (Integer) spinPeligroModificar.getValue();
                double pagoMensual = (Double) spinPagoModificar.getValue();


                boolean modificado = repo.modificar(aux.getId(), nombre, mision, peligro, pagoMensual);
                if (modificado) {
                    JOptionPane.showMessageDialog(Principal, "Avenger modificado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(Principal, "Error al modificar el Avenger.");
                }
                aux = null;
                lblSeleccionado.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 400);
    }


}

