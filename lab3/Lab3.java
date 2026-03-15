import java.util.InputMismatchException;
import java.util.Scanner;

class Bus {
    private String driverName;
    private String busNumber;
    private int routeNumber;
    private String brand;
    private int startYear;
    private int mileage;

    public Bus(String driverName, String busNumber, int routeNumber, String brand, int startYear, int mileage) {
        this.driverName = driverName;
        this.busNumber = busNumber;
        this.routeNumber = routeNumber;
        this.brand = brand;
        this.startYear = startYear;
        this.mileage = mileage;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-10s | %-12d | %-10s | %-7d | %-10d",
                driverName, busNumber, routeNumber, brand, startYear, mileage);
    }

    public static void printHeader() {
        System.out.println(String.format("%-20s | %-10s | %-12s | %-10s | %-7s | %-10s",
                "Водій", "Номер", "Маршрут №", "Марка", "Рік", "Пробіг (км)"));
        System.out.println("-".repeat(80));
    }
}

public class Lab3 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- Лабораторна робота №3 ---");

        Bus[] buses = new Bus[5];

        buses[0] = new Bus("Іванов І.І.", "АА1234ВВ", 101, "Богдан", 2010, 150000);
        buses[1] = new Bus("Петров П.П.", "АА5678ВВ", 102, "Еталон", 2018, 50000);
        buses[2] = new Bus("Сидоров С.С.", "АХ9999СХ", 101, "Mercedes", 2005, 300000);
        buses[3] = new Bus("Коваленко К.", "ВС1111АА", 103, "MAN", 2020, 20000);

        System.out.println("Дані про перші 4 автобуси згенеровано системою.");
        System.out.println("Введіть дані про 5-й автобус:");

        buses[4] = inputBusInfo();

        System.out.println("\nУсі автобуси у базі:");
        printBuses(buses);

        while (true) {
            System.out.println("\nМеню пошуку:");
            System.out.println("1. Список автобусів заданого маршруту");
            System.out.println("2. Список автобусів, які в експлуатації більше 10 років");
            System.out.println("3. Список автобусів, пробіг яких більше 100000 км");
            System.out.println("0. Вихід");
            int choice = readInt("Ваш вибір: ", 0, 3);

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    int searchRoute = readInt("Введіть номер маршруту для пошуку: ", 1, 9999);
                    searchByRoute(buses, searchRoute);
                    break;
                case 2:
                    searchInOperationMoreThan10Years(buses);
                    break;
                case 3:
                    searchHighMileage(buses);
                    break;
            }
        }

        scanner.close();
    }

    private static Bus inputBusInfo() {
        while (true) {
            try {
                System.out.print("ПІБ водія: ");
                String driver = scanner.nextLine().trim();
                if (driver.isEmpty()) throw new IllegalArgumentException("Ім'я не може бути пустим.");

                System.out.print("Номер автобуса (формат ХХ0000ХХ): ");
                String number = scanner.nextLine().trim();
                if (number.isEmpty()) throw new IllegalArgumentException("Номер не може бути пустим.");

                int route = readInt("Номер маршруту: ", 1, 9999);

                System.out.print("Марка автобуса: ");
                String brand = scanner.nextLine().trim();
                if (brand.isEmpty()) throw new IllegalArgumentException("Марка не може бути пустою.");

                int currentYear = java.time.Year.now().getValue();
                int year = readInt("Рік початку експлуатації (1950 - " + currentYear + "): ", 1950, currentYear);

                int mileage = readInt("Пробіг (у км, більший від 0): ", 0, 2000000);

                return new Bus(driver, number, route, brand, year, mileage);
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка введення даних: " + e.getMessage() + " Спробуйте ще раз.");
            }
        }
    }

    private static int readInt(String prompt, int min, int max) {
        int result = 0;
        while (true) {
            System.out.print(prompt);
            try {
                result = scanner.nextInt();
                scanner.nextLine();

                if (result < min || result > max) {
                    throw new IllegalArgumentException("Значення має бути в межах від " + min + " до " + max + ".");
                }
                return result;
            } catch (InputMismatchException e) {
                System.out.println("Помилка типу даних: Очікується ціле число. Спробуйте ще раз.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка діапазону: " + e.getMessage());
            }
        }
    }

    private static void printBuses(Bus[] buses) {
        Bus.printHeader();
        for (Bus bus : buses) {
            if (bus != null) {
                System.out.println(bus);
            }
        }
    }

    private static void searchByRoute(Bus[] buses, int routeNumber) {
        System.out.println("\nРезультат пошуку за маршрутом №" + routeNumber + ":");
        boolean found = false;
        Bus.printHeader();
        for (Bus bus : buses) {
            if (bus != null && bus.getRouteNumber() == routeNumber) {
                System.out.println(bus);
                found = true;
            }
        }
        if (!found) System.out.println("За заданим критерієм пошуку даних не знайдено.");
    }

    private static void searchInOperationMoreThan10Years(Bus[] buses) {
        int currentYear = java.time.Year.now().getValue();
        System.out.println("\nРезультат пошуку автобусів в експлуатації більше 10 років (старші ніж " + (currentYear - 10) + "):");
        boolean found = false;
        Bus.printHeader();
        for (Bus bus : buses) {
            if (bus != null && (currentYear - bus.getStartYear()) > 10) {
                System.out.println(bus);
                found = true;
            }
        }
        if (!found) System.out.println("За заданим критерієм пошуку даних не знайдено.");
    }

    private static void searchHighMileage(Bus[] buses) {
        System.out.println("\nРезультат пошуку автобусів з пробігом > 100000 км:");
        boolean found = false;
        Bus.printHeader();
        for (Bus bus : buses) {
            if (bus != null && bus.getMileage() > 100000) {
                System.out.println(bus);
                found = true;
            }
        }
        if (!found) System.out.println("За заданим критерієм пошуку даних не знайдено.");
    }
}
