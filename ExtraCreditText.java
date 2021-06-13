package Assig4;

import java.util.Scanner;

public class ExtraCreditText {
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String [] args)
	{
		
		System.out.println("About to test Reorder methods");
		MultiDS<Integer> newDS = new MultiDS<Integer>(15);
		for (int i = 0; i < 8; i++)
		{
			newDS.addItem(new Integer(i));
		}
		System.out.println(newDS.toString());
		String k = "y";
		do {
			System.out.print("How many indexes do you want to shift to left?>");
			int i = input.nextInt();
			newDS.shiftLeft(i);
			System.out.println(newDS.toString());
			System.out.print("How many indexes do you want to shift to right?>");
			int j = input.nextInt();
			newDS.shiftRight(j);
			System.out.println(newDS.toString());
			System.out.print("Do you want to continue(y/n)?>");
			input.nextLine();
			k = input.nextLine();
		}while(k.equals("y"));
	
	}
}
