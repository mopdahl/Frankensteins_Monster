public class GrabbableObject {
    
    String name;
    String description;
    int weight;

    public GrabbableObject(String name, String description, int weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public GrabbableObject(String name, String description) {
        this(name, description, 0);
    }

    public GrabbableObject() {
        this("An Object", "This is an object.");
    }

    public String toString() {
        return this.name;
    }
}
