package com.balenii.api.StudentManagementApp.repository;

import com.balenii.api.StudentManagementApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

    Student findByStudentId(String id);
    List<Student> findAllByCourse(String course);
}
