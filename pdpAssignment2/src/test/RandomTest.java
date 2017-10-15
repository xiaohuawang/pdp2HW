package test;

import java.util.List;

import com.ERSimulator;
import com.Patient;
import com.PatientGeneratorRandom;

public class RandomTest {

	// public void testRandom() {
	// Random r = new Random();
	// int randomInt = r.nextInt(10);
	// System.out.println(randomInt);
	// }

	public static void main(String[] args) {

		PatientGeneratorRandom pg = new PatientGeneratorRandom();
		List<Patient> list = pg.creatPatient();
//		ERSimulator first = new ERSimulator();

		for (int i = 0; i < list.size(); i++) {
			System.out.println("come");
//			long res = first.compareTimeDif(first.getLocalTime(), list.get(i).getArrivalTime());
			System.out.println("patient arrival sec= " + list.get(i).getArrivalTime());
			System.out.println("patient treatment sec= " + list.get(i).getTreatmentTime() / 1000);
			System.out.println("danger level = " + list.get(i).getUrgencyLevel());
			System.out.println();
		}

	}
}
