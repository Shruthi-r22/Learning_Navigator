package com.crio.learningnavigator.exchange.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The GenericResponse class represents a generic response containing data of type T.
 *
 * <p>This class is used to encapsulate a generic response where the data type can vary.</p>
 *
 * <p><b>Fields:</b></p>
 * <ul>
 *   <li>{@code T data} - The data of type T to be returned in the response.</li>
 * </ul>
 *
 * <p><b>Annotations:</b></p>
 * <ul>
 *   <li>{@code @Data} - Generates getters, setters, toString, equals, and hashCode methods.</li>
 *   <li>{@code @AllArgsConstructor} - Generates a constructor with arguments for all fields.</li>
 *   <li>{@code @NoArgsConstructor} - Generates a no-argument constructor.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This class should be used to create responses that can hold any type of data.</p>
 *
 * <pre>{@code
 * // Example usage:
 * GenericResponse<String> response = new GenericResponse<>("Success");
 * }</pre>
 *
 * @param <T> the type of data encapsulated in the response
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenericResponse<T> {
    /**
     * The data of type T to be returned in the response.
     */
    private T data;
}