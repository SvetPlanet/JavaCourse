import java.util.ArrayList;
import java.util.Random;

public class MainClass {
    public static void main(String[] args){
        Plate plate = new Plate(150);
        plate.info();
        System.out.println();
        Cat[] cats = initCats();
        feedCats(cats, plate);
    }

    public static Cat[] initCats(){
        ArrayList<Cat> cats = new ArrayList<Cat>();
        int n = 12;
        Random random = new Random();

        for (int i = 0; i < n; i++){
            cats.add(new Cat("Cat" + (i + 1), 1 + random.nextInt(41)));
        }

        return cats.toArray(new Cat[0]);
    }

    public static void feedCats(Cat[] cats, Plate plate){
        int requestFood = 0;

        for (Cat cat:cats) {
            cat.eat(plate);

            if (!cat.isFullness()){
                requestFood += cat.getAppetite();
            }

            System.out.println("Eat");
            System.out.println(cat);
            plate.info();
            System.out.println();
        }

        requestFood -= plate.getFood();

        if (requestFood > 0){
            System.out.println("Add more food: " + requestFood);
            plate.addFood(requestFood);
            plate.info();
            System.out.println();

            for (Cat cat : cats){
                if (!cat.isFullness()){
                    cat.eat(plate);
                    System.out.println("Eat");
                    System.out.println(cat);
                    plate.info();
                    System.out.println();
                }
            }
        }
    }
}
