package com.dosmakhambetbaktiyar.model;

import javax.persistence.*;

@Entity
@Table(name="event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private User user;
    @OneToOne
    private File file;

    public Event(Integer id, File file) {
        this.id = id;
        this.file = file;
    }

    public Event(User user, File file) {
        this.user = user;
        this.file = file;
    }

    public Event(Integer id, User user, File file) {
        this.id = id;
        this.user = user;
        this.file = file;
    }

    public Event() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", user=" + user +
                ", file=" + file +
                '}';
    }
}
