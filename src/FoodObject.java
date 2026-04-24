public class FoodObject extends GrabbableObject {
    int healthBoost;
    String foodReview;
    
    public FoodObject(String name, String description, int weight, int healthBoost, String foodReview) {
        super(name, description, weight);
        this.healthBoost = healthBoost;
        this.foodReview = foodReview;
    }
}
