package tasks;

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
        // ������� ��������� �� ������ ����� � ��������� �� ������� ����
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
            // ���� �� ����� � �������, �� �������
            if (!statuses.contains(subTask.getStatus())) {
                statuses.add(subTask.getStatus());
            }
        }
        // ���� ������ ���� � ��� = new ��� ��� �������� �� NEW
        if (statuses.size() == 0 || statuses.size() == 1 && statuses.get(0) == Status.NEW) {
            this.setStatus(Status.NEW);
            // ���� ��� ���������, �� ������ DONE
        } else if (statuses.size() == 1 && statuses.get(0) == Status.DONE) {
            this.setStatus(Status.DONE);
            // ����� � ��������
        } else {
            this.setStatus(Status.IN_PROGRESS);
        }
    }

    @Override
    public String toString() {
        return "tasks.Epic{" +
                "id=" + this.getId() +
                ", tt.='" + this.getTitle() + '\'' +
                ", st.=" + this.getStatus() + '\'' +
                ", {{s.tasks=" + this.subTaskArrayList + "}}" +
                '}';
    }
}
