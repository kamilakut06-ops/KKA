package Lab3;
import java.util.Arrays;
public class Lab3_2 {
        public static void main(String[] args) {
            System.out.println("=== Задание 1. Статический класс F ===");
            task1();

            System.out.println("\n=== Задание 2. Тест static ===");
            task2();

            System.out.println("\n=== Задание 3. Статика и экземпляры ===");
            task3();

            System.out.println("\n=== Задание 4. Массив объектов Circle ===");
            task4();

            System.out.println("\n=== StopWatch ===");
            taskStopWatch();
        }

        // =========================================================
        // ЗАДАНИЕ 1: Какие вызовы допустимы?
        // =========================================================
        static class F {
            int i;
            static String s;
            void method() {}
            static void statMethod() {}
        }

        static void task1() {
            F f = new F();
            // Допустимые вызовы:
            System.out.println(f.i);
            System.out.println(f.s);
            f.method();
            f.statMethod();
            System.out.println(F.s);
            F.statMethod();

            // НЕДОПУСТИМЫЕ
            System.out.println("Все допустимые вызовы выполнены.");
        }

        // =========================================================
        // ЗАДАНИЕ 2: Расставить static
        // =========================================================
        static class TestStatic {
            int count;

            public static void main(String[] args) { }

            public int getCount() { return count; }

            public static int factorial(int n) {
                int result = 1;
                for (int i = 1; i <= n; i++) result *= i;
                return result;
            }
        }

        static void task2() {
            System.out.println("5! = " + TestStatic.factorial(5));
        }

        // =========================================================
        // ЗАДАНИЕ 3: Исправление ошибок
        // =========================================================
        static void task3() {

            C c = new C();
            c.method1();
        }

        static class C {
            public void method1() {
                method2();
            }
            public static void method2() {
                System.out.println("Исправленная ошибка из задания 3 (статический метод).");
            }
        }

        // =========================================================
        // ЗАДАНИЕ 4: Массив объектов Circle + Поиск максимальной площади
        // =========================================================
        static class Circle {
            double radius;
            static int numberOfObjects = 0;
            public Circle() {
                this(1.0);
            }

            public Circle(double radius) {
                this.radius = radius;
                numberOfObjects++;
            }

            public double getArea() {
                return Math.PI * radius * radius;
            }
        }

        static void task4() {

            Circle[] circles = new Circle[5];
            for (int i = 0; i < circles.length; i++) {

                circles[i] = new Circle(i + 1.0);
            }

            System.out.println("Создано кругов: " + Circle.numberOfObjects);


            Circle maxCircle = circles[0];
            for (int i = 1; i < circles.length; i++) {
                if (circles[i].getArea() > maxCircle.getArea()) {
                    maxCircle = circles[i];
                }
            }

            System.out.println("Круг с макс. площадью: радиус = " + maxCircle.radius
                    + ", площадь = " + String.format("%.2f", maxCircle.getArea()));
        }

        // =========================================================
        // StopWatch (Секундомер)
        // =========================================================
        static class StopWatch {
            private long startTime;
            private long endTime;

            public StopWatch() {
                this.startTime = System.currentTimeMillis();
            }

            public void start() {
                this.startTime = System.currentTimeMillis();
            }

            public void stop() {
                this.endTime = System.currentTimeMillis();
            }

            public long getStartTime() { return startTime; }
            public long getEndTime() { return endTime; }

            public long getElapsedTime() {
                return endTime - startTime;
            }
        }

        static void taskStopWatch() {
            int[] arr = new int[100000];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) (Math.random() * 100000);
            }

            StopWatch sw = new StopWatch(); // Запуск таймера
            sw.start();


            for (int i = 0; i < arr.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] < arr[minIndex]) {
                        minIndex = j;
                    }
                }
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }

            sw.stop();
            System.out.println("Время сортировки 100 000 чисел: " + sw.getElapsedTime() + " мс.");
        }
    }

