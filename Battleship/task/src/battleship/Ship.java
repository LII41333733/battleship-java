package battleship;

public class Ship {
    String name;
    int size;
    int targetsLeft;
    int[][] shipCoordinates;
    int[][] shipCoordinatesCollected;


    Ship(String name, int size, int targetsLeft, int[][] shipCoordinates, int[][] shipCoordinatesCollected) {
        this.name = name;
        this.size = size;
        this.targetsLeft = targetsLeft;
        this.shipCoordinates = shipCoordinates;
        this.shipCoordinatesCollected = shipCoordinatesCollected;
    }

    public void setTargetsLeft() {
        if (this.targetsLeft > 0) {
            this.targetsLeft--;
        }
    }

    public void setShipCoordinates(int c1, int c2, int counter) {
        shipCoordinates[counter][0] = c1;
        shipCoordinates[counter][1] = c2;
    }

}
