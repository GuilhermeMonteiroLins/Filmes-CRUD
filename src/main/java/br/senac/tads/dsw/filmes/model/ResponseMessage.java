package br.senac.tads.dsw.filmes.model;

public class ResponseMessage {

	private int status;
	private String message;
	private Object object;

	public ResponseMessage() {
		super();
	}

	public ResponseMessage(int status, String message, Object object) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
