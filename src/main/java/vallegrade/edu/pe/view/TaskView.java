package vallegrade.edu.pe.view;
import vallegrade.edu.pe.controller.TaskController;
import vallegrade.edu.pe.model.TaskEntity;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;




public class TaskView extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private TaskController controller = new TaskController();


    public TaskView() {
        setTitle("Gestor de Tareas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);


        modelo = new DefaultTableModel(new String[]{"ID", "Título", "Descripción", "Estado"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);


        JButton btnEditar = new JButton("Editar tarea seleccionada");
        btnEditar.addActionListener(e -> editarTarea());


        add(scroll, BorderLayout.CENTER);
        add(btnEditar, BorderLayout.SOUTH);


        cargarDatos();
        setVisible(true);
    }


    private void cargarDatos() {
        modelo.setRowCount(0);
        List<TaskEntity> lista = controller.listar();
        for (TaskEntity t : lista) {
            modelo.addRow(new Object[]{t.getId(), t.getTitulo(), t.getDescripcion(), t.getEstado()});
        }
    }


    private void editarTarea() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            int id = (int) modelo.getValueAt(fila, 0);
            String titulo = JOptionPane.showInputDialog("Nuevo título:", modelo.getValueAt(fila, 1));
            String descripcion = JOptionPane.showInputDialog("Nueva descripción:", modelo.getValueAt(fila, 2));
            String estado = JOptionPane.showInputDialog("Nuevo estado:", modelo.getValueAt(fila, 3));


            TaskEntity tarea = new TaskEntity();
            tarea.setId(id);
            tarea.setTitulo(titulo);
            tarea.setDescripcion(descripcion);
            tarea.setEstado(estado);


            controller.actualizar(tarea);
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea para editar.");
        }
    }
}
