package test;

public class TimeStampTest {

	public static void main(String args[]) {
		
		PatientGeneratorTest pg=new PatientGeneratorTest();
		pg.createArrivalTime();
		pg.createTreatmentTime();
		pg.createUrgencyLevel();
		
		for(int i=0;i<pg.treatmentTimeList.size();i++){
			System.out.println("min ="+pg.treatmentTimeList.get(i)/60);
		}
		System.out.println();
		
		for(int i=0;i<pg.arrivalTimeList.size();i++){
			System.out.println("arrive time= "+pg.arrivalTimeList.get(i));
		}
		
		System.out.println();
		
		for(int i=0;i<pg.urgencyLevelList.size();i++){
			System.out.println("urgent level= "+pg.urgencyLevelList.get(i));
		}
		
//		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
//							.format(Calendar.getInstance().getTime());
//		System.out.println(timeStamp);
	}

}
