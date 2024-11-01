package com.graphql.example.springgraphql.query;

import com.graphql.example.springgraphql.response.StudentResponse;
import com.graphql.example.springgraphql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StudentQueryController {

    private final StudentService studentService;

    @Autowired
    public StudentQueryController(StudentService studentService) {
        this.studentService = studentService;
    }

    @QueryMapping
    public StudentResponse student(@Argument Long id) {
        return studentService.getStudentById(id);
    }

    @QueryMapping
    public List<StudentResponse> students() {
        return studentService.getAllStudents();
    }
}
