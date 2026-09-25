package categoryB.Class_problemsW7;

abstract class KitchenTool {
    private int speedLevel = 1;
    public abstract String prepare();
    public int getSpeedLevel() {
        return speedLevel;
    }
    public void setSpeedLevel(int speedLevel) {
        if (speedLevel >= 1 && speedLevel <= 5) {
            this.speedLevel = speedLevel;
        }
    }
}
interface Washable {
    String clean();
}
class Blender extends KitchenTool implements Washable {
    public Blender() {
        super();
    }
    @Override
    public String prepare() {
        return "Blending at speed " + getSpeedLevel();
    }
    @Override
    public String clean() {
        return "Blender rinsed and dried";
    }
}
