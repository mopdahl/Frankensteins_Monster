import java.util.ArrayList;

public class Building implements BuildingRequirements {
    
    //Attributes
    String name;
    String description;
    ArrayList<ArrayList<Room>> rooms;

    //Constructor

    public Building(String name, String description) {
        this.name = name;
        this.description = description;
        this.rooms = new ArrayList<>();
    }

    public Building(String description) {
        this("N/A", description);
    }

    public String getName(){
        return this.name;
    }

    /**
     * Sets the Building's rooms to a grid of default Room objects.
     * @param width The number of rooms on the x-axis
     * @param height The number of rooms of the y-axis
     */
    private void setRooms(int width, int height) {
        ArrayList<ArrayList<Room>> outerArray = new ArrayList<>(height);

        for (int i=0; i < height; i++) {
            outerArray.add(new ArrayList<>(width));
            for (int j=0; j < width; j++) {
                outerArray.get(i).add(new Room());
            }
        }    
        this.rooms = outerArray;
    }


    public String toString(){
        return this.name;
    }

    }
