package hr.matlazar.ecc.domains;

import java.math.BigInteger;

public class ElGamalSend {

	private BigInteger sharedSecret;
	private BigInteger R;
	
	public BigInteger getSharedSecret() {
		return sharedSecret;
	}
	public void setSharedSecret(BigInteger sharedSecret) {
		this.sharedSecret = sharedSecret;
	}
	public BigInteger getR() {
		return R;
	}
	public void setR(BigInteger r) {
		R = r;
	}
	
	
}
