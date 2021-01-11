import java.io.InputStream;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;

        int streamValue = inputStream.read();
        while (streamValue != -1) {
            byte x = (byte) streamValue;
            System.out.print(x);
            streamValue = inputStream.read();
        }
    }
}