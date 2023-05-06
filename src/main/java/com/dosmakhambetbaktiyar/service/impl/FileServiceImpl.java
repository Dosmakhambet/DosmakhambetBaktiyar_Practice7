package com.dosmakhambetbaktiyar.service.impl;

import com.dosmakhambetbaktiyar.model.Event;
import com.dosmakhambetbaktiyar.model.File;
import com.dosmakhambetbaktiyar.model.User;
import com.dosmakhambetbaktiyar.repository.FileRepository;
import com.dosmakhambetbaktiyar.repository.impl.FileRepositoryImpl;
import com.dosmakhambetbaktiyar.service.EventService;
import com.dosmakhambetbaktiyar.service.FileService;
import com.dosmakhambetbaktiyar.service.UserService;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

public class FileServiceImpl implements FileService {
    private final FileRepository repository;
    private final UserService userService;
    private final EventService eventService;
    private final String filePath;



    public FileServiceImpl() {
        repository = new FileRepositoryImpl();
        filePath = "D:\\Upload\\";
        userService = new UserServiceImpl();
        eventService = new EventServiceImpl();
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


    @Override
    public File upload(Part filePart, Integer userId) {
        String fileName = filePart.getSubmittedFileName();
        User user = userService.get(userId);
        try {
            filePart.write(filePath + fileName);
            File file = create(new File(fileName,filePath + fileName));
            eventService.create(new Event(user,file));

            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
