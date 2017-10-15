package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.MyPriorityQueue;
import com.TreatmentRoom;

public class TestRoomQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyPriorityQueue<TreatmentRoom> roomTotalQueue = new MyPriorityQueue<TreatmentRoom>();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date currentDate = new Date();
		final long reqHoursInMillis = 1 * 60 * 60 * 1000;  // change 1 with required hour
		Date newDate1 = new Date(currentDate.getTime() + reqHoursInMillis);
		
//		System.out.println("currentDate ="+currentDate);
		
//		boolean isAvailable, Date willBeAvailable, long useRate, long totalUsedTine

		TreatmentRoom room0=new TreatmentRoom(true,currentDate,0,0,0);
		TreatmentRoom room1=new TreatmentRoom(true,currentDate,0,0,1);
		TreatmentRoom room2=new TreatmentRoom(true,currentDate,0,0,2);
		TreatmentRoom room3=new TreatmentRoom(true,currentDate,0,0,3);
//		TreatmentRoom room0=new TreatmentRoom(false,newDate1,0.2,0,0);
//		TreatmentRoom room1=new TreatmentRoom(true,currentDate,0.5,0,1);
//		TreatmentRoom room2=new TreatmentRoom(true,currentDate,0.1,0,2);
//		TreatmentRoom room3=new TreatmentRoom(false,currentDate,0.3,0,3);
		
		
		roomTotalQueue.insert(room0);
		roomTotalQueue.insert(room1);
		roomTotalQueue.insert(room2);
		roomTotalQueue.insert(room3);
		
		roomTotalQueue.remove();
		
//		roomTotalQueue.checkAllElement();
//		System.out.println();
//		
//		TreatmentRoom theRoom=roomTotalQueue.remove();
//		roomTotalQueue.checkAllElement();
//		System.out.println();
//		theRoom.setAvailable(false);
//		theRoom.setWillBeAvailable(newDate1);
//		roomTotalQueue.insert(theRoom);
//		roomTotalQueue.checkAllElement();
		
		System.out.println(roomTotalQueue.front().getRoomId());
		roomTotalQueue.remove();
		System.out.println(roomTotalQueue.front().getRoomId());
		roomTotalQueue.remove();
		System.out.println(roomTotalQueue.front().getRoomId());
		roomTotalQueue.remove();
//		System.out.println(roomTotalQueue.front().getRoomId());
//		roomTotalQueue.remove();
	}

}
