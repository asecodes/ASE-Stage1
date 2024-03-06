import java.util.concurrent.ConcurrentHashMap;

public class PassengerListManager {

    final private ConcurrentHashMap<String, Passenger> passengerMap;


    public PassengerListManager(ConcurrentHashMap<String, Passenger> passengerMap) {
        this.passengerMap = passengerMap;
    }


    public void addPassenger(Passenger passenger) {
        passengerMap.put(passenger.getReferenceCode(), passenger);
    }


    public Passenger getPassenger(String referenceCode) {
        return passengerMap.get(referenceCode);
    }


    public void removePassenger(String referenceCode) {
        passengerMap.remove(referenceCode);
    }


    public void exportPassengerList() {
        for (Passenger passenger : passengerMap.values()) {
            System.out.println(passenger.toString());
        }
    }
}
