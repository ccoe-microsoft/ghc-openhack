import java.util.*;


public class App {
    public static void run() {
        List<Creature> creatures = List.of(
            new Creature("Dragon", new Position(2, 2), new Direction[]{Direction.RIGHT, Direction.LEFT, Direction.DOWN}, 7, "🐉"),
            new Creature("Goblin", new Position(2, 3), new Direction[]{Direction.LEFT, Direction.RIGHT, Direction.UP}, 3, "👺"),
            new Creature("Ogre", new Position(0, 0), new Direction[]{Direction.RIGHT, Direction.DOWN, Direction.DOWN}, 5, "👹")
        );

        BattleSimulator simulator = new BattleSimulator(creatures);
        Map<String, Integer> scores = simulator.battle();
        for (Map.Entry<String, Integer> score : scores.entrySet()) {
            System.out.println(score.getKey() + ": " + score.getValue());
        }
    }

    public static void main(String[] args) {
        run();
    }
}

record Position(int x, int y) {
    public Position moveBy(int dx, int dy, int gridSize) {
        return new Position(Math.max(0, Math.min(x + dx, gridSize - 1)), Math.max(0, Math.min(y + dy, gridSize - 1)));
    }
}

class Creature {
    private final String name;
    private Position start;
    private final Direction[] moves;
    private final int power;
    private final String icon;

    public Creature(String name, Position start, Direction[] moves, int power, String icon) {
        this.name = name;
        this.start = start;
        this.moves = moves;
        this.power = power;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public Direction[] getMoves() {
        return moves;
    }

    public int getPower() {
        return power;
    }

    public String getIcon() {
        return icon;
    }
}

enum Direction {
    UP, DOWN, LEFT, RIGHT
}

class BattleSimulator {
    private static final int GRID_SIZE = 5;
    private static final String OVERLAP_ICON = "🤺";
    private static final String EMPTY_CELL_ICON = "⬜";
    private static final int INITIAL_MOVE = -1;

    private static final Map<Direction, int[]> DIRECTIONS = Map.of(
        Direction.UP, new int[]{-1, 0},
        Direction.DOWN, new int[]{1, 0},
        Direction.LEFT, new int[]{0, -1},
        Direction.RIGHT, new int[]{0, 1}
    );

    private final List<Creature> creatures;

    public BattleSimulator(List<Creature> creatures) {
        this.creatures = creatures;
    }

    public Map<String, Integer> battle() {
        String[][] grid = new String[GRID_SIZE][GRID_SIZE];
        Map<String, Integer> scores = new HashMap<>();
        for (Creature creature : creatures) {
            scores.put(creature.getName(), 0);
            grid[creature.getStart().x()][creature.getStart().y()] = creature.getIcon();
        }

        int maxMoves = creatures.stream().mapToInt(c -> c.getMoves().length).max().orElse(0);
        for (int move = INITIAL_MOVE; move < maxMoves; move++) {
            renderGrid(move, grid, scores);
            if (move == maxMoves - 1) break;

            for (Creature creature : creatures) {
                Position position = creature.getStart();
                if (move >= 0 && move < creature.getMoves().length) {
                    int[] direction = DIRECTIONS.get(creature.getMoves()[move]);
                    position = position.moveBy(direction[0], direction[1], GRID_SIZE);
                }

                final Position finalPosition = position;

                Creature overlappingCreature = creatures.stream()
                    .filter(c -> c.getStart().equals(finalPosition) && !c.getName().equals(creature.getName()))
                    .findFirst().orElse(null);

                if (overlappingCreature != null) {
                    scores.put(overlappingCreature.getName(), scores.get(overlappingCreature.getName()) - creature.getPower());
                    scores.put(creature.getName(), scores.get(creature.getName()) + creature.getPower());
                    grid[position.x()][position.y()] = OVERLAP_ICON;
                } else {
                    grid[creature.getStart().x()][creature.getStart().y()] = null;
                    creature.setStart(position);
                    grid[position.x()][position.y()] = creature.getIcon();
                }
            }
        }

        return scores;
    }

    private void renderGrid(int move, String[][] grid, Map<String, Integer> scores) {
        String moveText = move == INITIAL_MOVE ? "Initial Board" : "Move " + (move + 1);
        System.out.println(moveText);
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print((grid[i][j] != null ? grid[i][j] : EMPTY_CELL_ICON) + " ");
            }
            System.out.println();
        }
        System.out.println("Scores:");
        for (Map.Entry<String, Integer> score : scores.entrySet()) {
            System.out.println(score.getKey() + ": " + score.getValue());
        }
        System.out.println("-----");
    }
}