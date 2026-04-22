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

        // Person dummy
        Person yushan = new Person("Yushan");
        
        // Mansion creation
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

        // Cottage Creation:
        Building cottage = new Building("Cottage", "This is the cottage where M. De Lacey, Felix, Agatha, and Safie reside.");
        Room monstersDwelling = new Room("Monster's Dwelling", "This is where the player will be able to rest during their time at the cottage.", true);
        Room mainRoom = new Room("Main Living Space", "The main living room", false);

        // Ship creation:
        Building ship = new Building("Ship", "This is the ship where you may find some enemies, but Frankenstein lies here.");
        Room deck = new Room("Deck", "A bunch of sailors are here...", true);
        Room cabin = new Room("Cabin", "This area consists of a lot of rooms, however you want to venture into Frankenstein's room.", false);
        Room room = new Room("Ship Room 001", "There is a bed with a frail figure laying in it, who could it be?", false);

        // Mansion ArrayLists:
        ArrayList<Object> labCoordinates = new ArrayList<>();
        ArrayList<Object> victorsRoomCoordinates = new ArrayList<>();
        ArrayList<Object> kitchenCoordinates = new ArrayList<>();
        ArrayList<Object> livingRoomCoordinates = new ArrayList<>();

        // Woods ArrayLists
        ArrayList<Object> woods1Coordinates = new ArrayList<>();
        ArrayList<Object> woods2Coordinates = new ArrayList<>();
        ArrayList<Object> woods3Coordinates = new ArrayList<>();

        // Cottage ArrayLists
        ArrayList<Object> monstersDwellingCoordinates = new ArrayList<>();
        ArrayList<Object> mainRoomCoordinates = new ArrayList<>();

        // Ship ArrayLists
        ArrayList<Object> deckCoordinates = new ArrayList<>();
        ArrayList<Object> cabinCoordinates = new ArrayList<>();
        ArrayList<Object> roomCoordinates = new ArrayList<>();

        // Rooms and their coordinates arraylist list
        int i = 0;
        int j = 0;

        labCoordinates.add(lab);
        labCoordinates.add(i);
        labCoordinates.add(j);

        woods1Coordinates.add(woods1);
        woods1Coordinates.add(i);
        woods1Coordinates.add(j);

        monstersDwellingCoordinates.add(monstersDwelling);
        monstersDwellingCoordinates.add(i);
        monstersDwellingCoordinates.add(j);

        deckCoordinates.add(deck);
        deckCoordinates.add(i);
        deckCoordinates.add(j);

        i = 0;
        j = 1;

        victorsRoomCoordinates.add(victorsRoom);
        victorsRoomCoordinates.add(i);
        victorsRoomCoordinates.add(j);

        woods2Coordinates.add(woods2);
        woods2Coordinates.add(i);
        woods2Coordinates.add(j);

        mainRoomCoordinates.add(mainRoom);
        mainRoomCoordinates.add(i);
        mainRoomCoordinates.add(j);

        i = 0;
        j = 2;

        woods3Coordinates.add(woods3);
        woods3Coordinates.add(i);
        woods3Coordinates.add(j);

        cabinCoordinates.add(cabin);
        cabinCoordinates.add(i);
        cabinCoordinates.add(j);

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

        roomCoordinates.add(room);
        roomCoordinates.add(i);
        roomCoordinates.add(j);

        mansion.rooms.add(labCoordinates);
        mansion.rooms.add(victorsRoomCoordinates);
        mansion.rooms.add(kitchenCoordinates);
        mansion.rooms.add(livingRoomCoordinates);

        woods.rooms.add(woods1Coordinates);
        woods.rooms.add(woods2Coordinates);
        woods.rooms.add(woods3Coordinates);

        cottage.rooms.add(monstersDwellingCoordinates);
        cottage.rooms.add(mainRoomCoordinates);

        ship.rooms.add(deckCoordinates);
        ship.rooms.add(cabinCoordinates);
        ship.rooms.add(roomCoordinates);

        // Testing:
        System.out.println("inside: " + mansion);
        System.out.println(mansion.rooms);
        System.out.println("inside woods");
        System.out.println(woods.rooms);
        System.out.println("inside cottage");
        System.out.println(cottage.rooms);
        System.out.println("inside ship");
        System.out.println(ship.rooms);

        System.out.println(yushan.getCurrentBuilding());
        System.out.println(yushan.getCurrentRoom());

        yushan.enter(mansion);
        System.out.println(yushan.getCurrentBuilding());
        System.out.println(yushan.getCurrentRoom());

        yushan.enter(lab);
        yushan.enter(kitchen);
        yushan.enter(livingRoom);
        yushan.exit(mansion);

        System.out.println(yushan.getCurrentRoom());

        yushan.enter(woods);
        System.out.println(yushan.getCurrentBuilding());
        
        // yushan.enter(mansion);
        // System.out.println("Yushan is currently in: " + yushan.getCurrentBuilding());
        // System.out.println("Yushan is currently in room: " + yushan.getCurrentRoom());

        // System.out.println(mansion.rooms);
        // System.out.println(yushan.getCurrentRoom());

        // yushan.enter(victorsRoom);
        // System.out.println(yushan.getCurrentRoom());

        // yushan.enter(lab);
        // System.out.println(yushan.getCurrentRoom());

        // yushan.enter(kitchen);

        // System.out.println(yushan.getCurrentRoom());

        // yushan.exit(mansion);

        // yushan.enter(livingRoom);

        // System.out.println(yushan.getCurrentBuilding());

        // yushan.exit(mansion);

        // yushan.enter(woods);

        // System.out.println(yushan.getCurrentBuilding());
        // System.out.println(woods.rooms);

        // // Need to implement a system where player can only exit a building while in a certain room.

    }}

    
