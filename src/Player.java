public class Player extends Person {

    Boolean canRead;

    public Player(String name, Building building, Room room) {
        super(name);
        this.canRead = false;
        this.currentBuilding = building;
        this.currentRoom = room;
    }

    public void respawn(){
        if (this.alive == false){
            this.alive = true;
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
            destinationName.unlock();
        } else {
            System.out.println("You don't have the key to unlock this room.");
        }

    }


    }

    
    

