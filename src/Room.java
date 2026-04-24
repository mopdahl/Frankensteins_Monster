//import java.util.ArrayList
import java.util.ArrayList;
import java.util.HashMap;


public class Room implements RoomRequirements {
    
    private String name;
    private String description;
    public boolean hasExit;
    boolean isLocked;
    private GrabbableObject key;
    ArrayList<Person> people;
    ArrayList<GrabbableObject> items;
    HashMap<Direction, Boolean> exits; // Possibly change the boolean value to a Door class later


    public Room(String name, String description, boolean hasExit) {
        this.name = name;
        this.description = description;
        this.hasExit = hasExit;
        this.isLocked = false;
        this.key = null;
        this.people = new ArrayList<>();
        this.items = new ArrayList<>();
        this.exits = new HashMap<>();
    }

    public Room() {
        this("An Empty Room", "This room is empty.", false);
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
