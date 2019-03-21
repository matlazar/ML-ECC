package hr.matlazar.ecc.arithmetic;

import java.math.BigInteger;

public class ECCArithmetic {

	private static BigInteger p = new BigInteger("23");
	private static BigInteger a = new BigInteger("0");

	public static PointEC add(PointEC t1, PointEC t2) {
		BigInteger lambda = null;
		BigInteger x1 = null;
		BigInteger y1 = null;
		;

		if (t1.getX().equals(t2.getX()) && t1.getY().equals(t2.getY())) {
			return dbl(t1);
		}

		if (t1.getX().equals(new BigInteger("0")) && t1.getY().equals(new BigInteger("0"))) {
			return t2;
		}

		if (t2.getX().equals(new BigInteger("0")) && t2.getY().equals(new BigInteger("0"))) {
			return t1;
		}

		if (checkInfinityPoint(t1.getX(), t2.getX())) {

			lambda = ((t2.getY().subtract(t1.getY())).multiply((t2.getX().subtract(t1.getX())).modInverse(p))).mod(p);
			x1 = (lambda.pow(2).subtract(t1.getX()).subtract(t2.getX())).mod(p);
			y1 = (lambda.multiply(t1.getX().subtract(x1)).subtract(t1.getY())).mod(p);

		} else {
			lambda = new BigInteger("0");
			x1 = new BigInteger("0");
			y1 = new BigInteger("0");
		}

		PointEC tRez = new PointEC(x1, y1);

		return tRez;

	}

	public static PointEC dbl(PointEC t) {

		BigInteger lambda = null;
		BigInteger x1 = null;
		BigInteger y1 = null;
		;

		if (t.getX().equals(new BigInteger("0")) && t.getY().equals(new BigInteger("0"))) {
			return t;
		}

		if (checkInfinityPoint(t.getY())) {

			lambda = ((t.getX().pow(2).multiply(new BigInteger("3")).add(a))
					.multiply(t.getY().multiply(new BigInteger("2")).modInverse(p))).mod(p);
			x1 = (lambda.pow(2).subtract(t.getX().multiply(new BigInteger("2")))).mod(p);
			y1 = ((lambda.multiply(t.getX().subtract(x1))).subtract(t.getY())).mod(p);

		} else {

			lambda = new BigInteger("0");
			x1 = new BigInteger("0");
			y1 = new BigInteger("0");

		}

		PointEC tRez = new PointEC(x1, y1);
		return tRez;
	}

	public static PointEC mul(BigInteger n, PointEC ponSum) {
		PointEC r = new PointEC(new BigInteger("0"), new BigInteger("0"));
		PointEC p = ponSum;
		byte[] bytes = n.toByteArray();
		boolean[] bit = getBitArray(bytes);
		System.out.println(bit.length);
		for (int i = bit.length - 1; i >= 0; i--) {
			if (bit[i])
				r = add(r, p);
			p = dbl(p);
		}
		return r;
	}

	private static boolean checkInfinityPoint(BigInteger... numbers) {
		BigInteger getResult = null;
		if (numbers.length == 2) {
			getResult = numbers[0].subtract(numbers[1]);
		} else if (numbers.length == 1) {
			getResult = numbers[0].multiply(new BigInteger("2"));
		} else {
			System.out.println("Najviše dvije toèke se smiju unijeti");
			return false;
		}

		if (getResult.equals(new BigInteger("0"))) {
			return false;
		}
		return true;
	}

	private static boolean[] getBitArray(byte[] bytes) {

		boolean[] bits = new boolean[bytes.length * 8];
		for (int i = 0; i < bytes.length * 8; i++) {
			if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
				bits[i] = true;
		}
		return bits;
	}
}
