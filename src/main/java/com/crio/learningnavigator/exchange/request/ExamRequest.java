package com.crio.learningnavigator.exchange.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequest {
    /**
     * The identifier of the subject associated with the exam.
     */
    private Long subjectId;

    /**
     * The name of the exam.
     */
    private String name;
}
