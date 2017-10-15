package com;

import java.util.Date;

public class Patient implements Comparable<Patient> {

	private int patientId;
	private int urgencyLevel;
	private Date arrivalTime;
	private double treatmentTime;
	private long pWaitingTime;

	public Patient(Date arrivalTime, double treatmentTime, int urgencyLevel) {
		arrivalTime = this.arrivalTime;
		treatmentTime = this.treatmentTime;
		urgencyLevel = this.urgencyLevel;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public long getpWaitingTime() {
		return pWaitingTime;
	}

	public void setpWaitingTime(long pWaitingTime) {
		this.pWaitingTime = pWaitingTime;
	}

	public int getUrgencyLevel() {
		return urgencyLevel;
	}

	public void setUrgencyLevel(int urgencyLevel) {
		this.urgencyLevel = urgencyLevel;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getTreatmentTime() {
		return treatmentTime;
	}

	public void setTreatmentTime(double treatmentTime) {
		this.treatmentTime = treatmentTime;
	}

	public long compareTimeDif(Date date1, Date date2) {

		// System.out.println("come compareTimeDif");
		long date1Long = date1.getTime();
		long date2Long = date2.getTime();

		return date2Long - date1Long;
	}

	@Override
	public int compareTo(Patient p) {
		if (this.urgencyLevel > p.getUrgencyLevel()) {
			return 1;
			// return (this.age - p.getAge());
		} else if (this.urgencyLevel < p.getUrgencyLevel()) {
			return -1;
			// return (this.age - p.getAge());
		} else {
			if (this.compareTimeDif(this.getArrivalTime(), p.getArrivalTime()) > 0) {
				return 1;
			} else if (this.compareTimeDif(this.getArrivalTime(), p.getArrivalTime()) < 0) {
				return -1;
			}
		}
		return 0;
	}

}
