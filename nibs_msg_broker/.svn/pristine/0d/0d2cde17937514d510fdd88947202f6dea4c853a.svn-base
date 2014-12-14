package test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;

public class SqlRename {
	
	public static void main(String[] args) throws IOException {
		File[] files = new File("D:/pjt/nibs_msg_broker/batch_app/sh/src").listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".sql");
			}
		});

		RandomAccessFile f;
		FileInputStream fin;
		FileOutputStream fout;
		BufferedReader in;
		BufferedWriter out;
		String line;
		Matcher mat;
		int fromIdx, toIdx;
		
		for (File file : files) {
			f = new RandomAccessFile(file, "rws");
		}
	}
	
}