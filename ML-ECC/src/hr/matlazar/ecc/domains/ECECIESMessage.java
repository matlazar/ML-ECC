package hr.matlazar.ecc.domains;

import hr.matlazar.ecc.arithmetic.PointEC;

public class ECECIESMessage {

	public String message;
	public PointEC R;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public PointEC getR() {
		return R;
	}
	public void setR(PointEC r) {
		R = r;
	}


}