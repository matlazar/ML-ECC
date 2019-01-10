package hr.matlazar.ecc.domains;

import java.math.BigInteger;

public class KeyDomain {
	
	private BigInteger sharedSecret;
	private String message;
	
	public BigInteger getSharedSecret() {
		return sharedSecret;
	}
	public void setSharedSecret(BigInteger sharedSecret) {
		this.sharedSecret = sharedSecret;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
