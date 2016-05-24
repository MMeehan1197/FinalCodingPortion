package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

		
	//TODO - RocketDAL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketDAL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	
	@Test
	public void rate_test() {
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		
		
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);
		
		
	}
	
	
	@After
	public void AfterTest(){

	}

}
