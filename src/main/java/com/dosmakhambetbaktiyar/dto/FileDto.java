package com.dosmakhambetbaktiyar.dto;

import com.dosmakhambetbaktiyar.model.File;

public class FileDto {
    private Integer id;
    private String name;
    private String filePath;

    public FileDto() {
    }

    public FileDto(Integer id, String name, String filePath) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
    }

    public File asEntity(){
        return new File(id, name, filePath);
    }

    public static FileDto asDto(File file){
        return new FileDto(file.getId(),file.getName(),file.getFilePath());
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
