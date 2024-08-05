package com.crio.learningnavigator.service.implementation;

import com.crio.learningnavigator.service.EsterEggService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/**
 * The EsterEggServiceImpl class implements the EsterEggService interface to fetch "Easter egg" facts based on a number.
 *
 * <p>It uses a RestTemplate client to make an HTTP GET request to the numbersapi.com API and retrieve a fact based on the provided number.</p>
 *
 * <p><b>Dependencies:</b></p>
 * <ul>
 *   <li>{@code RestClient client} - The RestTemplate client used to make HTTP requests.</li>
 * </ul>
 *
 * <p><b>Constructor:</b></p>
 * <ul>
 *   <li>{@code EsterEggServiceImpl(RestClient client)} - Constructor to initialize the service with a RestTemplate client.</li>
 * </ul>
 *
 * <p><b>Methods:</b></p>
 * <ul>
 *   <li>{@code getEsterEgg(String number)} - Retrieves an "Easter egg" fact from numbersapi.com based on the provided number.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This class should be used to fetch and display interesting facts ("Easter eggs") based on numeric input.</p>
 *
 * <pre>{@code
 * // Example usage:
 * EsterEggService esterEggService = new EsterEggServiceImpl(restTemplate);
 * String fact = esterEggService.getEsterEgg("42");
 * }</pre>
 *
 * @see com.crio.learningnavigator.service.EsterEggService
 */
@Service
@RequiredArgsConstructor
public class EsterEggServiceImpl implements EsterEggService {

    private final RestClient client;

    /**
     * Retrieves an "Easter egg" fact from {<a href="http://numbersapi.com">NumberAPI</a>} based on the provided number.
     *
     * @param number the number for which to fetch the Easter egg fact
     * @return a string containing the Easter egg fact
     * @throws RestClientException if an error occurs while making the HTTP request
     */
    @Override
    public String getEsterEgg(String number) throws RestClientException {
        String BASE_URL = "http://numbersapi.com/";
        return client.get()
                .uri(BASE_URL + number)
                .retrieve()
                .body(String.class);
    }
}
