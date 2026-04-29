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

    public void use(Person user) {
        System.out.println("How exactly do you intend to use " + this.name + "?");
    }

    public String toString() {
        return this.name;
    }
}
