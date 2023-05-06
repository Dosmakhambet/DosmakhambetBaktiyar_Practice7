package com.dosmakhambetbaktiyar.dto;


import com.dosmakhambetbaktiyar.model.Event;

public class EventDto {
    private Integer id;
    private UserDto user;
    private FileDto file;

    public EventDto() {
    }

    public EventDto(Integer id, UserDto user, FileDto file) {
        this.id = id;
        this.user = user;
        this.file = file;
    }

    public Event asEntity(){
        return new Event(id,user.asEntity(),file.asEntity());
    }

    public static EventDto asDto(Event event){
        return new EventDto(event.getId(), UserDto.asDto(event.getUser()), FileDto.asDto(event.getFile()));
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public FileDto getFile() {
        return file;
    }

    public void setFile(FileDto file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", user=" + user +
                ", file=" + file +
                '}';
    }
}
