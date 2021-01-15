package bt3.command;

public enum CommandType {
    COFFEE(Position.COFFEE_POSITION),
    BREAD(Position.BREAD_POSITION),
    CHIP(Position.CHIP_POSITION),
    SALAD(Position.SALAD_POSITION),
    SANDWICH(Position.SANDWICH_POSITION),
    SAUSAGE(Position.SAUSAGE_POSITION),
    CHICKEN(Position.CHICKEN_POSITION),
    BISCUIT(Position.BISCUIT_POSITION),
    PIZZA(Position.PIZZA_POSITION),
    BURGER(Position.BURGER_POSITION);

    private final int pos;

    CommandType(int pos) {
        this.pos = pos;
    }

    public int getPosition() {
        return pos;
    }

    public static class Position {
        public static final int COFFEE_POSITION = 1;
        public static final int BREAD_POSITION = 2;
        public static final int CHIP_POSITION = 3;
        public static final int SALAD_POSITION = 4;
        public static final int SANDWICH_POSITION = 5;
        public static final int SAUSAGE_POSITION = 6;
        public static final int CHICKEN_POSITION = 7;
        public static final int BISCUIT_POSITION = 8;
        public static final int PIZZA_POSITION = 9;
        public static final int BURGER_POSITION = 10;
    }
}
