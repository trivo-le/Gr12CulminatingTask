public class Face {

	//Instance Variables
	private String eyeColour, hairColour, noseColour, lipColour;
	private int hairLength, dimension, eye1, eye2; 
	public int lipLength;
	public static int lipTips[] = new int[2];
	private static String[] colours = new String[]{"Erase","Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Magenta", "Black"};
	
	//Constructors
	public Face () {
		eyeColour = "Green";
		hairColour = "Black";
		noseColour = "Yellow";
		lipColour = "Red";
		hairLength = 1;
		dimension = 9;
		eye1 = 2; 
		eye2 = 6;
		lipLength = 5;
		}
	
	public Face (int[][]image) {
		eyeColour = findEyeColour(image);
		hairColour = findHairColour(image);
		noseColour = colours[image[image.length/2][image.length/2]];
		lipColour = colours[image[image.length-2][lipInfo(image)[0]]];
		hairLength = findHairLength(image);
		dimension = image.length;
		eye1 = findEyes(image)[0];
		eye2 = findEyes(image)[1];
		lipLength = lipInfo(image)[1];
		
		
		}
	
	//Assisting methods for the above constructor with a two dimensional array in its parameter
	public static int findHairLength(int[][]image) {
	//Finds the hair length from the "image" passed to its parameters and returns the value to the constructor	
		
		int length = 0;
		
		for (int row = 0; row < image.length; row++) {
			
			if (image[row][0]==(image[0][0]) && image[0][0]!=0) {
				length++;
				}//if
			
			else { break; }
			}//for
		
	return length;
	}
	
	public static String findEyeColour (int[][]image) {
	//Returns the eye colour of the image to the constructor	
		
		String foundEyeColour = null;
		
		foundEyeColour = colours[image[2][findEyes(image)[0]]];
		
		return foundEyeColour;
		}
	
	public static String findHairColour(int[][]image ) {

	//Returns a string representing the colour of the hair from the image passed to its parameters
		
		String foundHairColour = null;

		if (image[0][0]==0) {
			foundHairColour = "Bald";
			return foundHairColour;
			}
		
		else {
		for (int col = 0; col < colours.length; col++) {
			if (image[0][0]!=0 && image[0][0]==col) {
				foundHairColour = colours[col];
				}//if
			}//col
		}//else
		
		return foundHairColour;
		}
	
	public static int[] findEyes (int[][]image) {
	//Returns the positions of the first and second eye from the image passed to its parameters	
		
		int eyePositions[] = new int[2];
		int index = 0;
		
		for (int col = 1; col < image.length-1; col++) {
			
			//If the eyes are not the colour "erase", meaning the eyes are there, then place the position in the array
			if (image[2][col]!=0) {
				eyePositions[index] = col;
				index++;
				}
			}//col
		
		return eyePositions;
		}//findEyes
	
	public static int[] lipInfo (int[][]image) {
	//Finds the tips of the lips and returns it as an array of integers
		
		int length = 0;
		int[]lipInfo = new int[2];
		
		for (int col = 1; col < image.length-1; col++) {
			
			//If pixel is coloured, it is part of the lip
			if (image[image.length-2][col]!=0) {
				length++;
				lipInfo[0]=col;
				}//if
			
			}//for
		
		lipInfo[1]=length;	
		return lipInfo;
		}//findLips
	
	//Getters/Setters
	public String getEyeColour() {
		return eyeColour;
		}
	
	public int getHairLength() {
		return hairLength;
		}
	
	public String getHairColour() {
		return hairColour;
		}
	
	public void setEyeColour(String eyeColour) {
		this.eyeColour = eyeColour;
		}
	
	public void setHairLength(int hairLength) {
		if (hairLength <= dimension) {
			this.hairLength = hairLength;
			}
		else { System.out.println("Invalid"); }
		}//setHairLength
	
	public void setHairColour(String hairColour) {
		this.hairColour = hairColour;
		}
	
	public void setDimension (int dimension) {
		
		if (dimension == 7 || dimension == 9 || dimension == 11) {
			this.dimension = dimension;
			}//if
		}
	
	//Geometrics (Measuring distances)
	public int eyeWidth () {
	//Returns the number of pixels between the eyes of the implicit Face as an integer value
		return eye2-eye1-1;
		}
	
	public int topOfForeheadToChin () {
	//Returns the vertical distance from the forehead to the chin as an integer value 
		
		//If bald
		if (getHairLength()==0) {
			return dimension;
			}
		
		else { return dimension-1; }
		}
	
	public double eyesToNose() {
	//Returns the distance between one of the eyes to the nose as a double
		
		int x = (dimension/2)-eye1;
		int y = (dimension/2)-2;
		
		return (Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)));
		}
	
	public boolean equals (Face face) {
	//Compares the features of the face, specifically its geometrics to determine if there is a match	
		
		if (this.eyeWidth() == face.eyeWidth() &&
			this.topOfForeheadToChin() == face.topOfForeheadToChin()&&
			this.eyesToNose() == face.eyesToNose() &&  
			this.hairLength == face.hairLength &&
			this.lipLength == face.lipLength && 
			this.hairColour.equals(face.hairColour) &&
			this.dimension == face.dimension && 
			this.eyeColour.equals(face.eyeColour) &&	
			this.lipColour.equals(face.lipColour) && 
			this.noseColour.equals(face.noseColour) && 
			this.eye1 == face.eye1 &&
			this.eye2 == face.eye2) {
			return true;
			}
		
		else { return false; }
	}//equals
	
	public String toString() {
		return "Eye Colour: " + eyeColour +
			"\nEye Width: " + eyeWidth() +
			"\nHair Colour: " + hairColour +
			"\nHair Length: " + hairLength +
			"\nNose Colour: " + noseColour +
			"\nLip Colour: " + lipColour +
			"\nLip Length: " + lipLength +
			"\nForehead to Chin: " + topOfForeheadToChin() +
			"\nEyes to Nose: " + eyesToNose() +
			"\nDimension " + dimension +
			"\n " + eye1 + "\n" + eye2; 
		
		}
	
}
