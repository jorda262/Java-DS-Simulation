package edu.century.finalProject;

public class Shopper {
	//Instance variables
	private int checkoutTime;
	private int uid;
	private static int updater;
	
	//Constructor
	public Shopper(int checkoutTime)
	{
		this.checkoutTime = checkoutTime;
		Shopper.updater += 1;
		this.uid = updater - 4;
	}

	//Getter methods
	public int getCheckOutTime() {
		return checkoutTime;
	}
	
	public int getUid() {
		return uid;
	}

	//Setter method
	public void setCheckoutTime(int checkoutTime) {
		this.checkoutTime = checkoutTime;
	}


	@Override
	//To-string method
	public String toString() {
		return "Shopper [itemsInCart=" + checkoutTime + ", uid=" + uid + "]";
	}

	//Boolean equals method
	public boolean equals(Object obj)
	{
		if(obj instanceof Shopper)
		{
			Shopper shopper = (Shopper) obj;
			int otherUID = shopper.uid;
			int otherItems = shopper.checkoutTime;
			return(this.uid == otherUID && this.checkoutTime == otherItems);
		}
		return super.equals(obj);
	}	
}