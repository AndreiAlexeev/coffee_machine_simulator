enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private final int numberDangerLevel;

    DangerLevel(int numberDangerLevel){
        this.numberDangerLevel = numberDangerLevel;
    }

    public int getLevel(){
        return numberDangerLevel;
    }
}
class Main{
    public static void main(String[] args) {
        DangerLevel high = DangerLevel.HIGH;
        DangerLevel medium = DangerLevel.MEDIUM;

        System.out.println(high.getLevel() < medium.getLevel());
    }
}
