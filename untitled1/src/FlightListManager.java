import java.util.concurrent.ConcurrentHashMap;

public class FlightListManager {

    // Flight map to store flights with their codes as keys
    final private ConcurrentHashMap<String, Flight> flightMap;

    public FlightListManager(ConcurrentHashMap<String, Flight> flightMap) {
        this.flightMap = flightMap;
    }

    // Add a flight to the flight map
    public void addFlight(Flight flight) {
        flightMap.put(flight.getFlightCode(), flight);
    }

    // Get a flight based on the booking reference code
    public Flight getFlight(String flightCode) {
        return flightMap.get(flightCode);
    }

    // Remove a flight based on the booking reference code
    public void removeFlight(String flightCode) {
        flightMap.remove(flightCode);
    }

    // Print information for all flights
    public void exportFlightList() {
        for (Flight flight : flightMap.values()) {
            System.out.println(flight.toString());
        }
    }
}