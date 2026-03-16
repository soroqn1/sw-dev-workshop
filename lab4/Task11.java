import java.io.*;
import java.util.Scanner;

public class Task11 {
    public static void main(String[] args) {
        System.out.println("Лабораторна робота №4");
        System.out.println("Варіант 11: Відкрити будь-який текстовий файл та поміняти місцями перше і останнє слова в кожному рядку.");
        System.out.println("Студент: Сорочан Ярослав Сергійович\n");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть шлях до вхідного файлу: ");
        String inputFilePath = scanner.nextLine();

        System.out.print("Введіть шлях до вихідного файлу: ");
        String outputFilePath = scanner.nextLine();

        System.out.println("\nПочаток обробки файлу...");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            int lineNumber = 1;
            int processedLines = 0;

            while ((line = reader.readLine()) != null) {
                System.out.println("Читання рядка " + lineNumber + ": '" + line + "'");
                
                String processedLine = swapFirstAndLastWord(line);
                
                if (!line.equals(processedLine)) {
                    System.out.println("-> Змінено на: '" + processedLine + "'");
                    processedLines++;
                } else {
                    System.out.println("-> Рядок залишився без змін.");
                }

                writer.write(processedLine);
                writer.newLine();
                lineNumber++;
            }

            System.out.println("\nОбробку завершено успішно!");
            System.out.println("Всього прочитано рядків: " + (lineNumber - 1));
            System.out.println("Всього змінено рядків: " + processedLines);
            System.out.println("Результат записано у файл: " + outputFilePath);

        } catch (FileNotFoundException e) {
            System.err.println("Помилка: Вхідний файл не знайдено - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Помилка вводу/виводу: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Невідома помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static String swapFirstAndLastWord(String line) {
        int startIdx = 0;
        while(startIdx < line.length() && Character.isWhitespace(line.charAt(startIdx))) {
            startIdx++;
        }
        
        int endIdx = line.length() - 1;
        while(endIdx >= 0 && Character.isWhitespace(line.charAt(endIdx))) {
            endIdx--;
        }
        
        if (startIdx >= endIdx) {
            return line; 
        }
        
        String leadingSpaces = line.substring(0, startIdx);
        String trailingSpaces = line.substring(endIdx + 1);
        
        String content = line.substring(startIdx, endIdx + 1);
        
        int w1End = 0;
        while (w1End < content.length() && !Character.isWhitespace(content.charAt(w1End))) {
            w1End++;
        }
        
        if (w1End == content.length()) {
            return line; 
        }
        
        int w2Start = content.length() - 1;
        while (w2Start >= 0 && !Character.isWhitespace(content.charAt(w2Start))) {
            w2Start--;
        }
        
        String w1 = content.substring(0, w1End);
        String w2 = content.substring(w2Start + 1);
        String mid = content.substring(w1End, w2Start + 1);
        
        return leadingSpaces + w2 + mid + w1 + trailingSpaces;
    }
}