import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat();

    public static String getTimestamp() {
        return dateFormat.format(new Date());
    }
}
