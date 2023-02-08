package com.example.springbootdownloadexcel.service;

import com.example.springbootdownloadexcel.model.Contact;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelFileService {
    ByteArrayInputStream export(List<Contact> contacts);
}
