import java.util.Random;
import java.util.Scanner;

public class Lesson3 {
    public static void main(String[] args){
        task1();
    }

    public static void task1()
    {
        System.out.println("Задание 1. Игра \"Отгадай число\".");
        GamesDialog();
    }

    public static void GamesDialog(){
        byte userAnswer;

        do {
            System.out.println("\nИграть? (0 - нет, 1 - да)");
            userAnswer = getConsoleDigit();

            if (userAnswer == 1)
                runGame();
            else if (userAnswer != 0 && userAnswer != 1 )
                System.out.println("Ответ не распознан (0 - нет, 1 - да)."); // Можно было сделать anykey - да, но в задании именно такая формулировка.
        } while (userAnswer != 0);
    }

    public static byte getConsoleDigit(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextByte();
    }

    public static void runGame(){
        byte secretNumber = generateDigit();
        System.out.println("Загадана цифра от 0 да 9. Попробуйте угадать ее с трех попыток.");
        doAssumptions(secretNumber);
    }

    public static void doAssumptions(byte secretNumber){
        byte attemptsNum = 0;
        boolean win = false;

        while (attemptsNum < 3 && win == false){
            System.out.printf("Попытка %d.", attemptsNum + 1);
            win = checkUserAssumption(secretNumber);
            attemptsNum++;
        }

        System.out.println(win ? "WIN!" : "FAIL Т_Т");
        System.out.printf("Была загадана цифра %d", secretNumber);
    }

    public static byte generateDigit() {
        Random rnd = new Random();
        return (byte)rnd.nextInt(10);
    }

    public static byte getUserAssumption(){
        byte digit;

        do {
            System.out.println("Ваше предположение (в диапазоне от 0 до 9):");
            digit = getConsoleDigit();
        } while (digit < 0 || digit > 9);

        return digit;
    }

    public static boolean checkUserAssumption(byte secretDigit){
        byte userAssumption = getUserAssumption();

        if (secretDigit > userAssumption) {
            System.out.println("Загаданная цифра больше.");
            return false;
        }
        else if (secretDigit < userAssumption) {
            System.out.println("Загаданная цифра меньше.");
            return false;
        }
        else return true;
    }
}
