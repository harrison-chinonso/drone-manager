package chinonsoharrison.messages;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {

	private Date timestamp;
	private String message;

	public ErrorMessage() {
		super();
	}

	public ErrorMessage(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}
}
