public class Player extends Person {

    Boolean canRead;
    Room spawnPoint;
    int remainingLives;

    public Player(String name, Building building, Room room) {
        super(name);
        this.canRead = false;
        this.currentBuilding = building;
        this.currentRoom = room;
        this.spawnPoint = room;
        this.remainingLives = 1; // added AFTER architecture diagram was written
    }

    /**
     * Resets the player's statistics and location, and empties their inventory.
     */
    public void respawn(){
        if (!this.alive) {
            this.alive = true;
            this.healthLevel = 100;
            this.inventory.clear();
            this.inventoryWeight = 0;
            this.currentBuilding = this.spawnPoint.building;
            this.currentRoom = this.spawnPoint;
            System.out.println("Perhaps you can put yourself together again..."); // write a new prompt later
        } else {
            System.out.println("You are still alive.");
        }
    }

    /**
     * Allows the player to read.
     */
    public void learnToRead(){
        // In this future we probably have to implement something so that the player has to guess what they are saying
        // in order to unlock this ability
        this.canRead = true;
    }

    /**
     * Prints a description of the room and its contents.
     * @param room
     */
    public void observeRoom(Room room){ // Reformat later
        System.out.println(room.getDescription());

        if (!room.items.isEmpty()) {
            System.out.print("Items: ");
            System.out.println(room.items);
        }

        if (!room.people.isEmpty()) {
            System.out.print("People: ");
            System.out.println(room.people);
        }
    }

    /**
     * Prints the contents of the book, if the player can read.
     * @param book
     */
    public void read(Book book){
        if (this.canRead){
            System.out.println(book.getText());
        } else {
            System.out.println("You are unable to read currently. This is a skill you have to develop by speaking to a certain someone.");
        }
    }

    /**
     * Unlocks the given room, if this Player has the key and is adjacent to the room.
     * @param destinationName
     */
    public void unlockRoom(Room destinationName){
        if (this.inventory.contains(destinationName.getKey())){
            if (this.isAdjacentTo(destinationName)){
                if (!destinationName.isLocked) {throw new RuntimeException(destinationName + " is already unlocked!");}
                destinationName.unlock();
                System.out.println("You have unlocked " + destinationName);
            } else {
                System.out.println("You are not next to this room to unlock it.");
            }
        } else {
            System.out.println("You don't have the key to unlock this room.");
        }
    }

    /**
     * Locks the given room, if this Player has the key and is next to the room.
     * @param destination
     */
    public void lockRoom(Room destination) {
        if (this.inventory.contains(destination.getKey())) {
            if (this.currentRoom.equals(destination)) {throw new RuntimeException("You don't want to lock yourself inside!");}
            if (this.isAdjacentTo(destination)) {
                if (destination.isLocked) {throw new RuntimeException(destination + " is already locked.");}
                destination.lockRoom();
                System.out.println("You have locked " + destination + ".");
            } else {
                System.out.println("You are not next to this room.");
            }
        } else {
            System.out.println("You do not have the key.");
        }
    }

    /**
     * Checks to see if a person can be spoken to by player.
     */
    private Boolean canBeSpokenTo(Person personName){
        if (personName.dialogue.size() == personName.spokenTo || this.currentRoom != personName.currentRoom ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Allows player to speak to other characters.
     * @param personName
     */
    public void talkTo(Person personName){
        if (this.canBeSpokenTo(personName)){
            int i = personName.spokenTo;
            String response = personName.dialogue.get(i);
            System.out.println(personName + ": " + response);
            personName.spokenTo++;
            if (personName.spokenTo == personName.dialogue.size()){
                System.out.println("\n***");
                System.out.println("You've reached the end of your conversation with this person.");
                if(personName.canGrantRead){
                    System.out.println("You have unlocked the ability to read. Did you have items you could not read?");
                    this.canRead = true;
                }
}            
        } else {
            System.out.println("You cannot speak to this person.");
        }
    }

    /**
     * Leaves player corpse and inventory items at their current location.
     * Returns player to their spawn point with their original stats.
     */
    public void die() {
        super.die();
        this.remainingLives -= 1;
        this.respawn();
    }

    public String showStats() {
        return super.showStats() + " | lives remaining: " + this.remainingLives;
    }

    public String toString() {
        return "you";
    }

}

    
    

