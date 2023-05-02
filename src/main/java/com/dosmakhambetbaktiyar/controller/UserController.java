package com.dosmakhambetbaktiyar.controller;

import com.dosmakhambetbaktiyar.model.User;
import com.dosmakhambetbaktiyar.repository.impl.UserRepositoryImpl;
import com.dosmakhambetbaktiyar.service.UserService;
import com.dosmakhambetbaktiyar.service.impl.UserServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "user", value = "/user")
public class UserController extends HttpServlet {
    private UserService userService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String id = request.getParameter("id");
        try(PrintWriter out = response.getWriter()){
            if(id == null){
                List<User> users = userService.getAll();
                for(User user:users){
                    out.println(user);
                }
            }else{
                User user = userService.get(Integer.parseInt(id));
                out.println(user);
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String name = request.getParameter("name");
        try(PrintWriter out = response.getWriter()){
            User user = userService.create(new User(name));
            out.println(user);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        try(PrintWriter out = response.getWriter()){
            User user = userService.get(Integer.parseInt(id));
            user.setName(name);
            userService.update(user);
            out.println(user);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String id = request.getParameter("id");

        try(PrintWriter out = response.getWriter()){
            boolean status = userService.delete(Integer.parseInt(id));

            out.println(status);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
