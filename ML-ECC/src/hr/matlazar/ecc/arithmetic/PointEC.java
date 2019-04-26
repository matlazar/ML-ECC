package hr.matlazar.ecc.arithmetic;

import java.math.BigInteger;

public class PointEC {

	private BigInteger x;
	private BigInteger y;
	private boolean onCurve;
	
	public PointEC (BigInteger x, BigInteger y, boolean onCurve) {
		this.x = x;
		this.y = y;
		this.onCurve = onCurve;
	}
	
	public PointEC(BigInteger x, BigInteger y, BigInteger a, BigInteger b, BigInteger p) {
		super();
		this.x = x;
		this.y = y;
		this.onCurve = checkIfOnCruve(x, y, a, b, p);
	}

	public BigInteger getX() {
		return x;
	}

	public void setX(BigInteger x) {
		this.x = x;
	}

	public BigInteger getY() {
		return y;
	}

	public void setY(BigInteger y) {
		this.y = y;
	}

	public boolean isOnCurve() {
		return onCurve;
	}

	public void setOnCurve(boolean onCurve) {
		this.onCurve = onCurve;
	}

	public static boolean checkIfOnCruve(BigInteger x, BigInteger y, BigInteger a, BigInteger b, BigInteger p) {
		BigInteger ySide = y.pow(2).mod(p);
		BigInteger xSide = (x.pow(3).add(a.multiply(x)).add(b)).mod(p);
		if (ySide.equals(xSide)) {
			return true;
		}
		return false;
	}
}
