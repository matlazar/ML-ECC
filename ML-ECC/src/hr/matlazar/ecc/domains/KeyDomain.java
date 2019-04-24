package hr.matlazar.ecc.domains;

import hr.matlazar.ecc.arithmetic.PointEC;

public class KeyDomain {
	
	private PointEC sharedSecret;
	private String message;
	
	public PointEC getSharedSecret() {
		return sharedSecret;
	}
	public void setSharedSecret(PointEC sharedSecret) {
		this.sharedSecret = sharedSecret;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
