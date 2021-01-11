import java.io.InputStreamReader;
import java.io.BufferedReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean readerOn = false;
        boolean lettersEntered = false;
        int wordCount = 0;
        int letterHolder = 0;
        int readerValue = reader.read();
        while (readerValue != -1) {
            char current = (char) readerValue;
            if (current != ' ') {
                readerOn = true;
            }
            if (current == ' ' && readerOn) {
                lettersEntered = true;
                wordCount++;
                readerOn = false;
                letterHolder = 0;
            }
            letterHolder++;
            readerValue = reader.read();
        }
        if (lettersEntered) {
            if (letterHolder > 0) {
                wordCount++;
            }
            System.out.print(wordCount);
        } else {
            System.out.print(0);
        }
        reader.close();
    }
}