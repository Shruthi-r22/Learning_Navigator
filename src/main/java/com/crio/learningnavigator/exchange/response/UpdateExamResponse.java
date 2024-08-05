package com.crio.learningnavigator.exchange.response;

import java.util.List;

import com.crio.learningnavigator.entity.Exam;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UpdateExamResponse {
    /**
     * The updated Exam object.
     */
    private Exam exam;

    /**
     * The list of IDs for students not found during the update process.
     */
    private List<Long> notFoundStudentIds;
}
