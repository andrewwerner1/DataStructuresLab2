package matrixFunctions;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * @author Andrew Werner
 * This class is used to facilitate reading from input file
 * and outputing to output file. This class has two instance variables,
 * inFilePath and outFilePath, that correspond to the program's current 
 * input file path and output file path, respectively.
 * 
 * Additionally, class contains two methods, readInputString and writeOutput.
 * The first of these reads text from the input file and the second of
 * these prints text to the output file. 
 *
 */

public class FileHandler {

	// instance variables
	private String inFilePath = "";
	private String outFilePath = "";
	
	/**
	 * 
	 * @return Returns text contained in input file. 
	 * For this program, expected input
	 * is postfix expression.
	 */
	public String readInputString() {
		if(inFilePath.isEmpty()){
			return null;
		}
		String inputPostFix = "";
		try {
			inputPostFix = new String(Files.readAllBytes(Paths.get(inFilePath)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			return null;
		} 
		
		return inputPostFix;
	}

	/**
	 * 
	 * @param postFixEvalStr. For this program, postFixEvalStr represents sequence of commands to 
	 * to evaluate postFix expression
	 * @return true if successfully writes output to output file, false otherwise.
	 */
	public boolean writeOutput(String postFixEvalStr) {
		if(outFilePath.isEmpty() || postFixEvalStr == null ||postFixEvalStr.isEmpty()){
			return false;
		}
		try{
			Files.write(Paths.get(outFilePath), postFixEvalStr.getBytes());
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
		
		return true;
	}

	// getters and setters for two instance variables below
	
	public String getInputFilePath() {
		return inFilePath;
	}

	public void setInputFilePath(String inFilePath) {
		this.inFilePath = inFilePath;
	}

	public String getOutputFilePath() {
		return outFilePath;
	}

	public void setOutputFilePath(String outFilePath) {
		this.outFilePath = outFilePath;
	}

}
