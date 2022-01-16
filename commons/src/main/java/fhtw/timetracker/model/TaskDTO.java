package fhtw.timetracker.model;

public class TaskDTO {

    private int id;
    private String name;

    public TaskDTO() {
    }

    public TaskDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
