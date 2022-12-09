package entities;

/** Course Info is an abstraction used to store references to basic course entities.
 * @layer entities
 */
public interface CourseInfo {
    /** Gets the course's name
     *
     * @return returns the string corresponding to the course name
     */
    String getCourseName();

    /** Gets the course's id
     *
     * @return returns the string corresponding to the course id.
     */
    String getId();

    /** Gets the course's code
     *
     * @return returns the string corresponding to the course code
     */
    String getCourseCode();

}
