import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private HashMap<Integer, Task> taskList = new HashMap<>();

    public TaskManager() {}

    public HashMap<Integer, Task> getTaskList() {
        return taskList;
    }

    // получим задачу по ид
    public Task getTaskByID(Integer id) {
        return taskList.get(id);
    }

    // получим задачу по названию
    public Task findTaskByTitle(String title) {
        for (Object obj : taskList.keySet()) {
            if (taskList.get(obj).getTitle().equals(title)) {
                return taskList.get(obj);
            }
        }
        return null;
    }

    // добавить задачу в список задач
    public void addNewTask(Task task) {
        taskList.put(task.getId(), task);
    }

    // получить все задачи с типом задача
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Object obj : taskList.keySet()) {
            if (taskList.get(obj).getClass() == Task.class){
                tasks.add(taskList.get(obj));
            }
        }
        return tasks;
    }

    // получить всех эпиков
    public ArrayList<Epic> getAllEpics() {
        ArrayList<Epic> epics = new ArrayList<>();
        for (Object obj : taskList.keySet()) {
            if (taskList.get(obj).getClass() == Epic.class){
                epics.add((Epic) taskList.get(obj));
            }
        }
        return epics;
    }

    // получить все подзадачи
    public ArrayList<SubTask> getAllSubTasks() {
        ArrayList<SubTask> subTasks = new ArrayList<>();
        for (Object obj : taskList.keySet()) {
            if (taskList.get(obj).getClass() ==  SubTask.class){
                subTasks.add((SubTask) taskList.get(obj));
            }
        }
        return subTasks;
    }

    // чистка списка задач
    public void delAllTasks(){
        taskList.clear();
    }

    // удаление задачи из списка
    public void delTask(Task task){
        // если это эпик, то удалим и его подзадачи
        if (taskList.get(task.getId()).getClass() == Epic.class){
            for (SubTask subTask : ((Epic) taskList.get(task.getId())).getSubTaskArrayList()) {
                taskList.remove(subTask.getId());
            }
        // если это подзадача, то удалим из списка эпика со сменой статуса эпика
        } else if (taskList.get(task.getId()).getClass() == SubTask.class) {
            SubTask subTask = (SubTask)taskList.get(task.getId());
            subTask.getEpic().delSubTask(subTask);
        }
        taskList.remove(task.getId());
    }

    // обновление задачи
    public void updateTask(Task oldTask, Task newTask){
        taskList.remove(oldTask.getId());
        oldTask.updateTask(newTask);
        addNewTask(newTask);
    }
}
