package uc.course.register;

/** Provides an entry point from the Interface Adapters layer into the Use Case layer.
 * @layer use cases
 */
public interface CRegisterInputBoundary {
    /**
     * Invokes the Register Course use case
     * @param requestModel a request model containing information of the course being created
     * @return a CRegisterResponseModel corresponding to the requestModel
     */
    CRegisterResponseModel registerCourse(CRegisterRequestModel requestModel);
}