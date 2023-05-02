package com.dosmakhambetbaktiyar.repository.impl;

import com.dosmakhambetbaktiyar.model.Event;
import com.dosmakhambetbaktiyar.model.EventUserFile;
import com.dosmakhambetbaktiyar.model.File;
import com.dosmakhambetbaktiyar.model.User;
import com.dosmakhambetbaktiyar.repository.EventRepository;
import com.dosmakhambetbaktiyar.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {
    @Override
    public Event create(Event event) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(event);
            transaction.commit();

            return event;
        }catch (Exception e){
            System.err.println("Event create() error. " + e.getMessage());
        }

        return null;
    }

    @Override
    public Event get(Integer integer) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            EventUserFile eventUserFile = (EventUserFile) session.createQuery("select e.id as event_id, e.user.id as user_id,e.user.name as user_name, e.file as file from Event e join e.file join e.user where e.id = :id")
                    .setParameter("id",integer)
                    .setResultTransformer(Transformers.aliasToBean(EventUserFile.class))
                    .list().get(0);

            return new Event(eventUserFile.getEvent_id(), new User(eventUserFile.getUser_id(), eventUserFile.getUser_name()), eventUserFile.getFile());
        }catch (Exception e){
            System.err.println("Event get() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Event> getAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List events = session.createQuery("select e.id as event_id, e.user.id as user_id,e.user.name as user_name, e.file as file from Event e join e.file join e.user")
                    .setResultTransformer(Transformers.aliasToBean(EventUserFile.class))
                    .list();
            List<Event> eventList = new ArrayList<>();

            for(int i = 0 ; i < events.size(); i ++){
                EventUserFile eventUserFile = (EventUserFile) events.get(i);
                eventList.add(new Event(eventUserFile.getEvent_id(), new User(eventUserFile.getUser_id(), eventUserFile.getUser_name()), eventUserFile.getFile()));
            }
            transaction.commit();
            return eventList;
        }catch (Exception e){
            System.err.println("Event getAll() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Event event = session.get(Event.class,integer);
            session.delete(event);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.err.println("Event delete() error. " + e.getMessage());
        }
        return false;
    }

    @Override
    public Event update(Event event) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(event);
            transaction.commit();

            return event;
        }catch (Exception e){
            System.err.println("Event update() error. " + e.getMessage());
        }
        return null;
    }
}
