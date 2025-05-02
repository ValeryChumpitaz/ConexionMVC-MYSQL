package vallegrade.edu.pe.model;


import vallegrade.edu.pe.database.DatabaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TaskDAO {


    public List<TaskEntity> listarTareas() {
        List<TaskEntity> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas";


        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                TaskEntity t = new TaskEntity();
                t.setId(rs.getInt("id"));
                t.setTitulo(rs.getString("titulo"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setEstado(rs.getString("estado"));
                lista.add(t);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return lista;
    }


    public void actualizarTarea(TaskEntity tarea) {
        String sql = "UPDATE tareas SET titulo=?, descripcion=?, estado=? WHERE id=?";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setString(1, tarea.getTitulo());
            pstmt.setString(2, tarea.getDescripcion());
            pstmt.setString(3, tarea.getEstado());
            pstmt.setInt(4, tarea.getId());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
