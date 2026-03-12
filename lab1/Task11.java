import java.util.Random;
import java.util.Scanner;

public class Task11 {
    public static void main(String[] args) {
        System.out.println("Сорочан Ярослав Сергійович, 11 варіант");

        int[][] A = new int[3][3];
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                A[i][j] = random.nextInt(100);
            }
        }

        System.out.println("- матриця А (до обробки);");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть i: ");
        int i = scanner.nextInt();
        System.out.print("Введіть j: ");
        int j = scanner.nextInt();

        System.out.println("- матриця А (після обробки);");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(A[row][col] + " ");
            }
            System.out.println();
        }

        if (i >= 0 && i < 3 && j >= 0 && j < 3) {
            System.out.println("- значення a_ij матриці А: " + A[i][j]);
        } else {
            System.out.println("Помилка: індекси повинні бути від 0 до 2.");
        }

        scanner.close();
    }
}
