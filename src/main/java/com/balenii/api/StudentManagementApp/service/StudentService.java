package com.balenii.api.StudentManagementApp.service;

import com.balenii.api.StudentManagementApp.model.Student;
import com.balenii.api.StudentManagementApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student addStudent(Student stu){
        stu.setStudentId(generateStudentId());
        return studentRepository.save(stu);
    }

    private String generateStudentId() {
        Random random = new Random();
        // Generate an 8-digit random number and prefix it with '2'
        return "2" + String.format("%08d", random.nextInt(100000000));
    }

    public Student getStudentById(String studentId){
        return studentRepository.findByStudentId(studentId);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsByCourse(String course){
        return studentRepository.findAllByCourse(course);
    }

}
