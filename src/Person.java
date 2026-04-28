import java.util.ArrayList;  // Import this class to handle errors

public class Person {
    
    //Attributes
    public String name;
    public String description;
    public ArrayList<String> dialogue;
    public int spokenTo;
    protected Building currentBuilding;
    protected Building previouslyEnteredBuilding;
    protected Room currentRoom;
    protected int healthLevel;
    protected ArrayList<GrabbableObject> inventory;
    protected int inventoryWeight;
    protected int inventoryWeightLimit;
    protected int inventoryLimit;
    protected boolean alive;
    protected GrabbableObject heldItem;
    protected Person currentTarget;

    //Constructor
    public Person(String name, String description){
        this.name = name;
        this.description = description;
        this.currentBuilding = null;
        this.previouslyEnteredBuilding = null;
        this.currentRoom = null;
        this.healthLevel = 100;
        this.inventory = new ArrayList<>();
        this.inventoryLimit = 10;
        this.inventoryWeight = 0;
        this.alive = true;
        this.currentTarget = null;
        this.dialogue = new ArrayList<>();
        this.spokenTo = 0;
    }
    public Person(String name){
        this.name = name;
        this.currentBuilding = null;
        this.previouslyEnteredBuilding = null;
        this.currentRoom = null;
        this.healthLevel = 100;
        this.inventory = new ArrayList<>();
        this.inventoryLimit = 10;
        this.inventoryWeightLimit = 40;
        this.inventoryWeight = 0;
        this.alive = true;
        this.heldItem = null;
        this.dialogue = null;
        this.currentTarget = null;
        this.dialogue = new ArrayList<>();
        this.spokenTo = 0;
    }

    public Person(){
        this("Name Unknown");
    }

    public Building getCurrentBuilding(){
        return this.currentBuilding;
    }

    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    public int getHealthLevel(){
        return this.healthLevel;
    }

    public Person getCurrentTarget() {
        return this.currentTarget;
    }

    public void changeHealthLevel(int increment) {
        this.healthLevel += increment;
        if (this.healthLevel <= 0) {
            this.die();
        }
    }

    public String getName(){
        return this.name;
    }

    public String showStats() {
        return (this.name + " | hitpoints: " + this.getHealthLevel() + " | holding: " + this.heldItem);
    }

    /**
     * Allows person current building to be the parameter building.
     * @param building
     */
    public void enter(Building building) {
        if (this.currentBuilding == null){
            this.currentBuilding = building;
            this.enter((Room)building.rooms.get(1).get(1));
            System.out.println("-------------------------");
            System.out.println("Here is the list of possible rooms within " + building + ":");
            building.printMap();
            System.out.println("-------------------------"); 
            System.out.println("You are currently in " + this.currentRoom);
        } else {
            System.out.println("You are currently in a building, please exit it first before entering another building.");
        }
    }

    /**
     * Allows person to enter a room.
     * @param desiredRoom
     */
    public void enter(Room desiredRoom){

        // This method just allows you to enter the room if you currently aren't in a room, only for game designer
        if (this.currentRoom == null && this.previouslyEnteredBuilding == null){
                this.currentRoom = desiredRoom;
            System.out.println("-------------------------");
            System.out.println("You have entered " + desiredRoom);
            System.out.println("-------------------------");
            return;
        } 

        if (this.currentRoom == null){
            this.currentRoom = desiredRoom;
        }

        if (this.currentRoom == desiredRoom){
            System.out.println("You are already in this room!");
            return;
        }

        // Checks to see if row is within +- 1.
        if (this.isAdjacentTo(desiredRoom)) {
            if (!desiredRoom.isLocked){
                this.currentRoom = desiredRoom;
                System.out.println("You have entered " + desiredRoom);
            } else {
                System.out.println("The doorknob is jammed, perhaps it's locked. You would need a key to enter.");
            }
        } else {
            System.out.println("You are not next to this room, you cannot enter it.");
            }
    }

    public void exit(Building building) {
        if (this.currentBuilding == building){
            if (this.currentRoom.exit != null){
                this.previouslyEnteredBuilding = building;
                this.currentRoom = this.currentRoom.exit;
                this.currentBuilding = this.currentRoom.building;
                System.out.println("You have exited " + building);
            } else {
                System.out.println("The room you are currently in does not have an exit.");
            }
        } else {
            System.out.println("You are not currently in this building.");
        }
    }

    /**
     * Allows person to attack another person.
     * @param person
     */
    public void attack(Person person){
        if (person.currentRoom == this.currentRoom){
            int healthChange = 10;
            if (this.heldItem instanceof Weapon w) {healthChange += w.damage;}
            person.currentTarget = this;

            if (this instanceof Player) {
                System.out.print("You attack " + person);
            } else {
                System.out.print(this + " attacks " + person);
            }
            if (this.heldItem != null) {System.out.print(" with " + this.heldItem);}
            System.out.println("!");
            person.changeHealthLevel(-healthChange);

        }
    }

    /**
     * Allows person to hold a specific object in hand.
     * @param object
     */
    public void hold(GrabbableObject object) {
        if (this.inventory.contains(object)) {
            if (this.heldItem != object) {
                this.heldItem = object;
                System.out.println("You hold " + object + ".");
            } else {
                throw new RuntimeException("You are already holding " + object + ".");
            }
        } else {
            throw new RuntimeException("You must be get " + object + " before you can hold it.");
        }
    }

    /**
     * Allows person to put away object in hand.
     * @param object
     */
    public void putAway(GrabbableObject object) {
        if (this.heldItem == object) {
            this.heldItem = null;
            System.out.println("You stop holding " + object + ".");
        } else {
            throw new RuntimeException("You must be holding " + object + " before you put it away.");
        }
    }

    /**
     * Allows person to pick up an object if inventory contains space.
     * @param object
     */
    public void pickUp(GrabbableObject object){
        if (!this.inventory.contains(object)){
            if (this.inventory.size() < this.inventoryLimit){
                if (this.inventoryWeight + object.weight <= this.inventoryWeightLimit) {
                    this.inventory.add(object);
                    this.inventoryWeight += object.weight;
                    this.getCurrentRoom().removeItem(object);
                    System.out.println("You get " + object + ".");
                } else {
                    System.out.println("You cannot carry that much weight.");
                }
            } else {
                System.out.println("Your inventory is full.");
            }
        } else {
            System.out.println("You already have " + object + " in your inventory.");
        }
    }

    /**
     * Allows person to remove object from inventory.
     * @param object
     */
    public void putDown(GrabbableObject object){
        if (this.inventory.contains(object)){
            if (this.heldItem == object) {this.putAway(object);}
            this.inventory.remove(object);
            this.inventoryWeight -= object.weight;
            this.getCurrentRoom().addItem(object);
            System.out.println("You drop " + object + ".");
        } else {
            System.out.println(object + " is not in your inventory.");
        }
    }

    /**
     * Allows person to consume food that can either increase or decrease the person's health.
     * @param food
     */
    public void consume(FoodObject food){
        if (this.inventory.contains(food)){
            if (!(food.foodReview == null)){
                System.out.println(food.foodReview);
            }
            // Removes food item from inventory
            this.inventory.remove(food);
            this.changeHealthLevel(food.healthBoost);
        } else {
            System.out.println("You do not possess this item.");
        }
    }

    /**
     * Person can die and leave its corpse in the room they died in.
     */
    protected void die() {
        this.alive = false;
        this.currentRoom.removePerson(this);
        for (GrabbableObject obj : this.inventory) {
            this.currentRoom.addItem(obj);
        }
        this.currentRoom.addItem(new GrabbableObject("The corpse of " + this.name, "It is the mutilated body of " + this.name + ".", 40));
        if (this instanceof Player) {System.out.print("You have ");}
        else {System.out.print(this + " has ");}
        System.out.println("died!");
    }

    public String toString() {
        return this.name;
    }

    /**
     * Checks to see whether or not person is adjacent to the room they want to go to.
     * @param desiredRoom
     * @return
     */
    public boolean isAdjacentTo(Room desiredRoom){

        int desiredRow = 0;
        int desiredColumn = 0;
        int currentRow = 0;
        int currentColumn = 0;

        // Get current room row, column:
        for (int i = 0; i <= this.currentBuilding.rooms.size(); i++){
            if (this.currentRoom.equals(this.currentBuilding.rooms.get(i).get(0))){
                Object x = this.currentBuilding.rooms.get(i).get(1);
                Object y = this.currentBuilding.rooms.get(i).get(2);

                currentRow = Integer.parseInt(x.toString());
                currentColumn = Integer.parseInt(y.toString());
                break;
            }
        }

        // current desired room row, column
        for (int i = 0; i <= this.currentBuilding.rooms.size(); i++){
            if (desiredRoom.equals(this.currentBuilding.rooms.get(i).get(0))){
                Object desiredRoomRow = this.currentBuilding.rooms.get(i).get(1);
                Object desiredRoomColumn = this.currentBuilding.rooms.get(i).get(2);

                desiredRow = Integer.parseInt(desiredRoomRow.toString());
                desiredColumn = Integer.parseInt(desiredRoomColumn.toString());    
                break;
            } 
        }

        // do the implementation of whether or not the player has the ability to even enter desired room
        // check within distance, jMinus and jPlus

        int currentRowPlus = currentRow + 1;
        int currentRowMinus = currentRow - 1;

        // Checks to see if row is within +- 1.
        if ((currentRowPlus == desiredRow && currentColumn == desiredColumn) || (currentRowMinus == desiredRow && currentColumn == desiredColumn)) {
            return true;
        } else {
            int currentColumnPlus = currentColumn + 1;
            int currentColumnMinus = currentColumn - 1;

            //checks to see if column is within +-1.
            if ((currentRow == desiredRow && currentColumnPlus == desiredColumn) || (currentRow == desiredRow && currentColumnMinus == desiredColumn)) {
                return true;
                } else {
                    return false;
                }
            }
    }

}
