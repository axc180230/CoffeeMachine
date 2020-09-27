enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    int levelNum;

    DangerLevel(int levelNum) {
        this.levelNum = levelNum;
    }

    public int getLevel() {
        return this.levelNum;
    }
}