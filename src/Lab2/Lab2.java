package Lab2;
import java.text.SimpleDateFormat;
import java.util.*;

public class Lab2 {
    //Задание 1
    static void task1() {
        System.out.println("Задание 1:");
        Calendar received = Calendar.getInstance(); //Создан класс с текущей датой
        received.set(2026, Calendar.OCTOBER, 1);

        Date deadline = new Date(); // класс для хранения даты

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        System.out.println("Разработчик: Кутдюсова К.А.");
        System.out.println("Дата получения: " + sdf.format(received.getTime())); //дата пишется как по шаблону
        System.out.println("Дата сдачи (текущая): " + sdf.format(deadline));
    }

    //Задание 2
    static void task2(Scanner sc) {
        System.out.println("\nЗадание 2");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false); //запрет на ввод неправильных чисел

        System.out.print("Введите дату (формат дд.мм.гггг): ");
        String input = sc.nextLine();

        try {
            Date userDate = sdf.parse(input); //строка превращается в обьект data
            Date currentDate = new Date();

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(userDate);
            cal1.set(Calendar.HOUR_OF_DAY, 0); cal1.set(Calendar.MINUTE, 0); cal1.set(Calendar.SECOND, 0);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(currentDate);
            cal2.set(Calendar.HOUR_OF_DAY, 0); cal2.set(Calendar.MINUTE, 0); cal2.set(Calendar.SECOND, 0);

            if (cal1.getTime().equals(cal2.getTime())) {
                System.out.println("Введенная дата равна текущей.");
            } else if (userDate.after(currentDate)) {
                System.out.println("Введенная дата находится в будущем.");
            } else {
                System.out.println("Введенная дата находится в прошлом.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка формата даты! Введите строго dd.MM.yyyy");
        }
    }

    //Задание 3
    static class Student {
        String name;
        Calendar birthDate;
        //конструктор
        public Student(String name, int year, int month, int day) {
            this.name = name;
            this.birthDate = Calendar.getInstance();
            this.birthDate.set(year, month - 1, day);
        }

        public String getBirthDateString(String format) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(birthDate.getTime());
        }

        @Override //переопределение
        public String toString() {
            return "Студент: " + name + ", Дата рождения: " + getBirthDateString("dd.MM.yyyy");
        }
    }

    static void task3() {
        System.out.println("\nЗадание 3");
        Student s = new Student("Яна Кошкина", 1998, 3, 20);
        System.out.println(s);
        System.out.println("Короткий формат: " + s.getBirthDateString("dd.MM.yy"));
        System.out.println("Средний формат: " + s.getBirthDateString("dd MMM yyyy"));
        System.out.println("Полный формат: " + s.getBirthDateString("EEEE, d MMMM yyyy"));
    }

    //Задание 4
    static void task4(Scanner sc) {
        System.out.println("\nЗадание 4");
        System.out.print("Введите год: "); int year = sc.nextInt();
        System.out.print("Введите месяц (1-12): "); int month = sc.nextInt();
        System.out.print("Введите число: "); int day = sc.nextInt();
        System.out.print("Введите часы: "); int hour = sc.nextInt();
        System.out.print("Введите минуты: "); int minute = sc.nextInt();
        sc.nextLine();

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, minute, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        System.out.println("Созданный объект Date: " + sdf.format(date));
    }

    public static void run(Scanner sc) {
        System.out.println("\nПРАКТИКА №2");
        task1();
        task2(sc);
        task3();
        task4(sc);
    }
}


