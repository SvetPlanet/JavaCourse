import java.util.*;

public class Lesson2 {
    public static void main(String[] args) {
        System.out.println("Задание 1.");
        int arraySize = 0;

        do {
            System.out.print("Введите размер массива (размер массива должен быть больше 0: ");
            Scanner input = new Scanner(System.in);
            arraySize = input.nextInt();
        } while (arraySize <= 0);

        System.out.println("Старый массив.");
        byte[] array = doCreateBinaryArray(arraySize);
        doPrint1dArray(array);
        System.out.println("Новый массив.");
        doInvert(array);
        doPrint1dArray(array);

        System.out.println("\nЗадание 2.");
        doPrint1dArray(doCreateArrayWithStep());

        System.out.println("\nЗадание 3.");
        System.out.println("Старый массив.");
        byte[] array1 = doInitUserArray();
        doPrint1dArray(array1);
        System.out.println("Новый массив.");
        doMultiplication(array1);
        doPrint1dArray(array1);

        System.out.println("\nЗадание 4.");
        byte[][] array2d = doCreate2dArray(5);
        doPrint2dArray(array2d);

        System.out.println("\nЗадание 5.");
        doFindMinMaxIn1dArray(arraySize);

        System.out.println("\nЗадание 6.");
        byte[] arrayForCheckingSum = new byte[]{2, 2, 2, 1, 2, 2, 10, 1};
        doPrint1dArray(arrayForCheckingSum);
        System.out.println("Половины массива равны: " + doCheckHalfSum(arrayForCheckingSum));
        byte[] arrayForCheckingSum1 = doCreate1dArray(8, 10);
        doPrint1dArray(arrayForCheckingSum1);
        System.out.println("Половины массива равны: " + doCheckHalfSum(arrayForCheckingSum1));

        System.out.println("\nЗадание 7.");
        byte[] arrayUnbiased = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};//doCreate1dArray(15, 10);
        doPrint1dArray(arrayUnbiased);
        System.out.println("Смещение +4");
        doOffsetArray(arrayUnbiased, 4);
        doPrint1dArray(arrayUnbiased);
        System.out.println("Смещение -4");
        doOffsetArray(arrayUnbiased, -4);
        doPrint1dArray(arrayUnbiased);
    }

    public static byte[] doCreateBinaryArray(int size) {
        Random rnd = new Random();
        byte[] array = new byte[size];
        for (int i = 0; i < size; i++){
            array[i] = (byte)rnd.nextInt(2);
        }

        return array;
    }

    public static void doPrint1dArray(byte[] array) {
        for (int item : array)
            System.out.print(item + " ");
        System.out.println();
    }

    public static void doInvert(byte[] array) {
        for (int i = 0; i < array.length; i++){
            if (array[i] == 0)
                array[i] = 1;
            else  array[i] = 0;
        }
    }

    public static byte[] doCreateArrayWithStep() {
        byte[] array = new byte[8];
        for (byte i = 0; i < array.length; i++){
            array[i] = (byte)(i * 3);
        }

        return array;
    }

    public static byte[] doInitUserArray() {
        return new byte[] {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
    }

    public static void doMultiplication(byte[] array) {
        for (int i = 0; i < array.length; i++){
            if (array[i] < 6)
                array[i] *= 2;
        }
    }

    public static byte[][] doCreate2dArray(int size) {
        byte[][] array = new byte[size][size];
        Random rnd = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j){
                    array[i][j] = 1;
                }
                else {
                    array[i][j] = (byte)rnd.nextInt(10);
                }
            }
        }

        return array;
    }

    public static void doPrint2dArray(byte[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static byte[] doCreate1dArray(int size, int num) {
        byte[] array = new byte[size];
        Random rnd = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = (byte) rnd.nextInt(num + 1);
        }

        return array;
    }

    public static void doFindMinMaxIn1dArray(int size) {
        byte[] array = doCreate1dArray(size, 100);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
            if (array[i] < min) min = array[i];
            if (array[i] > max) max = array[i];
        }

        System.out.println();
        System.out.println("Min = " + min);
        System.out.println("Max = " + max);
    }

    public static boolean doCheckHalfSum(byte[] array){
        int n = array.length;

        for (int border = 1; border < n; border++){
            int sumLeft = 0;
            int sumRight = 0;

            for (int i = 0; i < border; i++){
                sumLeft += array[i];
            }

            for (int i = border; i < n; i++){
                sumRight += array[i];
            }

            if (sumLeft == sumRight)
                return true;
        }

        return false;
    }

    public static void swap(byte[] array, int i, int j){
        byte temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void doOffsetArray(byte[] array, int bias){
        int n = array.length;
        int remainder = n % Math.abs(bias);

        if (bias > 0){
            int stop = n;

            while (n > bias && n > 1){
                if (n - 2 * bias < remainder)
                    stop -= remainder;
                else stop -= bias;

                for (int i = n - 1; i >= stop; i--){
                    swap(array, i, i - bias);
                }

                if (remainder > bias) remainder = bias;

                if (n - 2 * bias >= remainder)
                    n-=bias;
                else {
                    n -= remainder;
                    bias -= remainder;
                }
            }
        }
        else if (bias < 0){
            bias = - bias;
            int stop = 0;
            int start = 0;
            while (n > bias && start < n - 1){
                if (start + 2 * bias > n - remainder)
                    stop += remainder;
                else stop += bias;

                for (int i = start; i < stop; i++){
                    swap(array, i, i + bias);
                }

                if (remainder > bias) remainder = bias;

                if (start + 2 * bias <= n - remainder)
                    start += bias;
                else {
                    start += remainder;
                    bias -= remainder;
                }
            }
        }
    }
}


