package usecases.course.updatemembers;

public interface UpdateCMemPresenter
{
    UpdateCMemResponseModel prepareSuccessView(UpdateCMemResponseModel user);
    UpdateCMemResponseModel prepareFailView(String error);

}

