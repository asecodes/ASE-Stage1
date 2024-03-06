import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Objects;

public class PassengerGenerator {
    private static final Random random = new Random();

    private static final Set<String> generatedReferenceCodes = new HashSet<>();

    private static String randomReferenceCode(int length1,int length2){
        String referenceCode;
        do {
            StringBuilder sb = new StringBuilder();
            String characters1 = "ACDEFGHIJKLNOPQRSTUVWXYZ";
            String characters2 = "0123456789";
            for(int i = 0; i < length1; i++) {
                int index = random.nextInt(characters1.length());
                sb.append(characters1.charAt(index));
            }
            for(int i = 0; i < length2; i++) {
                int index = random.nextInt(characters2.length());
                sb.append(characters2.charAt(index));
            }
            referenceCode = sb.toString();
        } while (generatedReferenceCodes.contains(referenceCode));
        generatedReferenceCodes.add(referenceCode);
        return referenceCode;
    }

    private static String randomName(int maxlength){
        String characters = "abcdefghijklmnopqrstuvwxyz";
        int length = random.nextInt(maxlength) + 1;
        StringBuilder sb = new StringBuilder();
        char a;
        for(int i =0; i < length; i++){
            int index = random.nextInt(characters.length());
            a = characters.charAt(index);
            if(i == 0){
                a = Character.toUpperCase(a);
            }
            sb.append(a);
        }

        if (sb.length() > 1) {
            int spaceIndex = random.nextInt(sb.length() - 1) + 1;
            sb.insert(spaceIndex, ' ');
        }

        return sb.toString();
    }

    private static String randomFlightCode(String filepath){
        HashMap<Integer,String> uniqueFlightCode= new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            boolean isFirstline = true;
            int i = -1;
            while((line = br.readLine())!=null){
                if(isFirstline){
                    isFirstline = false;
                    continue;
                }
                i++;
                String[] values = line.split(",");
                uniqueFlightCode.put(i,values[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int index = random.nextInt(uniqueFlightCode.size());
        StringBuilder sb = new StringBuilder();
        sb.append(uniqueFlightCode.get(index));
        return sb.toString();
    }

    private static String randomChecked(){
        List<String> checkedList = new ArrayList<>();
        checkedList.add("true");
        checkedList.add("false");
        Random random = new Random();
        String randomstatus = checkedList.get(random.nextInt(checkedList.size()));

        return randomstatus;
    }

    private static String randomLuggageSize(int minSize, int maxSize) {
        Random random = new Random();
        int length = random.nextInt(maxSize - minSize + 1) + minSize;
        int width = random.nextInt(maxSize - minSize + 1) + minSize;
        int height = random.nextInt(maxSize - minSize + 1) + minSize;
        return length + " * " + width + " * " + height;
    }

    private static int getPassengerCountForFlight(String flightCode, ConcurrentHashMap<String, Passenger> passengerMap) {
        int count = 0;
        for (Passenger passenger : passengerMap.values()) {
            if (passenger.getFlightCode().equals(flightCode)) {
                count++;
            }
        }
        return count;
    }

    public static Passenger generatePassenger() {
        String referenceCode = randomReferenceCode(4,5);
        String name = randomName(10);
        String flightCode = randomFlightCode("FlightList.csv");
        String isstatus = randomChecked();
        float LuggageWeight ;
        boolean status;
        if(Objects.equals(isstatus,"true")){
            status = true;
            LuggageWeight = Math.round(randomFloat(5f, 20f) * 10) / 10f;
        }else{
            status = false;
            LuggageWeight = Math.round(randomFloat(5f, 35f) * 10) / 10f;
        }
        //float LuggageWeight = Math.round(randomFloat(5f, 35f) * 10) / 10f;
        String LuggageSize = randomLuggageSize(3,15);

        return new Passenger(referenceCode, name, flightCode, status,LuggageSize, LuggageWeight);
    }

    private static float randomFloat(float min, float max) {
        return min + random.nextFloat() * (max - min);
    }

    public static void main(String[] args) {
        ConcurrentHashMap<String, Passenger> passengerMap = new ConcurrentHashMap<>();
        PassengerListManager pm = new PassengerListManager(passengerMap);
        ConcurrentHashMap<String, Flight> f = CheckMachine.readFlight("FlightList.csv");
        int i = 0;

        Passenger pa;
        do {
            pa = generatePassenger();
            pm.addPassenger(pa);
            System.out.println(pa.toString());
            i++;
        } while (getPassengerCountForFlight(pa.getFlightCode(), passengerMap) <= (f.get(pa.getFlightCode())).getMaxPassengerNum()&&i<101);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("PassengerList.csv"))) {

            writer.write("ReferenceCode,Name,FlightCode,Status,LuggageSize,LuggageWeight\n");


            for (Passenger passenger : passengerMap.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%.1f\n",
                        passenger.getReferenceCode(),
                        passenger.getName(),
                        passenger.getFlightCode(),
                        passenger.getChecked(),
                        passenger.getLuggageSize(),
                        passenger.getLuggageWeight());
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
