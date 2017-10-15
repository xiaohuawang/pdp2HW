package com;

import java.util.List;

public interface PatientGenerator {
		
	public List<Patient> creatPatient();
	public void createUrgencyLevel();
	public void createArrivalTime();
	public void createTreatmentTime();
	public int getTotalPatientNumber();
	
}
