package cfg.op.impl.job;

import java.io.File;
import java.util.Arrays;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cfg.op.def.job.ImageRecognition;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * @author dizisa
 *
 */
public class ImageRecognitionImpl implements ImageRecognition {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		
	}
	
	/**
	 * 
	 */
	public void run() {
		
		
		Tesseract tesseract = new Tesseract();

		tesseract.setDatapath("C:\\tessdata-main\\tessdata-main");
		tesseract.setLanguage("ell");
		tesseract.setOcrEngineMode(1);
//		tesseract.setVariable("load_system_dawg", "0");
//		tesseract.setVariable("load_freq_dawg", "0");
		tesseract.setConfigs(Arrays.asList("bazaar"));

		String text = null;
		try {
			text = tesseract.doOCR(new File("C:\\Users\\dizisa\\eclipse-workspace\\test\\agdi\\src\\main\\resources\\images\\deltio1.jpg"));
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.print(text);
	}

}
