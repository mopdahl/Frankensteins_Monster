import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;


public class GameLoop {

    private Player player; // Convert to Player class later
    private ArrayList<Building> buildings;
    private HashMap<String, Consumer<String>> commands;
    private Person antagonist;

    public GameLoop() {
        this.buildings = new ArrayList<>();
        this.commands = new HashMap<>();
    }

    /**
     * Method to initialize game variables. Includes commands, buildings, and the player.
     */
    private void setUp() {
        // Initializing Commands
        commands.put("enter", destinationName -> this.enter(destinationName));
        commands.put("exit", emptyString -> runIfEmpty(emptyString, () -> this.player.exit(this.player.getCurrentBuilding())));
        commands.put("look", objName -> this.look(objName)); // placeholder
        commands.put("consider", personName -> System.out.println(this.getPersonFromString(personName).showStats()));
        commands.put("get", objName -> this.player.pickUp(this.getObjectFromString(objName)));
        commands.put("drop", objName -> this.player.putDown(this.getObjectFromString(objName)));
        commands.put("inventory", emptyString -> runIfEmpty(emptyString, () -> System.out.println("Inventory: " + this.player.inventory)));
        commands.put("eat", foodName -> tryWithNewException(() -> this.player.consume((FoodObject) this.getObjectFromString(foodName))));
        commands.put("options", emptyString -> runIfEmpty(emptyString, () -> this.printCommandList()));
        commands.put("attack", personName -> this.player.attack(this.getPersonFromString(personName)));
        commands.put("lock", destinationName -> tryWithNewException(() -> this.player.lockRoom((Room) this.getLocationFromString(destinationName))));
        commands.put("unlock", roomName -> tryWithNewException(() -> this.player.unlockRoom((Room) this.getLocationFromString(roomName))));
        commands.put("map", misc -> runIfEmpty(misc, () -> this.player.currentBuilding.printMap()));
        commands.put("use", objName -> this.getObjectFromString(objName).use(this.player));
        commands.put("talk", personName -> this.player.talkTo(this.getPersonFromString(personName)));
        commands.put("read", bookName -> tryWithNewException(() -> this.player.read((Book) this.getObjectFromString(bookName))));
        
        // Initializing Buildings
        // Mansion creation
        Building mansion = new Building("Mansion", "Frankenstein's Mansion", """
-----------------------------------
|                |                |
|                |                |
|  Laboratory    | Frankenstein's |
|                |    Bedroom     |
|                |                |
-----------------------------------
|                |                |
|                |                |
|    Kitchen     |   Living Room  |
|                |    (has exit)  |
|                |                |
-----------------------------------
""");
        Room lab = new Room("Laboratory", "This room contains a chaotic and eerie environment. It is dimly lit by the glow of a lantern as the blinds are shut.\nIn the room there is a shelf where you see an assortment of scientific instruments. There is also paper scattered around the room on the floor.", null, mansion);
        Room victorsRoom = new Room("Frankenstein's Bedroom", "As you enter this room, you are met with an immediate stench.\nThere is an unmade bed and various mugs by the bedside table.\nOn the bed lies a book.", null, mansion);
        Room kitchen = new Room("Kitchen", "This spacious area is adorned with a dark oak dining table.\nOn the table is a basket of assorted fruits.\nThere is an icebox off to the side.", null, mansion);
        Room livingRoom = new Room("Living Room", "This area sure looks tenebrous. There is a dank smell in this room, the ceiling of the room seems to be falling apart.\nAn ominous air looms over.\nYou see a door.", null, mansion);

        // Cottage Creation:
        Building cottage = new Building("Cottage", "This is the cottage where M. De Lacey, Felix, Agatha, and Safie reside.", """
-----------------------------------
|                |                |
|                |                |
| Your Dwelling  |  Main Cottage  |
|  (has exit)    |                |
|                |                |
-----------------------------------

        """);
        Room monstersDwelling = new Room("My Dwelling", "You find yourself in the hovel next to the cottage.\nYou find yourself comfortable among the rats and cockroaches that roam around.\nAround you see you various farming tools.", null, cottage);
        Room mainRoom = new Room("Main Cottage", "This is the cottage where M. De Lacey, Felix, Agatha, and Safie reside.", null, cottage);

        // Ship creation:
        Building ship = new Building("Ship", "This is the ship where you may find some enemies, but Frankenstein lies here.", """
-----------------------------------
|                |                |
|                |                |
|      Deck      |      Cabin     |
|   (has exit)   |                |
|                |                |
----------------------------------- 
                 |                |
                 |                |
                 |     Room       |
                 |                |
                 |                |
                 ------------------

        """);
        Room deck = new Room("Deck", "A bunch of sailors are here...They don't seem very nice.\nThey ", null, ship);
        Room cabin = new Room("Cabin", "This area consists of a lot of rooms, however you want to venture into Frankenstein's room.", null, ship);
        Room room = new Room("Room", "There is a bed with a frail figure laying in it, who could it be?", null, ship);

                // Woods creation:
        Building woods = new Building("Woods", "This is the woods, there is a lot to explore here.", """

------------------
|                |
|                |
|     Mansion    |  
|                | 
|                |
------------------
       | |
       | |
       | |
       | |
       | |
       | |
       | |   
------------------------------------------------------
|                |                |                  |                  
|                |                |                  |-------------
| Beginning Woods|     Middle     |      Deep        |---------|  |
|                |      Woods     |      Woods       |         |  |
|                |                |                  |         |  |
------------------------------------------------------         |  |
                            | |                                |  |
                            | |                                |  |     |-----
                            | |                                |  |     |      --------
                            | |                                |  |     |               ----
                            | |                                |  |     |      --------
                            | |                                |  |     |-----
                            | |                                |  |     |
                    ------------------                    ------------------
                    |                |                    |                |
                    |                |                    |                |
                    |     Cottage    |                    |      Ship      |  
                    |                |                    |                |
                    |                |                    |                |
                    ------------------                     ----------------


        """);
        Room woods1 = new Room("Beginning Woods", "This is a section of the woods just outside of the Frankenstein Mansion.\nAround you see a pile of what seems like fabric- left by hunters?\nYou see a hawk on a tree staring at you disturbingly.\nYou see a creature that is eating away at some corpse of an wild animal.", livingRoom, woods);
        Room woods2 = new Room("Middle Woods", "You have travelled from the beginning woods into a deeper part of the woods.\nYou hear running water in the distance, and you hear a noise you've never heard before.\nLooking out, you see a cottage in the far distance.", monstersDwelling, woods);
        Room woods3 = new Room("Deep woods", "You are deep into the woods, far away you can see the ocean and a ship.", deck, woods);

        deck.exit = woods3;
        monstersDwelling.exit = woods2;
        livingRoom.exit = woods1;

        // Mansion ArrayLists:
        ArrayList<Object> labCoordinates = new ArrayList<>();
        ArrayList<Object> frankensteinsRoomCoordinates = new ArrayList<>();
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

        // Mansion rooms and their coordinates arraylist list

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

        frankensteinsRoomCoordinates.add(victorsRoom);
        frankensteinsRoomCoordinates.add(i);
        frankensteinsRoomCoordinates.add(j);

        woods2Coordinates.add(woods2);
        woods2Coordinates.add(i);
        woods2Coordinates.add(j);

        mainRoomCoordinates.add(mainRoom);
        mainRoomCoordinates.add(i);
        mainRoomCoordinates.add(j);

        cabinCoordinates.add(cabin);
        cabinCoordinates.add(i);
        cabinCoordinates.add(j);

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

        roomCoordinates.add(room);
        roomCoordinates.add(i);
        roomCoordinates.add(j);

        mansion.rooms.add(labCoordinates);
        mansion.rooms.add(frankensteinsRoomCoordinates);
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

        this.buildings.add(mansion);
        this.buildings.add(woods);
        this.buildings.add(cottage);
        this.buildings.add(ship);

        // Initializing people in rooms.
        woods3.addPerson(new Person("Elizabeth"));
        Person deLacey = new Person("DeLacey", "A kind blind guy that lives in the cottage.");

        // Giving Delacey dialogue
        mainRoom.addPerson(deLacey);
        deLacey.canGrantRead = true;
        
        try {
            File file = new File("delacey.txt");
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()){
                String data = fileReader.nextLine();
                deLacey.dialogue.add(data);
            }

            fileReader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        mainRoom.addPerson(new Person("Felix", "This is Delacey's son"));
        mainRoom.addPerson(new Person("Agatha", "This is De Lacey's Daugther."));
        mainRoom.addPerson(new Person("Safie", "This is Felix's fiancee."));
        deck.addPerson(new Person("Sailor 1", "Generic sailor"));
        deck.addPerson(new Person("Sailor 2", "Generic sailor"));
        deck.addPerson(new Person("Sailor 3", "Generic sailor"));
        deck.addPerson(new Person("Sailor 4", "Generic sailor"));

        Person victorFrankenstein = new Person("Victor Frankenstein", "This is the man that did it all. Should you seek revenge?");
        room.addPerson(victorFrankenstein);
        this.antagonist = victorFrankenstein;

        try {
            File dialogueFile = new File("frankenstein.txt");
            Scanner dialogueReader = new Scanner(dialogueFile);

            while (dialogueReader.hasNextLine()){
                String data = dialogueReader.nextLine();
                deLacey.dialogue.add(data);
            }

            dialogueReader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        
        // Initializing Player
        this.player = new Player("Frankie", mansion, lab);

        // Making sailors aggressive
        for (Person sailor : deck.people) {
            sailor.currentTarget = this.player;
        }


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
        victorsRoom.items.add(new Book("Diary", "Suspicious stains adorn the pages of this book. It seems to be the diary of the owner of this house, the person who would know who I am.\nI should keep this.", 10, "9/15/18??: I have made a human from scratch. No mother. No father. I am its creator. It shall have no other companion.\n9/20/18??: Today is the day that the creature I create come to life. \nI sure hope this creature isn't evil when it awakes. \nIf it is, I am running off to a boat off in the ocean on the coast of the deep woods."));
        victorsRoom.items.add(new GrabbableObject("Teddy Bear", "Looks old and worn, whoever's room this is...seems to be too old for a teddy bear."));
        victorsRoom.items.add(new Weapon("Knife", "What was he doing with this in his bedroom?", 5, 20));

        livingRoom.items.add(new GrabbableObject("Coffee table", "Nothing is on this coffee table.", this.player.inventoryWeightLimit));

 
        // Cottage: mainRoom
        monstersDwelling.items.add(new Weapon("Shovel", "This could really knock someone out", 20, 30));
        monstersDwelling.items.add(new FoodObject("Dead Rat", "This rat looks like its been dead for a while", 10, -50, "Am I gonna survive eating this?"));
        mainRoom.items.add(new FoodObject("Boiled Eggs", "Hopefully it is soft-boiled with a runny yolk.", 1, 5, "Ew...how do you mess up an egg?"));
        mainRoom.items.add(new Book("Paradise Lost", "An epic poem in blank verse by the English poet John Milton (1608–1674). The poem concerns the biblical story of the fall of man: the temptation of Adam and Eve by the fallen angel Satan and their expulsion from the Garden of Eden.", 5, "..."));
        mainRoom.items.add(new Book("Plutarch's Lives", "A series of 48 biographies of famous men written in Greek by the Greco-Roman philosopher, historian, and Apollonian priest Plutarch", 5, "..."));
        mainRoom.items.add(new Weapon("Bow & Arrow", "This is a bow and arrow", 10, 17));

        // Final room adjustments
        victorsRoom.lockRoom();
        victorsRoom.assignKey(key);

    }

    /**
     * Checks if the given string is empty, and if so runs the method.
     * @param string The string to be compared
     * @param method The method to be run
     * @throws RuntimeException When the string is not empty.
     */
    private static void runIfEmpty(String string, Runnable method) {
        if (string.isEmpty()) {
            method.run();
        } else {
            throw new RuntimeException("This command does not take any arguments!");
        }
    }

    /**
     * Tries to run the given method, but prints a new, set ExceptionError message.
     * @param method The method to be run
     * @throws RuntimeException When the method fails
     */
    private static void tryWithNewException(Runnable method) {
        try {
            method.run();
        } catch (RuntimeException e) {
            System.out.println("You cannot do that.");
        }
    }

    /**
     * Prints a list of the commands available in the game's commands.
     */
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

    /**
     * Finds and returns the first object in the player's inventory or current room with the given name.
     * @param objectName The name of the object to be returned
     * @return The first object whose name matches the given string.
     * @throws RuntimeException When no object with the given name is found.
     */
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

    /**
     * Finds and returns the first Person in the player's current room whose name matches the given string.
     * @param personName The name of the Person to be located
     * @return The person with the given name
     * @throws RuntimeException When no Person with the given name is found
     */
    private Person getPersonFromString(String personName) {
        if (personName.isEmpty()) {throw new RuntimeException("You need to have SOMEONE in mind.");}
        for (Person person : this.player.getCurrentRoom().people) {
            if (person.getName().toLowerCase().equals(personName)) {
                return person;
            }
        }
        if (personName.equals(this.player.name) || personName.equals("self")) {return this.player;}
        throw new RuntimeException("You don't see them here.");
    }

    public static void main(String[] args) {

        // Initializing game values
        GameLoop game = new GameLoop();
        game.setUp();

        Scanner input = new Scanner(System.in);

        boolean stillPlaying = true;
        System.out.println("\n || Welcome to FRANKENSTEIN'S MONSTER || ");
        System.out.println("\nYou are violently shaken awake and as you open your eyes, you see a figure run away.\nYour body is stiff, it's almost as if it's the first time you've ever moved.\nYou don't have any clothes on or anything on you.\nWHO are you?");
        System.out.println("\nTo find out, type 'options' to see what you can do.");

        // Game loop
        do {
            
            // --- End Game conditions:

            // If player runs out of lives, they lose
            if (game.player.remainingLives < 1) {
                System.out.println("But broken bones cannot be restored forever.");
                System.out.println("\nYou have lost!");
                break;

            // If player defeats Frankenstein, they win
            } else if (!game.antagonist.alive) {
                System.out.print("As you see your creator's body lying still before you, the rage animating your body slowly fades.");
                System.out.println(" Your limbs feel heavy, disjointed; your breath grows shallow. You have finally avenged his curse on your existence -- finally, looking at your own hands, you can see a monster.\n");
                System.out.println("You win!");
                break;
            }

            // --- Get and run user input
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

            // --- Check for attacks
            for (Person person : game.player.currentRoom.people) {
                if (person.getCurrentTarget() != null) {
                    person.attack(person.getCurrentTarget());
                }
            }

        } while (stillPlaying);

        input.close();
    }

}

