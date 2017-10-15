package test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	
	Calendar cal=Calendar.getInstance();
	int secondPassed=0;
	RandomTest r=new RandomTest();
	Timer myTimer=new Timer();
	TimerTask task=new TimerTask(){
		public void run(){
//			r.testRandom();
//			secondPassed++;
//			System.out.println("second passed "+secondPassed);
		}
	};
	
	public void start(){
		//the above task one min
//		myTimer.scheduleAtFixedRate(task, 1000, 1000);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime=df.format(cal.getInstance().getTime());
		long startTime = Timestamp.valueOf("2017-09-30 01:13:55").getTime();
//		long startTime = Timestamp.valueOf(currentTime).getTime();
		
		Date d = new Date(startTime);
		System.out.println(d);
//		myTimer.schedule(task,d );
//		System.out.println(currentTime);
	}
	
	public static void main(String[] args){
		
		
		TimerTest timer=new TimerTest();
		timer.start();
	}
	
}
