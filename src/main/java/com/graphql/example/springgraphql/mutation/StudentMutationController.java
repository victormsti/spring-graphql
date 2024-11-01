package com.graphql.example.springgraphql.mutation;

import com.graphql.example.springgraphql.request.CreateStudentRequest;
import com.graphql.example.springgraphql.request.UpdateStudentRequest;
import com.graphql.example.springgraphql.response.StudentResponse;
import com.graphql.example.springgraphql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StudentMutationController {

    private final StudentService studentService;

    @Autowired
    public StudentMutationController(StudentService studentService) {
        this.studentService = studentService;
    }

    @MutationMapping
    public StudentResponse createStudent(@Argument CreateStudentRequest request){
        return studentService.createStudent(request);
    }

    @MutationMapping
    public StudentResponse updateStudent(@Argument UpdateStudentRequest request) {
        return studentService.updateStudent(request);
    }

    @MutationMapping
    public boolean deleteStudent(@Argument Long id) {
        return studentService.deleteStudent(id);
    }
}
