import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.Status;
import manager.TaskManager;

import java.util.ArrayList;

public class Main {
    public static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        System.out.println("----------Добавим 2 эпика, подзадачи, задачу и выведем список");
        int idEpic1 = taskManager.addNewEpic("epic 1", "epic 1");
        Epic epic1 = taskManager.getEpicByID(idEpic1);
        taskManager.addNewSubTask("subtask 1.1", "subtask 1.1", epic1);

        int idEpic2 = taskManager.addNewEpic("epic 2", "epic 2");
        Epic epic2 = taskManager.getEpicByID(idEpic2);
        taskManager.addNewSubTask("subtask 2.1", "subtask 2.1", epic2);
        taskManager.addNewSubTask("subtask 2.2", "subtask 2.2", epic2);

        taskManager.addNewTask("task 1", "task 1");
        ArrayList<Task> tasks = taskManager.getTasks();
        System.out.println("Tasks: " + tasks);

        ArrayList<Epic> epics = taskManager.getEpics();
        System.out.println("Epics: " + epics);

        ArrayList<SubTask> subTasks = taskManager.getSubTasks();
        System.out.println("SubTasks: " + subTasks);

        System.out.println("----------Поменяем статус у подзадачи и проверим статус эпика до и после...");
        System.out.println("...эпик 2 - " + epic2);
        System.out.println("...статус эпика 2 до изменения статуса подзадачи = " + epic2.getStatus());
        if (epic2.getSubTaskArrayList().size() > 0) {
            SubTask subTaskEpic2 = epic2.getSubTaskArrayList().get(0);
            subTaskEpic2.setStatus(Status.IN_PROGRESS);
        }
        System.out.println("...статус эпика 2 после изменения статуса подзадачи = " + epic2.getStatus());
        System.out.println("...эпик 2 - " + epic2);

        System.out.println("удалим подзадачу IN_PROGRESS у эпика 2 и проверим его статус до и после...");
        System.out.println("...статус эпика 2 до удаления подзадачи = " + epic2.getStatus());
        if (epic2.getSubTaskArrayList().size() > 0) {
            SubTask subTaskEpic2 = epic2.getSubTaskArrayList().get(0);
            taskManager.delSubTaskByID(subTaskEpic2.getId());
        }
        System.out.println("...статус эпика 2 после удаления подзадачи = " + epic2.getStatus());
        System.out.println("...эпик 2 - " + epic2);

        System.out.println("----------Удалим задачи и выведем количество оставшихся задач...");
        taskManager.delAllTasks();
        taskManager.delAllEpics();
        taskManager.delAllSubTasks();
        System.out.println("...количество оставшихся задач = " + taskManager.getAllTasks().size());
    }
}
