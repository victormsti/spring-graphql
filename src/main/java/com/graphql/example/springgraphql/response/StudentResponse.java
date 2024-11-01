package com.graphql.example.springgraphql.response;

import java.util.ArrayList;
import java.util.List;

import com.graphql.example.springgraphql.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentResponse {

	private long id;

	private String firstName;

	private String lastName;

	private String email;
	
	private String street;

	private String city;
	
	private List<SubjectResponse> learningSubjects;

	private String fullName;
	
	public StudentResponse (Student student) {
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
		
		this.street = student.getAddress().getStreet();
		this.city = student.getAddress().getCity();

		if(student.getLearningSubjects() != null && !student.getLearningSubjects().isEmpty()){
			this.learningSubjects = new ArrayList<>();
			student.getLearningSubjects().forEach(learningSubject -> {
				this.learningSubjects.add(new SubjectResponse(learningSubject));
			});
		}
	}

}
