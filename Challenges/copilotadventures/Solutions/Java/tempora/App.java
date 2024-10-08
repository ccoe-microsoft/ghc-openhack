import java.util.ArrayList;
import java.util.List;

/**
 * The App class demonstrates the functionality of managing and comparing times of different clocks.
 */
public class App {

    public static void main(String[] args) {
        Clock grandClockTower = new Clock("Grand Clock Tower", "15:00");
        List<Clock> clocks = new ArrayList<>();
        clocks.add(new Clock("Clock 1", "14:45"));
        clocks.add(new Clock("Clock 2", "15:05"));
        clocks.add(new Clock("Clock 3", "15:00"));
        clocks.add(new Clock("Clock 4", "14:40"));

        System.out.println("Clock Data:");
        System.out.println(grandClockTower);
        for (Clock clock : clocks) {
            System.out.println(clock);
        }

        System.out.println("Time Differences:");
        for (Clock clock : clocks) {
            int timeDifference = calculateTimeDifference(grandClockTower, clock);
            System.out.println(timeDifference + " minutes");
        }
    }

    private static int calculateTimeDifference(Clock referenceClock, Clock otherClock) {
        String[] referenceTimeParts = referenceClock.getTime().split(":");
        String[] otherTimeParts = otherClock.getTime().split(":");

        int referenceMinutes = Integer.parseInt(referenceTimeParts[0]) * 60 + Integer.parseInt(referenceTimeParts[1]);
        int otherMinutes = Integer.parseInt(otherTimeParts[0]) * 60 + Integer.parseInt(otherTimeParts[1]);

        return Math.abs(referenceMinutes - otherMinutes);
    }

    static class Clock {
        private String name;
        private String time;

        public Clock(String name, String time) {
            this.name = name;
            this.time = time;
        }

        public String getTime() {
            return time;
        }

        @Override
        public String toString() {
            return name + " is at " + time;
        }
    }
}