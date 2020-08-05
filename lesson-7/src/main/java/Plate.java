public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        if (food >= n){
            food -= n;
            return true;
        }

        return false;
    }

    public void info() {
        System.out.println("plate has food: " + food);
    }

    public void addFood(int newFood) {
        food += newFood;
    }

    public int getFood() {
        return food;
    }

    public boolean IsEmpty(){
        if (food == 0){
            return true;
        }

        return false;
    }
}
