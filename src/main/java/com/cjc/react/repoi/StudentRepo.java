package com.cjc.react.repoi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjc.react.model.Student;

public interface StudentRepo extends JpaRepository<Student,Integer>
{

	Student findByUsernameAndPassword(String username, String password);

}
