package com;

import java.util.Date;

public class TreatmentRoom implements Comparable<TreatmentRoom> {

	private long totalUsedTime;
	// after how long time the room will be available
	private long rWaitingTime;
	private double useRate;
	private boolean isAvailable;
	private Patient patient;
	private Date willBeAvailable;
	private int roomId;

	public TreatmentRoom(boolean isAvailable, Date willBeAvailable, double useRate, long totalUsedTine, int roomId) {
		this.isAvailable = isAvailable;
		this.willBeAvailable = willBeAvailable;
		this.useRate = useRate;
		this.totalUsedTime = totalUsedTine;
		this.roomId = roomId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getWillBeAvailable() {
		return willBeAvailable;
	}

	public void setWillBeAvailable(Date willBeAvailable) {
		this.willBeAvailable = willBeAvailable;
	}

	public long getTotalUsedTime() {
		return totalUsedTime;
	}

	public void setTotalUsedTime(long totalUsedTime) {
		this.totalUsedTime = totalUsedTime;
	}

	public long getrWaitingTime() {
		if (!this.isAvailable()) {
			long arrivalTime = patient.getArrivalTime().getTime();
			double treatmentTime = patient.getTreatmentTime();
			long pWaitingTime = patient.getpWaitingTime();
			return arrivalTime + (long) treatmentTime + pWaitingTime;
		} else {
			return 0;
		}
	}

	public void setWaitingTime(long timeRemaining) {
		this.rWaitingTime = timeRemaining;
	}

	public double getUseRate() {
		return useRate;
	}

	public void setUseRate(double useRate) {
		this.useRate = useRate;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public long compareTimeDif(Date date1, Date date2) {
		long date1Long = date1.getTime();
		long date2Long = date2.getTime();

		return date2Long - date1Long;
	}

	public String toString() {
		return String.valueOf(this.getRoomId() + "          ava= " + this.isAvailable);
	}

	@Override
	public int compareTo(TreatmentRoom t) {
		// TODO Auto-generated method stub
		if (this.isAvailable() && t.isAvailable) {
			if (this.roomId < t.getRoomId()) {
				return 1;
			} else if (this.roomId > t.getUseRate()) {
				return -1;
			}
		} else if (this.isAvailable() && !t.isAvailable) {
			return 1;
		} else if (!this.isAvailable() && t.isAvailable) {
			return -1;
		} else if (!this.isAvailable() && !t.isAvailable) {
			if (this.compareTimeDif(this.willBeAvailable, t.willBeAvailable) > 0) {
				return 1;
			} else if (this.compareTimeDif(this.willBeAvailable, t.willBeAvailable) < 0) {
				return -1;
			}
		}
		return 0;
	}
}
