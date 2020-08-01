public class Dog extends Animal {
    public Dog(String name, double runLimit, double swimLimit, double jumpLimit){
        super(name, runLimit, swimLimit, jumpLimit);
    }

    @Override
    public boolean run(double runDistance){
        System.out.printf("Dog %s is running %.2f:\n", name, runDistance);
        return super.run(runDistance);
    }

    @Override
    public boolean swim(double swimDistance){
        System.out.printf("Dog %s is swimming %.2f:\n", name, swimDistance);
        return super.swim(swimDistance);
    }

    @Override
    public boolean jump(double jumpDistance){
        System.out.printf("Dog %s is jumping %.2f:\n", name, jumpDistance);
        return super.jump(jumpDistance);
    }
}
