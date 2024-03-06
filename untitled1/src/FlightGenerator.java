import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class FlightGenerator {

    private static final Random random = new Random();

    // Generate random destinations
    private static String randomDestination(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    // Generate random person names
    private static String randomName(int maxLength) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        int length = random.nextInt(maxLength) + 1; // Ensure at least 1 character
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char c = characters.charAt(index);
            if (i == 0) {
                c = Character.toUpperCase(c); // title case
            }
            sb.append(c);
        }
        return sb.toString();
    }

    // Generate random flight codes
    private static String randomFlightCode() {
        return randomDestination(3) + (random.nextInt(900) + 100); // 100-999
    }

    // Generate random integers
    private static int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    // Generate random floating-point numbers
    private static float randomFloat(float min, float max) {
        return min + random.nextFloat() * (max - min);
    }

    private static String randomLuggageSize(int minSize, int maxSize) {
        Random random = new Random();
        int length = random.nextInt(maxSize - minSize + 1) + minSize;
        int width = random.nextInt(maxSize - minSize + 1) + minSize;
        int height = random.nextInt(maxSize - minSize + 1) + minSize;
        return length + " * " + width + " * " + height;
    }

    // Generate random Flight objects
    public static Flight generateFlight() {
        String destination = randomDestination(1);
        String contractor = randomName(10);
        String flightCode = randomFlightCode();
        int maxPassengerNum = randomInt(80, 100);
        float freeLuggageWeight = Math.round(randomFloat(10f, 15f) * 10) / 10f; // Keep one decimal place
        float maxLuggageWeight = Math.round(randomFloat(20f, 25f) * 10) / 10f; // Keep one decimal place
        String maxLuggageSize = randomLuggageSize(4,20);

        return new Flight(destination, contractor, flightCode, maxPassengerNum, freeLuggageWeight, maxLuggageWeight,maxLuggageSize);
    }

    public static void main(String[] args) {

        ConcurrentHashMap<String, Flight> flightMap = new ConcurrentHashMap<>();
        FlightListManager fm = new FlightListManager(flightMap);
        // Generate and add 100 Flight objects to the list
        for (int i = 0; i < 3; i++) {
            Flight randomFlight = generateFlight();
            fm.addFlight(randomFlight); // Add the generated Flight object to the list
            System.out.println(randomFlight.toString());
        }

        // Save the generated Flight object to a CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("FlightList.csv"))) {
            // Write header
            writer.write("Destination,Contractor,FlightCode,MaxPassengerNum,FreeLuggageWeight,MaxLuggageWeight,MaxLuggageSize,LuggageWeightcapacity\n");

            // Traverse the mapping and write data to each Flight object
            for (Flight flight : flightMap.values()) {
                String line = String.format("%s,%s,%s,%d,%.1f,%.1f,%s,%.1f\n",
                        flight.getDestination(),
                        flight.getContractor(),
                        flight.getFlightCode(),
                        flight.getMaxPassengerNum(),
                        flight.getFreeLuggageWeight(),
                        flight.getMaxLuggageWeight(),
                        flight.getMaxLuggageSize(),
                        flight.getLuggageWeightcapacity());

                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}