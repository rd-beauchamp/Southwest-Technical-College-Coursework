package application;

public class Investment {
 
	private double matureValue;
	private double investmentAmount;
	private double monthlyInterestRate;
	private double years;
	
	public Investment() {
		
	}
	
	public Investment(double investmentAmount, double monthlyInterestRate, double years) {
		this.investmentAmount = investmentAmount;
		this.monthlyInterestRate = (monthlyInterestRate * .01) / 12;
		this.years = years;
	}
	
	protected double calculate () {
		
		return matureValue = investmentAmount * 
				Math.pow(1 + monthlyInterestRate, (years * 12));
	}
}
