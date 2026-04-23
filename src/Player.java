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

    public void observeRoom(Room room){
        System.out.println(room.getDescription());
    }
    public void read(Book book){
        if (this.canRead == true){
            System.out.println(book.getText());
        }
    }

    }

    
    

