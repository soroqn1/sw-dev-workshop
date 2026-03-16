import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab5 {

    public static class ArtExhibition {
        private String name;
        private String location;
        private String time;
        private List<Painting> paintings;

        public ArtExhibition(String name, String location, String time) {
            this.name = name;
            this.location = location;
            this.time = time;
            this.paintings = new ArrayList<>();
        }

        public void addPainting(String title, String author, String style) {
            Painting painting = new Painting(title, author, style);
            paintings.add(painting);
            System.out.println("Картину '" + title + "' успішно додано до виставки.");
        }

        public void displayExhibitionInfo() {
            System.out.println("\n--- Інформація про виставку ---");
            System.out.println("Назва: " + name);
            System.out.println("Місце проведення: " + location);
            System.out.println("Час проведення: " + time);
            System.out.println("Наповнення (картини):");
            if (paintings.isEmpty()) {
                System.out.println("  Виставка поки порожня.");
            } else {
                for (Painting p : paintings) {
                    System.out.println("  - " + p.toString());
                }
            }
            System.out.println("-------------------------------\n");
        }

        public void searchByAuthor(String author) {
            System.out.println("\nПошук картини за автором '" + author + "'...");
            boolean found = false;
            for (Painting p : paintings) {
                if (p.getAuthor().equalsIgnoreCase(author)) {
                    System.out.println("  Знайдено: " + p.toString());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("  Картин даного автора не знайдено.");
            }
        }

        public void searchByStyle(String style) {
            System.out.println("\nПошук картини за направленням '" + style + "'...");
            boolean found = false;
            for (Painting p : paintings) {
                if (p.getStyle().equalsIgnoreCase(style)) {
                    System.out.println("  Знайдено: " + p.toString());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("  Картин даного направлення не знайдено.");
            }
        }

        public class Painting {
            private String title;
            private String author;
            private String style;

            public Painting(String title, String author, String style) {
                this.title = title;
                this.author = author;
                this.style = style;
            }

            public String getTitle() {
                return title;
            }

            public String getAuthor() {
                return author;
            }

            public String getStyle() {
                return style;
            }

            @Override
            public String toString() {
                return String.format("Назва: '%s', Автор: %s, Направлення: %s", title, author, style);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Лабораторна робота №5");
        System.out.println("Варіант 11: Зовнішній клас - Художня виставка, внутрішній клас - Картина.");
        System.out.println("Студент: Сорочан Ярослав Сергійович\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Створення нової виставки:");
        System.out.print("Введіть назву виставки: ");
        String exhibitionName = scanner.nextLine();
        
        System.out.print("Введіть місце проведення: ");
        String location = scanner.nextLine();
        
        System.out.print("Введіть час проведення: ");
        String time = scanner.nextLine();

        ArtExhibition exhibition = new ArtExhibition(exhibitionName, location, time);
        System.out.println("Виставку успішно створено!\n");

        boolean running = true;
        while (running) {
            System.out.println("Меню:");
            System.out.println("1. Додати картину до виставки");
            System.out.println("2. Вивести інформацію про виставку та всі картини");
            System.out.println("3. Знайти картини за автором");
            System.out.println("4. Знайти картини за направленням");
            System.out.println("0. Вихід");
            System.out.print("Оберіть дію: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Помилка вводу. Будь ласка, введіть число.\n");
                scanner.nextLine();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введіть назву картини: ");
                    String title = scanner.nextLine();
                    System.out.print("Введіть автора картини: ");
                    String author = scanner.nextLine();
                    System.out.print("Введіть направлення (стиль): ");
                    String style = scanner.nextLine();
                    exhibition.addPainting(title, author, style);
                    System.out.println();
                    break;
                case 2:
                    exhibition.displayExhibitionInfo();
                    break;
                case 3:
                    System.out.print("Введіть автора для пошуку: ");
                    String searchAuthor = scanner.nextLine();
                    exhibition.searchByAuthor(searchAuthor);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Введіть направлення (стиль) для пошуку: ");
                    String searchStyle = scanner.nextLine();
                    exhibition.searchByStyle(searchStyle);
                    System.out.println();
                    break;
                case 0:
                    running = false;
                    System.out.println("Завершення роботи програми.");
                    break;
                default:
                    System.out.println("Невідома команда. Спробуйте ще раз.\n");
            }
        }

        scanner.close();
    }
}