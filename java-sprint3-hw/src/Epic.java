import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<SubTask> subTaskArrayList = new ArrayList<>();

    public Epic(int id, String title, String description) {
        super(id, title, description);
        this.setStatus(Status.NEW);
    }

    public ArrayList<SubTask> getSubTaskArrayList() {
        return subTaskArrayList;
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        // добавим подзадачи из нового эпика и установим им текущий эпик
        for (SubTask subTask : ((Epic) task).getSubTaskArrayList()) {
            subTask.setEpic(this);
            this.addSubTask(subTask);
        }
    }

    public void addSubTask(SubTask subTask) {
        subTaskArrayList.add(subTask);
    }

    public void delSubTask(SubTask subTask) {
        subTaskArrayList.remove(subTask);
        updateEpicStatus();
    }

    public void updateEpicStatus() {
        ArrayList<Status> statuses = new ArrayList<>();
        for (SubTask subTask : subTaskArrayList) {
            // если не нашли в массиве, то добавим
            if (!statuses.contains(subTask.getStatus())) {
                statuses.add(subTask.getStatus());
            }
        }
        // если статус один и все = new или нет подзадач то NEW
        if (statuses.size() == 0 || statuses.size() == 1 && statuses.get(0) == Status.NEW) {
            this.setStatus(Status.NEW);
            // если все выполнены, то статус DONE
        } else if (statuses.size() == 1 && statuses.get(0) == Status.DONE) {
            this.setStatus(Status.DONE);
            // иначе в процессе
        } else {
            this.setStatus(Status.IN_PROGRESS);
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + this.getId() +
                ", tt.='" + this.getTitle() + '\'' +
                ", st.=" + this.getStatus() + '\'' +
                ", {{s.tasks=" + this.subTaskArrayList + "}}" +
                '}';
    }
}
