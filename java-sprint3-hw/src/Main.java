import java.util.ArrayList;

public class Main {
    public static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        // добавить задачи
        System.out.println("----------Добавим 2 эпика, подзадачи, задачу и выведем список");
        addAllTasks();
        // вывести списки (задачи, эпики, подзадачи) задач
        printAllTasks();

        // удалим все задачи
        System.out.println("----------Удалим задачи и выведем количество оставшихся задач...");
        taskManager.delAllTasks();
        System.out.println("...количество оставшихся задач = " + taskManager.getTaskList().size());

        // добавим снова задачи
        System.out.println("----------Добавим снова те же задачи (2 эпика, подзадачи, задачу).");
        addAllTasks();

        System.out.println("----------Добавим эпик3, подзадачу 3.1 и заменим эпик 1 на эпик 3...");
        // создадим третий эпик с подзадачей для замены первого
        Epic epicThird = new Epic(Task.getNewId(),"epic 3", "epic 3");
        taskManager.addNewTask(new SubTask(Task.getNewId(),"subtask 3.1", "subtask epic 3", Status.NEW, epicThird));
        // заменим первый на третий эпик
        Epic foundFirstEpic = (Epic) taskManager.findTaskByTitle("epic 1");
        taskManager.updateTask(foundFirstEpic, epicThird);
        System.out.println("... список после апдэйта 3-го эпика...");
        printAllTasks();

        // поменяем статус у подзадачи и проверим статус эпика до и после
        System.out.println("----------Поменяем статус у подзадачи и проверим статус эпика до и после...");
        Epic epic2 = (Epic)taskManager.findTaskByTitle("epic 2");
        System.out.println("...эпик 2 - " + epic2);
        System.out.println("...статус эпика 2 до изменения статуса подзадачи = " + epic2.getStatus());
        if (epic2.getSubTaskArrayList().size() > 0){
            SubTask subTaskEpic2 = epic2.getSubTaskArrayList().get(0);
            subTaskEpic2.setStatus(Status.IN_PROGRESS);
        }
        System.out.println("...статус эпика 2 после изменения статуса подзадачи = " + epic2.getStatus());
        System.out.println("...эпик 2 - " + epic2);

        /* удалим подзадачу IN_PROGRESS у эпика и проверим его статус
        останется одна задача со статусом NEW и эпик также должен изменить статус на NEW */
        System.out.println("удалим подзадачу IN_PROGRESS у эпика 2 и проверим его статус до и после...");
        System.out.println("...статус эпика 2 до удаления подзадачи = " + epic2.getStatus());
        if (epic2.getSubTaskArrayList().size() > 0) {
            SubTask subTaskEpic2 = epic2.getSubTaskArrayList().get(0);
            taskManager.delTask(subTaskEpic2);
        }
        System.out.println("...статус эпика 2 после удаления подзадачи = " + epic2.getStatus());
        System.out.println("...эпик 2 - " + epic2);
    }

    /* добавим два эпика с подзадачами
    и одну простую задачу */
    public static void addAllTasks(){
        Epic epic1 = new Epic(Task.getNewId(),"epic 1", "epic 1");
        taskManager.addNewTask(epic1);
        taskManager.addNewTask(new SubTask(Task.getNewId(),"subtask 1.1", "subtask 1.1", Status.NEW, epic1));
        taskManager.addNewTask(new SubTask(Task.getNewId(),"subtask 1.2", "subtask 1.2", Status.NEW, epic1));

        Epic epic2 = new Epic(Task.getNewId(),"epic 2", "epic 2");
        taskManager.addNewTask(epic2);
        taskManager.addNewTask(new SubTask(Task.getNewId(),"subtask 2.1", "subtask 2.1", Status.NEW, epic2));
        taskManager.addNewTask(new SubTask(Task.getNewId(),"subtask 2.2", "subtask 2.2", Status.NEW, epic2));

        taskManager.addNewTask(new Task(Task.getNewId(),"task 1", "task 1", Status.NEW));
    }

    /* выведем в консоль отдельно
    задачи, эпики, подзадачи */
    public static void printAllTasks(){
        ArrayList<Task> tasks = taskManager.getAllTasks();
        System.out.println("Tasks: " + tasks);

        ArrayList<Epic> epics = taskManager.getAllEpics();
        System.out.println("Epics: " + epics);

        ArrayList<SubTask> subTasks = taskManager.getAllSubTasks();
        System.out.println("SubTasks: " + subTasks);
    }

}
