package com.crio.learningnavigator.service;

/**
 * The EsterEggService interface provides a method to retrieve information about a number.
 *
 * <p>Implementations of this interface should fetch data from an external service based on the provided number.</p>
 *
 * <p><b>Methods:</b></p>
 * <ul>
 *   <li>{@code getEsterEgg(String number)} - Retrieves information about the provided number.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This interface should be implemented to interact with an external service that provides information based on a number.</p>
 *
 * <pre>{@code
 * // Example usage:
 * EsterEggService esterEggService = new EsterEggServiceImpl(client);
 * String eggInfo = esterEggService.getEsterEgg("42");
 * }</pre>
 *
 * @see com.crio.learningnavigator.service.implementation.EsterEggServiceImpl
 */
public interface EsterEggService {

    /**
     * Retrieves information about the provided number.
     *
     * @param number the number for which information is to be retrieved
     * @return a string containing information about the number
     */
    String getEsterEgg(String number);
}
