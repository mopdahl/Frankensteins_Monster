import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class GameLoop {

    private Person player; // Convert to Player class later
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

        // Initializing Commands
        commands.put("enter", destinationName -> System.out.println(destinationName)); // placeholder
        commands.put("look", anyString -> System.out.println(this.player.getCurrentRoom().getDescription())); // placeholder
        
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

        // Initializing Player

        this.player = new Person("Frankie");
        this.player.currentRoom = lab;
    }

    public static void main(String[] args) {

        // Initializing game values
        GameLoop game = new GameLoop();
        game.setUp();

        Scanner input = new Scanner(System.in);

        boolean stillPlaying = true;

        System.out.println("Welcome to Draft #1!\nYou are in Frankenstein's Lab, but can't go anywhere yet.");
        System.out.println("Type 'enter' to repeat whatever you input, or 'look' to see the description of the room you're in.\nType 'quit' to end the game.");

        // Game loop
        do {

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

