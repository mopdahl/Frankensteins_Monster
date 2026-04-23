import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class GameLoop {

    private Player player; // Convert to Player class later
    private ArrayList<Building> buildings;
    private HashMap<String, Consumer<String>> commands;

    public GameLoop() {
        this.buildings = new ArrayList<>();
        this.commands = new HashMap<>();
    }

    /**
     * Method to initialize game variables. Includes commands, buildings, and the player.
     */
    private void setUp() {
        FoodObject food = new FoodObject("food", "a piece of food", 0, 0); // placeholder: needed to get Class<Food> object 

        // Initializing Commands
        commands.put("enter", destinationName -> this.enter(destinationName));
        commands.put("exit", buildingName -> this.player.exit(this.player.getCurrentBuilding())); // currently accepts any string after first word 'exit'
        commands.put("look", anyString -> this.player.observeRoom(this.player.getCurrentRoom())); // placeholder
        commands.put("get", objName -> this.player.pickUp(this.getObjectFromString(objName))); // edit Person.get to change currentRoom inventory
        commands.put("drop", objName -> this.player.putDown(this.getObjectFromString(objName))); // ditto ^
        commands.put("inventory", anyString -> System.out.println("Inventory: " + this.player.inventory));
        commands.put("eat", foodName -> this.player.consume(castAs(food.getClass(), this.getObjectFromString(foodName))));

        
        // Initializing Buildings
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

        this.buildings.add(mansion);
        this.buildings.add(woods);

        livingRoom.items.add(new FoodObject("Test Object", "this is an item", 0, 0));

        // Initializing Player

        this.player = new Player("Frankie", mansion, lab);
        //this.player.currentRoom = lab; -> currentRoom is currently private
    }

    /**
     * Moves the player from their current location to the Building or Room with the given name.
     * @param destinationName The name of the desired location.
     * @throws RuntimeException When there is no location with the given name.
     */
    private void enter(String destinationName) {
        Object destination = this.getLocationFromString(destinationName);
        if (destination instanceof Room destinationRoom) {
            this.player.enter(destinationRoom);
        } else if (destination instanceof Building destinationBuilding) {
            this.player.enter(destinationBuilding);
            // Shows what rooms is existant.
            // I might move everything to the building class later on so it's not that compacted in here.
            System.out.println("-------------------------");
            System.out.println("Here is the list of possible rooms within " + destinationBuilding + ":");
            destinationBuilding.getRooms();
            System.out.println("-------------------------"); 
        } else {
            throw new RuntimeException(destinationName + " is not an accessible location.");
        }
    }

    /**
     * Finds and returns the first instance of a Building or Room with the given name.
     * @param locationName The name of the desired location
     * @return The location with the given name, as an Object
     */
    private Object getLocationFromString(String locationName) {

        // Check each Building to see if the name matches
        for (Building building : this.buildings) {
            if (building.getName().toLowerCase().equals(locationName)) {
                return (Building) building;
            }
        }

        // Check each currently accessible room (those in the current building)
        for (ArrayList<Object> roomArray : this.player.getCurrentBuilding().rooms) {
            if (roomArray.get(0).toString().toLowerCase().equals(locationName)) {
                return (Room) roomArray.get(0);
            }
        }

        throw new RuntimeException("Are you quite sure that's a place you could go?");
    }

    private GrabbableObject getObjectFromString(String objectName) {
        for (GrabbableObject obj : this.player.inventory) {
            if (obj.name.toLowerCase().equals(objectName)) {
                return obj;
            }
        }

        for (GrabbableObject obj : this.player.currentRoom.items) {
            if (obj.name.toLowerCase().equals(objectName)) {
                return obj;
            }
        }

        throw new RuntimeException("You don't see that here.");
    }

    private static <T> T castAs(Class<T> objClass, Object obj) {
        return objClass.cast(obj);
    }

    public static void main(String[] args) {

        // Initializing game values
        GameLoop game = new GameLoop();
        game.setUp();

        Scanner input = new Scanner(System.in);

        boolean stillPlaying = true;

        System.out.println("Welcome to Draft #1!");
        System.out.println("Type 'enter' to move between buildings or rooms, 'exit' to leave a building, or 'look' to see the description of the room you're in.\nType 'quit' to end the game.");

        // Game loop
        do {

            System.out.print("Your move: ");
            String[] currentInput = input.nextLine().toLowerCase().split("\s+");
            
            // The first word of the input
            String mainCommand = currentInput[0];
            // The remaining words of the input
            String commandParameter = String.join(" ", Arrays.copyOfRange(currentInput, 1, currentInput.length));

            // If user input is a game command, try implementing the command
            if (game.commands.containsKey(mainCommand)) {
                try {
                    game.commands.get(mainCommand).accept(commandParameter);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }

            // If user inputs "quit", exit the game loop.
            } else if (mainCommand.equals("quit") && commandParameter.isEmpty()) {
                stillPlaying = false;

            // Otherwise, print message.    
            } else {
                System.out.println("You aren't quite sure how to do that.");
            }

        } while (stillPlaying);

        input.close();
    }

}

