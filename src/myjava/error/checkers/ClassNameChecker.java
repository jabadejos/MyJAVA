/**
 * 
 */
package myjava.error.checkers;

import myjava.error.BuildChecker;
import myjava.error.ErrorRepository;
import myjava.error.ParserHandler;

/**
 * Checks if the class name is consistent with the file name

 *
 */
public class ClassNameChecker implements IErrorChecker {
	private final static String TAG = "MyJAVAProg_ClassNameChecker";
	
	private String className;
	private boolean successful = true;
	
	public ClassNameChecker(String readClassName) {
		this.className = readClassName;
	}
	
	@Override
	public void verify() {
		if(this.className.equals(ParserHandler.getInstance().getCurrentClassName()) == false) {
			this.successful = false;
			String additionalMsg = "Class name is " +this.className+ " while file name is " +ParserHandler.getInstance().getCurrentClassName();
			BuildChecker.reportCustomError(ErrorRepository.INCONSISTENT_CLASS_NAME, additionalMsg);
		}
	}
	
	/*
	 * Corrects the class name so that the semantics analyzer can continue
	 */
	public String correctClassName() {
		if(this.successful) {
			return this.className;
		}
		else {
			return ParserHandler.getInstance().getCurrentClassName();
		}
	}
}