import java.io.*;

public class ASmall {
    static final boolean DEBUG = false;
    static int mNumCase = 0;
    static BufferedReader mBr = null;
    static PrintWriter mPw = null;

    public static void print(String s) {
	if (DEBUG == false) return;
	System.out.println(s);
    }

    public static String calc(int n, int k) {
	print("N={" + n + "}, K={" + k + "}");
	if (n > 30) {
	    print("too many snappers");
	    return "OFF";
	}
	boolean[] snappers = new boolean[n];
	int i = 0, j = 0;
	for (i = 0; i < n; i++) {
	    snappers[i] = false;
	}

	boolean rSnapper = false;
	for (i = 0; i < k; i++) {
	    rSnapper = snappers[0];
	    snappers[0] = !snappers[0];
	    for (j = 1; j < n; j++) {
		if (rSnapper == false) {
		    break;
		}
		rSnapper = snappers[j];
		snappers[j] = !snappers[j];
	    }
	}
	for (i = 0; i < n; i++) {
	    if (snappers[i] == false) {
		break;
	    }
	}
	print("power is supplied to: {" + i +"}");
	if (i == n) {
	    return "ON";
	}
	return "OFF";
    }

    public static int openInput(String fileName) {
	try {
	    FileReader fr = new FileReader(fileName);
	    mBr = new BufferedReader(fr);
	} catch(Exception e) {
	    return -1;
	}
	return 0;
    }

    public static void closeInput() {
	try {
	    mBr.close();
	} catch(Exception e) {}
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
	} catch(Exception e) {}
    }

    public static String readLine() {
	String ret = null;
	try {
	    ret = mBr.readLine();
	} catch(Exception e) {
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
	while((tmpStr = readLine()) != null) {
	    strArray = tmpStr.split(" ");
	    if (strArray.length != 2) {
		print("failed to parse test case.");
		return;
	    }
	    ans = calc(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]));
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
	if (file.exists() == false || file.isFile() == false || file.canWrite() == false) {
	    return false;
	}
	return true;
    }

    public static void main (String[] args) {
	String fileName = null;
	try {
	    fileName = args[0];
	    print("Open file: {" +  fileName + "}");
	    exec(fileName);
	} catch(Exception e) {
	}
	if (fileName == null) {
	    print("File name must be specified.");
	    return;
	}
	print("Hello World!!");
    }
}