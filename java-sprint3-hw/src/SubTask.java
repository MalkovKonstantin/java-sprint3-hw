public class SubTask extends Task {
    private Epic epic;

    public SubTask(int id, String title, String description, Status status, Epic epic) {
        super(id, title, description, status);
        this.epic = epic;
        epic.addSubTask(this);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public void setStatus(Status status) {
        super.setStatus(status);
        epic.updateEpicStatus();
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + this.getId() +
                ", title='" + this.getTitle() + '\'' +
                ", status=" + this.getStatus() + '\'' +
                ", epic=" + this.epic.getTitle() +
                '}';
    }
}
