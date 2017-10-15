package test;

import com.MyPriorityQueue;

public class PQTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyPriorityQueue<Integer> pq=new MyPriorityQueue<Integer>();
		pq.insert(10);
		pq.insert(15);
		pq.insert(12);
		
		
		System.out.println(pq.front());
		System.out.println(pq.remove().intValue());
	}

}
