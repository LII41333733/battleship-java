package battleship;

import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Battleship battleship = new Battleship();
        battleship.setShips();
    }

    static class Battleship {
        boolean gameWon = false;
        int testArrayCounter = 0;
        boolean isDev = false;
        String[][] test1 = new String[][]{
                {"a1", "e1"},
                {"a3", "d3"},
                {"a5", "c5"},
                {"a7", "c7"},
                {"a9", "b9"}
        };
        String[][] test2 = new String[][]{
                {"a1", "a5"},
                {"c1", "c4"},
                {"e1", "e3"},
                {"g1", "g3"},
                {"i1", "i2"}
        };
        String[] testArr = new String[]{
                "a1", "a1", "a2", "a2", "a3", "a3", "a4", "a4", "a5", "a5",
                "c1", "c1", "c2", "c2", "c3", "c3", "c4", "c4",
                "e1", "e1", "e2", "e2", "e3", "e3",
                "g1", "g1", "g2", "g2", "g3", "g3",
                "i1", "i1", "i2", "i2"
        };
        String[] testArr2 = new String[]{
                "a1", "a1", "b1", "b1", "c1", "c1", "d1", "d1", "e1", "e1",
                "a3", "a3", "b3", "b3", "c3", "c3", "d3", "d3",
                "a5", "a5", "b5", "b5", "c5", "c5",
                "a7", "a7", "b7", "b7", "c7", "c7",
                "a9", "a9", "b9", "b9"
        };


        Player[] players = new Player[]{new Player(1), new Player(2)};
        boolean isPlayer1Turn = true;
        boolean hasGameStarted = false;
        String shotResult = "";
        Scanner scanner = new Scanner(System.in);

        Coordinate[] coordinates = new Coordinate[2];

        public Coordinate getC1() {
            return coordinates[0];
        }

        public Coordinate getC2() {
            return coordinates[1];
        }

        public Ship getCurrentShip() {
            return getCurrentPlayer().ships[getCurrentPlayer().shipsLeft - 1];
        }

        public int getCurrentPlayerIndex() {
            return isPlayer1Turn ? 0 : 1;
        }

        public Player getCurrentPlayer() {
            return players[getCurrentPlayerIndex()];
        }

        public Player getOpposingPlayer() {
            int whichPlayerIndex = getCurrentPlayerIndex() == 1 ? 0 : 1;
            return players[whichPlayerIndex];
        }

        public int[][][] getCurrentGrid() {
            return getCurrentPlayer().grid;
        }

        public void setShips() {
            startMessage();
            printGrid(getCurrentPlayer().grid, true);
            gameHandler();
            for (int i = 0; i < players.length; i++) {
                while (getCurrentPlayer().shipsLeft - 1 >= 0) {
                    setShip();
                }

                if (i != 0) {
                    switchPlayers();
                }
            }
            runGame();
        }

        public void setShip() {
            String[][] whichTest = getCurrentPlayer().id == 1 ? test1 : test2;
            String c1_holder = isDev ? whichTest[testArrayCounter][0] : scanner.next();
            String c2_holder = isDev ? whichTest[testArrayCounter][1] : scanner.next();

            coordinates[0] = new Coordinate(c1_holder);
            coordinates[1] = new Coordinate(c2_holder);

            if (Coordinate.checkIsValid(getC1(), getC2())) {
                setOrder();
                String validator = Coordinate.checkIsValidDetails(getC1(), getC2(), getCurrentShip(), getCurrentGrid());
                if ("pass".equals(validator)) {
                    updateGrid();
                    testArrayCounter++;
                    getCurrentPlayer().shipsLeft--;
                    printGrid(getCurrentPlayer().grid, true);

                    if (getCurrentPlayer().shipsLeft == 0) {
                        testArrayCounter = 0;
                        passMessage();
                        switchPlayers();
                        if (getCurrentPlayer().id == 2) {
                            startMessage();
                            printGrid(getCurrentPlayer().grid, true);
                            gameHandler();
                        }
                    } else {
                        gameHandler();
                    }
                } else {
                    System.out.println(validator);
                }
            }
        }

        public void printGrid(int[][][] grid, boolean showShips) {
            System.out.println(Grid.firstRow);
            for (int i = 0; i < 10; i++) {
                StringBuilder fogLine = new StringBuilder();
                for (int j = 0; j < 10; j++) {
                    boolean isAShip = grid[i][j][0] > -1;
                    boolean isFog = grid[i][j][0] == -1 || grid[i][j][0] == -2;
                    boolean isAHit = grid[i][j][0] == -3;
                    boolean isAMiss = grid[i][j][0] == -4;

                    if (isAShip && showShips) {
                        fogLine.append(GridIcons.SHIP.icon);
                    } else if (isAShip || isFog) {
                        fogLine.append(GridIcons.FOG.icon);
                    } else if (isAHit) {
                        fogLine.append(GridIcons.HIT.icon);
                    } else if (isAMiss) {
                        fogLine.append(GridIcons.MISS.icon);
                    }
                }
                System.out.printf("%c %s%n", Character.toUpperCase(Grid.letters[i]), fogLine.toString());
            }
        }

        public void gameHandler() {
            if (hasGameStarted) {
                if (!"".equals(shotResult)) {
                    System.out.println(shotResult);
                    shotResult = "";
                }
                if (gameWon) {
                    hasGameStarted = false;
                }
            } else {
                System.out.printf("%nEnter the coordinates of the %s (%s cells):%n%n", getCurrentShip().name, getCurrentShip().size);
            }
        }

        public void updateGrid() {
            int charIndex1 = coordinates[0].getCharIndex();
            int intIndex1 = coordinates[0].getIntIndex();
            int counter = 0;
            int shipSize = getCurrentShip().size;
            if (Coordinate.checkHorizontal(getC1(), getC2())) {
                for (int i = intIndex1; i < shipSize + intIndex1; i++) {
                    getCurrentGrid()[charIndex1][i][0] = charIndex1;
                    getCurrentGrid()[charIndex1][i][1] = i;
                    getCurrentShip().setShipCoordinates(charIndex1, i, counter);
                    if (i == intIndex1 && intIndex1 - 1 != -1) {
                        getCurrentGrid()[charIndex1][i - 1][0] = -2;
                        getCurrentGrid()[charIndex1][i - 1][1] = -2;
                    }
                    if (charIndex1 != 9) {
                        if (getCurrentGrid()[charIndex1 + 1][i][0] == -1) {
                            getCurrentGrid()[charIndex1 + 1][i][0] = -2;
                            getCurrentGrid()[charIndex1 + 1][i][1] = -2;
                        }
                    }
                    if (charIndex1 != 0) {
                        if (getCurrentGrid()[charIndex1 - 1][i][0] == -1) {
                            getCurrentGrid()[charIndex1 - 1][i][0] = -2;
                            getCurrentGrid()[charIndex1 - 1][i][1] = -2;
                        }
                    }
                    if (i == shipSize + intIndex1 - 1 && i + 1 != 10) {
                        getCurrentGrid()[charIndex1][i + 1][0] = -2;
                        getCurrentGrid()[charIndex1][i + 1][1] = -2;
                    }
                    counter++;
                }
            } else {
                for (int i = charIndex1; i < shipSize + charIndex1; i++) {
                    getCurrentGrid()[i][intIndex1][0] = i;
                    getCurrentGrid()[i][intIndex1][1] = intIndex1;
                    getCurrentShip().setShipCoordinates(i, intIndex1, counter);
                    if (i == charIndex1 && charIndex1 - 1 != -1) {
                        getCurrentGrid()[i - 1][intIndex1][0] = -2;
                        getCurrentGrid()[i - 1][intIndex1][1] = -2;
                    }
                    if (intIndex1 != 9) {
                        getCurrentGrid()[i][intIndex1 + 1][0] = -2;
                        getCurrentGrid()[i][intIndex1 + 1][1] = -2;
                    }
                    if (intIndex1 != 0) {
                        getCurrentGrid()[i][intIndex1 - 1][0] = -2;
                        getCurrentGrid()[i][intIndex1 - 1][1] = -2;
                    }
                    if (i == shipSize + charIndex1 - 1 && i + 1 != 10) {
                        getCurrentGrid()[i + 1][intIndex1][0] = -2;
                        getCurrentGrid()[i + 1][intIndex1][1] = -2;
                    }
                    counter++;
                }
            }
        }

        public void runGame() {
            hasGameStarted = true;
            switchPlayers();
            int testCounter = 0;
            while (hasGameStarted && !gameWon) {
                printFullBoard();
                turnMessage();
                String test = isDev ? testArr[testCounter] : scanner.next();
                coordinates[0] = new Coordinate(test);
                if (Coordinate.hasCorrectFormatting(getC1())) {
                    shotsFired();
                    gameHandler();
                    switchPlayers();
                    if (!gameWon) {
                        passMessage();
                    }
                } else {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                }
                testCounter++;
            }
        }

        public void printFullBoard() {
            printGrid(getOpposingPlayer().grid, false);
            System.out.println("---------------------");
            printGrid(getCurrentPlayer().grid, true);
        }

        static boolean areArraysEqual(int[] array1, int[] array2) {
            return Arrays.deepEquals(new Object[]{array1}, new Object[]{array2});
        }

        public String checkHitType() {
            boolean shipHit = false;
            boolean shipSunk = false;
            for (Ship ship : getOpposingPlayer().ships) {
                if (ship.targetsLeft > 0) {
                    for (int a = 0; a < ship.size; a++) {
                        if (areArraysEqual(Coordinate.getShotArray(getC1()), ship.shipCoordinates[a])) {
                            ship.shipCoordinatesCollected[ship.targetsLeft - 1] = Coordinate.getShotArray(getC1());
                            ship.setTargetsLeft();
                            shipHit = true;

                            if (ship.targetsLeft == 0) {
                                shipSunk = true;
                            }
                        }
                    }
                }
            }
            int sinkCheck = 5;
            for (Ship ship : getOpposingPlayer().ships) {
                if (ship.targetsLeft == 0) {
                    sinkCheck--;
                }
            }
            if (sinkCheck < 1) {
                gameWon = true;
                return GridIcons.WON.message;
            } else {
                if (shipSunk) {
                    return GridIcons.SUNK.message;
                } else if (shipHit) {
                    return GridIcons.HIT.message;
                } else {
                    return "";
                }
            }
        }

        public void shotsFired() {
            Coordinate c1 = getC1();
            shotResult = "";
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    int[] b = getOpposingPlayer().grid[i][j];
                    if (b[0] == -3 && areArraysEqual(new int[]{i, j}, Coordinate.getShotArray(c1))) {
                        shotResult = GridIcons.HIT.message;
                    }
                    if (areArraysEqual(b, Coordinate.getShotArray(c1))) {
                        b[0] = -3;
                        b[1] = -3;
                        shotResult = checkHitType();
                    }
                }
            }
            if ("".equals(shotResult)) {
                getOpposingPlayer().grid[c1.getCharIndex()][c1.getIntIndex()][0] = -4;
                getOpposingPlayer().grid[c1.getCharIndex()][c1.getIntIndex()][0] = -4;
                shotResult = GridIcons.MISS.message;
            }
        }

        public void setOrder() {
            Coordinate c1 = getC1();
            Coordinate c2 = getC2();
            if ((Coordinate.checkCharEquality(c1, c2) && c1.getInt() > c2.getInt()) || (Coordinate.checkIntEquality(c1, c2) && c1.charToInt() > c2.charToInt())) {
                coordinates[0] = c2;
                coordinates[1] = c1;
            }
        }

        public void startMessage() {
            System.out.printf("Player %d, place your ships on the game field%n%n", getCurrentPlayer().id);
        }

        public void passMessage() {
            System.out.printf("%nPress Enter and pass the move to another player%n");
            try {
                System.in.read();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public void turnMessage() {
            System.out.printf("%nPlayer %d, it's your turn:%n%n", getCurrentPlayer().id);
        }

        public void switchPlayers() {
            isPlayer1Turn = !isPlayer1Turn;
        }
    }
}