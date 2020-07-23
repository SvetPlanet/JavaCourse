import com.sun.glass.ui.Size;

import java.util.Scanner;
import java.util.Random;

public class Lesson4 {
    public static char[][] map;
    public static int [] winX;
    public static int [] winY;
    //public static final int SIZE = 3;
    public static final int SIZE = 5;
    //public static final int DOTS_TO_WIN = 3;
    public static final int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args){
        System.out.printf("Игра \"Крестики-нолики\"");
        initMap();
        printMap();

        while (true){
            userTurn();
            printMap();
            if (checkWin(DOT_X))
            {
                System.out.println("\nПользователь выиграл!");
                printWinWay();
                break;
            }

            if (isMapFull()){
                System.out.println("\nНичья.");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O))
            {
                System.out.println("\nAI выиграл!");
                printWinWay();
                break;
            }

            if (isMapFull()){
                System.out.println("Ничья.");
                break;
            }
        }

        System.out.println("Игра закончена.");
    }

    public static void initMap(){
        map = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void initWinWay(){
        winX = new int[DOTS_TO_WIN];
        winY = new int[DOTS_TO_WIN];

        for (int i = 0; i < DOTS_TO_WIN; i++){
            winX[i] = 0;
            winY[i] = 0;
        }
    }

    public static void printMap(){
        System.out.println();
        for (int i = 0; i < SIZE; i++){
            System.out.printf("%d ", i + 1);
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
    }

    public static void printWinWay(){
        System.out.println();
        for (int i = 0; i < SIZE; i++){
            System.out.printf("%d ", i + 1);
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (!isWinCoordinate(i, j))
                    System.out.printf("%c ", map[i][j]);
                else
                    System.out.print(ANSI_GREEN + map[i][j] + " " + ANSI_RESET);
            }
            System.out.println();
        }
    }

    public static boolean isWinCoordinate(int i, int j){
        for (int k = 0; k < DOTS_TO_WIN; k++){
            if (winX[k] == i && winY[k] == j)
                return true;
        }
        return false;
    }

    public static void userTurn(){
        System.out.println("\nВаш ход. Введите координату клетки.");
        int x, y;
        boolean validation = false;

        do{
            System.out.print("X = ");
            x = scanner.nextInt() - 1;
            System.out.print("Y = ");
            y = scanner.nextInt() - 1;

            validation = isCellValid(x, y);
            if (!validation)
                System.out.println("Некорректный ввод. Повторите.");

        } while(!validation);

        map[x][y] = DOT_X;
    }

    public static boolean isCellValid(int x, int y){
        if (x >= SIZE || y >= SIZE || x < 0 || y < 0)
            return false;

        if (map[x][y] != DOT_EMPTY)
            return false;

        return true;
    }

    public static void aiTurn(){
        System.out.println("\nХод AI");

        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        }while (!isCellValid(x, y));
        map[x][y] = DOT_O;

        System.out.printf("AI сходил в точку x = %d, y = %d", x + 1, y + 1);
        System.out.println();
    }

    public static boolean isMapFull(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (map[i][j] == DOT_EMPTY)
                    return false;
            }
        }

        return true;
    }

    public static boolean checkWin(char symbol){
        if (checkRow(symbol) || checkColumn(symbol) || checkSubDiagonal(symbol) || checkMainDiagonal(symbol))
            return true;
        return false;
    }

    public static boolean checkRow(char symbol){
        boolean winRow;

        for (int i = 0; i < SIZE; i++){
            for (int k = 0; k <= SIZE - DOTS_TO_WIN; k++){
                winRow = true;
                initWinWay();

                for (int j = k; j < k + DOTS_TO_WIN; j++){
                    if (map[i][j] != symbol)
                        winRow = false;
                    else setWinWayPoint(j - k, i, j);
                }

                if (winRow)
                    return true;
            }
        }

        return false;
    }

    public static boolean checkColumn(char symbol) {
        boolean winColumn;

        for (int i = 0; i < SIZE; i++){
            for (int k = 0; k <= SIZE - DOTS_TO_WIN; k++){
                winColumn = true;
                initWinWay();

                for (int j = k; j < k + DOTS_TO_WIN; j++){
                    if (map[j][i] != symbol)
                        winColumn = false;
                    else setWinWayPoint(j - k, i, j);
                }

                if (winColumn)
                    return true;
            }
        }

        return false;
    }

    public static boolean checkSubDiagonal(char symbol){
        boolean winSubDiagonal;
        int tempRow, tempCol, step;

        for (int col = 0; col <= SIZE - DOTS_TO_WIN; col++) {
            for (int row = DOTS_TO_WIN - 1; row < SIZE; row++){
                tempRow = row;
                tempCol = col;
                step = DOTS_TO_WIN;
                winSubDiagonal = true;
                initWinWay();

                while (step > 0 ){
                    if (map[tempRow][tempCol] != symbol)
                        winSubDiagonal = false;
                    setWinWayPoint(DOTS_TO_WIN - step, tempRow, tempCol);
                    step--;
                    tempRow--;
                    tempCol++;
                }

                if (winSubDiagonal)
                    return true;
            }
        }

        return false;
    }

    public static boolean checkMainDiagonal(char symbol){
        boolean winMainDiagonal;
        int tempRow, tempCol, step;

        for (int col = 0; col <= SIZE - DOTS_TO_WIN; col++) {
            for (int row = 0; row <= SIZE - DOTS_TO_WIN; row++){
                tempRow = row;
                tempCol = col;
                step = DOTS_TO_WIN;
                winMainDiagonal = true;
                initWinWay();

                while (step > 0 ){
                    if (map[tempRow][tempCol] != symbol)
                        winMainDiagonal = false;
                    setWinWayPoint((DOTS_TO_WIN - step), tempRow, tempCol);
                    step--;
                    tempRow++;
                    tempCol++;
                }

                if (winMainDiagonal)
                    return true;
            }
        }

        return false;
    }

    public static void setWinWayPoint(int k, int i, int j){
        winX[k] = i;
        winY[k] = j;
    }
}
