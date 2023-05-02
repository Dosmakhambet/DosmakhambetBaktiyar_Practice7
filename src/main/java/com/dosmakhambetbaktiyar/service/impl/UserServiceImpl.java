package com.dosmakhambetbaktiyar.service.impl;

import com.dosmakhambetbaktiyar.model.User;
import com.dosmakhambetbaktiyar.repository.UserRepository;
import com.dosmakhambetbaktiyar.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }
    @Override
    public User create(User user) {
        if(user.getId() == null)
            return repository.create(user);

        return null;
    }

    @Override
    public User get(Integer id) {

        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public User update(User user) {
        if(user.getId() != null)
            return repository.update(user);

        return null;
    }
}
