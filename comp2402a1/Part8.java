package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Part8 {

	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		String maxSeen = null;
		String lastOutput = null;
		String line;

		java.util.function.BiFunction<String,String,Integer> cmp = (a, b) -> {
			if (a == null && b == null) return 0;
			if (a == null) return -1;
			if (b == null) return 1;
			try {
				long ai = Long.parseLong(a);
				long bi = Long.parseLong(b);
				return Long.compare(ai, bi);
			} catch (NumberFormatException e) {
				return a.compareTo(b);
			}
		};

		while ((line = r.readLine()) != null) {
			boolean allPrevSmaller = (maxSeen == null) || (cmp.apply(line, maxSeen) > 0);
			boolean smallerThanLastOutput = (lastOutput != null) && (cmp.apply(line, lastOutput) < 0);

			if (allPrevSmaller || smallerThanLastOutput) {
				w.println(line);
				lastOutput = line;
			}

			if (maxSeen == null || cmp.apply(line, maxSeen) > 0) {
				maxSeen = line;
			}
		}
	}

	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 1e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
