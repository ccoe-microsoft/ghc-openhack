public class Planet implements Comparable<Planet> {
    private String name;
    private double distanceAU;
    private int sizeKm;
    private String lightStatus;

    public Planet(String name, double distanceAU, int sizeKm) {
        this.name = name;
        this.distanceAU = distanceAU;
        this.sizeKm = sizeKm;
        this.lightStatus = "Full";
    }

    public String getName() {
        return name;
    }

    public double getDistanceAU() {
        return distanceAU;
    }

    public int getSizeKm() {
        return sizeKm;
    }

    public String getLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(String lightStatus) {
        this.lightStatus = lightStatus;
    }

    @Override
    public int compareTo(Planet other) {
        return Double.compare(this.distanceAU, other.distanceAU);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", distanceAU=" + distanceAU +
                ", sizeKm=" + sizeKm +
                ", lightStatus='" + lightStatus + '\'' +
                '}';
    }
}