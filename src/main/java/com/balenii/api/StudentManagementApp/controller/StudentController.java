package com.balenii.api.StudentManagementApp.controller;

import com.balenii.api.StudentManagementApp.model.Student;
import com.balenii.api.StudentManagementApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller  // Use @RestController instead of @Controller
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    // Create a new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{course}")
    public ResponseEntity<List<Student>> getAllStudentsByCourse(@PathVariable String course){
        List<Student> students = studentService.getAllStudentsByCourse(course);
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable String studentId){
        Student stu = studentService.getStudentById(studentId);
        studentService.deleteStudent(stu);
        return ResponseEntity.ok(stu);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> editStudent(@PathVariable String studentId, @RequestBody Student updatedStudent) {
        // Check if the student exists
        Student existingStudent = studentService.getStudentById(studentId);
        if (existingStudent == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the fields
        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setCourse(updatedStudent.getCourse());

        // Save and return updated student
        Student savedStudent = studentService.editStudent(existingStudent);
        return ResponseEntity.ok(savedStudent);
    }

}
