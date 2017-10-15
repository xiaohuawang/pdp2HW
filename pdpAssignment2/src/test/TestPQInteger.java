package test;

import com.MyPriorityQueue;
import com.TreatmentRoom;

public class TestPQInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<Integer>();
		queue.insert(0);
		queue.insert(1);
		queue.insert(2);
		queue.insert(3);
		
//		queue.checkAllElement();
		
		queue.remove();
		
//		queue.checkAllElement();
	}

}
