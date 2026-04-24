//import java.util.ArrayList
import java.util.ArrayList;


public class Room implements RoomRequirements {
    
    private String name;
    private String description;
    public Room exit;
    boolean isLocked;
    private GrabbableObject key;
    ArrayList<Person> people;
    ArrayList<GrabbableObject> items;
    Building building;

    public Room(String name, String description, Room exit, Building building) {
        this.name = name;
        this.description = description;
        this.exit = exit;
        this.isLocked = false;
        this.key = null;
        this.people = new ArrayList<>();
        this.items = new ArrayList<>();
        this.building = building;
    }

    public Room() {
        this("An Empty Room", "This room is empty.", null, null);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.name;
    }

    public void addPerson(Person person) {
        this.people.add(person);
        person.currentRoom = this;
    }

    public void removePerson(Person person) {
        if (this.people.contains(person)) {
            this.people.remove(person);
        }
    }

    public void addItem(GrabbableObject item) {
        this.items.add(item);
    }

    public void removeItem(GrabbableObject item) {
        if (this.items.contains(item)) {
            this.items.remove(item);
        }
    }

    public void lockRoom(){
        this.isLocked = true;
    }

    public void unlock(){
        this.isLocked = false;
    }

    public GrabbableObject getKey(){
        return this.key;
    }

    public void assignKey(GrabbableObject key){
        this.key = key;
    }

    public static void main(String[] args) {




    }

}
