package com.alex.library.bookmanagementsystem.controllers;


import com.alex.library.bookmanagementsystem.service.FileService;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping(value = "/exportpdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportPdf() {
        ByteArrayOutputStream allUsersPdf = fileService.createAllUsersPdfFile();
        return new ResponseEntity<>(allUsersPdf.toByteArray(), HttpStatus.OK);
    }
}