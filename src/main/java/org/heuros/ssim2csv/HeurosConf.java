package org.heuros.ssim2csv;

/**
 * Configuration file of the app. 
 * 
 * @author bahadrzeren
 *
 */
public class HeurosConf {
	private String ssim = null;
	private String rotation = null;
	private String carryIn = null;

	private String output = null;

	public String getSsim() {
		return ssim;
	}

	public void setSsim(String ssim) {
		this.ssim = ssim;
	}

	public String getRotation() {
		return rotation;
	}

	public void setRotation(String rotation) {
		this.rotation = rotation;
	}

	public String getCarryIn() {
		return carryIn;
	}

	public void setCarryIn(String carryIn) {
		this.carryIn = carryIn;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
