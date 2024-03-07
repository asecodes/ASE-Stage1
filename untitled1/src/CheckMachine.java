import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CheckMachine {
    String scannedReferenceCode;
    String scannedPassengerName;
    String scannedFlightCode;
    String scannedLuggageSize;
    float scannedLuggageWeight;

    public CheckMachine(String scannedReferenceCode, String scannedPassengerName, String scannedFlightCode, String scannedLuggageSize, float scannedLuggageWeight) {
        this.scannedReferenceCode = scannedReferenceCode;
        this.scannedPassengerName = scannedPassengerName;
        this.scannedFlightCode = scannedFlightCode;
        this.scannedLuggageSize = scannedLuggageSize;
        this.scannedLuggageWeight = scannedLuggageWeight;
    }

    public static void checkInformation(String scannedReferenceCode, String scannedPassengerName, ConcurrentHashMap<String, Passenger> list, ConcurrentHashMap<String, Flight> list2) throws FormatException {
        Passenger p = list.get(scannedReferenceCode);
        try {
            if (scannedReferenceCode.length() != 9) {
                throw new FormatException("The length of the reference code does not match", "000");
            } else {
                String firstFourChars = scannedReferenceCode.substring(0, 4);
                boolean allLetters = true;

                for (int i = 0; i < firstFourChars.length(); i++) {
                    char ch = firstFourChars.charAt(i);
                    if (!Character.isLetter(ch)) {
                        allLetters = false;
                        break; // No need to continue checking once a non-letter character is found
                    }
                }
                if (!allLetters) {
                    throw new FormatException("The letter verification code of the reference code is incorrect", "001");
                } else {
                    String lastFiveChars = scannedReferenceCode.substring(scannedReferenceCode.length() - 5);
                    boolean allNumbers = true;

                    for (int i = 0; i < lastFiveChars.length(); i++) {
                        char ch = lastFiveChars.charAt(i);
                        if (!Character.isDigit(ch)) {
                            allNumbers = false;
                            break;
                        }
                    }
                    if (!allNumbers) {
                        throw new FormatException("The numeric verification code of the reference code is incorrect", "002");
                    }
                }
            }
        } catch (FormatException e) {
            e.logException(); // Call custom method to log exception information
            System.err.println("Exception handled, error code: " + e.getErrorCode()); // Directly access the additional property
        }

        try {
            Passenger a = list.get(scannedReferenceCode);
            if (a == null) {
                throw new FormatException("Reference code could not be found", "003");
            }
        } catch (FormatException e) {
            e.logException(); // Call custom method to log exception information
            System.err.println("Exception handled, error code: " + e.getErrorCode()); // Directly access the additional property
        }

        if (!Objects.equals(p.getName(), scannedPassengerName)) {
            System.out.println("Sorry. Your name cannot be found. Please retype the name!");
        } else {
            if (!p.isChecked()) {
                System.out.print(p.toString()+"\n");
                //System.out.println(p.getFlightCode());
                Flight f = list2.get(p.getFlightCode());
                System.out.println("This is your flight information:"+f.toString()+"\n");
                if (CheckMachine.calculateFee(p.getLuggageWeight(), p.getLuggageSize(), p.getFlightCode(), list2) > 10100) {

                    System.out.println("Sorry. Your luggage size is over the limit. Please contact the staff!");
                } else if (CheckMachine.calculateFee(p.getLuggageWeight(), p.getLuggageSize(), p.getFlightCode(), list2) < 10200 && CheckMachine.calculateFee(p.getLuggageWeight(), p.getLuggageSize(), p.getFlightCode(), list2) > 10000) {
                    System.out.println("Sorry. Your luggage weight is over the limit. Please contact the staff!");
                } else {
                    System.out.println("Checks in successfully!");
                    System.out.println("You need to pay " + String.format("%.1f", CheckMachine.calculateFee(p.getLuggageWeight(), p.getLuggageSize(), p.getFlightCode(), list2)) + " dollars for the luggage weight!");
                }
            } else {
                System.out.println("You have already checked in.");
            }
        }

        //Flight f = list2.get(p.getFlightCode());
        for (Passenger m : list.values()) {
            for(Flight f:list2.values()){
                if(Objects.equals(m.getFlightCode(),f.getFlightCode())){
                    if(m.isChecked()) {
                        //System.out.println("这是正在检查的乘客："+ m.toString() );
                        f.addCheckinNum();
                        //System.out.println(f.getFlightCode()+" 已经签到的乘客数量: "+f.getCheckinNum());
                        f.addLuggageNum();
                        //System.out.println(f.getFlightCode()+" 已经装载的行李数量: "+f.getLuggageNum());
                        f.addTotalWeight(m.getLuggageWeight());
                        //System.out.println(f.getFlightCode()+" 已经装载的行李总重量: "+f.getTotalWeight());
                        //System.out.println("\n"+f.toString());
                        if(CheckMachine.calculateFee(m.getLuggageWeight(), m.getLuggageSize(), m.getFlightCode(), list2)>10000){
                            int a =0;
                            f.addTotalFee(a);
                        }else {
                            f.addTotalFee(CheckMachine.calculateFee(m.getLuggageWeight(), m.getLuggageSize(), m.getFlightCode(), list2));
                        }
                    }
                }
            }


        }



    }

    public static float calculateFee(float luggageWeight, String luggageSize, String flightCode, ConcurrentHashMap<String, Flight> list) {
        float fee = 0;
        // Default luggage fee is 0
        float freeLuggageWeight;
        float maxLuggageWeight;
        String maxLuggageSize;

        Flight f = list.get(flightCode);
        freeLuggageWeight = f.getFreeLuggageWeight();
        maxLuggageWeight = f.getMaxLuggageWeight();
        maxLuggageSize = f.getMaxLuggageSize();

        String[] values1 = luggageSize.split("\\s*\\*\\s*");
        String[] values2 = maxLuggageSize.split("\\s*\\*\\s*");
        for(int i =0;i<3;i++){

            if(Integer.parseInt(values1[i]) > Integer.parseInt(values2[i]) ){
                fee = 10000;
                return fee + 200;
            }else{
                fee = 0;

            }

        }
        if (luggageWeight < freeLuggageWeight) {
            return fee;
        } else if (luggageWeight > maxLuggageWeight) {
            fee = 10000;
            return fee + 100;
        } else {
            fee = (luggageWeight - freeLuggageWeight) * 10;
            return fee;
        }
    }

    public static ConcurrentHashMap<String,Passenger> readPassenger(String passengerList){
        try(BufferedReader bf = new BufferedReader(new FileReader(passengerList))) {
            String line;
            boolean isFirstLine = true;
            ConcurrentHashMap<String,Passenger> pl = new ConcurrentHashMap<>();
            while((line = bf.readLine())!=null){
                if(isFirstLine){
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                boolean v3 = Boolean.parseBoolean(values[3]);
                String v4 = values[4];
                float v5 = Float.parseFloat(values[5]);
                Passenger p = new Passenger(values[0],values[1],values[2],v3,v4,v5);
                pl.put(values[0],p);
            }
            return pl;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static ConcurrentHashMap<String,Flight> readFlight(String flightList) throws FormatException {
        try(BufferedReader bf = new BufferedReader(new FileReader(flightList))) {
            String line;
            boolean isFirstLine = true;
            ConcurrentHashMap<String,Flight> pl = new ConcurrentHashMap<>();
            while((line = bf.readLine())!=null){
                if(isFirstLine){
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");

                int v3 = Integer.parseInt(values[3]);
                float v4 = Float.parseFloat(values[4]);
                float v5 = Float.parseFloat(values[5]);
                String v6 = values[6];
                Flight f = new Flight(values[0],values[1],values[2],v3,v4,v5,v6);
                pl.put(values[2],f);
            }
            return pl;

        } catch (FileNotFoundException e) {
            throw new FormatException("File not found", "100");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

}
