package mapreduce.longestwords;

import java.util.Arrays;

import org.apache.hadoop.io.Text;
/**
 * Converts strings
 * @author oleh
 *
 */
public class StringUtils {
	/**
	 * Creates a string of Iterable <Text> and separate them by separator 
	 * @param values
	 * @param separator
	 * @return string
	 */
	public static void main(String[] args) {
		 String text = "What  human, contrivance could do that?";
System.out.println(Arrays.toString(breakLineToWords(text)));
		
	}
	public static String stringBuilder(Iterable<Text> values, String separator) {		
		StringBuffer buffer=new StringBuffer();
		for (Text string : values) {
			buffer.append(string.toString()).append(separator);
		}
		return buffer.toString();
	}
	/**
	 * Breaks line to words
	 * @param line
	 * @return String[]
	 */
	public static String[] breakLineToWords(String line) {
		line=line.replaceAll("[!?.,]", "");	
		String[] words = line.split("\\s+");
		return words;
	}

}
