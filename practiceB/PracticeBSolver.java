import java.io.*;

public class PracticeBSolver {
	static final boolean DEBUG = true;
	static int mNumCase = 0;
	static BufferedReader mBr = null;
	static PrintWriter mPw = null;

	public static void print(String s) {
		if (DEBUG == false)
			return;
		System.out.println(s);
	}

	public static int openInput(String fileName) {
		try {
			FileReader fr = new FileReader(fileName);
			mBr = new BufferedReader(fr);
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public static void closeInput() {
		try {
			mBr.close();
		} catch (Exception e) {
		}
	}

	public static int openOutput(String fileName) {
		File f = new File(fileName);
		if (canWrite(f) == false) {
			print("failed to prepare out file.");
			return -1;
		}
		try {
			mPw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public static void closeOutput() {
		try {
			mPw.close();
			mPw = null;
		} catch (Exception e) {
		}
	}

	public static String readLine() {
		String ret = null;
		try {
			ret = mBr.readLine();
		} catch (Exception e) {
		}
		return ret;
	}

	public static int writeLine(String str) {
		try {
			mPw.println(str);
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public static String calc(long R, long k, int N, long[] groups) {
		print("R={" + R + "}, k={" + k + "}, N={" + N + "}");
		long i = 0, a = 0, c = 0, pax = 0, tmpPax = 0, total = 0;
		int b = 0;

		for (a = 0; a < R; a++) {
			pax = 0;
			for (c = 0; c < N; c++) {
				if ((tmpPax = pax + groups[b]) > k)
					break;
				pax = tmpPax;
				b++;
				if (b == N)
					b = 0;
			}
			total += pax;
		}
		return "" + total;
	}

	public static void exec(String fileName) {
		if (openInput(fileName) != 0) {
			print("failed to open input.");
			return;
		}
		if (openOutput("out.txt") != 0) {
			print("failed to open output.");
			closeInput();
			return;
		}

		String tmpStr = null;
		String[] strArray = null;
		tmpStr = readLine();
		if (tmpStr == null) {
			return;
		}
		mNumCase = Integer.parseInt(tmpStr);

		int numCase = 0;
		String ans = null;
		String out = null;
		long R = 0, k = 0;
		int N = 0;
		int i = 0, j = 0;
		for (i = 0; i < mNumCase; i++) {
			tmpStr = readLine();
			if (tmpStr == null)
				break;
			strArray = tmpStr.split(" ");
			if (strArray.length != 3) {
				print("failed to parse test case.");
				return;
			}
			R = Long.parseLong(strArray[0]);
			k = Long.parseLong(strArray[1]);
			N = Integer.parseInt(strArray[2]);

			tmpStr = readLine();
			if (tmpStr == null)
				break;
			strArray = tmpStr.split(" ");
			if (strArray.length != N) {
				print("failed to parse test case. Group total doesn't match.");
				return;
			}
			long[] groups = new long[N];
			for (j = 0; j < N; j++) {
				groups[j] = Long.parseLong(strArray[j]);
			}
			ans = calc(R, k, N, groups);

			out = "Case #" + (numCase + 1) + ": " + ans;
			print(out);
			if (writeLine(out) != 0) {
				break;
			}
			numCase++;
		}
		print("number of parsed case: {" + numCase + "}");
		closeInput();
		closeOutput();
	}

	private static boolean canWrite(File file) {
		if (file.exists() == false || file.isFile() == false
				|| file.canWrite() == false) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String fileName = null;
		try {
			fileName = args[0];
			print("Open file: {" + fileName + "}");
			exec(fileName);
		} catch (Exception e) {
			print("failed to execute. {" + e + "}");
		}
		if (fileName == null) {
			print("File name must be specified.");
			return;
		}
		print("Solved!!");
	}
}