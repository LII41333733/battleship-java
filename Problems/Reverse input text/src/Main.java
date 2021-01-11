import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int readerValue = reader.read();
        String holder = "";
        while (readerValue != -1) {
            char c = (char) readerValue;
            String s = Character.toString(c);
            holder = s + holder;
            readerValue = reader.read();
        }
        System.out.print(holder);
        reader.close();
    }
}
