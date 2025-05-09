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
        setTitle("Lista de Tareas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Botones
        JButton btnCrear = new JButton("Crear");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        panel.add(btnCrear);
        panel.add(btnActualizar);
        panel.add(btnEliminar);

        add(panel, BorderLayout.SOUTH);

        btnCrear.addActionListener(e -> crearTarea());
        btnActualizar.addActionListener(e -> actualizarTarea());
        btnEliminar.addActionListener(e -> eliminarTarea());
    }

    public void cargarDatos() {
        String[] columnas = {"ID", "Título", "Descripción", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        List<Task> tareas = controller.listarTareas();

        for (Task tarea : tareas) {
            Object[] fila = {tarea.getId(), tarea.getTitulo(), tarea.getDescripcion(), tarea.getEstado()};
            modelo.addRow(fila);
        }

        table.setModel(modelo);
    }

    private void crearTarea() {
        String titulo = JOptionPane.showInputDialog(this, "Título:");
        String descripcion = JOptionPane.showInputDialog(this, "Descripción:");
        String estado = JOptionPane.showInputDialog(this, "Estado:");

        Task tarea = new Task();
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setEstado(estado);

        controller.crearTarea(tarea);
        cargarDatos();
    }

    private void actualizarTarea() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID de la tarea a actualizar:"));
        String titulo = JOptionPane.showInputDialog(this, "Nuevo título:");
        String descripcion = JOptionPane.showInputDialog(this, "Nueva descripción:");
        String estado = JOptionPane.showInputDialog(this, "Nuevo estado:");

        Task tarea = new Task();
        tarea.setId(id);
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setEstado(estado);

        controller.actualizarTarea(tarea);
        cargarDatos();
    }

    private void eliminarTarea() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID de la tarea a eliminar:"));
        controller.eliminarTarea(id);
        cargarDatos();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TaskView().setVisible(true);
        });
    }
}
