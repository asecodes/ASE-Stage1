public class Flight {
    private String destination;
    private String contractor;
    private String flightCode;
    private int maxPassengerNum;
    private float freeLuggageWeight;
    private float maxLuggageWeight;

    private String maxLuggageSize;

    private float LuggageWeightcapacity;

    private int checkinNum;

    private int luggageNum;

    private float totalFee;

    private float totalWeight;

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void addTotalWeight(float Weight) {
        this.totalWeight += Weight;
    }

    public float getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(float totalFee) {
        this.totalFee = totalFee;
    }

    public void addTotalFee(float Fee) {
        this.totalFee += Fee;
    }

    public int getLuggageNum() {
        return luggageNum;
    }

    public void setLuggageNum(int luggageNum) {
        this.luggageNum = luggageNum;
    }

    public void addLuggageNum() {
        this.luggageNum += 1;
    }



    public int getCheckinNum() {
        return checkinNum;
    }

    public void setCheckinNum(int checkinNum) {
        this.checkinNum = checkinNum;
    }

    public void addCheckinNum() {
        this.checkinNum += 1;
    }

    public float getLuggageWeightcapacity() {
        return LuggageWeightcapacity;
    }

    public String getMaxLuggageSize() {
        return maxLuggageSize;
    }

    public void setMaxLuggageSize(String maxLuggageSize) {
        this.maxLuggageSize = maxLuggageSize;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public int getMaxPassengerNum() {
        return maxPassengerNum;
    }

    public void setMaxPassengerNum(int maxPassengerNum) {
        this.maxPassengerNum = maxPassengerNum;
    }

    public float getFreeLuggageWeight() {
        return freeLuggageWeight;
    }

    public void setFreeLuggageWeight(float freeLuggageWeight) {
        this.freeLuggageWeight = freeLuggageWeight;
    }

    public float getMaxLuggageWeight() {
        return maxLuggageWeight;
    }

    public void setMaxLuggageWeight(float maxLuggageWeight) {
        this.maxLuggageWeight = maxLuggageWeight;
    }

    public Flight(String destination, String contractor, String flightCode, int maxPassengerNum, float freeLuggageWeight, float maxLuggageWeight,String maxLuggageSize) {
        this.destination = destination;
        this.contractor = contractor;
        this.flightCode = flightCode;
        this.maxPassengerNum = maxPassengerNum;
        this.freeLuggageWeight = freeLuggageWeight;
        this.maxLuggageWeight = maxLuggageWeight;
        this.maxLuggageSize = maxLuggageSize;
        this.LuggageWeightcapacity = Math.round(this.maxLuggageWeight * this.maxPassengerNum * 10) / 10f;;
        this.checkinNum = 0;
        this.luggageNum = 0;
        this.totalFee = 0;
        this.totalWeight = 0;

    }

    @Override
    public String toString() {
        return "Flight{" +
                "destination='" + destination + '\'' +
                ", contractor='" + contractor + '\'' +
                ", flightCode='" + flightCode + '\'' +
                ", maxPassengerNum=" + maxPassengerNum +
                ", freeLuggageWeight=" + freeLuggageWeight +
                ", maxLuggageWeight=" + maxLuggageWeight +
                ", maxLuggageSize='" + maxLuggageSize + '\'' +
                ", LuggageWeightcapacity=" + LuggageWeightcapacity +
                ", checkinNum=" + checkinNum +
                ", luggageNum=" + luggageNum +
                ", totalFee=" + Math.round(totalFee * 10) / 10f +
                ", totalWeight=" + Math.round(totalWeight * 10) / 10f +
                '}';
    }
}
