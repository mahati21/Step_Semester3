package categoryB.Class_problemsW7;

abstract class Toy {
    private static int counter = 1000;
    private final String toyId;
    public Toy() {
        counter++;
        toyId = "TOY-" + counter;
    }
    public abstract String makeSound();
    public String getToyId() {
        return toyId;
    }
}
class ToyCar extends Toy {
    private String name;
    public ToyCar(String name) {
        this.name = name;
    }
    @Override
    public String makeSound() {
        return name + ": Vroom vroom!";
    }
}
class ToyRobot extends Toy {
    private String name;
    public ToyRobot(String name) {
        this.name = name;
    }
    @Override
    public String makeSound() {
        return name + ": Beep boop!";
    }
}