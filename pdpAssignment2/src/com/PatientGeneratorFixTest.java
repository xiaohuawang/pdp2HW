package com;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PatientGeneratorFixTest implements PatientGenerator {

	public List<Date> arrivalTimeList = new ArrayList<Date>();
	public List<Double> treatmentTimeList = new ArrayList<Double>();
	public List<Integer> urgencyLevelList = new ArrayList<Integer>();
	public final int totalPatientNumber = 7;
	// public final int totalPatientNumber = 2;
	public final int highestAgencyLevel = 3;
//	private int totalPatientNumber;

	Random r = new Random();

	// treatmentTime from 10s to 15s
	public final int treatmentSec = 1 * 5 * 1000;
	Calendar cal = Calendar.getInstance();

	public int getTotalPatientNumber() {
		return totalPatientNumber;
	}

	// set patient constructor
	public List<Patient> creatPatient() {
		List<Patient> patientList = new ArrayList<Patient>();
		createArrivalTime();
		createUrgencyLevel();
		createTreatmentTime();

		System.out.println("total patient num= " + totalPatientNumber);
		for (int i = 0; i < totalPatientNumber; i++) {
			Patient patient = new Patient(arrivalTimeList.get(i), treatmentTimeList.get(i), urgencyLevelList.get(i));
			patient.setArrivalTime(arrivalTimeList.get(i));
			System.out.println("treat time= " + treatmentTimeList.get(i)/1000);
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

	// create arrivalTime (patients arrive every 5s)
	public void createArrivalTime() {

		for (int i = 0; i < totalPatientNumber; i++) {

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = df.format(cal.getInstance().getTime());
			long startTime = Timestamp.valueOf(currentTime).getTime();
			long unixtime = startTime + 5 * 1000 * (i + 1);

			Date d = new Date(unixtime);
			arrivalTimeList.add(d);
		}
	}

	// set the fixed treatmentTime
	public void createTreatmentTime() {

		treatmentTimeList.add((double) 20 * 1000);
		treatmentTimeList.add((double) 15 * 1000);
		treatmentTimeList.add((double) 13 * 1000);
		treatmentTimeList.add((double) 17 * 1000);
		treatmentTimeList.add((double) 20 * 1000);
		treatmentTimeList.add((double) 12 * 1000);
		treatmentTimeList.add((double) 15 * 1000);

	}

}
