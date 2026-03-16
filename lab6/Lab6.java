import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Lab6 {

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double distanceToLine(double a, double b, double c) {
            return Math.abs(a * x + b * y + c) / Math.sqrt(a * a + b * b);
        }

        @Override
        public String toString() {
            return String.format("(%.2f, %.2f)", x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        System.out.println("Лабораторна робота №6");
        System.out.println("Варіант 11: Множина HashSet точок (x,y). Знайти точки, що найбільше і найменше віддалені від прямої A*x+B*y+C=0.");
        System.out.println("Студент: Сорочан Ярослав Сергійович\n");

        Scanner scanner = new Scanner(System.in);
        Set<Point> points = new HashSet<>();

        System.out.println("Генерація 20 випадкових точок...");
        for (int i = 0; i < 20; i++) {
            double x = Math.round((Math.random() * 20 - 10) * 100.0) / 100.0;
            double y = Math.round((Math.random() * 20 - 10) * 100.0) / 100.0;
            points.add(new Point(x, y));
        }

        System.out.println("\nЗгенеровані точки:");
        int count = 1;
        for (Point p : points) {
            System.out.print(p + "  ");
            if (count % 5 == 0) System.out.println();
            count++;
        }
        System.out.println();

        System.out.println("\nВведіть коефіцієнти для рівняння прямої A*x + B*y + C = 0");
        System.out.print("Введіть A: ");
        double a = scanner.nextDouble();
        
        System.out.print("Введіть B: ");
        double b = scanner.nextDouble();
        
        System.out.print("Введіть C: ");
        double c = scanner.nextDouble();

        if (a == 0 && b == 0) {
            System.out.println("Помилка: Коефіцієнти A та B не можуть одночасно дорівнювати 0 (це не пряма).");
            scanner.close();
            return;
        }

        Point maxPoint = null;
        Point minPoint = null;
        double maxDistance = -1;
        double minDistance = Double.MAX_VALUE;

        System.out.println("\n--- Відстані точок до прямої ---");
        for (Point p : points) {
            double distance = p.distanceToLine(a, b, c);
            System.out.printf("Точка %s віддалена на %.4f%n", p, distance);

            if (distance > maxDistance) {
                maxDistance = distance;
                maxPoint = p;
            }

            if (distance < minDistance) {
                minDistance = distance;
                minPoint = p;
            }
        }

        System.out.println("\n--- Результати ---");
        System.out.printf("Рівняння прямої: %.2f*x + %.2f*y + %.2f = 0%n", a, b, c);
        
        if (maxPoint != null) {
            System.out.printf("Найбільше віддалена точка: %s, відстань = %.4f%n", maxPoint, maxDistance);
        }
        
        if (minPoint != null) {
            System.out.printf("Найменше віддалена точка: %s, відстань = %.4f%n", minPoint, minDistance);
        }

        scanner.close();
    }
}