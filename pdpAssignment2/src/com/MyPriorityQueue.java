package com;

import java.util.ArrayList;

public class MyPriorityQueue<E extends Comparable<E>> {

	private ArrayList<E> pqueue = null;

	// constructor
	public MyPriorityQueue() {
		pqueue = new ArrayList<E>();
	}

	// E remove()
	public E remove() {
		if (pqueue.size() > 0) {
			swapElement(0, pqueue.size() - 1);
			E result = pqueue.remove(pqueue.size() - 1);
			rollDown(0);
			return result;
		} else {
			return null;
		}
	}
	
	// void insert (E e)
	public void insert(E e) {
		pqueue.add(e);
		rollUp(pqueue.size() - 1);
	}

	// E front()
	public E front() {
		return pqueue.get(0);
	}

	// get the current pqueue size
	public int getSize() {
		int len = 0;
		for (E e : pqueue) {
			len++;
		}
		return len;
	}

	// boolean isEmpty()
	public boolean isEmpty() {
		if (pqueue.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void removeSpecificItem(E e) {
		pqueue.remove(e);
	}

	private int parentIndex(int i) {
		return (i - 1) / 2;
	}

	private int leftIndex(int i) {
		return 2 * i + 1;
	}

	private int rightIndex(int i) {
		return 2 * i + 2;
	}

	private void swapElement(int i, int j) {
		E tmp = pqueue.get(i);
		pqueue.set(i, pqueue.get(j));
		pqueue.set(j, tmp);
	}

	private void rollDown(int i) {
		int left = leftIndex(i);
		int right = rightIndex(i);
		int largest = i;

		if (left < pqueue.size() && !isGreaterOrEqual(largest, left)) {
			largest = left;
		}
		if (right < pqueue.size() && !isGreaterOrEqual(largest, right)) {
			largest = right;
		}

		if (largest != i) {
			swapElement(largest, i);
			rollDown(largest);
		}
	}

	private void rollUp(int i) {
		while (i > 0 && !isGreaterOrEqual(parentIndex(i), i)) {
			swapElement(parentIndex(i), i);
			i = parentIndex(i);
		}
	}

	// determine if the fist element is greater or equal to the second element
	private boolean isGreaterOrEqual(int firstIndex, int secondIndex) {
		E eFirst = pqueue.get(firstIndex);
		E eSecond = pqueue.get(secondIndex);
		if (eFirst.compareTo(eSecond) >= 0) {
			return true;
		}
		return false;
	}

	// List testForwardTraversal()
	// List testReverseTraversal()
}
