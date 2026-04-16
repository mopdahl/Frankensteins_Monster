//import java.util.ArrayList
import java.util.HashMap;


public class Room implements RoomRequirements {
    
    private String name;
    private String description;
    //ArrayList<Person> people;
    //ArrayList<GrabbableObject> items;
    HashMap<Direction, Boolean> exits; // Possibly change the boolean value to a Door class later


    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        // this.people = new ArrayList<>();
        // this.items = new ArrayList<>();
        this.exits = new HashMap<>();
    }

    public Room() {
        this("An Empty Room", "This room is empty.");
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public static void main(String[] args) {
        
    }

}
