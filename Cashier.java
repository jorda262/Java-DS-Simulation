package edu.century.finalProject;

public class Cashier {
	//Instance variables
	private Shopper shopper;
	private int numberOfCashiers;
	private int checkoutTime;
	private int timeLeft;

	//Constructor
	public Cashier(Shopper shopper) {
		this.shopper = shopper;
		this.checkoutTime = shopper.getCheckOutTime();
		this.timeLeft = shopper.getCheckOutTime();
		numberOfCashiers += 1;
	}

	// Setter methods
	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
		setCheckoutTime(shopper.getCheckOutTime());

	}

	public void setCheckoutTime(int checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	// Getter methods
	public Shopper getShopper() {
		return shopper;
	}

	public int getNumberOfCashiers() {
		return numberOfCashiers;
	}

	public int getCheckoutTime() {
		return checkoutTime;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	//Method for determining if the Cashier is busy or not
	public boolean isBusy() {
		return (timeLeft > 0);
	}

	//Method for reducing the amount of waiting time for the shopper
	public void reduceRemainingTime() {
		if (timeLeft > 0) {
			timeLeft--;
		}
	}

	//Method for beginning the customer service
	public void startCustomerService() {
		if (timeLeft > 0) {
			throw new IllegalStateException("Cashier is already busy");
		}
		timeLeft = checkoutTime;
	}

}