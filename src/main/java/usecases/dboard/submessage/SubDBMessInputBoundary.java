package usecases.dboard.submessage;

// Use case layer

public interface SubDBMessInputBoundary {
    SubDBMessResponseModel submitMessage(SubDBMessRequestModel requestModel);
}
