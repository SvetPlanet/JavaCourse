public class Inheritance {
    public static void main(String[] args){
        Animal[] animals = {
                new Cat("Felix", 200, 0, 2),
                new Cat("Mary", 400, 0, 3),
                new Dog("Rex", 500, 10, 0.5),
                new Dog("Hunter", 1500, 50, 1.5)
        };

        for (Animal animal:animals) {
            System.out.println(animal.run(300));
            System.out.println(animal.swim(5));
            System.out.println(animal.jump(2));
            System.out.println();
        }
    }
}
