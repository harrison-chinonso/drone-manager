package chinonsoharrison.messages;

public class MessageResponses {

	public static String CODE_OK = "success";
	public static String CODE_ERROR = "error";
	public static String MESSAGE_COMPLETED = "Successfully completed";
	public static String MESSAGE_CREATE = "Successfully created";
	private Object Obj = null;
	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public MessageResponses(String code, String message, Object Obj) {
		this.code = code;
		this.message = message;
		this.Obj = Obj;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static MessageResponses response(String code, String message, Object obj) {
		return new MessageResponses(code, message, obj);
	}

	public Object getObj() {
		return Obj;
	}

	public void setObj(Object Obj) {
		this.Obj = Obj;
	}
}
