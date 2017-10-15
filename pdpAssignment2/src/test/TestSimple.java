package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestSimple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Test1> list=new ArrayList<Test1>();
		Scanner sc=new Scanner(System.in);
		int input=sc.nextInt();
		for(int i=0;i<input;i++){
			Test1 test=new Test1();
			list.add(test);
		}
		System.out.println(list.get(3).test);
	}

}
