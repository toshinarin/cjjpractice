import java.io.*;
import java.util.ArrayList;

public class PrimeNumbers {
	private static final boolean DEBUG = true;
	private static final int LIST_LENGTH = 1000000;

	public static void print(String s) {
		if (DEBUG == false)
			return;
		System.out.println(s);
	}
	
	public static ArrayList<Integer> getPrimeNumbers() {
		boolean[] flags = new boolean[LIST_LENGTH];
		ArrayList<Integer> ret = new ArrayList<Integer>();

		int a = 0, i = 0, j = 0;
		for (a = 2; a < LIST_LENGTH; a++) {
			if (flags[a] == true) {
				continue;
			}
			ret.add(a);

			for (i = a; i < LIST_LENGTH; i += a) {
				flags[i] = true;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		print("########### Start!! ############");
		try {
			ArrayList<Integer> primeNumbers = getPrimeNumbers();
			int i = 0;
			int length = primeNumbers.size();
			for (i = 0; i < length; i++) {
				print(primeNumbers.get(i) + "");
			}
		} catch (Exception e) {
			print("failed to execute. error: {" + e + "}");
			e.printStackTrace();
		}
		print("########## Finished!! ##########");
	}
}
