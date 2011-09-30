import java.io.*;
import java.util.ArrayList;

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

	private static boolean canWrite(File file) {
		if (file.exists() == false || file.isFile() == false
				|| file.canWrite() == false) {
			return false;
		}
		return true;
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

	private static final int LIST_LENGTH = 1000000;
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
	
	static int[] mP = null;
	static int[] mR = null;

	public static void init(int n) {
		mP = new int[n];
		mR = new int[n];
		for (int i = 0; i < n; i++) {
			mP[i] = i;
			mR[i] = 0;
		}
	}

	public static int find(int x) {
		if (mP[x] == x) {
			return x;
		} else {
			return mP[x] = find(mP[x]);
		}
	}
	
	public static void unite(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) return;
		
		if (mR[x] < mR[y]) {
			mP[x] = y;
		} else {
			mP[y] = x;
			if (mR[x] == mR[y]) mR[x]++;
		}
	}
	
	public static boolean same(int x, int y) {
		return find(x) == find(y);
	}

	public static int getNearPrimeNumberIndex(int x, ArrayList<Integer> pNums) {
		int length = pNums.size();
		int max = pNums.get(length - 1); 
		if (x > max) return 0;

		int index = -1;		
		for(int i = x; i <= max; i++) {
			index = pNums.indexOf(i);
			if (index >= 0) break;
		}
		return index;
	}
	
	public static String calc(long A, long B, long P) {
		print("A={" + A + "}, B={" + B + "}, P={" + P + "}");
		long tempNum = B - A + 1;
		if (tempNum > Integer.MAX_VALUE) {
			return "0";
		}
		int num = (int)tempNum;
		// can not unite any groups or can not solve the test case.
		if (P > LIST_LENGTH || P > Integer.MAX_VALUE) {
			return num + "";
		}
		init(num);
		ArrayList<Integer> pNums = getPrimeNumbers();
		int index = getNearPrimeNumberIndex((int)P, pNums);
		if (index == -1) return num + "";
		int length = pNums.size();
		int pNum = 0;
		for (int i = index; i < length; i++) {
			pNum = pNums.get(i);
			if (pNum > num) {
				break;
			}
			for (int j = 0; j < pNum; j++) {
				if ((A + j) % pNum == 0 && j + pNum < num) {
					for (int k = j + pNum; k < num; k += pNum) {
						unite(j, k);
					}
				}
			}
		}
		int ret = 0;
		for (int l = 0; l < num; l++) {
			if (mP[l] == l) {
				ret++;
			}
		}

		return ret + "";
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
		long A = 0, B = 0, P = 0;

		int i = 0;
		for (i = 0; i < mNumCase; i++) {
			tmpStr = readLine();
			if (tmpStr == null)
				break;
			strArray = tmpStr.split(" ");
			if (DEBUG && strArray.length != 3) {
				print("failed to parse test case.");
				return;
			}
			A = Long.parseLong(strArray[0]);
			B = Long.parseLong(strArray[1]);
			P = Long.parseLong(strArray[2]);

			ans = calc(A, B, P);

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

	public static void main(String[] args) {
		print("########### Start!! ############");
		String fileName = null;
		try {
			fileName = args[0];
			print("Open file: {" + fileName + "}");
			exec(fileName);
		} catch (Exception e) {
			print("failed to execute. error: {" + e + "}");
			e.printStackTrace();
		}
		if (fileName == null) {
			print("File name must be specified.");
			return;
		}
		print("########## Finished!! ##########");
	}
}