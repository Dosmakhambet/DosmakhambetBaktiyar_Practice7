package com.dosmakhambetbaktiyar.service.impl;

import com.dosmakhambetbaktiyar.model.File;
import com.dosmakhambetbaktiyar.repository.FileRepository;
import com.dosmakhambetbaktiyar.service.FileService;

import java.util.List;

public class FileServiceImpl implements FileService {
    private FileRepository repository;

    public FileServiceImpl(FileRepository repository){
        this.repository = repository;
    }
    @Override
    public File create(File file) {
        if(file.getId() == null)
            return repository.create(file);

        return null;
    }

    @Override
    public File get(Integer id) {

        return repository.get(id);
    }

    @Override
    public List<File> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public File update(File file) {
        if(file.getId() != null)
            return repository.update(file);

        return null;
    }
}
