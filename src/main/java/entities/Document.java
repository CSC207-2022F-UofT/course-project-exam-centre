package entities;

public abstract class Document {

    private String name;
    private String id;
    private Course course;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
