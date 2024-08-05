package com.crio.learningnavigator.exchange.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequest {
    /**
     * The name of the subject.
     * Constraints:
     * - The subject name must not be null.
     * - The subject name must not be empty.
     */
    @NotNull
    @NotEmpty
    private String name;
}
