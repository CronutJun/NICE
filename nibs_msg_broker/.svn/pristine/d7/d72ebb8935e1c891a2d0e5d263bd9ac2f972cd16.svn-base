package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintSqlFileList {
	
	public static void main(String[] args) throws IOException {
		File[] files = new File("D:/pjt/nibs_msg_broker/batch_app/sh").listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".sh");
			}
		});

		BufferedReader in;
		String line;
		Matcher mat;
		
		for (File file : files) {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			while ((line = in.readLine()) != null) {
				mat = Pattern.compile("[\\w_]+.sql").matcher(line);
				
				if (mat.find()) {
					System.out.println(mat.group());
				}
			}
		}
	}
	
}