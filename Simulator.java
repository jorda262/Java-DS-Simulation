package edu.century.finalProject;

import java.util.Scanner;

public class Simulator {
	// Instance variables
	private int closingTime;
	private int currentTime;
	private static Queue queue;

	// Constructor
	public Simulator(int currentTime, int closingTime) {
		this.closingTime = closingTime;
		this.currentTime = currentTime;
	}

	// Getter methods
	public int getClosingTime() {
		return closingTime;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	// Setter methods
	public void setClosingTime(int closingTime) {
		this.closingTime = closingTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	// Method for printing out time menu for user prompt
	public static void printTimes() {
		System.out.println("Enter the desired time for the simulation in seconds: ");
		System.out.println("3600 for 1 hour");
		System.out.println("7200 for 2 hours");
		System.out.println("10800 for 3 hours");
		System.out.println("14400 for 4 hours");
		System.out.println("18000 for 5 hours");
		System.out.println("21600 for 6 hours");
		System.out.println("25200 for 7 hours");
		System.out.println("28800 for 8 hours");
		System.out.println("32400 for 9 hours");
		System.out.println("36000 for 10 hours");
		System.out.println("39600 for 11 hours");
		System.out.println("43200 for 12 hours");
	}

	// Method for generating shoppers based on a probability curve
	public static boolean query(int time, int speed) {
		double rand = (Math.random());
		double probability = (((-0.5 / 466560000) * (Math.pow(time - 21600, 2)) + 0.5)) / speed;
		if (rand < probability) {
			return true;
		} else {
			return false;
		}
	}

	// Method for creating a shopper with random number of items 1-60
	public static Shopper createShopper() {
		int random = 1 + (int) (Math.random() * 60);
		Shopper shopper = new Shopper(random);
		System.out.println("Customer " + shopper.getUid() + " entered queue.");
		return shopper;
	}

	// Method for adding a shopper to the queue
	public static void addShopperToQueue(Shopper shopper) {
		queue.addToTail(shopper);
		System.out.println("There are " + queue.size() + " people in the line.");
	}

	// Method for simulating a queue with a user given number of cashiers
	public static void startSim(Simulator simulation, int maxCashiers, int speed) throws InterruptedException {
		int current = simulation.getCurrentTime();
		int closing = simulation.getClosingTime();
		queue = new Queue();
		Cashier[] cashiers = new Cashier[maxCashiers];
		for (int i = 0; i < maxCashiers; i++) {
			cashiers[i] = new Cashier(new Shopper(0));
		}
		while (current < closing) {
			boolean flag = query(current, speed);
			if (current % 3600 == 0) {
				System.out.println(current / 3600 + " hours passed by");
				Shopper shopper = new Shopper(0);
				System.out.println("Served " + shopper.getUid() + " customers in " + current / 3600 + " hours!");
			}
			if (flag) {
				addShopperToQueue(createShopper());
				Thread.sleep(10);
			} 

			// Conditional for checking that there are people in the queue
			if (!queue.isEmpty()) {
				for (int i = 0; i < maxCashiers; i++) {
					// Conditional for testing if the cashier is not busy and
					// able to receive a customer
					if (!cashiers[i].isBusy()) {
						if (!queue.isEmpty()) {
							Shopper person1 = queue.removeFromHead();
							cashiers[i].setShopper(person1);
							cashiers[i].startCustomerService();
							System.out.println(
									 "Customer: # "+ person1.getUid() + " has " + cashiers[i].getTimeLeft() + " seconds left with Cashier " + (i + 1));
							Thread.sleep(10);
						}
					}
				}

			}
			// Conditional for decrementing the times each customer has left
			// with the cashier
			for (int i = 0; i < maxCashiers; i++) {
				if (cashiers[i].isBusy()) {
					cashiers[i].reduceRemainingTime();
					System.out.println(
							"Time left with Cashier " + (i + 1) + ": " + cashiers[i].getTimeLeft() + " seconds");
					Thread.sleep(10);
				}
			}
			current++;
		}

	}

	public static void main(String[] args) {
		int runTime;
		int maxNumberOfCashiers;
		int customerGenerationSpeed;
			Scanner cin = new Scanner(System.in);
			System.out.println("Enter the max number of desired cashiers: ");
			maxNumberOfCashiers = cin.nextInt();
			System.out.println("How quickly do you want customers generated? (1-10) 1: Fast - 10: Slow");
			customerGenerationSpeed = cin.nextInt();
			printTimes();
			runTime = cin.nextInt();
			cin.close();

		Simulator simulation = new Simulator(0, runTime);
		try {
			startSim(simulation, maxNumberOfCashiers, customerGenerationSpeed);
			Shopper shopper = new Shopper(0);
			System.out.println(
					"Simulation over! Served " + shopper.getUid() + " customers in " + runTime / 3600 + " hours!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}