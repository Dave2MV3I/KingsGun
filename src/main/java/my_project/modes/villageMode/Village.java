package my_project.modes.villageMode;

public class Village {
    private String dungeonMap = "src/main/resources/graphic/testImage.png";
    private int mapX;
    private int mapY;

    public Village(int x, int y) {
        mapX = x;
        mapY = y;
    }

    public String getVillageMap() {
        return dungeonMap;
    }
    public int getMapX() {return mapX;}
    public int getMapY() {return mapY;}
}
