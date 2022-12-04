package uc.course.register;

<<<<<<< HEAD:src/main/java/uc/course/register/CRegisterlnputBoundary.java
/** Provides an entry point from the Interface Adapters layer into the Use Case layer.
 * @layer use cases
 */
public interface CRegisterlnputBoundary {
    /**
     * Invokes the Register Course use case
     * @param requestModel a request model containing information of the course being created
     * @return a CRegisterResponseModel corresponding to the requestModel
     */
=======
public interface CRegisterInputBoundary {
>>>>>>> main:src/main/java/uc/course/register/CRegisterInputBoundary.java
    CRegisterResponseModel registerCourse(CRegisterRequestModel requestModel);
}
