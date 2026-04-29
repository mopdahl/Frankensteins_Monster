public class Book extends GrabbableObject {
    private String text;

    public Book(String name, String description, int weight, String text) {
        super(name, description, weight);
        this.text = text;
    }

    public Book() {
        this("A Book", "It is a small, leather-bound book.", 1, "...");
    }

    public String getText() {
        return this.text;
    }
}
