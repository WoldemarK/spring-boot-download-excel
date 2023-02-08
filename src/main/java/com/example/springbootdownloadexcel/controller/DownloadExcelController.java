package com.example.springbootdownloadexcel.controller;

import com.example.springbootdownloadexcel.model.Contact;
import com.example.springbootdownloadexcel.repository.ContactRepository;
import com.example.springbootdownloadexcel.service.ExcelFileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DownloadExcelController {
    private final ContactRepository contactRepository;
    private final ExcelFileService excelFileService;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/downloadExcelFile")
    public void downloadExcelFile(HttpServletResponse response) throws IOException {
        List<Contact> contacts = contactRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = excelFileService.export(contacts);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=contacts.xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
    }
}
