import java.util.ArrayList;

public class Person {
    
    //Attributes
    public String name;
    protected Building currentBuilding;
    protected Building previouslyEnteredBuilding;
    protected Room currentRoom;
    protected int healthLevel;
    protected ArrayList<GrabbableObject> inventory;
    protected int inventoryWeight;
    protected int inventoryWeightLimit;
    protected int inventoryLimit;
    protected boolean alive;

    //Constructor
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

    public void changeHealthLevel(int increment) {
        this.healthLevel += increment;
        if (this.healthLevel <= 0) {
            this.die();
        }
    }

    public String getName(){
        return this.name;
    }
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

    public void enter(Room desiredRoom){

        int desiredRow = 0;
        int desiredColumn = 0;
        int currentRow = 0;
        int currentColumn = 0;

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
            if (!desiredRoom.isLocked){
                this.currentRoom = desiredRoom;
                System.out.println("You have entered " + desiredRoom);
            } else {
                System.out.println("The doorknob is jammed, perhaps it's locked");
            }
        } else {
            int currentColumnPlus = currentColumn + 1;
            int currentColumnMinus = currentColumn - 1;

            //checks to see if column is within +-1.
            if ((currentRow == desiredRow && currentColumnPlus == desiredColumn) || (currentRow == desiredRow && currentColumnMinus == desiredColumn)) {
                if (!desiredRoom.isLocked){
                    this.currentRoom = desiredRoom;
                    System.out.println("You have entered " + desiredRoom);
                } else {
                    System.out.println("The doorknob is jammed, perhaps it's locked");
                }

                } else {
                    // This will be a runtime exception eventually I believe,
                    // Later on maybe implement a "This room does not even exist" exception.
                    System.out.println("You are not next to this room, you cannot enter this room.");
                }
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

    public void attack(Person person){
        if (person.currentRoom == this.currentRoom){
            //in the future we probably want a different health level change if the person is holding a weapon
            // also maybe in the future if the person being attacked has armor?
            int healthChange = 10;
            person.changeHealthLevel(-healthChange);
           // person.respondToAttack();
        }
    }


    public void pickUp(GrabbableObject object){
        if (!this.inventory.contains(object)){
            if (this.inventory.size() < this.inventoryLimit){
                if (this.inventoryWeight + object.weight <= this.inventoryWeightLimit) {
                    this.inventory.add(object);
                    this.inventoryWeight += object.weight;
                    this.getCurrentRoom().removeItem(object);
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

    public void putDown(GrabbableObject object){
        if (this.inventory.contains(object)){
            this.inventory.remove(object);
            this.inventoryWeight -= object.weight;
            this.getCurrentRoom().addItem(object);
        } else {
            System.out.println(object + " is not in your inventory.");
        }
    }

    public void consume(FoodObject food){
        if (this.inventory.contains(food)){
            // In the future we can have maybe rankings for food? For like how much health it gives the player?
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

    protected void die() {
        this.alive = false;
        this.currentRoom.removePerson(this);
        for (GrabbableObject obj : this.inventory) {
            this.currentRoom.addItem(obj);
        }
        this.currentRoom.addItem(new GrabbableObject("The corpse of " + this.name, "It is the mutilated body of " + this.name + ".", 40));
    }

    // public void respondToAttack() {
    //     if (this.getHealthLevel() <= 0) {
    //         this.die();
    //     }
    // }

    public String toString() {
        return this.name;
    }

}
