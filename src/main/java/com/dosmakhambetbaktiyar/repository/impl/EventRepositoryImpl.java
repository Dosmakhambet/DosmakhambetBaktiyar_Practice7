package com.dosmakhambetbaktiyar.repository.impl;

import com.dosmakhambetbaktiyar.model.Event;

import com.dosmakhambetbaktiyar.repository.EventRepository;
import com.dosmakhambetbaktiyar.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            // TODO: native query
            Event event = (Event) session.createNativeQuery("SELECT * FROM events e INNER JOIN users u ON u.id = e.user_id INNER JOIN files f ON f.id = e.file_id WHERE id = :id")
                    .setParameter("id",integer)
                    .getSingleResult();

            return event;
        }catch (Exception e){
            System.err.println("Event get() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Event> getAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){

            List<Event> events = session.createNativeQuery("SELECT * FROM events e INNER JOIN users u ON u.id = e.user_id INNER JOIN files f ON f.id = e.file_id")
                    .list();

            return events;
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
