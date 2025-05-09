package vallegrade.edu.pe.service;

import vallegrade.edu.pe.model.Task;
import vallegrade.edu.pe.model.TaskDAO;

import java.util.List;

public class TaskService {
    private TaskDAO taskDAO;

    public TaskService() {
        this.taskDAO = new TaskDAO();
    }

    // Crear tarea
    public void crearTarea(Task tarea) {
        taskDAO.crearTarea(tarea);
    }

    // Obtener todas las tareas
    public List<Task> obtenerTareas() {
        return taskDAO.listarTareas();
    }

    // Actualizar tarea
    public void actualizarTarea(Task tarea) {
        taskDAO.actualizarTarea(tarea);
    }

    // Eliminar tarea
    public void eliminarTarea(int id) {
        taskDAO.eliminarTarea(id);
    }
}
