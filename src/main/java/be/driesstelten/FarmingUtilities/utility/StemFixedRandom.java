package be.driesstelten.FarmingUtilities.utility;

import java.util.Random;

@SuppressWarnings("serial")
public class StemFixedRandom extends Random {
	
	 private boolean do_proper_random = false;

	    public int nextInt(int i)
	    {
	        if (this.do_proper_random) {
	            this.do_proper_random = !this.do_proper_random;
	            return super.nextInt(i);
	        }
	        else {
	            this.do_proper_random = !this.do_proper_random;
	            return 0;
	        }
	    }

}
