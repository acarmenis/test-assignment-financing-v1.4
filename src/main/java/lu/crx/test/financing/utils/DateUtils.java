package lu.crx.test.financing.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.concurrent.TimeUnit;

/**
 * @author Andreas Karmenis
 * @created 13/01/2024 - 9:58 PM
 * @project test-assignment-financing-v1.4
 */
public class DateUtils {
    public static Period getPeriod(LocalDateTime then, LocalDateTime now) {
        return Period.between(then.toLocalDate(), now.toLocalDate());
    }

    public static Duration getDuration(LocalDateTime then, LocalDateTime now) {
        return Duration.between(now, then);
    }

    public static String convertToHumanDate(long millisec){
        return  String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millisec),
                TimeUnit.MILLISECONDS.toMinutes(millisec) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisec)),
                TimeUnit.MILLISECONDS.toSeconds(millisec) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisec)));
    }


}
