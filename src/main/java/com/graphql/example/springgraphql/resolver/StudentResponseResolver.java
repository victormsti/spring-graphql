package com.graphql.example.springgraphql.resolver;

import com.graphql.example.springgraphql.entity.Subject;
import com.graphql.example.springgraphql.enums.SubjectNameFilter;
import com.graphql.example.springgraphql.response.StudentResponse;
import com.graphql.example.springgraphql.response.SubjectResponse;
import com.graphql.example.springgraphql.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentResponseResolver {
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentResponseResolver(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @SchemaMapping(typeName = "StudentResponse", field = "learningSubjects")
    public List<SubjectResponse> getLearningSubjects(StudentResponse studentResponse, @Argument("subjectFilter") SubjectNameFilter subjectFilter) {
        List<SubjectResponse> subjects = studentService.getStudentById(studentResponse.getId()).getLearningSubjects();
        return subjects.stream()
                .filter(subject -> (subjectFilter == null || subjectFilter == SubjectNameFilter.All) || subject.getSubjectName().equalsIgnoreCase(subjectFilter.name()))
                .collect(Collectors.toList());
    }

    @SchemaMapping(typeName = "StudentResponse", field = "fullName")
    public String getFullName(StudentResponse studentResponse) {
        return studentResponse.getFirstName() + " " + studentResponse.getLastName();
    }
}
