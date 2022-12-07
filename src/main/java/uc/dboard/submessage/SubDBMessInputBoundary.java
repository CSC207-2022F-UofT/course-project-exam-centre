package uc.dboard.submessage;

/** SubDBMessInputBoundary provides an entry point from the Interface Adapters layer into the Use Case layer
 * for the submit discussion board message use case
 * @layer use cases
 */
public interface SubDBMessInputBoundary {
    /**
     * Invokes the discussion board submit message use case
     * @param requestModel contains information about the solution, user,
     *                     the user they're responding to, and the body of the text
     * @return A ResponseModel corresponding to the requestModel
     */
    SubDBMessResponseModel submitMessage(SubDBMessRequestModel requestModel);
}
