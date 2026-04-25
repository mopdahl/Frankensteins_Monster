public class Player extends Person {

    Boolean canRead;
    Room spawnPoint;

    public Player(String name, Building building, Room room) {
        super(name);
        this.canRead = false;
        this.currentBuilding = building;
        this.currentRoom = room;
        this.spawnPoint = room;
    }

    public void respawn(){
        if (!this.alive) {
            this.alive = true;
            this.inventory.clear();
            this.inventoryWeight = 0;
            this.currentBuilding = this.spawnPoint.building;
            this.currentRoom = this.spawnPoint;
            System.out.println("You have died!");
            System.out.println("Perhaps you can put yourself together again..."); // write a new prompt later
        } else {
            System.out.println("You are still alive.");
        }
    }

    public void learnToRead(){
        // In this future we probably have to implement something so that the player has to guess what they are saying
        // in order to unlock this ability
        this.canRead = true;
    }

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
    public void read(Book book){
        if (this.canRead){
            System.out.println(book.getText());
        }
    }

    public void unlockRoom(Room destinationName){
        if (this.inventory.contains(destinationName.getKey())){
            if (this.isAdjacentTo(destinationName)){
                destinationName.unlock();
                System.out.println("You have unlocked " + destinationName);
            } else {
                System.out.println("You are not next to this room to unlock it.");
            }
        } else {
            System.out.println("You don't have the key to unlock this room.");
        }

    }

    public void die() {
        super.die();
        this.respawn();
    }

    }

    
    

