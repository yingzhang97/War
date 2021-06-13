package Assig4;

//CS 0401 Fall 2019
//Shell of MultiDS<T> class to be used with Assignment 4.
//You must complete this class so that:
//1) It works with test program Assig4A.java
//2) You can use it in your War program

import java.io.*;
import java.util.*;

import Assig3.GamePlayer;
@SuppressWarnings("unchecked")
public class MultiDS<T> implements PrimQ<T>, Reorder, ExtraCredit
{
	private T [] theData;  // Note that the data is an array of T
	// You will also need one or more state variables to keep track of your
	// logical data.  The variables you need depend on how you will manage
	// your queue.
	
	private Random R;  // This is needed so that you can shuffle your dat
	int size = 0;

	public MultiDS(int sz)
	{
		theData = (T []) new Object[sz];  // Note how this is created
		R = new Random();
		
		// You will also need to initialize your other state variable(s).
		// You will need to create a new array in a similar way in your
		// resize() method.
	}

	@Override
	// Logically reverse the data in the Reorder object so that the item
	// that was logically first will now be logically last and vice
	// versa.  The physical implementation of this can be done in 
	// many different ways, depending upon how you actually implemented
	// your physical MultiDS<T> class
	public void reverse() {
		int j = 0;
		for (int i =size-1; i>= 0;i--) {
			if(!(j>=i)) {
				T temp = theData[i];
				theData[i] = theData[j];
				theData[j] = temp;
			}
			j++;
		}
		
	}

	@Override
	// Remove the logical last item of the DS and put it at the 
	// front.  As with reverse(), this can be done physically in
	// different ways depending on the underlying implementation. 
	public void shiftRight() {
		T temp = theData[size-1];
		for (int i = size-1; i > 0; i--) {
			theData[i] = theData[i-1];
		}
		theData[0] = temp;
	}

	@Override
	// Remove the logical first item of the DS and put it at the
	// end.  As above, this can be done in different ways.
	public void shiftLeft() {
		T temp = theData[0];
		for (int i = 0; i < size-1; i++) {
		theData[i]=theData[i+1];
	}
		theData[size-1] = temp;
	}
	

		
	public void shuffle() {
		for (int i =0; i<size; i++) {
			int j = R.nextInt(size);   //check if it's length-1
			T temp = theData[i];
			theData[i] = theData[j];
			theData[j] = temp;
		}
		// Reorganize the items in the object in a pseudo-random way.  The exact
		// way is up to you but it should utilize a Random object (see Random in 
		// the Java API).  Thus, after this operation the "oldest" item in the
		// DS could be arbitrary.
		
	}

	@Override
	// Add a new Object to the PrimQ<T> in the next available location.  If
	// all goes well, return true.  The iterface allows for this method to
	// return false if the add does not succeed.  [However, in your implementation
	// it should always succeed (i.e you should resize your array if necessary)]
	public boolean addItem(T item) {
		if (size == theData.length)
		{
			theData = resize(theData);
		}
		theData[size] = item;
		size ++;
		return true;
	}

	public T [] resize(T [] oldA)
	{
		int oldL = oldA.length;
		int newL = 2 * oldL;
		T [] newA = (T []) new Object [newL];
		for (int i = 0; i < oldA.length; i++)
			newA[i] = oldA[i];
		return newA;
	}
	@Override
	// Remove and return the "oldest" item in the PrimQ.  If the PrimQ
	// is empty, return null.
	public T removeItem() {
		if (!empty()) {
			T temp = theData[0];
			for(int i=0; i<size-1; i++) {
			theData[i] = theData[i+1];
			}
			theData[size-1] = null;
			size--;
			return temp;
		}
		else {
			return null;
			}
	}
	

	@Override
	// Return true if the PrimQ is full, and false otherwise.  [In your
	// implementation, this method should always return false]
	public boolean full() {
		boolean i = false;
		if (size == theData.length) {
			theData = resize(theData);
			i = true;
		}
		return i;
	}

	@Override
	// Return true if the PrimQ is empty, and false otherwise
	public boolean empty() {
		if (this.size()==0) {
			return true;
		}
		else
			return false;
	}

	@Override
	// Return the number of items currently in the PrimQ
	public int size() {
		return this.size;
	}

	@Override
	// Reset the PrimQ to empty status by reinitializing the variables
	// appropriately
	public void clear() {
	theData = (T []) new Object[theData.length];
	R = new Random();
	size = 0;
	}

	public String toString()
	{
		StringBuffer B = new StringBuffer();
		B.append("Contents:");
		for (int i = 0; i < size; i++)
			B.append(theData[i].toString() + " ");
		return B.toString();
	}

	@Override
	public void shiftRight(int i) {
		for (int j = 0; j < i; j++) {
			shiftRight();
		}
	}

	@Override
	public void shiftLeft(int i) {
		// TODO Auto-generated method stub
		T [] newData = (T []) new Object [i % size];
		for (int j = 0; j <newData.length;j++ ) {
			newData[j] = theData[j];
		}
		for (int j = 0; j < size-i % size; j++) {
			theData[j]=theData[j+i % size];
		}
		for (int j = 0; j < newData.length;j++ ) {
			theData[size-i % size+j] = newData[j];
		}
	}
	
	// Below you should implement all of the methods in the PrimQ<T> and Reorder
	// interfaces.  See the details for these methods in files PrimQ.java and
	// Reorder.java.  See how these are actually used in the test program Assig4A.java.
	// Once you have completed this class the program Assig4A.java should compile and
	// run and give output identical to that shown in file A4Out.txt (except for the
	// order of the data after shuffling, since that should be random).
	
}
