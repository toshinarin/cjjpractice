
public class PrimeNumbers {
	private static final boolean DEBUG = true;
	private static final int LIST_LENGTH = 1000000;

	public static void print(String s) {
		if (DEBUG == false)
			return;
		System.out.println(s);
	}
	
	public static int[] getPrimeNumbers() {
		boolean[] flags = new boolean[LIST_LENGTH];
		int[] ret = new int[LIST_LENGTH];

		int a = 0, i = 0, j = 0;
		for (a = 2; a < LIST_LENGTH; a++) {
			if (flags[a] == true) {
				continue;
			}
			ret[j] = a;
			j++;

			for (i = a; i < LIST_LENGTH; i += a) {
				flags[i] = true;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		print("########### Start!! ############");
		try {
			int[] primeNumbers = getPrimeNumbers();
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
