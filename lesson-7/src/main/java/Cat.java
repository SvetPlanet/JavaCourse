public class Cat {
    private String name;
    private int appetite;
    private boolean fullness;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.fullness = false;
    }

    public void eat(Plate plate) {
        if (!fullness){
            fullness = plate.decreaseFood(appetite);
        }
    }

    public boolean isFullness() {
        return fullness;
    }

    public int getAppetite() {
        return appetite;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
       return name + " has appetite: " + appetite + " fullness: " + fullness;
    }
}
