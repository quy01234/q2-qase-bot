package piotrrr.thesis.common.combat;

import soc.qase.tools.vecmath.Vector3f;

public class FiringInstructions {

	public Vector3f fireDir;
	
	public boolean doFire = true;

	public FiringInstructions(Vector3f fireDir) {
		this.fireDir = fireDir;
	}

}