package use_case.translation;

public interface TranslationOutputBoundary {

    /**
     * Fires a signal to the article view model to update the view accordingly.
     *
     * @param outputData The TranslationOutputData to unpack and update the state accordingly with the data given.
     * @author Jaron Fernandes
     */
    void prepareSuccessView(TranslationOutputData outputData);
    /**
     * Fires a signal to the article view model to update the view accordingly.
     *
     * @param error The error message as a string.
     * @author Jaron Fernandes
     */
    void prepareFailView(String error);
}
