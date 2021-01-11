public class Main {

//    enum Secret {
//        STAR, CRASH, START, // ...
//    }

    public static void main(String[] args) {
    // write your program here
        int counter = 0;
        Secret[] values =  Secret.values();
        for (Secret secret : values) {
            if (secret.toString().substring(0, 4).equals("STAR")) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}

/* sample enum for inspiration
   enum Secret {
    STAR, CRASH, START, // ...
}
*/