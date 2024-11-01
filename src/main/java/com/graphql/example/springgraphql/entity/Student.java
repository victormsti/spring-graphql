package com.graphql.example.springgraphql.entity;

import java.util.List;

import com.graphql.example.springgraphql.request.CreateStudentRequest;
import com.graphql.example.springgraphql.request.UpdateStudentRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
	private List<Subject> learningSubjects;

	public Student (CreateStudentRequest createStudentRequest) {
		this.firstName = createStudentRequest.getFirstName();
		this.lastName = createStudentRequest.getLastName();
		this.email = createStudentRequest.getEmail();
	}

	public void fromUpdate(UpdateStudentRequest request){
		if (request.getFirstName() != null) {
			setFirstName(request.getFirstName());
		}
		if (request.getLastName() != null) {
			setLastName(request.getLastName());
		}
		if (request.getEmail() != null) {
			setEmail(request.getEmail());
		}
		if (request.getStreet() != null) {
			getAddress().setStreet(request.getStreet());
		}
		if (request.getCity() != null) {
			getAddress().setCity(request.getCity());
		}
	}
}
