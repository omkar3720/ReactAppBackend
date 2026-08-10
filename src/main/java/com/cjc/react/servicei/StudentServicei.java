package com.cjc.react.servicei;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cjc.react.model.Student;

public interface StudentServicei 
{
    

	public Student saveStudent(String studentJson, MultipartFile imageDocument,MultipartFile pdfFile);

	public List<Student> getAllStudents();

	public Student getSingle(int id);
	
	public Student getSingle(String username, String password);

	public Student updateStudent(int id, String studentJson, MultipartFile imageDocument, MultipartFile pdfFile);

	public void deleteStudent(int id);

}
