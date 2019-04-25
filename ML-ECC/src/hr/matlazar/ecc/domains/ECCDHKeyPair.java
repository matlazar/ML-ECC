package hr.matlazar.ecc.domains;

import hr.matlazar.ecc.arithmetic.PointEC;

public class ECCDHKeyPair {

	private PointEC publicKeyPoint;
	private String privateKey;

	public PointEC getPublicKeyPoint() {
		return publicKeyPoint;
	}
	public void setPublicKeyPoint(PointEC publicKeyPoint) {
		this.publicKeyPoint = publicKeyPoint;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}



}