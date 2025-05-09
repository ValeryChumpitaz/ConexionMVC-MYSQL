package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.Task;
import vallegrade.edu.pe.service.TaskService;

import java.util.List;

public class TaskController {
    private TaskService taskService;

    public TaskController() {
        this.taskService = new TaskService();
    }

    // Crear tarea
    public void crearTarea(Task tarea) {
        taskService.crearTarea(tarea);
    }

    // Obtener tareas
    public List<Task> listarTareas() {
        return taskService.obtenerTareas();
    }

    // Actualizar tarea
    public void actualizarTarea(Task tarea) {
        taskService.actualizarTarea(tarea);
    }

    // Eliminar tarea
    public void eliminarTarea(int id) {
        taskService.eliminarTarea(id);
    }
}
