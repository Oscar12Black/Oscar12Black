import javax.swing.*;  
import javax.swing.table.DefaultTableModel;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.io.*;  
  
public class proyectoFinal extends JFrame {  
    private JTextField nameField;  
    private JTextField phoneField;  
    private JTextField locationField;  
    private JTextField dateField;  
    private JTextField timeField;  
    private JTable eventTable;  
    private DefaultTableModel tableModel;  
  
    public proyectoFinal() {  
        setTitle("Agenda de Eventos");  
        setSize(600, 400);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(new BorderLayout());  
  
        // Panel de entrada  
        JPanel inputPanel = new JPanel();  
        inputPanel.setLayout(new GridLayout(6, 2));  
  
        inputPanel.add(new JLabel("Nombre:"));  
        nameField = new JTextField();  
        inputPanel.add(nameField);  
  
        inputPanel.add(new JLabel("Teléfono:"));  
        phoneField = new JTextField();  
        inputPanel.add(phoneField);  
  
        inputPanel.add(new JLabel("Localidad:"));  
        locationField = new JTextField();  
        inputPanel.add(locationField);  
  
        inputPanel.add(new JLabel("Fecha:"));  
        dateField = new JTextField();  
        inputPanel.add(dateField);  
  
        inputPanel.add(new JLabel("Hora de Inicio:"));  
        timeField = new JTextField();  
        inputPanel.add(timeField);  
  
        JButton addButton = new JButton("Agregar");  
        addButton.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                agregarEvento();  
            }  
        });  
        inputPanel.add(addButton);  
  
        JButton editButton = new JButton("Editar");  
        editButton.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                editarEvento();  
            }  
        });  
        inputPanel.add(editButton);  
  
        // Panel de fondo  
        BackgroundPanel backgroundPanel = new BackgroundPanel();  
        backgroundPanel.setLayout(new BorderLayout());  
        backgroundPanel.add(inputPanel, BorderLayout.NORTH);  
  
        // Tabla de eventos  
        tableModel = new DefaultTableModel(new String[]{"Nombre", "Teléfono", "Localidad", "Fecha", "Hora"}, 0);  
        eventTable = new JTable(tableModel);  
        backgroundPanel.add(new JScrollPane(eventTable), BorderLayout.CENTER);  
  
        setContentPane(backgroundPanel);  
  
        // Cargar datos guardados (si los hay)  
        cargarDatosGuardados();  
    }  
  
    private void agregarEvento() {  
        String name = nameField.getText();  
        String phone = phoneField.getText();  
        String location = locationField.getText();  
        String date = dateField.getText();  
        String time = timeField.getText();  
        tableModel.addRow(new Object[]{name, phone, location, date, time});  
        guardarDatos();  
        limpiarCampos();  
        JOptionPane.showMessageDialog(this, "Contacto agregado!");  
    }  
  
    private void editarEvento() {  
        int selectedRow = eventTable.getSelectedRow();  
        if (selectedRow != -1) {  
            nameField.setText((String) tableModel.getValueAt(selectedRow, 0));  
            phoneField.setText((String) tableModel.getValueAt(selectedRow, 1));  
            locationField.setText((String) tableModel.getValueAt(selectedRow, 2));  
            dateField.setText((String) tableModel.getValueAt(selectedRow, 3));  
            timeField.setText((String) tableModel.getValueAt(selectedRow, 4));  
            tableModel.removeRow(selectedRow);  
            guardarDatos();  
        } else {  
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un contacto para editar.");  
        }  
    }  
  
    private void cargarDatosGuardados() {  
        try (BufferedReader br = new BufferedReader(new FileReader("eventos.txt"))) {  
            String line;  
            while ((line = br.readLine()) != null) {  
                String[] data = line.split(",");  
                tableModel.addRow(data);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    private void guardarDatos() {  
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("eventos.txt"))) {  
            for (int i = 0; i < tableModel.getRowCount(); i++) {  
                StringBuilder row = new StringBuilder();  
                for (int j = 0; j < tableModel.getColumnCount(); j++) {  
                    row.append(tableModel.getValueAt(i, j)).append(",");  
                }  
                bw.write(row.toString());  
                bw.newLine();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    private void limpiarCampos() {  
        nameField.setText("");  
        phoneField.setText("");  
        locationField.setText("");  
        dateField.setText("");  
        timeField.setText("");  
    }  
  
    public static void main(String[] args) {  
        proyectoFinal agenda = new proyectoFinal();  
        agenda.setVisible(true);  
    }  
  
    // Clase para el panel de fondo  
    class BackgroundPanel extends JPanel {  
        private Image backgroundImage;  
  
        public BackgroundPanel() {  
            // Cargar la imagen de fondo  
            backgroundImage = new ImageIcon("resources/elegante-sombrero-charro-tradicional-fondo-nopales-cactus.jpg").getImage(); // Cambia la ruta a tu imagen  
        }  
  
        @Override  
        protected void paintComponent(Graphics g) {  
            super.paintComponent(g);  
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);  
        }  
    }  
}  
