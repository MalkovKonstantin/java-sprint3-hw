import java.util.ArrayList;

public class Main {
    public static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        // �������� ������
        System.out.println("----------������� 2 �����, ���������, ������ � ������� ������");
        addAllTasks();
        // ������� ������ (������, �����, ���������) �����
        printAllTasks();

        // ������ ��� ������
        System.out.println("----------������ ������ � ������� ���������� ���������� �����...");
        taskManager.delAllTasks();
        System.out.println("...���������� ���������� ����� = " + taskManager.getTaskList().size());

        // ������� ����� ������
        System.out.println("----------������� ����� �� �� ������ (2 �����, ���������, ������).");
        addAllTasks();

        System.out.println("----------������� ����3, ��������� 3.1 � ������� ���� 1 �� ���� 3...");
        // �������� ������ ���� � ���������� ��� ������ �������
        Epic epicThird = new Epic(Task.getNewId(),"epic 3", "epic 3");
        taskManager.addNewTask(new SubTask(Task.getNewId(),"subtask 3.1", "subtask epic 3", Status.NEW, epicThird));
        // ������� ������ �� ������ ����
        Epic foundFirstEpic = (Epic) taskManager.findTaskByTitle("epic 1");
        taskManager.updateTask(foundFirstEpic, epicThird);
        System.out.println("... ������ ����� ������� 3-�� �����...");
        printAllTasks();

        // �������� ������ � ��������� � �������� ������ ����� �� � �����
        System.out.println("----------�������� ������ � ��������� � �������� ������ ����� �� � �����...");
        Epic epic2 = (Epic)taskManager.findTaskByTitle("epic 2");
        System.out.println("...���� 2 - " + epic2);
        System.out.println("...������ ����� 2 �� ��������� ������� ��������� = " + epic2.getStatus());
        if (epic2.getSubTaskArrayList().size() > 0){
            SubTask subTaskEpic2 = epic2.getSubTaskArrayList().get(0);
            subTaskEpic2.setStatus(Status.IN_PROGRESS);
        }
        System.out.println("...������ ����� 2 ����� ��������� ������� ��������� = " + epic2.getStatus());
        System.out.println("...���� 2 - " + epic2);

        /* ������ ��������� IN_PROGRESS � ����� � �������� ��� ������
        ��������� ���� ������ �� �������� NEW � ���� ����� ������ �������� ������ �� NEW */
        System.out.println("������ ��������� IN_PROGRESS � ����� 2 � �������� ��� ������ �� � �����...");
        System.out.println("...������ ����� 2 �� �������� ��������� = " + epic2.getStatus());
        if (epic2.getSubTaskArrayList().size() > 0) {
            SubTask subTaskEpic2 = epic2.getSubTaskArrayList().get(0);
            taskManager.delTask(subTaskEpic2);
        }
        System.out.println("...������ ����� 2 ����� �������� ��������� = " + epic2.getStatus());
        System.out.println("...���� 2 - " + epic2);
    }

    /* ������� ��� ����� � �����������
    � ���� ������� ������ */
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

    /* ������� � ������� ��������
    ������, �����, ��������� */
    public static void printAllTasks(){
        ArrayList<Task> tasks = taskManager.getAllTasks();
        System.out.println("Tasks: " + tasks);

        ArrayList<Epic> epics = taskManager.getAllEpics();
        System.out.println("Epics: " + epics);

        ArrayList<SubTask> subTasks = taskManager.getAllSubTasks();
        System.out.println("SubTasks: " + subTasks);
    }

}
