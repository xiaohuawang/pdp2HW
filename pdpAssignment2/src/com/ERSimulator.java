package com;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ERSimulator {

//	PatientGeneratorFixTest pg1 = new PatientGeneratorFixTest();
//	PatientGeneratorRandom pg2 = new PatientGeneratorRandom();
	MyPriorityQueue<Patient> patientPqueue = new MyPriorityQueue<Patient>();
	MyPriorityQueue<TreatmentRoom> roomEmptyPqueue = new MyPriorityQueue<TreatmentRoom>();
	MyPriorityQueue<TreatmentRoom> roomOccupiedPqueue = new MyPriorityQueue<TreatmentRoom>();
	MyPriorityQueue<TreatmentRoom> roomTotalPqueue = new MyPriorityQueue<TreatmentRoom>();
	List<Patient> patientList = null;
	List<TreatmentRoom> treatmentRoomList = null;
	List<Long> timeRoomEmptyList = null;
	Timer myTimer = new Timer();

	// constructor
	public ERSimulator() {
	}

	public Date getLocalTime() {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = df.format(cal.getInstance().getTime());
		long localTimeLong = Timestamp.valueOf(currentTime).getTime();
		Date localTime = new Date(localTimeLong);
		// System.out.println("local time= " + localTime);

		return localTime;
	}

	// suppose date2 is the latter time, return the time diff
	public long compareTimeDif(Date date1, Date date2) {

		// System.out.println("come compareTimeDif");
		long date1Long = date1.getTime();
		long date2Long = date2.getTime();

		return date2Long - date1Long;
	}

	public Date datePlusTime(Date date, double time) {

		Date newDate = new Date(date.getTime() + (long) time);
		return newDate;
	}

	public void start() {

		myTimer.schedule(new Simulator(), 0);
	}

	public static void main(String[] args) {

		ERSimulator first = new ERSimulator();
		// first.getLocalTime();
		// first.getPatient();
		// System.out.println("patientlist.size= " + first.patientList.size());
		first.start();
	}

	// internal thread class
	class Simulator extends TimerTask implements Runnable {

		ERSimulator first = new ERSimulator();
		int patientIndex = 0;
		int numberofRoomInput=0;
		
		PatientGenerator pg = null;
		
//		PatientGeneratorRandom pg1=new PatientGeneratorRandom();
//		PatientGeneratorFixTest pg2=new PatientGeneratorFixTest();

		public void run() {
			// start run
			System.out.println("---------------test start---------------");

			final Date startTime = first.getLocalTime();
			Date currentTime = first.getLocalTime();
			this.createTreatmentroomTest();
			String input=this.chooseSimulationType();
				
			//if choose random
			if(input.equals("p")){
				pg= new PatientGeneratorFixTest();
			}
			else if(input.equals("r")){
				pg = new PatientGeneratorRandom();
			}
			
			patientList = pg.creatPatient();
			
			try {
				while (true){
					
					//deal with the situation no new patient come
					if(patientIndex==pg.getTotalPatientNumber()){
						System.out.println("no new patient come");
						//still patient in wating queue
						if(patientPqueue.isEmpty()&&allRoomAvailable()){
							System.out.println("---------------------END---------------------");
							break;
						}
						if(!patientPqueue.isEmpty()){
							roomFullWaiting();
							continue;
						}else{
							roomFirstNotFull();
							continue;
						}							
					}

					if (hasRoomAvailable()) {
						// if at least one room is available
						if (!allRoomAvailable()) {
							// if the next event is room will be empty
							if (first.compareTimeDif(firstAvailableOccupiedRoomTime(),
									patientList.get(patientIndex).getArrivalTime()) > 0) {
								this.roomFirstNotFull();
								// if there's some room avail and the next event
								// is coming a new patient
							} else {
								this.patientFirstNotFull();
							}
							// all the room are empty, the next event has to be
							// new patient come
						} else {
							this.emptyRoomSituation();
						}
					}
					// if there's no room available
					else {
						// patientPqueue.insert(patientList.get(patientIndex));
						// patientIndex++;

						// if there's no waiting queue when all the room is full
						if (patientPqueue.isEmpty()) {
							// if the next event is room will be empty
							// the next event is room dequeue
							if (first.compareTimeDif(firstAvailableOccupiedRoomTime(),
									patientList.get(patientIndex).getArrivalTime()) > 0) {
								this.roomFirstFullNoWaiting();
							
							//if the next event is coming a new patient
							}else{
								this.patientFirstFullNoWaiting();
							}
						}
						// if there's a waiting queue when all the room is full
						else {
							//if the next event is room deque
							if(first.compareTimeDif(firstAvailableOccupiedRoomTime(),
									patientList.get(patientIndex).getArrivalTime()) > 0) {
									roomFullWaiting();
								//if the next event is new patient come to hospital
							}else{
									patientFullWaiting();
							}
						}
					}
				}
				this.cancel();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		// 7 method below to determine the sleep time
		//all room are full, there's patient in the waiting queue
		public void patientFullWaiting() throws InterruptedException{
			
			Thread.sleep(compareTimeDif(first.getLocalTime(), patientList.get(patientIndex).getArrivalTime()));
			System.out.println("come1");
			
			Patient patient=patientList.get(patientIndex);
			patient.setPatientId(patientIndex+1);
			patientPqueue.insert(patient);
			
			System.out.println("local time= " + first.getLocalTime());
			System.out.println("The " + (patientIndex + 1) + " patient come");
			System.out.println(
					"patient.treatmentTime= " + patientList.get(patientIndex).getTreatmentTime() / 1000 + " seconds");
			System.out.println("patient.UrgencyLevel= " + patientList.get(patientIndex).getUrgencyLevel());
			
			this.checkRoomQueue();
			this.checkPatientQueue();
			
			if(patientIndex<pg.getTotalPatientNumber()){
				patientIndex++;
			}
			
		}
		
		//when all the room is full, the new patient will be the first one in the waiting queue
		public void patientFirstFullNoWaiting() throws InterruptedException{
			
			
			Thread.sleep(compareTimeDif(first.getLocalTime(), patientList.get(patientIndex).getArrivalTime()));
			System.out.println("come2");
			
			Patient patient=patientList.get(patientIndex);
			patient.setPatientId(patientIndex+1);
			patientPqueue.insert(patient);
			
			System.out.println("local time= " + first.getLocalTime());
			System.out.println("The " + (patientIndex + 1) + " patient come");
			System.out.println(
					"patient.treatmentTime= " + patientList.get(patientIndex).getTreatmentTime() / 1000 + " seconds");
			System.out.println("patient.UrgencyLevel= " + patientList.get(patientIndex).getUrgencyLevel());
			
			this.checkRoomQueue();
			this.checkPatientQueue();
			
			if (patientIndex < pg.getTotalPatientNumber()) {
				patientIndex++;
			}
		}
		
		//room is not full, new patient come, put the patient into the room
		public void patientFirstNotFull() throws InterruptedException {
			
			Thread.sleep(compareTimeDif(first.getLocalTime(), patientList.get(patientIndex).getArrivalTime()));
			System.out.println("come3");
			
			System.out.println("local time= " + first.getLocalTime());
			System.out.println("The " + (patientIndex + 1) + " patient come");
			System.out.println(
					"patient.treatmentTime= " + patientList.get(patientIndex).getTreatmentTime() / 1000 + " seconds");
			System.out.println("patient.UrgencyLevel= " + patientList.get(patientIndex).getUrgencyLevel());
			
			Patient patient=patientList.get(patientIndex);
			patient.setPatientId(patientIndex+1);
			TreatmentRoom room = roomTotalPqueue.remove();
			room.setPatient(patient);
			room.setAvailable(false);
			room.setWillBeAvailable(
					datePlusTime(first.getLocalTime(), patientList.get(patientIndex).getTreatmentTime()));
			System.out.println("the " + room.getRoomId() + " room will be available at " + room.getWillBeAvailable());
			roomTotalPqueue.insert(room);

			System.out.println("local time =" + first.getLocalTime());
			this.checkRoomQueue();
			this.checkPatientQueue();
			System.out.println();
			
			if(patientIndex<pg.getTotalPatientNumber()){
				patientIndex++;
			}
		}
		
		//the room is full, dequeue the room, and put dequeue the patient into the room
		public void roomFullWaiting() throws InterruptedException {
			
			
			Thread.sleep(compareTimeDif(first.getLocalTime(), firstAvailableOccupiedRoomTime()));
			System.out.println("come4");
			
			int roomId = this.firstAvailableOccupiedRoomId();
			TreatmentRoom room = treatmentRoomList.get(roomId);
			
			//get the patient waiting in the queue
			Patient patient=patientPqueue.remove();
			room.setAvailable(false);
			room.setPatient(patient);
			room.setWillBeAvailable( first.datePlusTime(first.getLocalTime(),patient.getTreatmentTime()));
			
			System.out.println("the " + room.getRoomId() + " will be available at " + room.getWillBeAvailable());
			System.out.println("local time= " + first.getLocalTime());
			this.checkRoomQueue();
			this.checkPatientQueue();
			System.out.println();
			
		}
		
		//room first come, full room, no waiting queue, dequeue the room, waiting for the next patient
		public void roomFirstFullNoWaiting() throws InterruptedException {
			
			Thread.sleep(compareTimeDif(first.getLocalTime(), firstAvailableOccupiedRoomTime()));
			System.out.println("come5");
			
			int roomId = this.firstAvailableOccupiedRoomId();
			TreatmentRoom room = treatmentRoomList.get(roomId);
			roomTotalPqueue.removeSpecificItem(room);
			
			room.setAvailable(true);
			room.setWillBeAvailable(first.getLocalTime());
			room.setPatient(null);
			roomTotalPqueue.insert(room);
			
			System.out.println("one room become empty");
			System.out.println("local time= " + first.getLocalTime());
			System.out.println("the  " + roomId + " room is available");
			this.checkRoomQueue();
			this.checkPatientQueue();
			
			System.out.println();
		}
	
		//room is not full, dequeue a room
		public void roomFirstNotFull() throws InterruptedException {

			Thread.sleep(compareTimeDif(first.getLocalTime(), firstAvailableOccupiedRoomTime()));
			System.out.println("come6");
			// try to get the specific first available room

			int roomId = this.firstAvailableOccupiedRoomId();
			TreatmentRoom room = treatmentRoomList.get(roomId);
			roomTotalPqueue.removeSpecificItem(room);

			room.setAvailable(true);
			room.setWillBeAvailable(first.getLocalTime());
			room.setPatient(null);
			roomTotalPqueue.insert(room);
			// this.checkRoomQueue();

			System.out.println("one room become empty");
			System.out.println("local time= " + first.getLocalTime());
			System.out.println("the  " + roomId + " room is available");
			this.checkRoomQueue();
			this.checkPatientQueue();
			
			System.out.println();
		}
		
		
		public void emptyRoomSituation() throws InterruptedException {
			
//			System.out.println("come empty"); 
			System.out.println("all the rooms are empty");
			System.out.println("local time= " + first.getLocalTime());
			System.out.println("the next patient coming in "
					+ compareTimeDif(first.getLocalTime(), patientList.get(patientIndex).getArrivalTime()) / 1000
					+ " secs");
			System.out.println();
			Thread.sleep(compareTimeDif(first.getLocalTime(), patientList.get(patientIndex).getArrivalTime()));
			System.out.println("the " + (patientIndex + 1) + " patient come");
			System.out.println("local time= " + first.getLocalTime());
			System.out.println(
					"patient.treatmentTime= " + patientList.get(patientIndex).getTreatmentTime() / 1000 + " seconds");
			System.out.println("patient.UrgencyLevel= " + patientList.get(patientIndex).getUrgencyLevel());
			// System.out.println();

			TreatmentRoom room = roomTotalPqueue.remove();
			room.setAvailable(false);
			room.setWillBeAvailable(
					datePlusTime(first.getLocalTime(), patientList.get(patientIndex).getTreatmentTime()));
			
			Patient patient=patientList.get(patientIndex);
			patient.setPatientId(patientIndex+1);
			room.setPatient(patient);
			System.out.println("the " + room.getRoomId() + " will be available at " + room.getWillBeAvailable());
			roomTotalPqueue.insert(room);
			
			this.checkRoomQueue();
			this.checkPatientQueue();	
			System.out.println();
			
			// need to set useRate here
			if(patientIndex<pg.getTotalPatientNumber()){
				patientIndex++;
			}
		}
		
		// check if there's room available
		private boolean hasRoomAvailable() {
			// TODO Auto-generated method
			for (TreatmentRoom room : treatmentRoomList) {
				if (room.isAvailable()) {
					return true;
				}
			}
			return false;
		}

		private boolean allRoomAvailable() {
			// TODO Auto-generated method
			for (TreatmentRoom room : treatmentRoomList) {
				if (!room.isAvailable()) {
					return false;
				}
			}
			return true;
		}

		// get the first available occupiedroom id
		public int firstAvailableOccupiedRoomId() {
			Date nearestDate = null;
			int roomId = 0;
			try {
				nearestDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2099-01-01 00:00:00");
				for (int i = 0; i < treatmentRoomList.size(); i++) {
					// loop all the not available room
					if (!treatmentRoomList.get(i).isAvailable()) {
						if (first.compareTimeDif(nearestDate, treatmentRoomList.get(i).getWillBeAvailable()) < 0) {
							roomId = treatmentRoomList.get(i).getRoomId();
							nearestDate = treatmentRoomList.get(i).getWillBeAvailable();
						}
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("nearest room id= "+roomId);
			return roomId;
		}

		// return the nearest roomAvailable Date for all the oc
		public Date firstAvailableOccupiedRoomTime() {

			Date nearestDate = null;
			try {
				nearestDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2099-01-01 00:00:00");
				for (int i = 0; i < treatmentRoomList.size(); i++) {
					// loop all the not available room
					if (!treatmentRoomList.get(i).isAvailable()) {
						if (first.compareTimeDif(nearestDate, treatmentRoomList.get(i).getWillBeAvailable()) < 0) {
							// treatmentRoomList.get(i).getRoomId();
							nearestDate = treatmentRoomList.get(i).getWillBeAvailable();
						}
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return nearestDate;
		}

		public void patientPQTest() {

			for (int i = 0; i < pg.getTotalPatientNumber(); i++) {
				long timeDif = first.compareTimeDif(first.getLocalTime(), patientList.get(i).getArrivalTime());
				System.out.println("the next patient comming in " + timeDif + " seconds");
				try {
					Thread.sleep(timeDif);
					patientPqueue.insert(patientList.get(i));
					System.out.println();
					System.out.println("this patient emergency= " + patientList.get(i).getUrgencyLevel());
					System.out.println(" this patient arrive time" + patientList.get(i).getArrivalTime());
					System.out.println("patient pq size = " + patientPqueue.getSize());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println();
			System.out.println("zuigao emergency= " + patientPqueue.front().getUrgencyLevel());
			System.out.println("zuigao shijian= " + patientPqueue.front().getArrivalTime());
		}

		// create room Instance
		public List<TreatmentRoom> createRoomInstance() {
			treatmentRoomList = new ArrayList<TreatmentRoom>();
			int roomId = 0;

			System.out.println("pls input number of treatment rooms you want");
			Scanner sc = new Scanner(System.in);
			numberofRoomInput = sc.nextInt();
			for (int i = 0; i < numberofRoomInput; i++) {
				// setup new room constructor
				// boolean isAvailable,Date willBeAvailable,long useRate,int roomId
				// room Id start from 0
				TreatmentRoom room = new TreatmentRoom(true, first.getLocalTime(), 0, 0, roomId);
				roomId++;
				treatmentRoomList.add(room);
				roomTotalPqueue.insert(room);
			}
			this.checkRoomQueue();
			
			return treatmentRoomList;
		}
		
		public String chooseSimulationType() {
			// TODO Auto-generated method stub
			System.out.println("choose the simulation type you want (PreSet/Random)? ");
			System.out.println("pls input (p/r)");
			Scanner sc=new Scanner(System.in);
			String input=sc.nextLine();
			
			return input;
		}

		public void checkRoomQueue() {
			
			List<TreatmentRoom> tempList=new ArrayList<TreatmentRoom>();
			
			for (int i = 0; i < numberofRoomInput; i++) {
				System.out.println(roomTotalPqueue.front().getRoomId() + "             " + roomTotalPqueue.front().isAvailable()
								+"             ");
				TreatmentRoom room = roomTotalPqueue.remove();
				tempList.add(room);
			}
			
			for (int i = 0; i < numberofRoomInput; i++) {
				roomTotalPqueue.insert(tempList.get(i));
			}
		}
		
		public void checkPatientQueue() {
			
			List<Patient> patientList=new ArrayList<Patient>();
			int patientPqSize=patientPqueue.getSize();
			System.out.println("patient queue size= "+patientPqueue.getSize());
			System.out.println( "patient urgent levle "+"          " + "patient  arrival time"+"                 "
								+ "patient id");
			
			for (int i = 0; i < patientPqSize; i++) {
				System.out.println(patientPqueue.front().getUrgencyLevel()+ "                              " + patientPqueue.front().getArrivalTime()
						+"          "+patientPqueue.front().getPatientId());
				Patient patient = patientPqueue.remove();
				patientList.add(patient);
			}
			
			for (int i = 0; i < patientPqSize; i++) {
				patientPqueue.insert(patientList.get(i));
			}
			System.out.println();
		}
		
		public void createTreatmentroomTest() {

			createRoomInstance();
			System.out.println("create room instance successfully");

		}
	}
}
