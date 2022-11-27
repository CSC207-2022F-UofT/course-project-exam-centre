package entities;

public abstract class Document {

    /**
     * The name of the document
     */
    private String name;

    /**
     * A unique identifier for the document
     */
    private String id;

    /**
     * The course that the document belongs to
     */
    private Course course;

    /**
     * The user who uploaded the document
     */
    private User user;


    /** Constructs a new Document abstract class for TestDoc or SolutionDoc to extend.
     *
     * @param name The name of the document
     * @param id The document identifier
     * @param course The course the document belongs to
     * @param user The user that uploaded the document
     */
    public Document(String name, String id, Course course, User user) {
        this.name = name;
        this.id = id;
        this.course = course;
        this.user = user;
    }

    /**
     * Gets the document's name
     * @return The document's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the unique id of the document
     * @return Returns the document's ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the course that this document belongs to
     * @return Returns the Course item
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Gets the user who uploaded the document
     * @return The associated user
     */
    public User getUser() {
        return user;
    }

}
