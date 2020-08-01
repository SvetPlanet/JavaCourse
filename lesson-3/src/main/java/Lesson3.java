import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Lesson3 {
    public static void main(String[] args){
        task("Задание 1. Игра \"Отгадай число\".");
        task("Задание 2. Игра \"Отгадай фрукт\".");
    }

    public static void task(String taskName)
    {
        System.out.println(taskName);
        GamesDialog(taskName);
    }

    public static int[] getGameSettings(String gameName){
        if (gameName.startsWith("Задание 1"))
            return new int[] {1, 10};
        else if (gameName.startsWith("Задание 2"))
            return new int [] {2, 25};

        return null;
    }

    public static void GamesDialog(String gameName){
        byte userAnswer;

        do {
            System.out.println("Играть? (0 - нет, 1 - да)");
            userAnswer = getConsoleDigit();

            if (userAnswer == 1){
                RunGame(gameName);
            }
            else if (userAnswer != 0 && userAnswer != 1 )
                System.out.println("Ответ не распознан (0 - нет, 1 - да)."); // Можно было сделать anykey - да, но в задании именно такая формулировка.
        } while (userAnswer != 0);
    }

    public static byte getConsoleDigit(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextByte();
    }

    public static String getConsoleString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String[] getFruits(){
        return new String[]{"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
                "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    }

    public static void print1DArray(String[] array){
        for(int i = 0; i < array.length; i++){
            if (i != array.length - 1){
                System.out.printf("%s, ", array[i]);
            }

            if ((i + 1) % 8 == 0) System.out.println();
        }
    }

    public static void RunGame(String gameName){
        int[] gameSettings = getGameSettings(gameName);

        if (gameSettings != null){
            int gameIndex = gameSettings[0];
            byte bound = (byte)gameSettings[1];
            byte secretNumber = generateDigit(bound);

            if (gameIndex == 1){
                System.out.println("Загадана цифра от 0 да 9. Попробуйте угадать ее с трех попыток.");
                doAssumptions(secretNumber);
            }
            else {
                System.out.println("Фрукт загадан. Попробуйте отгадать его.");
                System.out.println("Подсказка: все возможные фрукты:");
                print1DArray(getFruits());
                doAssumptions(getFruits()[secretNumber]);
            }
        }
    }

    public static void doAssumptions(byte secretNumber){
        byte attemptsNum = 0;
        boolean win = false;

        while (attemptsNum < 3 && win == false){
            System.out.printf("Попытка %d. ", attemptsNum + 1);
            win = checkUserAssumption(secretNumber);
            attemptsNum++;
        }

        System.out.println(win ? "WIN!" : "FAIL Т_Т");
        System.out.printf("Была загадана цифра %d.\n", secretNumber);
    }

    public static void doAssumptions(String secretFruit){
        boolean win = false;

        while (win == false){
            win = checkUserAssumption(secretFruit);
        }
        System.out.println("WIN!");
    }

    public static byte generateDigit(byte bound) {
        Random rnd = new Random();
        return (byte)rnd.nextInt(bound);
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

    public static boolean checkUserAssumption(String secretFruit){
        System.out.println("Ваше предположение:");
        String userAssumption = getConsoleString();

        if (secretFruit.equals(userAssumption))
            return true;
        else {
            System.out.printf("Fail. Подсказка: %s \n", getHint(secretFruit, userAssumption));
            return false;
        }
    }

    public static int getMinSize(String secretFruit, String userAssumption){
        int secretFruitLen = secretFruit.length();
        int userAssumptionLen = userAssumption.length();

        return (secretFruitLen <= userAssumptionLen) ? secretFruitLen : userAssumptionLen;
    }

    public static String getHint(String secretFruit, String userAssumption){
        ArrayList<Character> hint = new ArrayList<>();
        char[] secretFruitChars = secretFruit.toCharArray();
        char[] userAssumptionChars = userAssumption.toCharArray();
        int minLen = getMinSize(secretFruit, userAssumption);

        for (int i = 0; i < 14; i++){
            if (i < minLen){
                if (secretFruitChars[i] == userAssumptionChars[i])
                    hint.add(secretFruitChars[i]);
                else hint.add('#');
            }
            else hint.add('#');
        }

        return  hint.toString();
    }
}
