package com.crio.learningnavigator.controller;

import com.crio.learningnavigator.service.EsterEggService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The EsterEggController class defines endpoints for retrieving Easter egg trivia.
 *
 * <p>Endpoints:</p>
 * <ul>
 *   <li>{@code GET /hidden-feature/{number}} - Retrieves trivia related to the specified number.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>Use this controller to fetch trivia related to numbers using the {@code EsterEggService}.</p>
 *
 * <pre>{@code
 * // Example usage:
 * // GET /hidden-feature/42
 * // Response Body: "42 is the number of kilometers in a marathon."
 * }</pre>
 */
@RestController
@RequestMapping("/hidden-feature")
@RequiredArgsConstructor
public class EsterEggController {

    private final EsterEggService esterEggService;

    /**
     * Retrieves trivia related to the specified number.
     *
     * @param number the number for which trivia is requested
     * @return a ResponseEntity containing the trivia related to the specified number
     */
    @GetMapping("/{number}")
    public ResponseEntity<?> getEsterEggTrivia(@PathVariable String number) {
        return ResponseEntity.ok().body(esterEggService.getEsterEgg(number));
    }

}
