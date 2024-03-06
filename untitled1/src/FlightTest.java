import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight("New York", "Delta Airlines", "DL123", 180, 15.0f, 23.0f, "158 cm x 62 cm x 40 cm");
    }

    @Test
    void getTotalWeight() {
        flight.setTotalWeight(100.0f);
        assertEquals(100.0f, flight.getTotalWeight());
    }

    @Test
    void setTotalWeight() {
        flight.setTotalWeight(150.0f);
        assertEquals(150.0f, flight.getTotalWeight());
    }

    @Test
    void addTotalWeight() {
        flight.setTotalWeight(100.0f);
        flight.addTotalWeight(50.0f);
        assertEquals(150.0f, flight.getTotalWeight());
    }

    @Test
    void getTotalFee() {
        flight.setTotalFee(200.0f);
        assertEquals(200.0f, flight.getTotalFee());
    }

    @Test
    void setTotalFee() {
        flight.setTotalFee(250.0f);
        assertEquals(250.0f, flight.getTotalFee());
    }

    @Test
    void addTotalFee() {
        flight.setTotalFee(100.0f);
        flight.addTotalFee(150.0f);
        assertEquals(250.0f, flight.getTotalFee());
    }

    @Test
    void getLuggageNum() {
        flight.setLuggageNum(5);
        assertEquals(5, flight.getLuggageNum());
    }

    @Test
    void setLuggageNum() {
        flight.setLuggageNum(10);
        assertEquals(10, flight.getLuggageNum());
    }

    @Test
    void addLuggageNum() {
        flight.setLuggageNum(0);
        flight.addLuggageNum();
        assertEquals(1, flight.getLuggageNum());
    }

    @Test
    void getCheckinNum() {
        flight.setCheckinNum(5);
        assertEquals(5, flight.getCheckinNum());
    }

    @Test
    void setCheckinNum() {
        flight.setCheckinNum(10);
        assertEquals(10, flight.getCheckinNum());
    }

    @Test
    void addCheckinNum() {
        flight.setCheckinNum(0);
        flight.addCheckinNum();
        assertEquals(1, flight.getCheckinNum());
    }

    @Test
    void getLuggageWeightcapacity() {
        // Assuming you set LuggageWeightcapacity in the constructor based on maxLuggageWeight and maxPassengerNum
        float expected = flight.getMaxLuggageWeight() * flight.getMaxPassengerNum();
        assertEquals(expected, flight.getLuggageWeightcapacity(), 0.1);
    }

    @Test
    void getMaxLuggageSize() {
        assertEquals("158 cm x 62 cm x 40 cm", flight.getMaxLuggageSize());
    }

    @Test
    void setMaxLuggageSize() {
        flight.setMaxLuggageSize("160 x 60 x 45 ");
        assertEquals("160 x 60 x 45 ", flight.getMaxLuggageSize());
    }

    @Test
    void getDestination() {
        assertEquals("New York", flight.getDestination());
    }

    @Test
    void setDestination() {
        flight.setDestination("Los Angeles");
        assertEquals("Los Angeles", flight.getDestination());
    }

    @Test
    void getContractor() {
        assertEquals("Delta Airlines", flight.getContractor());
    }

    @Test
    void setContractor() {
        flight.setContractor("United Airlines");
        assertEquals("United Airlines", flight.getContractor());
    }

    @Test
    void getFlightCode() {
        assertEquals("DL123", flight.getFlightCode());
    }

    @Test
    void setFlightCode() {
        flight.setFlightCode("UA456");
        assertEquals("UA456", flight.getFlightCode());
    }

    @Test
    void getMaxPassengerNum() {
        assertEquals(180, flight.getMaxPassengerNum());
    }

    @Test
    void setMaxPassengerNum() {
        flight.setMaxPassengerNum(200);
        assertEquals(200, flight.getMaxPassengerNum());
    }

    @Test
    void getFreeLuggageWeight() {
        assertEquals(15.0f, flight.getFreeLuggageWeight());

    }
}