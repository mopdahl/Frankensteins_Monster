public class Weapon extends GrabbableObject {

    int damage;

    public Weapon(String name, String description, int weight, int damage){
        super(name, description, weight);
        this.damage = damage;
    }
    
    /**
     * If the Person is wielding the weapon, removes it. Otherwise the Person wields the weapon.
     * @param user The Person using the Weapon.
     */
    public void use(Person user) {
        if (user.heldItem == this) {
            user.putAway(this);
        } else {
            user.hold(this);
        }
    }
}
