package com.graphql.example.springgraphql.service;

import com.graphql.example.springgraphql.entity.Student;
import com.graphql.example.springgraphql.request.CreateStudentRequest;
import com.graphql.example.springgraphql.request.UpdateStudentRequest;
import com.graphql.example.springgraphql.response.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse getStudentById (long id);
    List<StudentResponse> getAllStudents();
    StudentResponse createStudent (CreateStudentRequest createStudentRequest);
    StudentResponse updateStudent(UpdateStudentRequest request);
    boolean deleteStudent(Long id);
}
