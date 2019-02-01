package hr.matlazar.ecc.domains;


public class KeyDomain {
	
	private String sharedSecret;
	private String message;
	
	public String getSharedSecret() {
		return sharedSecret;
	}
	public void setSharedSecret(String sharedSecret) {
		this.sharedSecret = sharedSecret;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
