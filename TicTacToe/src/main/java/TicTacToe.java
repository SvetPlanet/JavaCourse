import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        start();
    }

    static void start() {
        final char playerSign = 'X';
        final char computerSign = 'Y';

        char[][] field = initField();
        String winnerName;

        drawField(field);

        do {
             if (isMapFull(field)){
                 winnerName = "Dead heat.";
                 break;
             }

            doPlayerMove(field, playerSign);
            drawField(field);

            if (checkWin(field, playerSign)) {
                winnerName = "Player";
                break;
            }

            if (isMapFull(field)){
                winnerName = "Dead heat.";
                break;
            }

            doAIMove(field, computerSign);
            drawField(field);

            if (checkWin(field, computerSign)) {
                winnerName = "Computer";
                break;
            }
        } while (true);

        if (winnerName != "Dead heat."){
            System.out.println("Sir, congratulations!");
            System.out.println("You are winner Mr. " + winnerName);
        }
        else {
            System.out.println(winnerName);
        }
    }

     static boolean isMapFull(char[][] field){
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field.length; j++){
                if (field[i][j] == '-')
                    return false;
            }
        }

        return true;
    }

    static void doAIMove(char[][] field, char sign) {
        Random random = new Random();
        System.out.println("Computer's move...");

        int xVal, yVal;

        do
        {
            xVal = random.nextInt(3);
            yVal = random.nextInt(3);
        } while (field[xVal][yVal] != '-');

        System.out.println(String.format("Computer's X-value: %s", xVal));
        System.out.println(String.format("Computer's Y-value: %s", yVal));

        field[xVal][yVal] = sign;
    }

    static void doPlayerMove(char[][] field, char sign) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sir, you move...");

        int[] userInput = userInput(field.length, scanner);
        int xVal = userInput[0], yVal = userInput[1];

        while (field[xVal][yVal] != '-') {
            System.out.println(String.format("Field[%s][%s] is already occupied", xVal + 1, yVal + 1));
            userInput = userInput(field.length, scanner);
            xVal = userInput[0];
            yVal = userInput[1];
        }

        field[xVal][yVal] = sign;
    }

    static int[] userInput(int bound, Scanner scanner){
        int xVal, yVal;
        do{
            System.out.println("Please enter X-value [1-3]");
            xVal = scanner.nextInt() - 1;
            System.out.println("Please enter Y-value [1-3]");
            yVal = scanner.nextInt() - 1;
        } while(xVal >= bound || yVal >= bound || xVal < 0 || yVal < 0);

        return new int[] {xVal, yVal};
    }

    static boolean checkWin(char[][] field, char sign) {
        for (int i = 0; i < field.length; i++) {
            if (field[i][0] == sign && field[i][1] == sign && field[i][2] == sign) {
                return true;
            }
        }

        for (int i = 0; i < field.length; i++) {
            if (field[0][i] == sign && field[1][i] == sign && field[2][i] == sign) {
                return true;
            }
        }

        boolean mainDiagonalWin = true;
        boolean subDiagonalWin = true;

        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field.length; j++){
                if (i == j && field[i][j] != sign){
                    mainDiagonalWin = false;
                }

                if (i + j == field.length - 1 && field[i][j] != sign){
                    subDiagonalWin = false;
                }
            }
        }

        if (mainDiagonalWin || subDiagonalWin) {
            return mainDiagonalWin || subDiagonalWin;
        }

        return false;
    }

    static void drawField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    static char[][] initField() {
        return new char[][]{
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'},
        };
    }
}
