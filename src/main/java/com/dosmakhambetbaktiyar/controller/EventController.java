package com.dosmakhambetbaktiyar.controller;

import com.dosmakhambetbaktiyar.model.Event;
import com.dosmakhambetbaktiyar.model.File;
import com.dosmakhambetbaktiyar.model.User;
import com.dosmakhambetbaktiyar.repository.impl.EventRepositoryImpl;
import com.dosmakhambetbaktiyar.repository.impl.FileRepositoryImpl;
import com.dosmakhambetbaktiyar.repository.impl.UserRepositoryImpl;
import com.dosmakhambetbaktiyar.service.EventService;
import com.dosmakhambetbaktiyar.service.FileService;
import com.dosmakhambetbaktiyar.service.UserService;
import com.dosmakhambetbaktiyar.service.impl.EventServiceImpl;
import com.dosmakhambetbaktiyar.service.impl.FileServiceImpl;
import com.dosmakhambetbaktiyar.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "event", value = "/event")
public class EventController extends HttpServlet {
    private EventService eventService;
    private UserService userService;
    private FileService fileService;

    @Override
    public void init() throws ServletException {
        eventService = new EventServiceImpl(new EventRepositoryImpl());
        userService = new UserServiceImpl(new UserRepositoryImpl());
        fileService = new FileServiceImpl(new FileRepositoryImpl());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String id = request.getParameter("id");
        try(PrintWriter out = response.getWriter()){
            if(id == null){
                List<Event> events = eventService.getAll();
                for(Event event:events){
                    out.println(event);
                }
            }else{
                Event event = eventService.get(Integer.parseInt(id));
                out.println(event);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String file_id = request.getParameter("file_id");
        String user_id = request.getParameter("user_id");
        try(PrintWriter out = response.getWriter()){
            User user = userService.get(Integer.parseInt(user_id));
            File file = fileService.get(Integer.parseInt(file_id));
            Event event = eventService.create(new Event(user,file));
            out.println(event);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String id = request.getParameter("id");
        String file_id = request.getParameter("file_id");
        String user_id = request.getParameter("user_id");
        try(PrintWriter out = response.getWriter()){
            Event event = eventService.get(Integer.parseInt(id));
            User user = userService.get(Integer.parseInt(user_id));
            File file = fileService.get(Integer.parseInt(file_id));
            event.setUser(user);
            event.setFile(file);
            eventService.update(event);
            out.println(event);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String id = request.getParameter("id");
        try(PrintWriter out = response.getWriter()){
            boolean status = eventService.delete(Integer.parseInt(id));
            out.println(status);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
