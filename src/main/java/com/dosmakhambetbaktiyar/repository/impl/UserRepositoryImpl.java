package com.dosmakhambetbaktiyar.repository.impl;

import com.dosmakhambetbaktiyar.model.Event;
import com.dosmakhambetbaktiyar.model.File;
import com.dosmakhambetbaktiyar.model.User;
import com.dosmakhambetbaktiyar.model.UserEventFile;
import com.dosmakhambetbaktiyar.repository.UserRepository;
import com.dosmakhambetbaktiyar.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User create(User user) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        }catch (Exception e){
            System.err.println("User create() error. " + e.getMessage());
        }

        return null;
    }

    @Override
    public User get(Integer integer) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class,integer);
            List userEventFiles = session.createQuery("select e.user.id as user_id, e.id as event_id, e.file.id as file_id, e.file.name as file_name, e.file.filePath as file_path from Event e join e.user join e.file where e.user.id in :id")
                    .setParameter("id",integer)
                    .setResultTransformer(Transformers.aliasToBean(UserEventFile.class)).list();
            User user1 = new User(user.getId(),user.getName());
            List<Event> events = new ArrayList<>();
            for(int i =0 ; i < userEventFiles.size(); i++){
                UserEventFile userEventFile = (UserEventFile) userEventFiles.get(i);
                events.add(new Event(userEventFile.getEvent_id(), new File(userEventFile.getFile_id(),userEventFile.getFile_name(),userEventFile.getFile_path())));
            }
            user1.setEvents(events);
            transaction.commit();
            return user1;
        }catch (Exception e){
            System.err.println("User get() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List users = session.createQuery("from User").list();
            List<Integer> userIds = new ArrayList();

            for(int i = 0 ; i < users.size(); i++){
                User user = (User) users.get(i);
                userIds.add(user.getId());
            }

            List userEventFiles = session.createQuery("select e.user.id as user_id, e.id as event_id, e.file.id as file_id, e.file.name as file_name, e.file.filePath as file_path from Event e join e.user join e.file where e.user.id in :ids")
                    .setParameter("ids",userIds)
                    .setResultTransformer(Transformers.aliasToBean(UserEventFile.class)).list();

            List<User> userList = new ArrayList<>();
            for(int j = 0 ; j < users.size(); j ++){
                User user = (User) users.get(j);
                User user1 = new User(user.getId(),user.getName());
                for(int i =0; i < userEventFiles.size(); i ++){
                    UserEventFile userEventFile = (UserEventFile) userEventFiles.get(i);

                    if(user.getId().equals(userEventFile.getUser_id())){
                        user1.setEvents(List.of(new Event(userEventFile.getEvent_id(),new File(userEventFile.getFile_id(),userEventFile.getFile_name(),userEventFile.getFile_path()))));
                    }
                }
                userList.add(user1);
            }

            transaction.commit();
            return userList;
        }catch (Exception e){
            System.err.println("User getAll() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class,integer);
            session.delete(user);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.err.println("User delete() error. " + e.getMessage());
        }
        return false;
    }

    @Override
    public User update(User user) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();

            return user;
        }catch (Exception e){
            System.err.println("User update() error. " + e.getMessage());
        }
        return null;
    }
}
