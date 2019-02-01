package hr.matlazar.ecc.domains;


public class ElGamalSend {

	private String sharedSecret;
	private String R;
	
	public String getSharedSecret() {
		return sharedSecret;
	}
	public void setSharedSecret(String sharedSecret) {
		this.sharedSecret = sharedSecret;
	}
	public String getR() {
		return R;
	}
	public void setR(String r) {
		R = r;
	}
	
	
}
