package matrixFunctions;

public class Matrix {

	private String newLine = System.getProperty("line.separator");
	private int matrix[][];
	int size;
	int rows;
	
	
	public Matrix(String matrixStr, int size){
		try{
			String lines[] = matrixStr.split(newLine);
			if(lines.length == 0){
				System.err.println("Error with input. At least two lines of input expected.");
				return;
			}
			if (size <= 0){
				System.err.println("Error, matrix must be dimension of at least 1");
				return;
			}
			int matrixHeight = lines.length;
			if(size != matrixHeight){
				System.err.println("Error, number of rows in matrix does not equal matrix dimension provided.");
				return;
			}
			this.size = size;
			this.matrix = new int[this.size][this.size];
			for (int i=0; i<lines.length; i++){
				String rowArr[] = lines[i].split(" ");
				if(rowArr.length != this.size){
					System.err.println("Error, row " + i + " length doesn't match matrix size.");
					return;
				}
				for(int j=0; j<rowArr.length; j++){
					int row = i + 1;
					this.matrix[i][j] = Integer.parseInt(rowArr[j]);
					this.rows = row;
				}
			}
			
		}
		catch(NumberFormatException e){
			System.err.println("Error, input seems to contain invalid characters. Only numbers accepted.");
			return;
		}
		catch (Exception e){
			System.err.println("Error creating matrix");
			return;
		}
		
	}
	
	public String printMatrix(){
		String matrixStr = "";
		for(int i=0; i< this.size; i++){
			for(int j=0; j< this.size; j++){
				if(j > 0){
					matrixStr += " " + this.matrix[i][j];
				}
				else{
					matrixStr += this.matrix[i][j];
				}				
			}
			matrixStr += newLine;
		}
		return matrixStr;
	}
}
