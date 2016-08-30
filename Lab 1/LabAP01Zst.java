// LabAP01Zst.java  starting version
// Practice with reading values from keyboard, integer division, floating division
//	and modular division.

import java.util.Scanner;

public class LabAP01Zst
{
	public static void main(String args[])
	{
		System.out.println("LabAP01Z");
		int num1, num2, num3;
		num1 = num2 = num3 = 0;
		double var1, var2, var3;
		var1 = var2 = var3 = 0.0;
		// create a Scanner object

		System.out.print("Enter two integers ==>");
		// read an integer from the keyboard twice, store in num1 and num2

		// calculate the quotient and store it into num3

		System.out.println("The quotient of num1 / num2 is "+ num3);

		// calculate the remainder and store it into num3

		System.out.println("The remainder of num1 % num2 is "+ num3);

		System.out.print("Enter two doubles ==>");
		// read a double from the keyboard twice, store in var1 and var2

		// calculate the quotient and store it into var3

		System.out.println("The quotient of var1 / var2 is "+ var3);
		// calculate the remainder and store it into var3

		System.out.println("The remainder of var1 % var2 is "+ var3);
	}
}

/* Try with numbers that divide evenly and that don't.
 * Try with first > second and with second > first.
 * Try entering doubles when reading integers, and integers when reading doubles.
 * Write your own examples of calculations that mix doubles and integers.
 * What happens when you store int into a double? a double into a int?
 *	Will the program still work correctly if you enter all 4 values on one line
 *	with spaces between?
 */