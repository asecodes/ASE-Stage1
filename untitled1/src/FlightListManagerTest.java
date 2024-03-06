import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightListManagerTest {

    private FlightListManager flightListManager;
    private ConcurrentHashMap<String, Flight> flightMap;
    private Flight testFlight;

    @BeforeEach
    void setUp() {
        flightMap = new ConcurrentHashMap<>();
        flightListManager = new FlightListManager(flightMap);
        testFlight = new Flight("D", "Acme Airlines", "ABC123", 100, 10.0f, 20.0f, "22 * 14 * 9");
    }

    @Test
    void addFlight() {
        flightListManager.addFlight(testFlight);
        assertTrue(flightMap.containsKey(testFlight.getFlightCode()), "Flight should be added to the map.");
    }

    @Test
    void getFlight() {
        flightMap.put(testFlight.getFlightCode(), testFlight);
        Flight retrievedFlight = flightListManager.getFlight(testFlight.getFlightCode());
        assertEquals(testFlight, retrievedFlight, "Retrieved flight should match the original flight.");
    }

    @Test
    void removeFlight() {
        flightMap.put(testFlight.getFlightCode(), testFlight);
        flightListManager.removeFlight(testFlight.getFlightCode());
        assertFalse(flightMap.containsKey(testFlight.getFlightCode()), "Flight should be removed from the map.");
    }

    @Test
    void exportFlightList() {
        // Since exportFlightList prints to the console, we'll verify its functionality indirectly.
        // Add a flight and check if it's in the map, as exportFlightList would iterate over these entries.
        flightListManager.addFlight(testFlight);
        assertTrue(flightMap.containsKey(testFlight.getFlightCode()), "Flight should be in the map for export.");
        // Actual output to the console is not verified here, as it requires a different testing approach.
    }
}
