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
        FoodObject food = new FoodObject("food", "a piece of food", 0, 0, "NA"); // placeholder: needed to get Class<Food> object 

        // Initializing Commands
        commands.put("enter", destinationName -> this.enter(destinationName));
        commands.put("exit", buildingName -> this.player.exit(this.player.getCurrentBuilding())); // currently accepts any string after first word 'exit'
        commands.put("look", objName -> this.look(objName)); // placeholder
        commands.put("consider", personName -> System.out.println(this.getPersonFromString(personName).getName() + " : " + this.getPersonFromString(personName).healthLevel));
        commands.put("get", objName -> this.player.pickUp(this.getObjectFromString(objName)));
        commands.put("drop", objName -> this.player.putDown(this.getObjectFromString(objName)));
        commands.put("inventory", anyString -> System.out.println("Inventory: " + this.player.inventory));
        commands.put("eat", foodName -> this.player.consume(castAs(food.getClass(), this.getObjectFromString(foodName))));
        commands.put("options", anyString -> this.printCommandList());
        commands.put("attack", personName -> this.player.attack(this.getPersonFromString(personName)));
        commands.put("unlock", destinationName -> this.player.unlockRoom((Room) this.getRoomFromString(destinationName)));
        commands.put("map", misc -> this.player.currentBuilding.printMap());
        
        // Initializing Buildings
        Building mansion = new Building("Mansion", "Victor's Mansion", """
-----------------------------------
|                |                |
|                |                |
|  Laboratory    | Frankenstein's |
|                |      Room      |
|                |                |
-----------------------------------
|                |                |
|                |                |
|    Kitchen     |   Living Room  |
|                |                |
|                |                |
-----------------------------------
""");
        Room lab = new Room("Laboratory", "This room contains a chaotic and eerie environment. It is dimly lit by the glow of a lantern as the blinds are shut. In the room there is a shelf where you see an assortment of scientific instruments. There is also paper scattered around the room on the floor.", false);
        Room frankensteinsRoom = new Room("Frankenstein's Bedroom", "As you enter this room, you are met with an immediate stench.\nThere is an unmade bed and various mugs by the bedside table.\nOn the bed lies a book.", false);
        Room kitchen = new Room("Kitchen", "This spacious area is adorned with a dark oak dining table.\nOn it is a basket of assorted fruits.\nThere is an icebox off to the side.", false);
        Room livingRoom = new Room("Living Room", "This area sure looks tenebrous. There is a dank smell in this room, the ceiling of the room seems to be falling apart.\nAn ominous air looms over.\nYou see a door.", true);

        // Woods creation:

        Building woods = new Building("Woods", "This is the woods, there is a lot to explore here.");
        Room woods1 = new Room("Beginning Woods", "This is a section of the woods just outside of the Frankenstein Mansion.", true);
        Room woods2 = new Room("Middle Woods", "You have travelled from the beginning woods into a deeper part of the woods. You see a cottage in the far distance.", true);
        Room woods3 = new Room("Deep woods", "You are deep into the woods, far away you can see the ocean and ship.", true);

        // Mansion ArrayLists:
        ArrayList<Object> labCoordinates = new ArrayList<>();
        ArrayList<Object> frankensteinsRoomCoordinates = new ArrayList<>();
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

        frankensteinsRoomCoordinates.add(frankensteinsRoom);
        frankensteinsRoomCoordinates.add(i);
        frankensteinsRoomCoordinates.add(j);

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
        mansion.rooms.add(frankensteinsRoomCoordinates);
        mansion.rooms.add(kitchenCoordinates);
        mansion.rooms.add(livingRoomCoordinates);

        woods.rooms.add(woods1Coordinates);
        woods.rooms.add(woods2Coordinates);
        woods.rooms.add(woods3Coordinates);

        this.buildings.add(mansion);
        this.buildings.add(woods);

        // lab.addPerson(new Person("Randall the Rando"));
        lab.addPerson(new Person("Elizabeth"));
        // livingRoom.items.add(new FoodObject("Test Object", "this is an item", 0, 0,"NA"));


        // Initializing Player
        this.player = new Player("Frankie", mansion, lab);
        //this.player.currentRoom = lab; -> currentRoom is currently private

        // Initializing objects in rooms

        // Laboratory
        lab.items.add(new Weapon("Syringe", "This looks like its got some junk in it, but it's sharp.", 0, 5));
        lab.items.add(new GrabbableObject("Document", "There is some various scribbles on this paper, \nthere is a drawing of something in vague resemblance of the person you saw right before you woke up.", 0));

        // Kitchen 
        GrabbableObject key = (new GrabbableObject("Key", "A key, perhaps it opens a door in this building?", 0));
        kitchen.items.add(key);
        kitchen.items.add(new FoodObject("Bread", "It looks half-eaten and has a hard outer shell.", 5, 10, "Well, that was nasty!"));
        kitchen.items.add(new FoodObject("Salted Pork", "A suspicious smell is coming from this meat", 5, -10, "BLEGH BLEGH BLEGH!"));
        kitchen.items.add(new FoodObject("Squab", "This seems gourmet...", 5, 15, "Wow... that was DELICIOUS!"));

        // Victor's Bedroom
        frankensteinsRoom.items.add(new Book("Frankenstein's Super Secret Diary", "Suspicious stains adorn the pages of this book", 10, "Today is the day that the creature I create come to life. \nI sure hope this creature isn't evil when it awakes. \nIf it is, I am running off to a boat off in the ocean on the coast of the deep woods."));


        // Final room adjustments
        frankensteinsRoom.lockRoom();
        frankensteinsRoom.assignKey(key);

    }

    private void printCommandList() {
        for (String commandName : this.commands.keySet()) {
            System.out.print(commandName + " | ");
        }
        System.out.println("quit");
    }

    /**
     * Prints a description of the desired object.
     * @param objName The name of the desired object
     */    
    private void look(String objName) {
        if (objName.equals("around") || objName.equals("")) {
            System.out.println("You are in the " + this.player.currentRoom + ".");
            this.player.observeRoom(this.player.getCurrentRoom());
        } else {
            System.out.println(this.getObjectFromString(objName).description);
        }
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
        if (locationName.isEmpty()) {throw new RuntimeException("You need to have SOME PLACE in mind.");}
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

    private Object getRoomFromString(String locationName) {
        if (locationName.isEmpty()) {throw new RuntimeException("You need to have SOME PLACE in mind.");}
            for (int i = 0; i < this.buildings.size(); i++){
                Building building = this.buildings.get(i);
                for (i = 0; i < building.rooms.size(); i++){
                    Room room = (Room) building.rooms.get(i).get(0);
                    if (room.getName().toLowerCase().equals(locationName)){
                        return room;
                    }
                }
            }
        return null;
        }

    private GrabbableObject getObjectFromString(String objectName) {
        if (objectName.isEmpty()) {throw new RuntimeException("You need to have SOMETHING in mind.");}
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

    private Person getPersonFromString(String personName) {
        if (personName.isEmpty()) {throw new RuntimeException("You need to have SOMEONE in mind.");}
        for (Person person : this.player.getCurrentRoom().people) {
            if (person.getName().toLowerCase().equals(personName)) {
                return person;
            }
        }
        throw new RuntimeException("You don't see them here.");
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
        System.out.println("\n || Welcome to FRANKENSTEIN'S MONSTER || ");
        System.out.println("\nYou are violently shaken awake and as you open your eyes, you see a figure run away.\nYour body is stiff, it's almost as if it's the first time you've ever moved.\nYou don't have any clothes on or anything on you.\nWHO are you?");
        System.out.println("\nTo found out, type 'options' to see what you can do.");

        // Game loop
        do {
            System.out.println();
            System.out.print("Input: ");
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

