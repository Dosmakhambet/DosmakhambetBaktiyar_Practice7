package com.dosmakhambetbaktiyar.controller;

import com.dosmakhambetbaktiyar.dto.UserDto;
import com.dosmakhambetbaktiyar.model.User;
import com.dosmakhambetbaktiyar.service.UserService;
import com.dosmakhambetbaktiyar.service.impl.UserServiceImpl;
import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "users", value = "/api/V1/users")
public class UserController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    private final Gson gson = new Gson();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String id = request.getParameter("id");
        try(PrintWriter out = response.getWriter()){
            if(id == null){
                List<User> users = userService.getAll();
                List<UserDto> userDtos = users.stream().map(UserDto::asDto).collect(Collectors.toList());
                out.println(gson.toJson(userDtos));
            }else{
                User user = userService.get(Integer.parseInt(id));
                out.println(gson.toJson(user));
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
            out.println(gson.toJson(user));
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
            out.println(gson.toJson(user));
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

            out.println(gson.toJson(status));
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
