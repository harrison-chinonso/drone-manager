package chinonsoharrison.messages;

public enum ErrorMessages {

	INTERNAL_SERVER_ERROR("internal server error. please contact the administrator"),
	NO_RECORD_FOUND("transaction could not be found"),
	COULD_NOT_UPDATE_RECORD("transaction could not be updated"),
	COULD_NOT_DELETE_RECORD("transaction could not be deleted"),
	COULD_NOT_LOAD_MEDICATION("loading medication process could not be completed");
	
	private String errorMessages;

	ErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

	public String getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	
}
