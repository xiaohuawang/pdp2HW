package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date currentDate = new Date();
		final long reqHoursInMillis = 1 * 60 * 60 * 1000;  // change 1 with required hour
		Date newDate = new Date(currentDate.getTime() + reqHoursInMillis);
		System.out.println(dateFormat.format(newDate));

	}

}
