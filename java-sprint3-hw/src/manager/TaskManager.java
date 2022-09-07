package manager;

import tasks.Epic;
import tasks.Status;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private static int idCounter;
    private final HashMap<Integer, Task> taskList = new HashMap<>();

    private static int getNewId() {
        return ++idCounter;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(taskList.values());
    }

    public int addNewTask(String title, String description){
        Task newTask = new Task(getNewId(), title, description, Status.NEW);
        taskList.put(newTask.getId(), newTask);
        return newTask.getId();
    }

    public int addNewEpic(String title, String description){
        Epic newTask = new Epic(getNewId(), title, description);
        taskList.put(newTask.getId(), newTask);
        return newTask.getId();
    }

    public int addNewSubTask(String title, String description, Epic epic){
        SubTask newTask = new SubTask(getNewId(), title, description, Status.NEW, epic);
        taskList.put(newTask.getId(), newTask);
        return newTask.getId();
    }

    public void updateTask(Task task) {
        taskList.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        taskList.put(epic.getId(), epic);
    }

    public void updateSubTask(SubTask subTask) {
        taskList.put(subTask.getId(), subTask);
    }

    public Task getTaskByID(int id){
        return taskList.get(id);
    }

    public Epic getEpicByID(int id){
        return (Epic) taskList.get(id);
    }

    public SubTask getSubTaskByID(int id){
        return (SubTask) taskList.get(id);
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Object obj : taskList.keySet()) {
            if (taskList.get(obj).getClass() == Task.class) {
                tasks.add(taskList.get(obj));
            }
        }
        return tasks;
    }

    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> epics = new ArrayList<>();
        for (Object obj : taskList.keySet()) {
            if (taskList.get(obj).getClass() == Epic.class) {
                epics.add((Epic) taskList.get(obj));
            }
        }
        return epics;
    }

    public ArrayList<SubTask> getSubTasks() {
        ArrayList<SubTask> subTasks = new ArrayList<>();
        for (Object obj : taskList.keySet()) {
            if (taskList.get(obj).getClass() == SubTask.class) {
                subTasks.add((SubTask) taskList.get(obj));
            }
        }
        return subTasks;
    }

    public void delAllTasks() {
        ArrayList<Task> tasks = getTasks();
        for (Task task : tasks) {
            delTaskByID(task.getId());
        }
    }

    public void delTaskByID(int id){
        taskList.remove(id);
    }

    public void delAllEpics() {
        ArrayList<Epic> epics = getEpics();
        for (Epic epic : epics) {
            delEpicByID(epic.getId());
        }
    }

    public void delEpicByID(int id){
        for (SubTask subTask : ((Epic) taskList.get(id)).getSubTaskArrayList()) {
            taskList.remove(subTask.getId());
        }
        taskList.remove(id);
    }

    public void delAllSubTasks() {
        ArrayList<SubTask> subTasks = getSubTasks();
        for (Task subTask : subTasks) {
            delSubTaskByID(subTask.getId());
        }
    }

    public void delSubTaskByID(int id){
        SubTask subTask = (SubTask) taskList.get(id);
        subTask.getEpic().delSubTask(subTask);
        taskList.remove(id);
    }
}
