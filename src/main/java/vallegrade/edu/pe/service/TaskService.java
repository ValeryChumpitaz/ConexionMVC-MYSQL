package vallegrade.edu.pe.service;
import vallegrade.edu.pe.model.TaskDAO;
import vallegrade.edu.pe.model.TaskEntity;


import java.util.List;


public class TaskService {




    private TaskDAO dao = new TaskDAO();


    public List<TaskEntity> obtenerTareas() {
        return dao.listarTareas();
    }


    public void editarTarea(TaskEntity tarea) {
        dao.actualizarTarea(tarea);
    }
}
