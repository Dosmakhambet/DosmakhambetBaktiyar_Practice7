package com.dosmakhambetbaktiyar.repository.impl;

import com.dosmakhambetbaktiyar.model.User;
import com.dosmakhambetbaktiyar.repository.UserRepository;
import com.dosmakhambetbaktiyar.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

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

            List<User> users = session.createNativeQuery("select u.id,u.name,e.id as event_id, f.id as file_id, f.name as file_name, f.filePath as file_filePath from users u left join events e on e.user_id = u.id inner join files f on f.id = e.file_id WHERE u.id = :id")
                    .setParameter("id",integer)
                    .setResultTransformer(Transformers.aliasToBean(User.class))
                    .list();

            System.out.println(users);
            return users.get(0);
        }catch (Exception e){
            System.err.println("User get() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){

            List users = session.createNativeQuery("SELECT * FROM users")
                    .setResultTransformer(Transformers.aliasToBean(User.class))
                    .list();
            return users;
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
