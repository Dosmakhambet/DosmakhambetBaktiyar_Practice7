package com.dosmakhambetbaktiyar.controller;

import com.dosmakhambetbaktiyar.model.File;
import com.dosmakhambetbaktiyar.model.User;
import com.dosmakhambetbaktiyar.repository.impl.FileRepositoryImpl;
import com.dosmakhambetbaktiyar.service.FileService;
import com.dosmakhambetbaktiyar.service.impl.FileServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "file", value = "/file")
public class FileController extends HttpServlet {
    private FileService fileService;


    @Override
    public void init() throws ServletException {
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
                List<File> files = fileService.getAll();
                for(File file:files){
                    out.println(file);
                }
            }else{
                File file = fileService.get(Integer.parseInt(id));
                out.println(file);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String name = request.getParameter("name");
        String filePath = request.getParameter("filePath");
        try(PrintWriter out = response.getWriter()){
            File file = fileService.create(new File(name,filePath));
            out.println(file);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String filePath = request.getParameter("filePath");

        try(PrintWriter out = response.getWriter()){
            File file = fileService.get(Integer.parseInt(id));
            file.setName(name);
            file.setFilePath(filePath);
            fileService.update(file);
            out.println(file);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String id = request.getParameter("id");

        try(PrintWriter out = response.getWriter()){
            boolean status = fileService.delete(Integer.parseInt(id));

            out.println(status);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
