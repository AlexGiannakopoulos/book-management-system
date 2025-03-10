package com.alex.library.bookmanagementsystem.service;


import com.alex.library.bookmanagementsystem.model.dto.UserDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    UserService UserService;

    public ByteArrayOutputStream createAllUsersPdfFile() {
        List<UserDto> allUsersDto = UserService.getAllUsersDto();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.addTitle("Application Users");
            document.add(new Paragraph("Hello World!"));
            PdfPTable table = new PdfPTable(2);
            for(UserDto dto : allUsersDto) {
                table.addCell(dto.getFirstname());
                table.addCell(dto.getLastname());
            }
            document.add(table);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        document.close();
        return outputStream;
    }
}
