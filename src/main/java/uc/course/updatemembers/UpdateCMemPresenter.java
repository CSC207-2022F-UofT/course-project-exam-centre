package uc.course.updatemembers;

public interface UpdateCMemPresenter
{
    /**
     * Prepares a success screen
     * @param user UpdateCMemResponseModel containing the information of the user
     */
    UpdateCMemResponseModel prepareSuccessView(UpdateCMemResponseModel user);

    /**
     * Prepares a failure view
     * @param error A string of the error that has occurred
     */
    UpdateCMemResponseModel prepareFailView(String error);

}

