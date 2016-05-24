package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.FinanceLib;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws Exception
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		
		
		// double interest... This is the highest interest rate which will be the
		// interest rate if the Credit Score is lower than the minimum Credit Score
		// for the highest interest rate
		double interest = rates.get(0).getdInterestRate();
		
		try{
			for(RateDomainModel r : rates){
				//A double type can't return null and if it is null will return a zero
				if(r.getdInterestRate() == 0){
					throw new RateException(r);
				}
				//For Each Loop that stores Interest Rate if it meets the min credit score
				if(GivenCreditScore >= r.getiMinCreditScore()){
					interest = r.getdInterestRate()/100;
				}
			}	
			//This is the exception being caught and "handled"
		}catch(RateException e){
			e.printStackTrace();
			System.out.println(e.getRdm().toString() + "Does not have an interest rate");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		return interest;
		
		
	}

	//Checks to see if the PITI requirements are met	
	public static boolean checkRequirements(double rate, double payment, double income, double request){
		
		double PITI1 = .28 * (income/12);
		double PITI2 = .36 * (income/12) - payment;
		boolean check = false;
		
		if(PITI1 >= request | PITI2 >= request){
			check = true;
		}
		return check;
	}
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
	
}
