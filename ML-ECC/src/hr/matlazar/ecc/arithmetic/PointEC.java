package hr.matlazar.ecc.arithmetic;

import java.math.BigInteger;

public class PointEC {

	private static BigInteger p = new BigInteger("23");
	private static BigInteger a = new BigInteger("0");
	private static BigInteger b = new BigInteger("7");

	private BigInteger x;
	private BigInteger y;
	private boolean onCurve;

	public PointEC(BigInteger x, BigInteger y) {
		super();
		this.x = x;
		this.y = y;
		this.onCurve = checkIfOnCruve(x, y);
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

	public static boolean checkIfOnCruve(BigInteger x, BigInteger y) {
		BigInteger ySide = y.pow(2).mod(p);
		BigInteger xSide = (x.pow(3).add(a.multiply(x)).add(b)).mod(p);
		if (ySide.equals(xSide)) {
			return true;
		}
		return false;
	}
}
