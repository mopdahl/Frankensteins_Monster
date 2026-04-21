import java.util.ArrayList;

public class Building implements BuildingRequirements {
    
    //Attributes
    String name;
    String description;
    ArrayList<ArrayList<Object>> rooms;

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

    public static void main(String[] args) {
        
        // Current test buildings and rooms: 

        Building mansion = new Building("Mansion", "Victor's Mansion");
        Room lab = new Room("Laboratory", "This is victor's laboratory.", false);
        Room victorsRoom = new Room("Victor's Bedroom", "This is Victor's Bedroom", false);
        Room kitchen = new Room("Kitchen", "This is Victor's Kitchen, he has assorted food laying around.", false);
        Room livingRoom = new Room("Living Room", "This is Victor's living room, he has assorted items in this room, perhaps CLUES.", true);

        // Woods creation:

        Building woods = new Building("Woods", "This is the woods, there is a lot to explore here.");
        Room woods1 = new Room("Beginning Woods", "This is a section of the woods just outside of the Frankenstein Mansion.", true);
        Room woods2 = new Room("Middle Woods", "You have travelled from the beginning woods into a deeper part of the woods. You see a cottage in the far distance.", true);
        Room woods3 = new Room("Deep woods", "You are deep into the woods, far away you can see the ocean and ship.", true);

        Person yushan = new Person("Yushan");

        // Mansion ArrayLists:
        ArrayList<Object> labCoordinates = new ArrayList<>();
        ArrayList<Object> victorsRoomCoordinates = new ArrayList<>();
        ArrayList<Object> kitchenCoordinates = new ArrayList<>();
        ArrayList<Object> livingRoomCoordinates = new ArrayList<>();

        // Woods ArrayLists
        ArrayList<Object> woods1Coordinates = new ArrayList<>();
        ArrayList<Object> woods2Coordinates = new ArrayList<>();
        ArrayList<Object> woods3Coordinates = new ArrayList<>();


        // Mansion rooms and their coordinates arraylist list

        int i = 0;
        int j = 0;

        labCoordinates.add(lab);
        labCoordinates.add(i);
        labCoordinates.add(j);

        woods1Coordinates.add(woods1);
        woods1Coordinates.add(i);
        woods1Coordinates.add(j);

        i = 0;
        j = 1;

        victorsRoomCoordinates.add(victorsRoom);
        victorsRoomCoordinates.add(i);
        victorsRoomCoordinates.add(j);

        woods2Coordinates.add(woods2);
        woods2Coordinates.add(i);
        woods2Coordinates.add(j);

        i = 0;
        j = 2;

        woods3Coordinates.add(woods3);
        woods3Coordinates.add(i);
        woods3Coordinates.add(j);

        i = 1;
        j = 0;

        kitchenCoordinates.add(kitchen);
        kitchenCoordinates.add(i);
        kitchenCoordinates.add(j);

        i = 1;
        j = 1;

        livingRoomCoordinates.add(livingRoom);
        livingRoomCoordinates.add(i);
        livingRoomCoordinates.add(j);


        mansion.rooms.add(labCoordinates);
        mansion.rooms.add(victorsRoomCoordinates);
        mansion.rooms.add(kitchenCoordinates);
        mansion.rooms.add(livingRoomCoordinates);

        woods.rooms.add(woods1Coordinates);
        woods.rooms.add(woods2Coordinates);
        woods.rooms.add(woods3Coordinates);

        // Testing
        System.out.println("inside: " + mansion);
        System.out.println(mansion.rooms);
        yushan.enter(mansion);
        System.out.println("Yushan is currently in: " + yushan.getCurrentBuilding());
        System.out.println("Yushan is currently in room: " + yushan.getCurrentRoom());

        System.out.println(mansion.rooms);
        System.out.println(yushan.getCurrentRoom());

        yushan.enter(victorsRoom, mansion);
        System.out.println(yushan.getCurrentRoom());

        yushan.enter(lab, mansion);
        System.out.println(yushan.getCurrentRoom());

        yushan.enter(kitchen, mansion);

        System.out.println(yushan.getCurrentRoom());

        yushan.exit(mansion);

        yushan.enter(livingRoom, mansion);

        System.out.println(yushan.getCurrentBuilding());

        yushan.exit(mansion);

        yushan.enter(woods);

        System.out.println(yushan.getCurrentBuilding());
        System.out.println(woods.rooms);

    

        // Need to implement a system where player can only exit a building while in a certain room.

    }}

    
