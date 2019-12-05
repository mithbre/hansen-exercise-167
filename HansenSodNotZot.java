/**************************************
 * Author: Calvin Hansen
 * Course: CPT 167
 * Purpose: Java practice program. Functions/methods continued. Module 6. 
 * Create Date: 2019.12.03
 */

package edu.cpt167.hansen.participation;
import java.util.Scanner;

public class HansenSodNotZod 
{
	public static final double DISCOUNT_RATE_MEMBER = 0.15;
	public static final double DISCOUNT_RATE_SENIOR = 0.25;
	public static final double DISCOUNT_RATE_NONE = 0.0;
	public static final double TAX_RATE = 0.075;
	
	public static final int MAX_PURCHASE_COUNT = 1;
	public static final int RESET_VALUE = 0;
	public static final double RESET_CUST_TOTAL = 0.0;
	
	public static final double PRICE_PREMIUM = 100.0;
	public static final double PRICE_SPECIAL = 20.0;
	public static final double PRICE_BASIC = 1.0;
	public static final String NAME_PREMIUM = "Power Drill";
	public static final String NAME_SPECIAL = "Ratchet";
	public static final String NAME_BASIC = "Screwdriver";

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);

		int memberCount = 0, seniorCount = 0, noDiscountCount = 0;
		int purchaseCounter = 0, premiumCount = 0, specialCount = 0;
		int basicCount = 0, howMany = 0;
		char menuSelection = 'z', itemSelection = 'z';
		double itemPrice = 0.0, subTotal = 0.0, totalCost = 0.0;
		double discountAmt = 0.0, discountPrice = 0.0, tax = 0.0;
		double discountRate = 0.0, grandTotal = 0.0, customerTotal = 0.0;
		String userName = "", itemName = "";
		
		displayWelcomeBanner();

		userName = getUserName(input);
		menuSelection = validateMainMenu(input);

		while (menuSelection != 'Q')
		{
			while (purchaseCounter < MAX_PURCHASE_COUNT) {
				itemSelection = validateItemMenu(input);
				howMany = validateHowMany(input);

				if (menuSelection == 'A')
				{
					discountRate = DISCOUNT_RATE_MEMBER;
					memberCount++;
				}
				else if (menuSelection == 'B')
				{
					discountRate = DISCOUNT_RATE_SENIOR;
					seniorCount++;
				}
				else
				{
					discountRate = DISCOUNT_RATE_NONE;
					noDiscountCount++;
				}
			
				if (itemSelection == 'A')
				{
					itemName = NAME_PREMIUM;
					itemPrice = PRICE_PREMIUM;
					premiumCount++;
				}
				else if (itemSelection == 'B')
				{
					itemName = NAME_SPECIAL;
					itemPrice = PRICE_SPECIAL;
					specialCount++;
				}
				else
				{
					itemName = NAME_BASIC;
					itemPrice = PRICE_BASIC;
					basicCount++;
				}
			
				// calculations
				discountAmt = itemPrice * discountRate;
				discountPrice = itemPrice - discountAmt;
				subTotal = howMany * discountPrice;
				tax = subTotal * TAX_RATE;
				totalCost = subTotal + tax;
				grandTotal += totalCost;
				customerTotal += totalCost;
			
				displayItemReceipt(itemName, itemPrice,
						discountPrice, howMany, subTotal, tax, totalCost);
				purchaseCounter++;
			}
			displayCustomerReport(customerTotal);
			purchaseCounter = RESET_VALUE;
			customerTotal = RESET_CUST_TOTAL;
			menuSelection = validateMainMenu(input);
		}
		
		if (grandTotal > 0.0)
		{
			displayFinalReport(userName, memberCount, seniorCount,
					noDiscountCount, premiumCount, specialCount, basicCount,
					grandTotal);
		}

		displayFarewellMessage();
		input.close();
	}
	
	public static void displayWelcomeBanner()
	{
		// welcome the user
		System.out.printf("%s\n", "Welcome to Sod not Zod.");
		System.out.printf("%s\n\n", "We have a few items on special today.");
	} //END displayWelcomeBanner()
	
	public static String getUserName(Scanner borrowedInput)
	{
		// Prompt, capture, and return the users name.

		String localUserName = "";
		System.out.printf("%s", "What is your name? ");
		localUserName = borrowedInput.nextLine();
		return localUserName;
	} // END getUserName()
	
	public static void displayMainMenu()
	{
		// Display the menu choices.
		System.out.printf
		("\n%s","Please select a member type by using one of the ");
		System.out.printf("%s\n\n", "letters\nwithin the brackets.");
		
		System.out.printf("%s\n", "Member type selection menu.");
		System.out.printf("%s\n", "---------------------------");
		System.out.printf("%s%2.0f%s\n", "[a] member (a ",
				DISCOUNT_RATE_MEMBER * 100, "% discount)");
		System.out.printf("%s%2.0f%s\n", "[b] senior (a ",
				DISCOUNT_RATE_SENIOR * 100, "% discount)");
		System.out.printf("%s%2.0f%s\n", "[c] normal (a ",
				DISCOUNT_RATE_NONE   * 100, "% discount)");
		System.out.printf("%s\n", "[q] quit");
		System.out.printf("%s", "selection: ");
	}
	
	public static char validateMainMenu(Scanner borrowedInput)
	{
		// Present, capture, validate, and return the user's choice of
		// member type.

		char localSelection = 'z';
		
		displayMainMenu();
		localSelection = borrowedInput.nextLine().toUpperCase().charAt(0);
		
		while (localSelection != 'A' && localSelection != 'B' &&
			   localSelection != 'C' && localSelection != 'Q')
		{
			System.out.printf
			("\n%s","Please select a member type by using one of the ");
			System.out.printf("%s\n\n", "letters\nwithin the brackets.");
			displayMainMenu();
			localSelection = borrowedInput.nextLine().toUpperCase().charAt(0);
		}
		
		return localSelection;
	} // END validateMainMenu()
	
	public static void displayItemMenu()
	{
		// Display the item choices
		System.out.printf("\n%s\n", "Item Selection Menu");
		System.out.printf("%s\n", "---------------------------");
		System.out.printf("%s%7.2f%15s\n", "[a] $",
				PRICE_PREMIUM, NAME_PREMIUM);
		System.out.printf("%s%7.2f%15s\n", "[b] $",
				PRICE_SPECIAL, NAME_SPECIAL);
		System.out.printf("%s%7.2f%15s\n", "[c] $",
				PRICE_BASIC,   NAME_BASIC);
		System.out.printf("%s", "selection: ");
	}
	
	public static char validateItemMenu(Scanner borrowedInput)
	{
		// Prompt, capture, validate, and return the user's chosen purchase.

		char localSelection = 'z';
		
		displayItemMenu();
		localSelection = borrowedInput.nextLine().toUpperCase().charAt(0);
		
		while (localSelection != 'A' && localSelection != 'B' &&
			   localSelection != 'C' && localSelection != 'Q')
		{
			System.out.printf
			("\n%s","Please select a member type by using one of the ");
			System.out.printf("%s\n\n", "letters\nwithin the brackets.");
			displayItemMenu();
			localSelection = borrowedInput.nextLine().toUpperCase().charAt(0);
		}
		
		return localSelection;
	} // END validateItemMenu()
	
	public static int validateHowMany(Scanner borrowedInput) {
		// Prompt, capture, validate, and return the user's needed quantity.
		int localHowMany = 0;
		
		System.out.printf("\n%s", "quantity: ");
		localHowMany = borrowedInput.nextInt();
		
		while (localHowMany <= 0)
		{
			System.out.printf("\n%s\n", "no no bad.");
			
			System.out.printf("%s", "quantity: ");
			localHowMany = borrowedInput.nextInt();
		}
		
		// clean up the input buffer
		borrowedInput.nextLine();
		return localHowMany;
	} // END validateHowMany()
	
	public static void displayItemReceipt(String borrowedItemName,
			double borrowedItemPrice, double borrowedDiscountPrice,
			int borrowedHowMany, double borrowedSubTotal, double borrowedTax,
			double borrowedTotalCost)
	{
		// Display the transaction receipt
		
		System.out.printf("\n\n");
		System.out.printf("\n%s\n", "Item Receipt:");
		System.out.printf("%s\n", "-----------------------------------");
		System.out.printf("%-26s%s\n", "Item Name:", borrowedItemName);
		System.out.printf("%-26s%s%7.2f\n", "Original Price:", "$ ", borrowedItemPrice);
		System.out.printf("%-26s%s%7.2f\n", "Item price with discount:", "$ ", borrowedDiscountPrice);
		System.out.printf("%-26s%6d\n", "Quantity:", borrowedHowMany);
		System.out.printf("%s\n", "-----------------------------------");
		System.out.printf("%-26s%s%7.2f\n", "SubTotal:", "$ ", borrowedSubTotal);
		System.out.printf("%-26s%s%7.2f\n", "Tax:", "$ ", borrowedTax);
		System.out.printf("%35s\n", "---------");
		System.out.printf("%-26s%s%7.2f\n\n", "Total item cost:", "$ ", borrowedTotalCost);
	} // END displayItemReceipt()
	
	public static void displayCustomerReport(double borrowedCustomerTotal)
	{
		// Display the final ticket price for the customer.
		System.out.printf("%s\n", "-----------------------------------");
		System.out.printf("%-26s%s%7.2f\n\n", "Total ticket cost:", "$ ", borrowedCustomerTotal);
		System.out.printf("\n%s\n", "-----------------------------------");
		System.out.printf("%s\n", "-----------------------------------");
		System.out.printf("%s\n\n", "-----------------------------------");

	}
	
	public static void displayFinalReport(String borrowedUserName,
			int borrowedMemberCount, int borrowedSeniorCount,
			int borrowedNoDiscountCount, int borrowedPremiumCount,
			int borrowedSpecialCount, int borrowedBasicCount,
			double borrowedGrandTotal)
	{
		// Display the final discount counts to the user
		int totalDiscountCount = 0;
		totalDiscountCount = borrowedMemberCount + borrowedSeniorCount;
		
		System.out.printf("\n%s\n", "-----------------------------------");
		System.out.printf("%s\n", "-----------------------------------");
		System.out.printf("%s\n", "-----------------------------------");
		System.out.printf("\n%s\n", "Final Report");
		System.out.printf("%s\n", "---------------------------------");
		System.out.printf("%-29s%d\n", "Members:", borrowedMemberCount);
		System.out.printf("%-29s%d\n", "Seniors:", borrowedSeniorCount);
		System.out.printf("%-29s%d\n", "non members:", borrowedNoDiscountCount);
		System.out.printf("%-29s%d\n", "Discounted transactions:", totalDiscountCount);
		System.out.printf("%s\n", "---------------------------------");
		System.out.printf("%-24s%s%7.2f\n", "Grand Total:", "$ ", borrowedGrandTotal);
	} // END displayFinalReport()
	
	public static void displayFarewellMessage()
	{
		// let the user know that the program is finished.
		System.out.printf("\n%s\n", "Have a great trip home.");
	} // END displayFarewellMessage()
}
