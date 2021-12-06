package fhtw.timetracker.model;

import java.time.LocalDateTime;

public class Record {
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String notes;
    private Task task;

    public Record(LocalDateTime startTime, LocalDateTime endTime, String notes, Task task) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = notes;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", notes='" + notes + '\'' +
                ", task=" + task +
                '}';
    }
}
