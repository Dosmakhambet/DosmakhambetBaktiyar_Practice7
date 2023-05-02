package com.dosmakhambetbaktiyar.model;

public class UserEventFile {
    private Integer user_id;
    private Integer event_id;
    private Integer file_id;
    private String file_name;
    private String file_path;

    public UserEventFile() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public Integer getFile_id() {
        return file_id;
    }

    public void setFile_id(Integer file_id) {
        this.file_id = file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    @Override
    public String toString() {
        return "UserEventFile{" +
                "user_id=" + user_id +
                ", event_id=" + event_id +
                ", file_id=" + file_id +
                ", file_name='" + file_name + '\'' +
                ", file_path='" + file_path + '\'' +
                '}';
    }
}
