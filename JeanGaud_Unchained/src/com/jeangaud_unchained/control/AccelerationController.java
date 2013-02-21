package com.jeangaud_unchained.control;

public class AccelerationController {
	
	private final int MAX_DELTA = 8;
	
	public AccelerationController() {
		
	}
	
	public void computeDeltas(float[] orientation, int[] deltas) {
		deltas[0] = (int) (-(orientation[2]/(Math.PI/2))*MAX_DELTA);
		deltas[1] = (int) ((orientation[1]/(Math.PI/2))*MAX_DELTA);
	}
	
}
