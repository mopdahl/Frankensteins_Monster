public class Person implements PersonRequirements {
    
    //Attributes
    String name;
    Building currentBuilding;
    //Room currentRoom;
    int healthLevel;


    //Constructor
    public Person(String name, Building currentBuilding){
        this.name = name;
        this.currentBuilding = currentBuilding;
        this.healthLevel = 100;
    }

    public void enter(Building building) {
        this.currentBuilding = building;

    }

    public void exit(Building building) {

    }

    public void attack(Person person){

    }

    public String talk(){

    }



}
