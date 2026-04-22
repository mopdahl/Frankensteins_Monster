public class FoodObject extends GrabbableObject {
    String name;
    String description;
    int weight;
    int healthBoost;
    
    public FoodObject(String name, String description, int weight, int healthBoost) {
        super(name, description, weight);
        this.healthBoost = healthBoost;
    }
}
