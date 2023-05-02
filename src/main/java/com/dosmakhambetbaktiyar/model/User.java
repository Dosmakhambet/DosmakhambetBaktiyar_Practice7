package com.dosmakhambetbaktiyar.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @OneToMany(mappedBy="user")
    private List<Event> events;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name, List<Event> events) {
        this.name = name;
        this.events = events;
    }

    public User(Integer id, String name, List<Event> events) {
        this.id = id;
        this.name = name;
        this.events = events;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", events=" + events +
                '}';
    }
}
