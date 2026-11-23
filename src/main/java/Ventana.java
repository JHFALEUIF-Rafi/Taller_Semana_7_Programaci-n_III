import javax.swing.*;

public class Ventana {
    private JPanel Principal;
    private JTabbedPane tabbedPane1;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtMision;
    private JSpinner spinPeligro;
    private JSpinner spinPago;
    private JButton REGISTRARButton;
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblMision;
    private JLabel lblPeligro;
    private JLabel lblPago;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

