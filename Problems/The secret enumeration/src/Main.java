public class Main {

    public static void main(String[] args) {
        int counter = 0;

        for (Secret secret: Secret.values()) {
            if (secret.toString().substring(0, 4).equals("STAR")) {
                counter++;
            }
        }

        System.out.println(counter);
    }
}

/////* At least two constants start with STAR
//enum Secret {
//    STAR, CRASH, START, // ...
//}
////*/