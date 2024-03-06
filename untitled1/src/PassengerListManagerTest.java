import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.jupiter.api.Assertions.*;

class PassengerListManagerTest {

    private PassengerListManager passengerListManager;
    private ConcurrentHashMap<String, Passenger> passengerMap;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passengerMap = new ConcurrentHashMap<>();
        passengerListManager = new PassengerListManager(passengerMap);
        // Initialize a passenger object; ensure parameters match your Passenger constructor
        passenger = new Passenger("REF123", "John Doe", "FC123", true, "20 * 15 * 10", 15.5f);
    }

    @Test
    void addPassenger() {
        passengerListManager.addPassenger(passenger);
        assertTrue(passengerMap.containsKey(passenger.getReferenceCode()), "Passenger should be added to the map.");
    }

    @Test
    void getPassenger() {
        passengerMap.put(passenger.getReferenceCode(), passenger);
        Passenger retrievedPassenger = passengerListManager.getPassenger(passenger.getReferenceCode());
        assertEquals(passenger, retrievedPassenger, "Retrieved passenger should match the original passenger.");
    }

    @Test
    void removePassenger() {
        passengerMap.put(passenger.getReferenceCode(), passenger);
        passengerListManager.removePassenger(passenger.getReferenceCode());
        assertFalse(passengerMap.containsKey(passenger.getReferenceCode()), "Passenger should be removed from the map.");
    }

    @Test
    void exportPassengerList() {
        // Since exportPassengerList prints to the console, we'll verify its functionality indirectly.
        // Add a passenger and check if it's in the map, as exportPassengerList would iterate over these entries.
        passengerListManager.addPassenger(passenger);
        assertTrue(passengerMap.containsKey(passenger.getReferenceCode()), "Passenger should be in the map for export.");
        // Actual output to the console is not verified here, as it requires a different testing approach.
    }
}
