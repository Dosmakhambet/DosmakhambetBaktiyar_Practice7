package com.dosmakhambetbaktiyar.controller;

import com.dosmakhambetbaktiyar.dto.FileDto;
import com.dosmakhambetbaktiyar.model.File;
import com.dosmakhambetbaktiyar.service.FileService;
import com.dosmakhambetbaktiyar.service.impl.FileServiceImpl;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "files", value = "/api/V1/files")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class FileController extends HttpServlet {
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
                List<File> files = fileService.getAll();
                List<FileDto> fileDtos = files.stream().map(FileDto::asDto).collect(Collectors.toList());

                out.println(gson.toJson(fileDtos));
            }else{
                File file = fileService.get(Integer.parseInt(id));
                out.println(gson.toJson(file));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        String userId = request.getHeader("UserId");
        Part filePart = request.getPart("file");

        try(PrintWriter out = response.getWriter()){
           File file = fileService.upload(filePart, Integer.parseInt(userId));
           out.println(gson.toJson(file));
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
            out.println(gson.toJson(file));
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

            out.println(gson.toJson(status));
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
