package org.heuros.ssim2csv;

import java.io.IOException;
import java.util.List;

import org.heuros.conf.HeurosConfFactory;
import org.heuros.data.model.Leg;
import org.heuros.loader.ssim.SsimLoader;
import org.heuros.reporter.legcsv.LegCsvReporter;

/**
 * The main class that is used to start process.
 * 
 * @author bahadrzeren
 */
public class Ssim2Legs {

	public static void main(String[] args) throws IOException {
    	/**
    	 * Load configuration file.
    	 */
		String confFileName = null;
		if ((args != null)
				&& (args.length > 0))
			confFileName = args[0];

		HeurosConf conf = new HeurosConfFactory<HeurosConf>()
								.createConfObject(confFileName, HeurosConf.class);

		if (conf != null) {
			/**
			 * Load input data.
			 */
			List<Leg> legs = new SsimLoader().setSsimFileName(conf.getSsim())
														.setAcRotationFileName(conf.getRotation())
														.setCarryInFileName(conf.getCarryIn())
														.extractData();

			/**
			 * Convert input data into CSV format.
			 */
			new LegCsvReporter().setLegCsvReporter(conf.getOutput())
								.reportData(legs);
		}
    }
}
