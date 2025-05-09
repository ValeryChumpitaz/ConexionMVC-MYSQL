package vallegrade.edu.pe.model;

import vallegrade.edu.pe.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    private Connection connection;

    public TaskDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Crear tarea
    public void crearTarea(Task tarea) {
        if (tarea.getTitulo() == null || tarea.getDescripcion() == null || tarea.getEstado() == null) {
            System.out.println("Error: Hay campos nulos. Asegúrate de llenar todos los campos.");
            return;
        }
        String sql = "INSERT INTO tareas (titulo, descripcion, estado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tarea.getTitulo());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setString(3, tarea.getEstado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Leer tareas
    public List<Task> listarTareas() {
        List<Task> tareas = new ArrayList<>();
        String sql = "SELECT * FROM tareas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task tarea = new Task();
                tarea.setId(rs.getInt("id"));
                tarea.setTitulo(rs.getString("titulo"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setEstado(rs.getString("estado"));
                tareas.add(tarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tareas;
    }

    // Actualizar tarea
    public void actualizarTarea(Task tarea) {
        String sql = "UPDATE tareas SET titulo = ?, descripcion = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tarea.getTitulo());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setString(3, tarea.getEstado());
            stmt.setInt(4, tarea.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar tarea
    public void eliminarTarea(int id) {
        String sql = "DELETE FROM tareas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
