import java.util.ArrayList;

public class Room {
    
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

    /**
     * Returns the name of the room
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the room
     * @return descripton
     */
    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.name;
    }

    /**
     * Adds person to room
     * @param person
     */
    public void addPerson(Person person) {
        this.people.add(person);
        person.currentRoom = this;
    }

    /**
     * Removes person from room
     * @param person
     */
    public void removePerson(Person person) {
        if (this.people.contains(person)) {
            this.people.remove(person);
        }
    }

    /**
     * Adds item to a room
     * @param item
     */
    public void addItem(GrabbableObject item) {
        this.items.add(item);
    }

    /**
     * Removes item from room
     */
    public void removeItem(GrabbableObject item) {
        if (this.items.contains(item)) {
            this.items.remove(item);
        }
    }

    /**
     * Locks a room.
     */
    public void lockRoom(){
        this.isLocked = true;
    }

    /**
     * Unlocks room
     */
    public void unlock(){
        this.isLocked = false;
    }

    /**
     * returns the key of the room.
     * @return key
     */
    public GrabbableObject getKey(){
        return this.key;
    }

    /**
     * Assigns key to a room
     * @param key
     */
    public void assignKey(GrabbableObject key){
        this.key = key;
    }

    public static void main(String[] args) {




    }

}
