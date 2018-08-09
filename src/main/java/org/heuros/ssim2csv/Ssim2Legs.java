package org.heuros.ssim2csv;

import java.io.IOException;

import org.heuros.conf.HeurosConfFactory;
import org.heuros.core.base.Loader;
import org.heuros.core.base.Reporter;
import org.heuros.data.model.LegImpl;
import org.heuros.director.DataTransformDirector;
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

			Loader<LegImpl> loader = new SsimLoader(conf.getSsim(),
												conf.getRotation(),
												conf.getCarryIn());

			Reporter<LegImpl> reporter = new LegCsvReporter(conf.getOutput());

			new DataTransformDirector<LegImpl>().registerLoader(loader)
											.registerReporter(reporter)
											.proceed();
		}
    }
}
