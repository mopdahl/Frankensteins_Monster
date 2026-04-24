public class Weapon extends GrabbableObject {

    int damage;

    public Weapon(String name, String description, int weight, int damage){
        super(name, description, weight);
        this.damage = damage;
    }
    
}
