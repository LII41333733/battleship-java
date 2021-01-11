package battleship;

public class Player {
    int id;
    int shipsLeft = 5;
    int[][][] grid; // defense

    Ship[] ships = new Ship[]{
            new Ship("Destroyer", 2, 2, new int[2][2], new int[2][2]),
            new Ship("Cruiser", 3, 3, new int[3][2], new int[3][2]),
            new Ship("Submarine", 3, 3, new int[3][2], new int[3][2]),
            new Ship("Battleship", 4, 4, new int[4][2], new int[4][2]),
            new Ship("Aircraft Carrier", 5, 5, new int[5][2], new int[5][2])
    };

    Player(int id) {
        this.id = id;
        this.grid = Grid.getNewGrid();
    }

}
