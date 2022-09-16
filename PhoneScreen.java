import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PhoneScreen implements ActionListener{
	
	private static JFrame frame;
	private static JPanel panel, panel1, panel2, panel3;
	private JSlider slider;
	private JButton b1, b2, b3, unlock;
	private static JButton[] face;
	private JComboBox comboBox;
	private static String[] colours;
	private static int dimension;
	private JLabel label;
	private JTextArea textArea;
	private static JTextField textField;
	private static Color[] colors = {new Color(238,238,238),Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.BLACK};
	
	public PhoneScreen() {
		
	//Set the frame
	frame = new JFrame("Phone");
	frame.setSize(300,600);
	
	//Add panel to the frame
	panel = new JPanel();
	panel.setLayout(new BorderLayout());
	frame.add(panel);
	
	//Add panel1 and panel2 to panel
	panel1 = new JPanel();
	panel.add(panel1,BorderLayout.CENTER);
	panel2 = new JPanel();
	panel.add(panel2, BorderLayout.SOUTH);
	panel3 = new JPanel();
	
	label = new JLabel("Swipe to unlock");
	panel1.add(label);
	
	//add slider
	slider = new JSlider(0,200,0);
	//If slider is all the way to the right, give user options to buttons
	slider.addChangeListener(new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent arg0) {
			
			if (slider.getValue()==200) {
				placeButton(panel2,b1);
				placeButton(panel2,b2);
				placeButton(panel2,b3);
				slider.setVisible(false);
				panel2.remove(slider);
				label.setText("Choose dimensions for your face");
				}//if
			}//stateChanged
		});
	panel2.add(slider);	
	
	//Add buttons with action listeners
	b1 = new JButton("7");
	b2 = new JButton("9");
	b3 = new JButton("11");
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	
	//Add text field
	textField = new JTextField();
	
	//Add unlock button
	unlock = new JButton("unlock");
	unlock.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Face check = new Face(convertToIntTwoD());
			Face correct = new Face();
			
			System.out.println(check);
			System.out.println(correct);
			
			if (check.equals(correct)==true) {
				textField.setText("Success");
				}
			
			else {textField.setText("Fail");}
			
		}
		
	});
	
	//Close, and set visible
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new PhoneScreen();

		}

	public static void placeButton(JPanel panel, JButton button) {
	//Will add a given button to a panel passed to its parameters
		panel.add(button);
		}
	
	public static void placeTextArea(JTextArea textArea) {
		textArea = new JTextArea("Place eyes on the third row, "
				+ "\nHair on the outer edges (must be uniform)."
				+ "\nThe mouth should be a straight line, placed on the second last row"
				+ "\nThe nose should be in the middle");
		panel3.add(textArea);
		panel.add(panel3);
		}
	
	public static void removeLabel(JPanel panel, JLabel label) {
	//Removes the label from a panel, both of which are passed to its parameters	
		panel.remove(label);
		label.setVisible(false);
		}
	
	public static int[][] convertToIntTwoD () {
	//Converts the grid of buttons into a two dimensional array of integers to be passed to the face class	
		
	Color[][]faceColours = new Color[dimension][dimension];
	int[][] faceInt = new int[dimension][dimension];
	int counter = 0;
	
	for (int row = 0; row < dimension; row++) {
		for (int col = 0; col < dimension; col++) {
				
			//Set colours of the GUI's grid into the array of colours
			faceColours[row][col] = face[counter].getBackground();
				
			for (int index = 0; index < colours.length; index++) {
				
				//Match the colours to corresponding indices to fill the integer array
				if (faceColours[row][col]==colors[index]) {
						
					faceInt[row][col]=index;
					break;
					}//if
				}//for
			
			counter++;
			}//col
		}//row
	
		return faceInt;
		}//convertToIntoTwoD
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//When selecting the size of the grid face
		if (arg0.getSource()==b1||arg0.getSource()==b2||arg0.getSource()==b3) {
			
			dimension = Integer.valueOf(arg0.getActionCommand());
		
			//Add the grid, remove label, add textarea
			panel1.setLayout(new GridLayout(dimension,dimension));
			removeLabel(panel1,label);
			
			placeTextArea(textArea);
			
			face = new JButton[dimension*dimension];
			
			//Create buttons
			for (int index = 0; index < dimension*dimension; index++) {
				face[index] = new JButton(String.valueOf(index));
				face[index].setOpaque(true);
				face[index].addActionListener(this);
				placeButton(panel1,face[index]);
				
				}//for
			
			//Add colours for  the use to use to colour their "face"
			colours = new String[]{"Erase","Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Magenta", "Black"};
			comboBox = new JComboBox(colours);
			panel2.removeAll();
			panel2.add(comboBox);
			
			//Add Unlock and text field
			panel2.add(unlock);
			panel2.add(textField);
			
			}//if
		
		//Change colour of the button that was clicked
		else {
			face[Integer.valueOf(arg0.getActionCommand())].setBackground(colors[comboBox.getSelectedIndex()]);
			}
		
		
	}
	

	
	
	
}
