# Spring GraphQL Student Management System

This project is a simple Spring GraphQL application that allows you to manage students and their subject learnings using an H2 in-memory database. It provides a GraphQL API for performing CRUD operations on students and their associated subjects.

## Features

- **Manage Students**: Create, read, update, and delete student records.
- **Manage Subjects**: Associate subjects with students, including subject names and marks obtained.
- **GraphQL API**: Provides a flexible API for querying and mutating student and subject data.
- **H2 Database**: Utilizes an H2 in-memory database for easy setup and testing.

## Technologies Used

- **Spring Boot**: Framework for building the application.
- **Spring GraphQL**: For handling GraphQL requests and responses.
- **H2 Database**: In-memory database for data storage.
- **Lombok**: For reducing boilerplate code.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/student-management-system.git
   ```

2. Navigate to the project directory:

   ```bash
   cd student-management-system
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   mvn spring-boot:run
   ```

5. Access the GraphQL endpoint at `http://localhost:8080/graphql`.

### Sample Queries

- **Get All Students**:

  ```graphql
  query {
      students {
          id
          firstName
          lastName
          email
          learningSubjects {
              subjectName
              marksObtained
          }
      }
  }
  ```

- **Get a Student by ID**:

  ```graphql
  query {
      student(id: 1) {
          id
          firstName
          lastName
          email
          learningSubjects {
              subjectName
              marksObtained
          }
      }
  }
  ```

- **Create a New Student**:

  ```graphql
  mutation {
      createStudent(input: {
          firstName: "John",
          lastName: "Doe",
          email: "john.doe@example.com",
          street: "123 Main St",
          city: "Anytown",
          subjectsLearning: [{
              subjectName: "Mathematics",
              marksObtained: 95
          }]
      }) {
          id
          firstName
          lastName
      }
  }
  ```

- **Updating an Existing Student**:

  ```graphql
  mutation {
      updateStudent(input: {
          id: 4,
          firstName: "John",
          lastName: "Doe",
          email: "john.doe@example.com",
          street: "123 Main St",
          city: "Anytown",
          subjectsLearning: [{
              subjectName: "Mathematics",
              marksObtained: 95
          }]
      }) {
          id
          firstName
          lastName
      }
  }
  ```

- **Deleting an Existing Student**:

  ```graphql
  mutation {
      deleteStudent(id: 4)
  }

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Thanks to the Spring community for providing the tools and libraries that make this project possible.
