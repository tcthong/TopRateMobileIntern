package bt3.complain;

public enum Complain {
    GLAD(Level.COMPLAIN_GLAD_LEVEL),
    NORMAL(Level.COMPLAIN_NORMAL_LEVEL),
    BAD(Level.COMPLAIN_BAD_LEVEL),
    EXTREME_BAD(Level.COMPLAIN_EXTREME_BAD_LEVEL);

    private final int level;

    Complain(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static Complain getComplainByLevel(int level) {
        switch (level) {
            case Level.COMPLAIN_GLAD_LEVEL:
                return Complain.GLAD;

            case Level.COMPLAIN_NORMAL_LEVEL:
                return Complain.NORMAL;

            case Level.COMPLAIN_BAD_LEVEL:
                return Complain.BAD;

            case Level.COMPLAIN_EXTREME_BAD_LEVEL:
                return Complain.EXTREME_BAD;

            default:
                throw new IllegalArgumentException("Mức độ không hợp lệ");
        }
    }

    public static class Level {
        public static final int COMPLAIN_GLAD_LEVEL = 0;
        public static final int COMPLAIN_NORMAL_LEVEL = 1;
        public static final int COMPLAIN_BAD_LEVEL = 2;
        public static final int COMPLAIN_EXTREME_BAD_LEVEL = 3;
    }
}
