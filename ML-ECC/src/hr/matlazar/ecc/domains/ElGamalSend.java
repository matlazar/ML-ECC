package hr.matlazar.ecc.domains;

import java.math.BigInteger;

public class ElGamalSend {

	private String sharedSecret;
	private BigInteger R;
	
	public String getSharedSecret() {
		return sharedSecret;
	}
	public void setSharedSecret(String sharedSecret) {
		this.sharedSecret = sharedSecret;
	}
	public BigInteger getR() {
		return R;
	}
	public void setR(BigInteger r) {
		R = r;
	}
	
	
}
