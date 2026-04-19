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
        Room lab = new Room("Lab", "This is victor's labortory.");
        Room victorsRoom = new Room("Victor's Bedroom", "This is Victor's Bedroom");
        Room kitchen = new Room("Kitchen", "This is Victor's Kitchen, he has assorted fruits laying around.");
        Person yushan = new Person("Yushan");

        // In creation of nested ArrayList
        ArrayList<Object> labCoordinates = new ArrayList<>();
        ArrayList<Object> victorsRoomCoordinates = new ArrayList<>();
        ArrayList<Object> kitchenCoordinates = new ArrayList<>();


        // Room and their coordinates arraylist list

        int i = 0;
        int j = 0;

        labCoordinates.add(lab);
        labCoordinates.add(i);
        labCoordinates.add(j);

        i = 0;
        j = 1;

        victorsRoomCoordinates.add(victorsRoom);
        victorsRoomCoordinates.add(i);
        victorsRoomCoordinates.add(j);

        i = 1;
        j = 1;

        kitchenCoordinates.add(kitchen);
        kitchenCoordinates.add(i);
        kitchenCoordinates.add(j);


        mansion.rooms.add(labCoordinates);
        mansion.rooms.add(victorsRoomCoordinates);
        mansion.rooms.add(kitchenCoordinates);

        System.out.println("inside: " + mansion);
        System.out.println(mansion.rooms);

        yushan.enter(mansion);
        System.out.println("Yushan is currently in: " + yushan.getCurrentBuilding());

        System.out.println("Yushan is currently in room: " + yushan.getCurrentRoom());

        // yushan.enter(victorsRoom, mansion);

        System.out.println(mansion.rooms);
        System.out.println(yushan.getCurrentRoom());

        yushan.enter(victorsRoom, mansion);
        System.out.println(yushan.getCurrentRoom());

        yushan.enter(lab, mansion);
        System.out.println(yushan.getCurrentRoom());

        yushan.enter(kitchen, mansion);

    }}

    
