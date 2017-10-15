package com;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PatientGeneratorRandom implements PatientGenerator {

	public List<Date> arrivalTimeList = new ArrayList<Date>();
	public List<Integer> treatmentTimeList = new ArrayList<Integer>();
	public List<Integer> urgencyLevelList = new ArrayList<Integer>();
	public final int totalPatientNumber = 15;
	public final int highestAgencyLevel = 5;
//	private int totalPatientNumber;
	Random r = new Random();

	// arrivalRange 15s
	public final int arrivalSecRange = 5 * 1000;

	// treatmentRange 10s
	public final int treatmentSecRange = 10 * 1000;
	Calendar cal = Calendar.getInstance();

	public int getTotalPatientNumber() {
		return totalPatientNumber;
	}

	// set patient constructor
	public List<Patient> creatPatient() {
		System.out.println("create patient");
		System.out.println("Total number of patient = " + totalPatientNumber);
		List<Patient> patientList = new ArrayList<Patient>();

		// define all the variable
		createArrivalTime();
		createUrgencyLevel();
		createTreatmentTime();

		for (int i = 0; i < totalPatientNumber; i++) {
			Patient patient = new Patient(arrivalTimeList.get(i), treatmentTimeList.get(i), urgencyLevelList.get(i));
			patient.setArrivalTime(arrivalTimeList.get(i));
			patient.setTreatmentTime(treatmentTimeList.get(i));
			patient.setUrgencyLevel(urgencyLevelList.get(i));
			patientList.add(patient);
		}
		return patientList;
	}

	// define urgent Level
	public void createUrgencyLevel() {

		for (int i = 0; i < totalPatientNumber; i++) {
			int urgencyLevel = r.nextInt(highestAgencyLevel) + 1;
			urgencyLevelList.add(urgencyLevel);

		}
	}

	// create arrivalTime(next 2s-7s)
	public void createArrivalTime() {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = df.format(cal.getInstance().getTime());
		long startTime = Timestamp.valueOf(currentTime).getTime();

		for (int i = 0; i < totalPatientNumber; i++) {

			long unixtime = (long) (startTime + r.nextDouble() * arrivalSecRange + 2000);
			startTime = unixtime;
			Date d = new Date(unixtime);
			arrivalTimeList.add(d);
		}
	}

	// create treatmentTime (10s-20s)
	public void createTreatmentTime() {
		for (int i = 0; i < totalPatientNumber; i++) {
			int treatmentTime = (int) r.nextInt(treatmentSecRange) + 10 * 1000;
			treatmentTime = treatmentTime / 1000 * 1000;
			treatmentTimeList.add(treatmentTime);
		}
	}

}