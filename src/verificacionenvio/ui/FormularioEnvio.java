package verificacionenvio.ui;

import javax.swing.*;
import java.awt.*;
import verificacionenvio.dto.Paquete;
import verificacionenvio.impl.*;
import verificacionenvio.spec.Verificador;

public class FormularioEnvio extends JFrame {

    private final JTextField pesoField = new JTextField();
    private final JTextField largoField = new JTextField();
    private final JTextField anchoField = new JTextField();
    private final JTextField altoField = new JTextField();
    private final JTextField contenidoField = new JTextField();
    private final JTextField destinoField = new JTextField();
    //private final JCheckBox docCompletaCheckBox = new JCheckBox("Documentación Completa");
    private final JTextArea resultadoArea = new JTextArea(8, 40);
    private final JButton verificarButton = new JButton("Verificar Envío");

    public FormularioEnvio() {
        super("Verificación de Envío Internacional");
        configurarVentana();
        inicializarComponentes();
        configurarEventos();
        setVisible(true);
    }

    private void configurarVentana() {
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        agregarCampo(panel, gbc, "Peso (kg):", pesoField);
        agregarCampo(panel, gbc, "Largo (cm):", largoField);
        agregarCampo(panel, gbc, "Ancho (cm):", anchoField);
        agregarCampo(panel, gbc, "Alto (cm):", altoField);
        agregarCampo(panel, gbc, "Contenido:", contenidoField);
        agregarCampo(panel, gbc, "País de destino:", destinoField);

        gbc.gridx = 1;
        gbc.gridy++;
       // panel.add(docCompletaCheckBox, gbc);

        gbc.gridy++;
        panel.add(verificarButton, gbc);

        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Resultado:"), gbc);

        gbc.gridy++;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        add(panel);
    }

    private void agregarCampo(JPanel panel, GridBagConstraints gbc, String label, JTextField field) {
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
        gbc.gridy++;
    }

    private void configurarEventos() {
        verificarButton.addActionListener(e -> procesarFormulario());
    }

    private void procesarFormulario() {
        try {
            double peso = Double.parseDouble(pesoField.getText());
            double largo = Double.parseDouble(largoField.getText());
            double ancho = Double.parseDouble(anchoField.getText());
            double alto = Double.parseDouble(altoField.getText());
            String contenido = contenidoField.getText();
            String destino = destinoField.getText();
           // boolean docCompleta = docCompletaCheckBox.isSelected();

            Paquete paquete = new Paquete(peso, largo, ancho, alto, contenido,  destino);

            Verificador campos = new VerificadorCamposObligatorios();
            Verificador aduana = new AduanaLocal();
            Verificador dimensiones = new VerificadorDimensiones();
            //Verificador documentacion = new DocumentacionInternacional();
            Verificador controlDestino = new ControlDestino();

            campos.setSiguiente(aduana);
            aduana.setSiguiente(dimensiones);
            dimensiones.setSiguiente(controlDestino);
            //documentacion.setSiguiente(controlDestino);

            String resultado = campos.verificar(paquete);
            resultadoArea.setText(resultado);

        } catch (NumberFormatException ex) {
            resultadoArea.setText("⚠ Error: Todos los campos numéricos deben contener valores válidos.");
        }
    }
}
