package com.dosmakhambetbaktiyar.service.impl;

import com.dosmakhambetbaktiyar.model.Event;
import com.dosmakhambetbaktiyar.repository.EventRepository;
import com.dosmakhambetbaktiyar.service.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {
    private EventRepository repository;

    public EventServiceImpl(EventRepository repository){
        this.repository = repository;
    }
    @Override
    public Event create(Event event) {
        if(event.getId() == null)
            return repository.create(event);

        return null;
    }

    @Override
    public Event get(Integer id) {

        return repository.get(id);
    }

    @Override
    public List<Event> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Event update(Event event) {
        if(event.getId() != null)
            return repository.update(event);

        return null;
    }
}
