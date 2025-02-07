import javax.swing.*;

public class proyectoFinal{
    public static void main(String[] args) {

        JFrame frame = new JFrame("Mi primer proyeto");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(null);

        JLabel etiqueta_anuncio = new JLabel("¡Hola!,");
        JLabel etiqueta_2 = new JLabel("¡chequeo");
        JLabel etiqueta_3 = new JLabel("¡chequeo encima 1");
        JLabel etiqueta_4 = new JLabel("¡chequeo encima 2");
        JButton button = new JButton("Enviar");
        JLabel nombre_usuario = new JLabel("Nombre");
        JTextField textField = new JTextField(20);

        etiqueta_anuncio.setBounds(30, 50, 100, 30);
        etiqueta_2.setBounds(300, 50, 200, 30);
        etiqueta_3.setBounds(30, 90, 100, 30);
        etiqueta_4.setBounds(300, 90, 200, 30);
        button.setBounds(30, 90, 100, 30);
        nombre_usuario.setBounds(50, 180, 200, 30);
        textField.setBounds(200, 160, 200, 30);

        frame.add(etiqueta_anuncio);
        frame.add(etiqueta_2);
        frame.add(etiqueta_3);
        frame.add(etiqueta_4);
        frame.add(button);
        frame.add(nombre_usuario);

        frame.setVisible(true);
    }
}
