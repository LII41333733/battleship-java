import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isComplete = false;
        while (!isComplete) {
            String val = scanner.nextLine();
            if ("0".equals(val)) {
                isComplete = true;
            } else {
                try {
                    int parsed = Integer.parseInt(val);
                    System.out.println(parsed * 10);
                } catch (Exception e) {
                    System.out.println("Invalid user input:" + " " + val);
                }
            }
        }
    }
}