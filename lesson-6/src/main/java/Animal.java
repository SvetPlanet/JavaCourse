public class Animal {
    protected String name;
    protected double runLimit;
    protected double swimLimit;
    protected double jumpLimit;

    public Animal(String name, double runLimit, double swimLimit, double jumpLimit){
        this.name = name;
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
        this.jumpLimit = jumpLimit;
    }

    public boolean run(double runDistance){
        if (runDistance <= runLimit){
            return true;
        }

        return false;
    }

    public boolean swim(double swimDistance){
        if (swimDistance <= swimLimit){
            return true;
        }

        return false;
    }

    public boolean jump(double jumpDistance){
        if (jumpDistance <= jumpLimit){
            return true;
        }

        return false;
    }
}
