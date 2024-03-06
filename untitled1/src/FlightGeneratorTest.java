import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightGeneratorTest {

    @Test
    void generateFlight() {
        Flight flight = FlightGenerator.generateFlight();

        // Check if the generated flight is not null
        assertNotNull(flight, "The generated flight should not be null.");

        // Check if destination is a single uppercase letter
        assertTrue(flight.getDestination().matches("[A-Z]"), "The destination should be a single uppercase letter.");

        // Check if contractor name is not empty and properly capitalized
        assertFalse(flight.getContractor().isEmpty(), "The contractor name should not be empty.");
        assertTrue(Character.isUpperCase(flight.getContractor().charAt(0)), "The contractor name should start with an uppercase letter.");

        // Check if flight code matches the expected pattern (3 letters followed by 3 digits)
        assertTrue(flight.getFlightCode().matches("[A-Z]{3}\\d{3}"), "The flight code format should be 3 uppercase letters followed by 3 digits.");

        // Check if maxPassengerNum is within the expected range
        assertTrue(flight.getMaxPassengerNum() >= 80 && flight.getMaxPassengerNum() <= 100, "The maximum number of passengers should be between 80 and 100.");

        // Check if freeLuggageWeight is within the expected range
        assertTrue(flight.getFreeLuggageWeight() >= 10.0f && flight.getFreeLuggageWeight() <= 15.0f, "The free luggage weight should be between 10.0 and 15.0.");

        // Check if maxLuggageWeight is within the expected range
        assertTrue(flight.getMaxLuggageWeight() >= 20.0f && flight.getMaxLuggageWeight() <= 25.0f, "The maximum luggage weight should be between 20.0 and 25.0.");

        // Check if maxLuggageSize matches the expected format (numbers * numbers * numbers)
        assertTrue(flight.getMaxLuggageSize().matches("\\d+ \\* \\d+ \\* \\d+"), "The maximum luggage size should be in the format 'number * number * number'.");
    }
}
