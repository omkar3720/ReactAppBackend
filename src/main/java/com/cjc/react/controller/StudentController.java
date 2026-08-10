package com.cjc.react.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.react.model.Student;
import com.cjc.react.servicei.StudentServicei;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
    private StudentServicei studentService;

    @PostMapping("/save")
    public ResponseEntity<Student> createStudent(
            @RequestPart("student") String studentJson,
            @RequestPart(value = "image", required = false) MultipartFile imageDocument,@RequestPart(value = "pdf",required = false) MultipartFile pdfFile) {

        Student result = studentService.saveStudent(studentJson,imageDocument,pdfFile);
        return new ResponseEntity<Student>(result,HttpStatus.CREATED);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents()
    {
    	List<Student> getAll = studentService.getAllStudents();
    	return new ResponseEntity<List<Student>>(getAll,HttpStatus.OK);
    }
    
    @GetMapping("/getSingle/{id}")
    public ResponseEntity<Student> getSingle(@PathVariable int id) 
    {
       Student s1 = studentService.getSingle(id);
       return new ResponseEntity<Student>(s1,HttpStatus.OK);
    }
    
    @GetMapping("/getSingle/{username}/{password}")
    public ResponseEntity<Student> getSingle(@PathVariable String username,@PathVariable String password) 
    {
       Student s1 = studentService.getSingle(username,password);
       return new ResponseEntity<Student>(s1,HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestPart("student") String studentJson,
    		 @RequestPart(value = "image", required = false) MultipartFile imageDocument,@RequestPart(value="pdf",required = false) MultipartFile pdfFile) 
    {
       Student s1 = studentService.updateStudent(id,studentJson,imageDocument,pdfFile);
       return new ResponseEntity<Student>(s1,HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) 
    {
       studentService.deleteStudent(id);
       return new ResponseEntity<String>("Data deleted",HttpStatus.NO_CONTENT);
    }
    
}

