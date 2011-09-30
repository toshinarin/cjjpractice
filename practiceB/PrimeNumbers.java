
public class PrimeNumbers {
	private static final boolean DEBUG = true;
	private static final int LIST_LENGTH = 10000;

	public static void print(String s) {
		if (DEBUG == false)
			return;
		System.out.println(s);
	}
	
	public static long[] getPrimeNumbers(long num) {
		long[] ret = new long[LIST_LENGTH];

		long a = 0;
		int i = 0;
		boolean isPrime = true;
		for (a = 2; a < num; a++) {
			isPrime = true;
			for (i = 0; i < LIST_LENGTH && ret[i] != 0; i++) {
				if (a % ret[i] == 0) {
					isPrime = false;
					break;
				}
			}
			if (i >= LIST_LENGTH) {
				break;
			}
			if (isPrime) {
				ret[i] = a;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		print("########### Start!! ############");
		try {
			long[] primeNumbers = getPrimeNumbers(1000000L);
			int i = 0;
			for (i = 0; i < LIST_LENGTH && primeNumbers[i] != 0; i++) {
				print(primeNumbers[i] + "");
			}
		} catch (Exception e) {
			print("failed to execute. error: {" + e + "}");
			e.printStackTrace();
		}
		print("########## Finished!! ##########");
	}
}
