package battleship;

enum GridIcons {
    FOG("~ ", ""),
    MISS("M ", "You missed!"),
    HIT("X ", "You hit a ship!"),
    SHIP("O ", ""),
    SUNK("X ", "You sank a ship! Specify a new target:"),
    WON("X ", "You sank the last ship. You won. Congratulations!");

    String icon;
    String message;

    GridIcons(String icon, String message) {
        this.icon = icon;
        this.message = message;
    }

}

public class Grid {
    static String firstRow = "  1 2 3 4 5 6 7 8 9 10";
    static char[] letters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};

    public static int[][][] getNewGrid() {
        int[][][] defaultGrid = new int[10][10][2];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int x = 0; x < 2; x++) {
                    defaultGrid[i][j][x] = -1;
                }
            }
        }
        return defaultGrid;
    }
}
