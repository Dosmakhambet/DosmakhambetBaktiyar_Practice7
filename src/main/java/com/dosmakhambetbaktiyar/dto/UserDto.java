package com.dosmakhambetbaktiyar.dto;

import com.dosmakhambetbaktiyar.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    private Integer id;
    private String name;
    private List<EventDto> events;

    public UserDto() {
    }

    public UserDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDto(Integer id, String name, List<EventDto> events) {
        this.id = id;
        this.name = name;
        this.events = events;
    }

    public User asEntity(){
        return new User(id, name, events.stream().map(EventDto::asEntity).collect(Collectors.toList()));
    }

    public static UserDto asDto(User user){
        if(user.getEvents() == null)
            return new UserDto(user.getId(),user.getName());
        else
            return new UserDto(user.getId(),user.getName(),user.getEvents().stream().map(EventDto::asDto).collect(Collectors.toList()));
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

    public List<EventDto> getEvents() {
        return events;
    }

    public void setEvents(List<EventDto> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", events=" + events +
                '}';
    }
}
