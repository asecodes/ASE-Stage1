import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void getReferenceCode() {
        Passenger passenger = new Passenger("REF123", "John Doe", "FC123", true, "22 * 14 * 9", 15.5f);
        assertEquals("REF123", passenger.getReferenceCode(), "getReferenceCode should return the correct reference code.");
    }

    @Test
    void setReferenceCode() {
        Passenger passenger = new Passenger("", "John Doe", "FC123", true, "22 * 14 * 9", 15.5f);
        passenger.setReferenceCode("REF456");
        assertEquals("REF456", passenger.getReferenceCode(), "setReferenceCode should update the reference code correctly.");
    }

    @Test
    void getName() {
        Passenger passenger = new Passenger("REF123", "John Doe", "FC123", true, "22 * 14 * 9", 15.5f);
        assertEquals("John Doe", passenger.getName(), "getName should return the correct name.");
    }

    @Test
    void setName() {
        Passenger passenger = new Passenger("REF123", "", "FC123", true, "22 * 14 * 9", 15.5f);
        passenger.setName("Jane Doe");
        assertEquals("Jane Doe", passenger.getName(), "setName should update the name correctly.");
    }

    @Test
    void getFlightCode() {
        Passenger passenger = new Passenger("REF123", "John Doe", "FC123", true, "22 * 14 * 9", 15.5f);
        assertEquals("FC123", passenger.getFlightCode(), "getFlightCode should return the correct flight code.");
    }

    @Test
    void setFlightCode() {
        Passenger passenger = new Passenger("REF123", "John Doe", "", true, "22 * 14 * 9", 15.5f);
        passenger.setFlightCode("FC456");
        assertEquals("FC456", passenger.getFlightCode(), "setFlightCode should update the flight code correctly.");
    }

    @Test
    void isChecked() {
        Passenger passenger = new Passenger("REF123", "John Doe", "FC123", true, "22 * 14 * 9", 15.5f);
        assertTrue(passenger.isChecked(), "isChecked should return true when the passenger is checked.");
    }

    @Test
    void getChecked() {
        Passenger passenger = new Passenger("REF123", "John Doe", "FC123", false, "22 * 14 * 9", 15.5f);
        assertEquals("false", passenger.getChecked(), "getChecked should return 'false' when the passenger is not checked.");
    }

    @Test
    void setChecked() {
        Passenger passenger = new Passenger("REF123", "John Doe", "FC123", false, "22 * 14 * 9", 15.5f);
        passenger.setChecked(true);
        assertTrue(passenger.isChecked(), "setChecked should update the checked status correctly.");
    }

    @Test
    void getLuggageSize() {
        Passenger passenger = new Passenger("REF123", "John Doe", "FC123", true, "22 * 14 * 9", 15.5f);
        assertEquals("22 * 14 * 9", passenger.getLuggageSize(), "getLuggageSize should return the correct luggage size.");
    }

    @Test
    void setLuggageSize() {
        Passenger passenger = new Passenger("REF123", "John Doe", "FC123", true, "", 15.5f);
        passenger.setLuggageSize("24 * 16 * 10");
        assertEquals("24 * 16 * 10", passenger.getLuggageSize(), "setLuggageSize should update the luggage size correctly.");
    }

    @Test
    void setLuggageWeight() {
        Passenger passenger = new Passenger("REF123", "John Doe", "FC123", true, "22 * 14 * 9", 0f);
        passenger.setLuggageWeight(20.5f);
        assertEquals(20.5f, passenger.getLuggageWeight(), "setLuggageWeight should update the luggage weight correctly.");
    }
}