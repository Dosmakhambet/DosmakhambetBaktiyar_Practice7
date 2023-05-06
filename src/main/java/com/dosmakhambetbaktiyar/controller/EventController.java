package com.dosmakhambetbaktiyar.controller;

import com.dosmakhambetbaktiyar.model.Event;
import com.dosmakhambetbaktiyar.model.File;
import com.dosmakhambetbaktiyar.model.User;
import com.dosmakhambetbaktiyar.service.EventService;
import com.dosmakhambetbaktiyar.service.FileService;
import com.dosmakhambetbaktiyar.service.UserService;
import com.dosmakhambetbaktiyar.service.impl.EventServiceImpl;
import com.dosmakhambetbaktiyar.service.impl.FileServiceImpl;
import com.dosmakhambetbaktiyar.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "events", value = "/api/V1/events")
public class EventController extends HttpServlet {
    private final EventService eventService = new EventServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final FileService fileService = new FileServiceImpl();
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
                List<Event> events = eventService.getAll();
                for(Event event:events){
                    out.println(event);
                }
            }else{
                Event event = eventService.get(Integer.parseInt(id));
                out.println(gson.toJson(event));
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
            out.println(gson.toJson(event));
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
            out.println(gson.toJson(event));
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
            out.println(gson.toJson(status));
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
