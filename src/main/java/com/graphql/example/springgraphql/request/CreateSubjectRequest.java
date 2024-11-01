package com.graphql.example.springgraphql.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateSubjectRequest {

    private String subjectName;

    private Double marksObtained;
}
