package test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.Patient;

public class PatientGeneratorTest {

	public List<Date> arrivalTimeList = new ArrayList<Date>();
	public List<Double> treatmentTimeList = new ArrayList<Double>();
	public List<Integer> urgencyLevelList = new ArrayList<Integer>();
	public final int totalPatientNumber = 10;
	public final int highestAgencyLevel = 3;
	Random r = new Random();

	// treatmentTime from 10s to 15s
	public final int treatmentSec = 1 * 5 * 1000;
	Calendar cal = Calendar.getInstance();

	// set patient constructor
	public List<Patient> creatPatient() {
		List<Patient> patientList = new ArrayList<Patient>();

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
			// System.out.println(urgencyLevel);
		}
	}

	// create arrivalTime and treatementTime;
	public void createArrivalTime() {

		for (int i = 0; i < totalPatientNumber; i++) {
			// long startTime = Timestamp.valueOf("2013-01-01
			// 00:00:00").getTime();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = df.format(cal.getInstance().getTime());
			long startTime = Timestamp.valueOf(currentTime).getTime();
			long unixtime = (long) (startTime + r.nextDouble() * 24 * 60 * 60 * 1000);
			// long unixtime = (long) (startTime + r.nextDouble() * 24 * 60 * 60
			// * 1000);
			Date d = new Date(unixtime);
			arrivalTimeList.add(d);
			// System.out.println(d);
		}
	}

	public void createTreatmentTime() {

		for (int i = 0; i < totalPatientNumber; i++) {
			double treatmentTime = r.nextInt(treatmentSec) + 1*10*1000;
			treatmentTimeList.add(treatmentTime);
			// System.out.println(treatmentTime);
		}
	}
	// create treatmentTime;

}
