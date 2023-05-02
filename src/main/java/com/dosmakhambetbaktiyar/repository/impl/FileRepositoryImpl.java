package com.dosmakhambetbaktiyar.repository.impl;

import com.dosmakhambetbaktiyar.model.File;
import com.dosmakhambetbaktiyar.repository.FileRepository;
import com.dosmakhambetbaktiyar.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FileRepositoryImpl implements FileRepository {
    @Override
    public File create(File file) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(file);
            transaction.commit();
            return file;
        }catch (Exception e){
            System.err.println("File create() error. " + e.getMessage());
        }

        return null;
    }

    @Override
    public File get(Integer integer) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            File file = session.get(File.class,integer);
            return file;
        }catch (Exception e){
            System.err.println("File get() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<File> getAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List files = session.createQuery("from File").list();
            transaction.commit();
            return files;
        }catch (Exception e){
            System.err.println("File getAll() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            File file = session.get(File.class,integer);
            session.delete(file);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.err.println("File delete() error. " + e.getMessage());
        }
        return false;
    }

    @Override
    public File update(File file) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(file);
            transaction.commit();

            return file;
        }catch (Exception e){
            System.err.println("File update() error. " + e.getMessage());
        }
        return null;
    }
}
