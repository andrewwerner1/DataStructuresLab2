package matrixFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Properties;

public class Driver {

	private static FileHandler fileHandler = new FileHandler();
	private static String newLine = System.getProperty("line.separator");

	public static void main(String[] args) {
		System.out.println("Program to initiate execution");

		boolean configResult = setPropertiesFromConfig();
		if (!configResult) {
			System.err.println("Error reading config file");
			return;
		}
		String inputTxt = fileHandler.readInputString();
		if (inputTxt == null || inputTxt.isEmpty()) {
			System.err.println("Error reading input file or input file is empty.");
			return;
		}
		ArrayList<Matrix> matrices;
		matrices = parseInputText(inputTxt);
		if (matrices == null) {
			System.err.println("Error parsing input file");
			return;
		}
		String output = produceOutputTxt(matrices);
		if (output == null || output.isEmpty()) {
			System.err.println("Error producing output text");
			return;
		}
		boolean writeResult = fileHandler.writeOutput(output);
		if (!writeResult) {
			System.err.println("Error writing to output file");
			return;
		}
		System.out.println("Program Executed Successfully");
		return;
	}

	private static ArrayList<Matrix> parseInputText(String inputTxt) {
	    ArrayList<Matrix> matrices = new ArrayList<Matrix>();
		try {
			BufferedReader rdr = new BufferedReader(new StringReader(inputTxt));
			int matrixSize = 0;
			String matrixStr = "";
			for (String line = rdr.readLine(); line != null; line = rdr.readLine()) {
				if(line.trim().isEmpty()){
					continue;
				}
				String matrixSizeStr = line.trim();
				matrixSize = Integer.parseInt(matrixSizeStr);
				if (matrixSize < 1) {
					System.err.println("Input matrix must be of at least size 1");
					return null;
				}
				for (int i = 0; i < matrixSize; i++) {
					line = rdr.readLine();
					if (line == null || line.isEmpty()) {
						System.err.println("Input text is invalid.");
						return null;
					}
					matrixStr += line + newLine;
				}
				Matrix matrix = new Matrix(matrixStr, matrixSize);
				if (matrix.rows == 0 || matrix.size == 0 || matrix.rows != matrix.size || matrixSize != matrix.size) {
					System.err.println("Error with input file. Specified matrix size doesn't match matrix's dimensions.");
					return null;
				}
				matrices.add(matrix);
				matrixStr = "";
				matrixSize = 0;
			}
			rdr.close();
		} catch (Exception e) {
			System.err.println("Error parsing input text. Exception thrown");
			System.err.println(e.getMessage() + " " + e.getStackTrace());
			return null;
		}
		return matrices;
	}


	private static String produceOutputTxt(ArrayList<Matrix> matrices) {
		String output = "";
		int i = 1;
		for (Matrix matrix : matrices) {
			output += matrix.printMatrix();
			if (i != matrices.size()) {
				output += newLine;
			}
			i++;
		}
		return output;
	}

	/**
	 * pulls input file path and output file path from config.properties sets
	 * input file path and output file path values in fileHandler object
	 * 
	 * @return
	 */
	private static boolean setPropertiesFromConfig() {
		File configFile = new File("matrixFunctions/config.properties");

		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);

			String inputFilePath = props.getProperty("InputFilePath");
			String outputFilePath = props.getProperty("OutputFilePath");

			if (inputFilePath == null || inputFilePath.isEmpty()) {
				System.err.println("Problem finding an input File Path in config file");
				return false;
			}
			if (outputFilePath == null || outputFilePath.isEmpty()) {
				System.err.println("Problem finding an output File Path in config file");
				return false;
			}

			// System.out.print("input file path: " + inputFilePath);
			// System.out.print("output file path: " + outputFilePath);

			fileHandler.setInputFilePath(inputFilePath);
			fileHandler.setOutputFilePath(outputFilePath);

			reader.close();
		} catch (Exception ex) {
			System.err.println(ex);
			return false;
			// file does not exist
		}
		return true;
	}
}
