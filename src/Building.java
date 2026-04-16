import java.util.ArrayList;

public class Building implements BuildingRequirements {
    
    //Attributes
    String name;
    String description;
    ArrayList<Room> rooms;

    //Constructor

    public Building(String name, String description) {
        this.name = name;
        this.description = description;
        this.rooms = new ArrayList<>();
    }

    public Building(String description) {
        this("N/A", description);
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return this.name;
    }

    }
