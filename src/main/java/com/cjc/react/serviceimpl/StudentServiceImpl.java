package com.cjc.react.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.react.model.Student;
import com.cjc.react.repoi.StudentRepo;
import com.cjc.react.servicei.StudentServicei;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentServiceImpl implements StudentServicei{

	 @Autowired
	 StudentRepo studentRepository;
	 

	    @Override
	    public Student saveStudent(String studentJson,MultipartFile imageDocument,MultipartFile pdfFile) {
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            Student student = objectMapper.readValue(studentJson, Student.class);

	            if (imageDocument != null && !imageDocument.isEmpty()) {
	                student.getAddress().setImageDocument(imageDocument.getBytes());
	            }
	            
	            if (pdfFile != null && !pdfFile.isEmpty()) {
	                student.getAddress().setPdfFile(pdfFile.getBytes());
	            }

	           
	            return studentRepository.save(student);

	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }


		@Override
		public List<Student> getAllStudents() {
			
			return studentRepository.findAll();
		}


		@Override
		public Student getSingle(int id) 
		{
			Optional<Student> os = studentRepository.findById(id);
			if(os.isPresent())
			{
				Student s1 = os.get();
				
				return s1;
			}
			return null;
		}


		@Override
		public Student getSingle(String username, String password) {
			
		    Student st = studentRepository.findByUsernameAndPassword(username,password);
		    
		    return st;
		}		



		@Override
		public Student updateStudent(int id, String studentJson, MultipartFile imageDocument,MultipartFile pdfFile) {
			
			Optional<Student> os = studentRepository.findById(id);
			if(os.isPresent())
			{
				
				 try {
			            ObjectMapper objectMapper = new ObjectMapper();
			            Student student = objectMapper.readValue(studentJson, Student.class);

			            if (imageDocument != null && !imageDocument.isEmpty()) {
			                student.getAddress().setImageDocument(imageDocument.getBytes());
			            }
			            
			            if (pdfFile != null && !pdfFile.isEmpty()) {
			                student.getAddress().setPdfFile(pdfFile.getBytes());
			            }

			            Student s1 = os.get();
			          
			            s1.setName(student.getName());
			            s1.setRollNumber(student.getRollNumber());
			            s1.setAge(student.getAge());
			            s1.setUsername(student.getUsername());
			            s1.setPassword(student.getPassword());
			            s1.getAddress().setStreet(student.getAddress().getStreet());
			            s1.getAddress().setCity(student.getAddress().getCity());
			            s1.getAddress().setZipCode(student.getAddress().getZipCode());
			            s1.getAddress().setImageDocument(student.getAddress().getImageDocument());
			            s1.getAddress().setPdfFile(student.getAddress().getPdfFile());
			            
			            return studentRepository.save(s1);

			        } catch (Exception e) {
			            e.printStackTrace();
			            return null;
			        }
			}
			return null;
		}


		@Override
		public void deleteStudent(int id) 
		{
			studentRepository.deleteById(id);
			
		}

}
