package uc.course.updatemembers;

/** UpdateCMemInputBoundary provides an entry point from the Interface Adapter layer to the Use Case layer
 * to access the update members Use Case
 * @layer use cases
 */
public interface UpdateCMemInputBoundary {
    /**
     * Invokes the update members use case
     * @param requestModel UpdateCMemRequestModel containing the information of the user
     *                     and courses they want to be enrolled in
     * @return a UpdateCMemResponseModel corresponding to the UpdateCMemRequestModel
     */
    UpdateCMemResponseModel updateCourseMembership(UpdateCMemRequestModel requestModel);
}
