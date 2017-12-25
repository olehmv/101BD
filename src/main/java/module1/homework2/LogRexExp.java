package module1.homework2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LogRexExp{
	
	private static final String LOGENTRYPATTERN = "^([ip\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
    private static final Pattern PATTERN = Pattern.compile(LOGENTRYPATTERN);
    public static final int NUM_FIELDS = 9;
    public static ApacheLog parseApacheLog(String line) throws IllegalArgumentException{
    	Matcher matcher = PATTERN.matcher(line);
	    if (!matcher.matches()||NUM_FIELDS != matcher.groupCount()) {
	    	throw new IllegalArgumentException("Bad log entry");
	    }
	    return new ApacheLog(matcher.group(1), matcher.group(4), matcher.group(5), matcher.group(6), matcher.group(7) , matcher.group(9));
	    
    }
}