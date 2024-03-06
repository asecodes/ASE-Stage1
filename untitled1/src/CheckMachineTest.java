import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class CheckMachineTest {

    @Test
    void checkInformation() {
        // 创建示例数据
        ConcurrentHashMap<String, Passenger> passengerMap = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Flight> flightMap = new ConcurrentHashMap<>();

        // 添加一些示例乘客和航班
        Passenger passenger = new Passenger("ABCD12345", "John Doe", "FL123", false, "20 * 15 * 10", 25.0f);
        passengerMap.put("ABCD12345", passenger);

        Flight flight = new Flight("FL123", "New York", "Los Angeles", 200, 50.0f, 100.0f, "20 * 20 * 20");
        flightMap.put("FL123", flight);

        // 执行测试
        assertDoesNotThrow(() -> CheckMachine.checkInformation("ABCD12345", "John Doe", passengerMap, flightMap));
    }

    @Test
    void calculateFee() {
        // 创建示例航班
        ConcurrentHashMap<String, Flight> flightMap = new ConcurrentHashMap<>();
        Flight flight = new Flight("FL123", "New York", "Los Angeles", 200, 50.0f, 100.0f, "20 * 20 * 20");
        flightMap.put("FL123", flight);

        // 执行测试
        float fee = CheckMachine.calculateFee(25.0f, "20 * 15 * 10", "FL123", flightMap);
        assertEquals(250.0f, fee);
    }

    @Test
    void readPassenger() {
        // 执行测试
        ConcurrentHashMap<String, Passenger> passengerMap = CheckMachine.readPassenger("passengerList.csv");
        assertNotNull(passengerMap);
        assertFalse(passengerMap.isEmpty());
    }

    @Test
    void readFlight() {
        // 执行测试
        ConcurrentHashMap<String, Flight> flightMap = CheckMachine.readFlight("flightList.csv");
        assertNotNull(flightMap);
        assertFalse(flightMap.isEmpty());
    }
}
