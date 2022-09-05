package util;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private final HashMap<Integer, Task> taskList = new HashMap<>();

    public static int getNewId() {
        int idCounter = Task.getIdCounter();
        Task.setIdCounter(++idCounter);
        return idCounter;
    }

    public HashMap<Integer, Task> getTaskList() {
        return taskList;
    }

    public Task findTaskByTitle(String title) {
        for (Object obj : taskList.keySet()) {
            if (taskList.get(obj).getTitle().equals(title)) {
                return taskList.get(obj);
            }
        }
        return null;
    }

    public void addNewTask(Task task) {
        taskList.put(task.getId(), task);
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Object obj : taskList.keySet()) {
            if (taskList.get(obj).getClass() == Task.class) {
                tasks.add(taskList.get(obj));
            }
        }
        return tasks;
    }

    public ArrayList<Epic> getAllEpics() {
        ArrayList<Epic> epics = new ArrayList<>();
        for (Object obj : taskList.keySet()) {
            if (taskList.get(obj).getClass() == Epic.class) {
                epics.add((Epic) taskList.get(obj));
            }
        }
        return epics;
    }

    public ArrayList<SubTask> getAllSubTasks() {
        ArrayList<SubTask> subTasks = new ArrayList<>();
        for (Object obj : taskList.keySet()) {
            if (taskList.get(obj).getClass() == SubTask.class) {
                subTasks.add((SubTask) taskList.get(obj));
            }
        }
        return subTasks;
    }

    public void delAllTasks() {
        taskList.clear();
    }

    public void delTask(Task task) {
        if (taskList.get(task.getId()).getClass() == Epic.class) {
            for (SubTask subTask : ((Epic) taskList.get(task.getId())).getSubTaskArrayList()) {
                taskList.remove(subTask.getId());
            }
        } else if (taskList.get(task.getId()).getClass() == SubTask.class) {
            SubTask subTask = (SubTask) taskList.get(task.getId());
            subTask.getEpic().delSubTask(subTask);
        }
        taskList.remove(task.getId());
    }

    public void updateTask(Task oldTask, Task newTask) {
        taskList.remove(oldTask.getId());
        oldTask.updateTask(newTask);
        addNewTask(newTask);
    }
}
