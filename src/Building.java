import java.util.ArrayList;

public class Building {
    
    //Attributes
    String name;
    String description;
    ArrayList<ArrayList<Object>> rooms;
    String map;

    //Constructor

    public Building(String name, String description, String map){
        this.name = name;
        this.description = description;
        this.rooms = new ArrayList<>();
        this.map = map;
    }

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

    public void getRooms(){
        ArrayList<Object> buildingRooms = new ArrayList<>();

        for (int i = 0; i < this.rooms.size(); i++) {
            buildingRooms.add(this.rooms.get(i).get(0));    
        }

        for (int i = 0; i < buildingRooms.size(); i++) {
            System.out.println(i+1 + ": " + buildingRooms.get(i));
        }
    }

    public void printMap(){
        System.out.println(this.map);
    }

    public String toString(){
        return this.name;
    }

    public static void main(String[] args) {

        // Person dummy
        Person yushan = new Person("Yushan");

    }}

    
