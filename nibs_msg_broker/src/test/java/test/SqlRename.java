package test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;

public class SqlRename {
	
	public static void main(String[] args) throws IOException {
		File[] files = new File("D:/pjt/nibs_msg_broker/batch_app/sh/src").listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".sql");
			}
		});

		BufferedReader in;
		BufferedWriter out;
		String line;
		Matcher mat;
		int fromIdx, toIdx;
		
		for (File file : files) {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			
			while ((line = in.readLine()) != null) {
				if ((fromIdx = line.toUpperCase().indexOf("JIJUM")) != -1) {
					line.replaceAll("(jijum)", "BRANCH");
				}
			}
		}
	}
	
}