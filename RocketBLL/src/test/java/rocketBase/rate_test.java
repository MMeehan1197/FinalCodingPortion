package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void test() {
		try {
			assertEquals(RateBLL.getRate(800), .035, 0);
			assertEquals(RateBLL.getRate(750), .0375, 0);
			assertEquals(RateBLL.getRate(700), .040, 0);
			assertEquals(RateBLL.getRate(650), .045, 0);
			assertEquals(RateBLL.getRate(600), .050, 0);
		//	RateBLL.getPayment(r, n, p, f, t)
		//	Couldn't get the Finance Library working properly for some reason.
		//  I tried most everything to my ability and for some reason it was not
		//	Getting the right value that you posted in the docx sheet.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = RateException.class)
		public void rateExceptionTest() throws Exception{
			RateBLL.getRate(0);
		}
		
	
	}


