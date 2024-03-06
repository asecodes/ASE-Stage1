import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerGeneratorTest {

    @Test
    void generatePassenger() {
        // Assuming a setup for randomFlightCode to return a predictable value or mocking its behavior

        Passenger passenger = PassengerGenerator.generatePassenger();

        // Check if the generated passenger is not null
        assertNotNull(passenger, "Generated passenger should not be null.");

        // Check reference code format
        assertTrue(passenger.getReferenceCode().matches("[ACDEFGHIJKLNOPQRSTUVWXYZ]{4}\\d{5}"), "Reference code format should match pattern.");

        // Check if name is not empty and contains a space (representing first and last names)
        assertTrue(passenger.getName().contains(" "), "Name should contain a space.");
        assertFalse(passenger.getName().isEmpty(), "Name should not be empty.");

        // Assuming you have a way to validate flightCode against a known set of codes
        // assertTrue(predefinedFlightCodes.contains(passenger.getFlightCode()), "Flight code should be valid.");

        // Check if status is a boolean value (true or false)
        assertNotNull(passenger.isChecked(), "Checked status should not be null.");

        // Luggage size and weight checks could be more complex, depending on your needs
        String[] dimensions = passenger.getLuggageSize().split(" \\* ");
        assertEquals(3, dimensions.length, "Luggage size should have three dimensions.");
        for (String dimension : dimensions) {
            assertTrue(Integer.parseInt(dimension) >= 3 && Integer.parseInt(dimension) <= 15, "Each luggage dimension should be within the range 3 to 15.");
        }

        assertTrue(passenger.getLuggageWeight() >= 5.0f && passenger.getLuggageWeight() <= 35.0f, "Luggage weight should be within the range 5.0 to 35.0.");
    }
}
