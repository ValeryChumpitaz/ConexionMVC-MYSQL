package vallegrade.edu.pe.controller;
import vallegrade.edu.pe.model.TaskEntity;
import vallegrade.edu.pe.service.TaskService;


import java.util.List;


public class TaskController {


    private TaskService service = new TaskService();


    public List<TaskEntity> listar() {
        return service.obtenerTareas();
    }


    public void actualizar(TaskEntity tarea) {
        service.editarTarea(tarea);
    }
}
