import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        List<Combination> combinations = List.of(new Combination(Move.TWIRL, Move.SPIN),
                new Combination(Move.LEAP, Move.TWIRL), new Combination(Move.SPIN, Move.LEAP),
                new Combination(Move.TWIRL, Move.LEAP), new Combination(Move.LEAP, Move.SPIN));
        for (Combination combination : combinations) {
            Effect effect = Sequence.getInstance().getEffect(combination.move1, combination.move2);
            System.out.println("Combination: " + combination.move1 + " " + combination.move2 + " Effect: " + effect);
        }
    }

    public enum Move {
        TWIRL, LEAP, SPIN
    }

    public enum Effect {
        FIREFLIES, THUNDERSTORM, SNOWFALL, GENTLE_RAIN, TORNADO, RAINBOW
    }

    private static class Combination {
        private final Move move1;
        private final Move move2;

        public Combination(Move move1, Move move2) {
            this.move1 = move1;
            this.move2 = move2;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Combination that = (Combination) obj;
            return move1 == that.move1 && move2 == that.move2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(move1, move2);
        }
    }

    private static class Sequence {
        private final HashMap<Combination, Effect> sequences = new HashMap<>();
        private static Sequence instance;

        private Sequence() {
            sequences.put(new Combination(Move.TWIRL, Move.TWIRL), Effect.FIREFLIES);
            sequences.put(new Combination(Move.TWIRL, Move.LEAP), Effect.THUNDERSTORM);
            sequences.put(new Combination(Move.TWIRL, Move.SPIN), Effect.SNOWFALL);
            sequences.put(new Combination(Move.LEAP, Move.SPIN), Effect.GENTLE_RAIN);
            sequences.put(new Combination(Move.LEAP, Move.TWIRL), Effect.TORNADO);
            sequences.put(new Combination(Move.LEAP, Move.LEAP), Effect.SNOWFALL);
            sequences.put(new Combination(Move.SPIN, Move.LEAP), Effect.RAINBOW);
            sequences.put(new Combination(Move.SPIN, Move.SPIN), Effect.FIREFLIES);
            sequences.put(new Combination(Move.SPIN, Move.TWIRL), Effect.THUNDERSTORM);
        }

        public static Sequence getInstance() {
            if (instance == null) {
                instance = new Sequence();
            }
            return instance;
        }

        public Effect getEffect(Move move1, Move move2) {
            return sequences.get(new Combination(move1, move2));
        }
    }
}