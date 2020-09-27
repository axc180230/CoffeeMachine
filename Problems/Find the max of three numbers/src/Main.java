import java.util.Scanner;

public class Main {

    public static int getNumberOfMaxParam(int a, int b, int c) {
        int maxInt = a;
        int maxIntPosition = 0;
        int[] arr = new int[]{a, b, c};

        for (int num = 1; num < 3; num++) {
            if (arr[num] > maxInt) {
                maxInt = arr[num];
                maxIntPosition = num;
            }
        }

        return maxIntPosition + 1;

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();

        System.out.println(getNumberOfMaxParam(a, b, c));
    }
}