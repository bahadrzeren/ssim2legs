package org.heuros.ssim2csv;

import java.io.IOException;
import java.util.List;

import org.heuros.conf.HeurosConfFactory;
import org.heuros.data.model.LegExtensionFactory;
import org.heuros.data.model.LegModel;
import org.heuros.data.model.LegWrapper;
import org.heuros.data.model.LegWrapperFactory;
import org.heuros.loader.ssim.SsimLoader;
import org.heuros.processor.leg.LegProcessor;
import org.heuros.reporter.legcsv.LegCsvReporter;
import org.heuros.rule.LegRuleContext;

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
			List<LegModel> legs = new SsimLoader().setSsimFileName(conf.getSsim())
														.setAcRotationFileName(conf.getRotation())
														.setCarryInFileName(conf.getCarryIn())
														.extractData();

			/**
			 * Map to LegWrapper
			 */
			LegRuleContext legRuleContext = new LegRuleContext();
			LegExtensionFactory legExtensionFactory = new LegExtensionFactory();
			LegWrapperFactory legWrapperFactory = new LegWrapperFactory(legRuleContext, legExtensionFactory);
			List<LegWrapper> legWrappers = new LegProcessor().setRuleContext(legRuleContext)
																.setExtensionFactory(legExtensionFactory)
																.setWrapperFactory(legWrapperFactory)
																.proceed(legs);

			/**
			 * Convert input data into CSV format.
			 */
			new LegCsvReporter().setLegCsvReporter(conf.getOutput())
								.reportData(legWrappers);
		}
    }
}
