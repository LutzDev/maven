package de.hfu;

import static de.hfu.Queue.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class QueueTest {
	
	
	/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/
	
	 @Test
	 public void enqueTestOne() {
		 //Array ist schon befüllt
		 Queue q1 = new Queue(3);
		 final int eingabe = 4;
	     final int sollWert = 4;
	     q1.enqueue(1);
	     q1.enqueue(2);
	     q1.enqueue(3);
	     q1.enqueue(eingabe);
	     assertEquals(sollWert, q1.queue[q1.queue.length-1]);
	 }
	 
	 @Test
	 public void enqueTestTwo() {
		 //Eingabe landet an der ersten Position des Arrays
		 Queue q2 = new Queue(3);
		 final int eingabe = 2;
	     final int sollWert = 2;
	     q2.enqueue(eingabe);
	     assertEquals(sollWert, q2.queue[0]);
	 }
	 
	 @Test
	 public void enqueTestThree() {
		 //Eingabe landet an der zweiten Position des Arrays
		 Queue q3 = new Queue(3);
		 final int eingabe = 2;
	     final int sollWert = 2;
	     q3.enqueue(3);
	     q3.enqueue(eingabe);
	     assertEquals(sollWert, q3.queue[1]);
	 }
	 
	 
	 @Test
     public void dequeTestOne() {
		//Keine Zahl im Array
		 Queue q4 = new Queue(3);
		 final int eingabe = 2;
	     final int sollWert = 2;
		 try {
			 q4.dequeue();
			 fail("Erwartete Exception wurde nicht ausgeworfen");
		 }catch (IllegalStateException e){
			 System.out.println(Arrays.toString(q4.queue));
		 }
	 }
	 
	 @Test
     public void dequeTestTwo() {
		//zahl die am längsten in der Queue ist wird zurückgegeben
		 Queue q5 = new Queue(3);
		 final int eingabe = 2;
	     final int sollWert = 2;
	     q5.enqueue(sollWert);
	     q5.enqueue(2);
	     q5.enqueue(3);
	     assertEquals(sollWert, q5.dequeue());
	 }
	
}
