import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть довжину випадкового числа (більше або дорівнює 2): ");
        int length = scanner.nextInt();

        if (length < 2) {
            System.out.println("Помилка: довжина повинна бути принаймні 2, щоб можна було поміняти місцями першу і другу цифри.");
            return;
        }

        Random random = new Random();
        StringBuilder numBuilder = new StringBuilder();
        numBuilder.append(random.nextInt(9) + 1);
        for (int i = 1; i < length; i++) {
            numBuilder.append(random.nextInt(10));
        }

        String originalNum = numBuilder.toString();
        System.out.println("\nВихідне випадкове число: " + originalNum);

        String processedNum = processNumberString(originalNum);
        System.out.println("Число після обробки: " + processedNum);

        System.out.println("\n--- Перевірка прикладу з умови ---");
        String testStr = "64583";
        System.out.println("Вихідне число: " + testStr);
        System.out.println("Після обробки: " + processNumberString(testStr) + " (Очікувалося: 35573)");

        scanner.close();
    }

    public static String processNumberString(String numStr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numStr.length(); i++) {
            char ch = numStr.charAt(i);
            int digit = ch - '0';

            if (digit % 2 == 0) {
                if (digit == 0) {
                    sb.append('9');
                } else {
                    sb.append(digit - 1);
                }
            } else {
                sb.append(ch);
            }
        }

        if (sb.length() >= 2) {
            char first = sb.charAt(0);
            char second = sb.charAt(1);
            sb.setCharAt(0, second);
            sb.setCharAt(1, first);
        }

        return sb.toString();
    }
}
