package vallegrade.edu.pe.view;

import vallegrade.edu.pe.controller.TaskController;
import vallegrade.edu.pe.model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TaskView extends JFrame {
    private JTable table;
    private TaskController controller;

    public TaskView() {
        controller = new TaskController();
        setTitle("Gestión de Tareas");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        // Panel superior con título y color
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(33, 150, 243));  // Azul Google
        panelTitulo.setLayout(new BorderLayout());
        JLabel lblTitulo = new JLabel("Lista de Tareas");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);
        add(panelTitulo, BorderLayout.NORTH);

        // Tabla
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel botones con color de fondo y FlowLayout centrado
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(245, 245, 245));  // Gris claro
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));

        // Crear botones con iconos y colores
        JButton btnCrear = new JButton(" Crear");
        btnCrear.setIcon(UIManager.getIcon("FileView.fileIcon"));
        btnCrear.setBackground(new Color(76, 175, 80));  // Verde
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFocusPainted(false);
        btnCrear.setPreferredSize(new Dimension(130, 35));

        JButton btnActualizar = new JButton(" Actualizar");
        btnActualizar.setIcon(UIManager.getIcon("FileChooser.detailsViewIcon"));
        btnActualizar.setBackground(new Color(33, 150, 243));  // Azul
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setPreferredSize(new Dimension(130, 35));

        JButton btnEliminar = new JButton(" Eliminar");
        btnEliminar.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
        btnEliminar.setBackground(new Color(244, 67, 54));  // Rojo
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setPreferredSize(new Dimension(130, 35));

        panelBotones.add(btnCrear);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        // Eventos botones
        btnCrear.addActionListener(e -> crearTarea());
        btnActualizar.addActionListener(e -> actualizarTarea());
        btnEliminar.addActionListener(e -> eliminarTarea());
    }

    public void cargarDatos() {
        String[] columnas = {"ID", "Título", "Descripción", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        List<Task> tareas = controller.listarTareas();

        for (Task tarea : tareas) {
            Object[] fila = {tarea.getId(), tarea.getTitulo(), tarea.getDescripcion(), tarea.getEstado()};
            modelo.addRow(fila);
        }

        table.setModel(modelo);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);
    }

    private void crearTarea() {
        String titulo = JOptionPane.showInputDialog(this, "Título:");
        if (titulo == null || titulo.trim().isEmpty()) return;

        String descripcion = JOptionPane.showInputDialog(this, "Descripción:");
        if (descripcion == null) descripcion = "";

        String estado = JOptionPane.showInputDialog(this, "Estado:");
        if (estado == null || estado.trim().isEmpty()) estado = "Pendiente";

        Task tarea = new Task();
        tarea.setTitulo(titulo.trim());
        tarea.setDescripcion(descripcion.trim());
        tarea.setEstado(estado.trim());

        controller.crearTarea(tarea);
        cargarDatos();
    }

    private void actualizarTarea() {
        String idStr = JOptionPane.showInputDialog(this, "ID de la tarea a actualizar:");
        if (idStr == null || idStr.trim().isEmpty()) return;
        int id;
        try {
            id = Integer.parseInt(idStr.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String titulo = JOptionPane.showInputDialog(this, "Nuevo título:");
        if (titulo == null || titulo.trim().isEmpty()) return;

        String descripcion = JOptionPane.showInputDialog(this, "Nueva descripción:");
        if (descripcion == null) descripcion = "";

        String estado = JOptionPane.showInputDialog(this, "Nuevo estado:");
        if (estado == null || estado.trim().isEmpty()) estado = "Pendiente";

        Task tarea = new Task();
        tarea.setId(id);
        tarea.setTitulo(titulo.trim());
        tarea.setDescripcion(descripcion.trim());
        tarea.setEstado(estado.trim());

        controller.actualizarTarea(tarea);
        cargarDatos();
    }

    private void eliminarTarea() {
        String idStr = JOptionPane.showInputDialog(this, "ID de la tarea a eliminar:");
        if (idStr == null || idStr.trim().isEmpty()) return;
        int id;
        try {
            id = Integer.parseInt(idStr.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Seguro que desea eliminar la tarea con ID " + id + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            controller.eliminarTarea(id);
            cargarDatos();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Aplicar look and feel nativo para mejor apariencia
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new TaskView().setVisible(true);
        });
    }
}
