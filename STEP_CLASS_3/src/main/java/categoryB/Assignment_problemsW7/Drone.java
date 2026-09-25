package categoryB.Assignment_problemsW7;

abstract class Drone {
    private String id;
    public Drone(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public abstract String fly();
}
interface Trackable {
    String getLocation();
}
class DeliveryDrone extends Drone implements Trackable {
    public DeliveryDrone(String id) {
        super(id);
    }
    @Override
    public String fly() {
        return "Delivery drone flying";
    }
    @Override
    public String getLocation() {
        return getId() + " at Sector 4";
    }
}
class ScoutDrone extends Drone {
    public ScoutDrone(String id) {
        super(id);
    }
    @Override
    public String fly() {
        return "Scout drone flying";
    }
}
class GroundRobot implements Trackable {
    private String id;
    public GroundRobot(String id) {
        this.id = id;
    }
    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}
class TrackingSystem {
    static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable trackable = (Trackable) o;
            return trackable.getLocation();
        }
        return "Tracking not available";
    }
}
