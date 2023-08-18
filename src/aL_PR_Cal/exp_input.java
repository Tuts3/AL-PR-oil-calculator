package aL_PR_Cal;

import java.util.Scanner;

//goals:
// exp on level 12-4: 16070 (13,824 + 2246)
// assuming absolute lowest possible (768 x 6) * 3 "times 3 for three ironblood vanguard" + (1123 *2) "only using 2 iron blood vanguard ships
// how much oil to complete 12-4 : 42 for mob node 55 for boss 17 for sub
// assuming max oil cost ^
// required battles = 6 mobs 1 boss + 6 subs
// answer: 409 oil per run (worst case)

// priority ship exp:
// level 1 combat 1,200,000 exp
// level 2 combat 2,400,00
// how many runs of 12-4 to complete each
// how much oil costs total
// oil per hour (from market which would be minimum): 96 per/h 3600 max cap
// time taken to do stuff
// time taken to complete a run: 8 mins (max)

// TODO:
// add scanner functionality to update values (DONE)
// user can choose if using submarines
// use an if statement for exp ebing greater than 1,400,000 but less than 2,400,000 (FIXED)
// option to use submarines or not
// include how many ships gaining exp (must be different for main and vanguard) (DONE)
// new error: if inputed a value >1.4mill code shits itself (FIXED)
// add thing above but for the exp gain stuff (DONE)
// getting error where printing too many lines when asking for sub y/n (FIXED)

// add a function to convert percentages to exp value (done)
// make sure that function doesnt let you input a number less than 0 or greater than 100 (FIXED)
// make sure that the program can check the diffrence between comabt level 1 and combat l (FIXED)

public class exp_input {
	
	public static void main(String[] args) {
		// allows you to input how much exp you have
		Scanner combatLevel1Or2 = new Scanner(System.in);
        System.out.print("Combat data collection 1 or 2 (insert 1 or 2): ");
        int combatLevel = combatLevel1Or2.nextInt();
        
        int amountExp = 0; // Initialize amount of experience based on combat level
        if (combatLevel == 1) {
            amountExp = 1400000; // 1.4 million
        } else if (combatLevel == 2) {
            amountExp = 2400000; // 2.4 million
        } else {
            System.out.println("Invalid combat level. Please enter 1 or 2.");
            combatLevel1Or2.close();
            return; // Exit the program if combat level is invalid
        }
        
        Scanner percentScanner = new Scanner(System.in);
        System.out.print("How much exp have you gotten? (input percent number but dont use '%' sign e.g. 54 for 54%): ");
        double percentage = percentScanner.nextDouble();
        
        int curExp = 0; // Initialize curExp based on user input
        if (percentage < 0 || percentage > 100) {
            System.out.println("Invalid input. Percentage must be between 0 and 100.");
        } else {
            double result = (percentage / 100) * amountExp;
            System.out.println(percentage + "% of " + amountExp + " is: " + (int) result);
            curExp = (int) result; // Update the curExp value based on the calculated result
        }
        
        
		
		// checks if user is using a submarine
		Scanner useSubsScanner = new Scanner(System.in);
		System.out.print("Are you using submarines? (y/n): ");
		String useSubsInput = useSubsScanner.nextLine().toLowerCase(); // Convert to lowercase
		// makes sure both mob fleet and boss get values between 0-3
				int mobShips = getValidInput("mob", useSubsScanner);
				int bossShips = getValidInput("boss", useSubsScanner);
		int oilCost = 307;
		if (useSubsInput.equals("y") || useSubsInput.equals("yes")) {
			oilCost += 102; // Add extra 102 oil cost if using submarines
		}
		// maths stuff
		// NOTE TO USER: IF YOU WANT TO CHANGE EXP PER STAGE EDIT THE VALUES HERE
		int mobExpPerShip = 768 * 6; // each ship gives 768 exp for 6 battles
		int bossExpPerShip = 1123; // boss gives 1123 exp
		int mobExpGain = mobExpPerShip * mobShips; // times exp by amount of ships using
		int bossExpGain = bossExpPerShip * bossShips; // same as above ^
		int formationExpGain = mobExpGain + bossExpGain; // total exp gain per run

		int expGain = formationExpGain;
		int runTime = 8; // how long a run is in mins (i added an extra minute it used to be 7 to assume worst case)
		// ALSO CHANGE TIME TAKEN IF YOU CAN BEAT THE LEVEL FASTER
		
		//can't remember what you do lol // i think this determines how much exp you're gonna need???
		int runs1 = Math.max((1200000 - curExp) / expGain, 0);
		int runs2;
		// something something, fixes values greater than 1.4 mill but less than 2.4 mill
		if (curExp >= 1400000) {
			runs2 = Math.max((2400000 - curExp) / expGain, 0);
		} else {
			runs2 = Math.max((2400000 - 1400000) / expGain, 0);
		}
		// other maths
		int totalRuns = runs1 + runs2; // how many runs need to be completed
		int oilRun1 = oilCost * runs1; // oil cost for combat level 1
		int oilRun2 = oilCost * runs2; // same as above but for combat level 2
		int oilTotal = oilRun1 + oilRun2; // total oil needed
		
		// maths to calculate how long it takes to do runs and stuff
		int runTimeMins1 = runTime * runs1;
		int runTimeHours1 = runTimeMins1 / 60;
		int runTimeMins2 = runTime * runs2;
		int runTimeHours2 = runTimeMins2 / 60;
		
		// math dedicated to generating oil in the canteen
		double oilGen = 96;
		double oilTime = oilTotal / oilGen;
		double oilTime1 = oilRun1 / oilGen;
		double oilTime2 = oilRun2 / oilGen;
		// math dedicated to how long it would take to generate oil
		int oilTimeMins = (int) oilTime % 60;
		int oilTimeMins1 = (int) oilRun1 % 60;
		int oilTimeMins2 = (int) oilRun2 % 60;
		double oilDays1 = oilTime1 / 24;
		double oilHours1 = (oilTime1 % 1440) / 60;
		double oilDays2 = oilTime2 / 24;
		double oilHours2 = (oilTime2 % 1440) / 60;
		// all of the print statements to the console
		System.out.println();
		System.out.println();
		// level 1 combat exp
		System.out.println("How many runs to complete level 1 combat exp: " + runs1 + " runs");
		System.out.println("This would take " + runTimeMins1 + " mins to complete which is " + runTimeHours1 + " Hours");
		System.out.println("This costs " + oilRun1 + " oil to complete");
		System.out.println("Time taken to generate oil costs is: " + (int) oilTime1 + " hours and " + oilTimeMins1 + " mins");
		System.out.println("This is the equivalent of " + (int) oilDays1 + " days and " + (int) oilHours1 + " hours"); // time it takes to gen oil

		System.out.println();
		System.out.println();
		System.out.println();
		// level 2 combat exp
		System.out.println("How many runs to complete level 2 combat exp: " + runs2 + " runs");
		System.out.println("This would take " + runTimeMins2 + " mins to complete which is " + runTimeHours2 + " Hours");
		System.out.println("This costs " + oilRun2 + " oil to complete");
		System.out.println("Time taken to generate oil costs is: " + (int) oilTime2 + " hours and " + oilTimeMins2 + " mins");
		System.out.println("This is the equivalent of " + (int) oilDays2 + " days and " + (int) oilHours2 + " hours"); 

		System.out.println();
		System.out.println();
		System.out.println();
		// total amount of oil needed and total amount of runs
		System.out.println("The total amount of runs required for 3.6 mil exp is: " + totalRuns + " runs");
		System.out.println("Which requires " + oilTotal + " oil to complete");

		System.out.println();
		// total amount of stuff (calculated from combat level 1 and combat level 2)
		System.out.println("The amount of time game needs to be running is: " + (runTimeHours1 + runTimeHours2) + " hours and " + (runTimeMins1 + runTimeMins2)%60 + " mins");
		System.out.println("Time taken to generate oil costs is: " + (int) oilTime + " hours and " + oilTimeMins + " mins");
		System.out.println("This is the equivalent of " + (int) (oilTime / 24) + " days and " + ((int) oilTime % 24) + " hours and " + oilTimeMins + " mins");
		// closes scanner functions so no new inputs and stuff
		useSubsScanner.close();
		combatLevel1Or2.close();
		percentScanner.close();
	}
	// validates if the amount of ships is correct
	public static int getValidInput(String fleetType, Scanner scanner) {
	    int ships;
	    do {
	        System.out.print("How many ships are gaining exp in the " + fleetType + " fleet? (1, 2, or 3): ");
	        ships = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character
	    } while (ships < 1 || ships > 3);
	    return ships;
		//note to self: i dont know why you're here but im too scared to move you and break something
		// you know what they say if it aint broke dont fix it
	}
}