package battleship;

public class Coordinate {
    String c;

    Coordinate(String c) {
        this.c = c;
    }

    char getChar() {
        return Character.toLowerCase(c.charAt(0));
    }

    int getInt() {
        return Integer.parseInt(c.substring(1));
    }

    int getCharIndex() {
        return charToInt() - 1;
    }

    int getIntIndex() {
        return getInt() - 1;
    }

    int charToInt() {
        switch (getChar()) {
            case 'a':
                return 1;
            case 'b':
                return 2;
            case 'c':
                return 3;
            case 'd':
                return 4;
            case 'e':
                return 5;
            case 'f':
                return 6;
            case 'g':
                return 7;
            case 'h':
                return 8;
            case 'i':
                return 9;
            case 'j':
                return 10;
            default:
                return -1;
        }
    }

    static boolean checkIntEquality(Coordinate c1, Coordinate c2) {
        return c1.getInt() == c2.getInt();
    }
    static boolean checkCharEquality(Coordinate c1, Coordinate c2) {
        return c1.getChar() == c2.getChar();
    }

    static boolean checkVertical(Coordinate c1, Coordinate c2) {
        return checkIntEquality(c1, c2) && !checkCharEquality(c1, c2);
    }

    static boolean checkHorizontal(Coordinate c1, Coordinate c2) {
        return !checkIntEquality(c1, c2) && checkCharEquality(c1, c2);
    }

    static int[] getShotArray(Coordinate c) { return new int[]{c.getCharIndex(), c.getIntIndex()}; }

    static  boolean checkIsValid(Coordinate c1, Coordinate c2) {
        try {
            String stringLetters = new String(Grid.letters);
            return stringLetters.contains(Character.toString(c1.getChar())) &&
                    stringLetters.contains(Character.toString(c2.getChar())) &&
                    c1.getInt() >= 1 &&
                    c1.getInt() <= 10 &&
                    c2.getInt() >= 1 &&
                    c2.getInt() <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static String checkIsValidDetails(Coordinate c1, Coordinate c2, Ship currentShip, int[][][] currentGrid) {
        if (!Coordinate.hasCorrectFormatting(c1)) {
            return "Error! Wrong format!";
        }

        if (!hasCorrectDiagonalPositioning(c1, c2)) {
            return "Error! Wrong ship location! Try again:";
        }

        if (!hasCorrectLength(c1, c2, currentShip.size)) {
            return String.format("%nError! Wrong length of the %s! Try again:%n", currentShip.name);
        }

        if (!hasCorrectPlacement(c1, c2, currentGrid)) {
            return "Error! You placed it too close to another one. Try again:";
        }

        return "pass";
    }

    static boolean hasCorrectFormatting(Coordinate c) {
        try {
            return c.getInt() >= 1 && c.charToInt() >= 1 && c.getInt() <= 10 && c.charToInt() <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean hasCorrectDiagonalPositioning(Coordinate c1, Coordinate c2) {
        return Coordinate.checkVertical(c1, c2) || Coordinate.checkHorizontal(c1, c2);
    }

    static boolean hasCorrectLength(Coordinate c1, Coordinate c2, int size) {
        if (Coordinate.checkHorizontal(c1, c2)) {
            return Math.abs(c1.getInt() - c2.getInt()) + 1 == size;
        } else {
            return Math.abs((int) c1.getChar() - ((int) c2.getChar() + 1)) == size;
        }
    }

    static boolean hasCorrectPlacement(Coordinate c1, Coordinate c2, int[][][] currentGrid) {
        return currentGrid[c1.getCharIndex()][c1.getIntIndex()][0] == -1 && currentGrid[c2.getCharIndex()][c2.getIntIndex()][0] == -1;
    }
}
