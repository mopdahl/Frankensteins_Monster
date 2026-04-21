public class Person {
    
    //Attributes
    String name;
    Building currentBuilding;
    Room currentRoom;
    int healthLevel;


    //Constructor
    public Person(String name){
        this.name = name;
        this.currentBuilding = null;
        this.currentRoom = null;
        this.healthLevel = 100;
    }

    public Building getCurrentBuilding(){
        return this.currentBuilding;
    }

    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    public void enter(Building building) {
        if (this.currentBuilding == null){
            this.currentBuilding = building;
        } else {
            System.out.println("You are currently in a building, please exit it first before entering another building.");
        }

    }

    public void enter(Room desiredRoom, Building building){

        int desiredRow = 0;
        int desiredColumn = 0;
        int currentRow = 0;
        int currentColumn = 0;

        // This method just allows you to enter the room if you currently aren't in a room, only for game designer
        if (this.currentRoom == null){

            this.currentRoom = desiredRoom;
            return;
            
        }

        // Get current room row, column:
        for (int i = 0; i <= building.rooms.size(); i++){
            if (this.currentRoom.equals(building.rooms.get(i).get(0))){
                Object x = building.rooms.get(i).get(1);
                Object y = building.rooms.get(i).get(2);

                currentRow = Integer.parseInt(x.toString());
                currentColumn = Integer.parseInt(y.toString());
                System.out.println("You are in coordinates: " + currentRow + ", " + currentColumn);
                break;
            }
        }

        // current desired room row, column
        for (int i = 0; i <= building.rooms.size(); i++){
            if (desiredRoom.equals(building.rooms.get(i).get(0))){
                Object desiredRoomRow = building.rooms.get(i).get(1);
                Object desiredRoomColumn = building.rooms.get(i).get(2);

                desiredRow = Integer.parseInt(desiredRoomRow.toString());
                desiredColumn = Integer.parseInt(desiredRoomColumn.toString());

                System.out.println("You want to get to coordinates: " + desiredRow + ", " + desiredColumn);
                break;
            } 
        }

        // do the implementation of whether or not the player has the ability to even enter desired room
        // check within distance, jMinus and jPlus

        int currentRowPlus = currentRow + 1;
        int currentRowMinus = currentRow - 1;

        // Checks to see if row is within +- 1.
        if ((currentRowPlus == desiredRow && currentColumn == desiredColumn) || (currentRowMinus == desiredRow && currentColumn == desiredColumn)) {
            this.currentRoom = desiredRoom;
        } else {
            int currentColumnPlus = currentColumn + 1;
            int currentColumnMinus = currentColumn - 1;

            //checks to see if column is within +-1.
            if ((currentRow == desiredRow && currentColumnPlus == desiredColumn) || (currentRow == desiredRow && currentColumnMinus == desiredColumn)) {
                this.currentRoom = desiredRoom;

                } else {
                    // This will be a runtime exception eventually I believe,
                    // Later on maybe implement a "This room does not even exist" exception.
                    System.out.println("You are not next to this room, you cannot enter this room.");
                }}


 
    }

    public void exit(Building building) {
        if (this.currentBuilding == building){
            if (this.currentRoom.hasExit == true){
                this.currentBuilding = null;
            } else {
                System.out.println("The room you are currently in does not have an exit.");
            }
        } else {
            System.out.println("You are not currently in this building.");
        }
    }

    // public void attack(Person person){

    // }

    // public String talk(){

    // }



}
