package ru.geekbrains.lesson1;

import java.util.*;

public class Introduction {
    public static void main(String[] args) {
        byte byteVariable = 127;
        short shortVariable = 12700;
        int intVariable = 127000000;
        long longVariable = 127000000000000000l;
        float floatVariable = 0.000005f;
        double doubleVariable = 0.00000000005;
        boolean boolVariable = true;
        char charVariable = 'c';
        String stringVariable = "string";

        System.out.println(doCalculation(1.0, 2.0, 3.5, 2.0)); // 3.75
        System.out.println(doCalculation(1.0, 2.0, 3.5, 0.0)); // 0.0
        System.out.println(doCheckSum(10, 10.5)); // false
        System.out.println(doCheckSum(10, 2.0)); // true
        doPrintSign(5); // Положиетльное
        doPrintSign(0); // Положиетльное
        doPrintSign(-5); // Отрицательное
        System.out.println(IsNegative(-2)); // true
        System.out.println(IsNegative(2)); // false
        doCustomGreeting("Svetlana");

        Scanner input = new Scanner(System.in);
        System.out.println("Введите год: ");
        Short year = input.nextShort();
        IsLeapYear(year);
    }

    public static double doCalculation(double a, double b, double c, double d){
        if (d != 0)
            return a * (b + (c / d));

        System.out.print("Делитель не может быть равен 0. ");
        return 0;
    }

    public static boolean doCheckSum(double a, double b){
        double sum = a + b;
        if (sum>=10.0 && sum <=20.0)
            return true;

        return false;
    }

    public static void doPrintSign(int a) {
        if (Math.signum(a) >=0 )
            System.out.println("Положительное");
        else System.out.println("Отрицательное");
    }

    public static boolean IsNegative(int a) {
        if (Math.signum(a) < 0)
            return  true;
        return  false;
    }

    public static void doCustomGreeting(String name){
        System.out.println("Hello, " + name);
    }

    public static void IsLeapYear(short year){
        System.out.print(year + " - ");
        if (year % 4 == 0){
            if (year % 100 == 0 && year % 400 != 0) {
                System.out.print("Обычный");
                return;
            }

            System.out.print("Високосный");
        }
        else {
            System.out.print("Обычный");
        }
    }
}