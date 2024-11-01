package com.graphql.example.springgraphql.service;

import com.graphql.example.springgraphql.entity.Address;
import com.graphql.example.springgraphql.entity.Student;
import com.graphql.example.springgraphql.entity.Subject;
import com.graphql.example.springgraphql.exceptions.StudentException;
import com.graphql.example.springgraphql.repository.AddressRepository;
import com.graphql.example.springgraphql.repository.StudentRepository;
import com.graphql.example.springgraphql.repository.SubjectRepository;
import com.graphql.example.springgraphql.request.CreateStudentRequest;
import com.graphql.example.springgraphql.request.CreateSubjectRequest;
import com.graphql.example.springgraphql.request.UpdateStudentRequest;
import com.graphql.example.springgraphql.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
	private final StudentRepository studentRepository;
	private final AddressRepository addressRepository;
	private final SubjectRepository subjectRepository;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, AddressRepository addressRepository, SubjectRepository subjectRepository) {
		this.studentRepository = studentRepository;
		this.addressRepository = addressRepository;
		this.subjectRepository = subjectRepository;
	}

	@Override
	public StudentResponse getStudentById (long id) {
		return new StudentResponse(studentRepository.findById(id)
				.orElseThrow(() -> new StudentException(String.format("Student not found by id %s", id))));
	}

	@Override
	public StudentResponse createStudent (CreateStudentRequest createStudentRequest) {
		Student student = new Student(createStudentRequest);

		Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());

		address = addressRepository.save(address);

		student.setAddress(address);
		student = studentRepository.save(student);

		List<Subject> subjectsList = new ArrayList<>();

		if(createStudentRequest.getSubjectsLearning() != null) {
			for (CreateSubjectRequest createSubjectRequest :
					createStudentRequest.getSubjectsLearning()) {
				Subject subject = new Subject();
				subject.setSubjectName(createSubjectRequest.getSubjectName());
				subject.setMarksObtained(createSubjectRequest.getMarksObtained());
				subject.setStudent(student);

				subjectsList.add(subject);
			}

			subjectRepository.saveAll(subjectsList);
		}

		student.setLearningSubjects(subjectsList);

		return new StudentResponse(student);
	}

	@Override
	public StudentResponse updateStudent(UpdateStudentRequest request) {
		Student student = studentRepository.findById(request.getId())
				.orElseThrow(() -> new StudentException(
						String.format("Student not found by id %s", request.getId())
				));
		student.fromUpdate(request);

		if (request.getSubjectsLearning() != null) {
			List<Subject> updatedSubjects = new ArrayList<>();
			for (CreateSubjectRequest subjectRequest : request.getSubjectsLearning()) {
				Subject subject = new Subject();
				subject.setSubjectName(subjectRequest.getSubjectName());
				subject.setMarksObtained(subjectRequest.getMarksObtained());
				subject.setStudent(student);
				updatedSubjects.add(subject);
			}
			subjectRepository.saveAll(updatedSubjects);
			student.setLearningSubjects(updatedSubjects);
		}

		return new StudentResponse(studentRepository.save(student));
	}

	@Override
	public boolean deleteStudent(Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentException(String.format("Student not found by id %s", id)));

		List<Subject> subjects = student.getLearningSubjects();
		if (subjects != null && !subjects.isEmpty()) {
			subjectRepository.deleteAll(subjects);
		}
		studentRepository.deleteById(id);
		return true;
	}

	@Override
	public List<StudentResponse> getAllStudents() {
		List<Student> students = studentRepository.findAll();

		return students.stream()
				.map(StudentResponse::new)
				.collect(Collectors.toList());
	}
}
