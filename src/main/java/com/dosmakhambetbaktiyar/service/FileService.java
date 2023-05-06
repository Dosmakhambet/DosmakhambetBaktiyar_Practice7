package com.dosmakhambetbaktiyar.service;

import com.dosmakhambetbaktiyar.model.File;

import javax.servlet.http.Part;

public interface FileService extends GenericService<File, Integer>{

    File upload(Part filePart, Integer userId);
}
