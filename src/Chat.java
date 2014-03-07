import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;



public class Chat implements ActionListener{
	
    
    
	//Declare JFrame components
	private JTextArea display;
	private JTextField Jinput;
	private JButton btn;
	private JScrollPane scroll;
	private JFrame frame;
	
    public Chat(){
    	
    	
    	//Create JFrame
    	frame = new JFrame("ChatVader");
    	frame.setSize(700,500);
    	frame.setLayout(new FlowLayout());
    	
    	//Set JFrame's default close operation to EXIT_ON_CLOSE
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	//Initialize JTextArea
    	display = new JTextArea(25,57);
    	//Set JTextArea so it cannot be edited
    	display.setEditable(false);
    	//Set JTA to wrap lines
    	display.setLineWrap(true);
    	
    	//Create new JScrollPane based on the JTA
    	scroll = new JScrollPane(display);
    	
    	//Initialize JTextField
    	Jinput = new JTextField(50);
    	
    	//Initialize JButton
    	btn = new JButton("Send");
    	btn.addActionListener(this);
    	
    	//Add all components to JFrame's content pane
    	frame.getContentPane().add(scroll);
    	frame.getContentPane().add(Jinput);
    	frame.getContentPane().add(btn);
    	
    	
    	
    	//Show the JFrame
    	frame.setVisible(true);
    	
    }

	public static void main(String[]args)throws IOException{
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new Chat();
	            }
	        });
 
        
		/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
        
		do{
			//Adds some delay to make the chatbot seem more realistic
			try {
				Thread.sleep(750);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
			System.out.print("<User>");
			input = br.readLine();
			
			//Edit input to slash any special characters, leading/trailing spaces, etc.
			input=input.trim();
			input=input.toLowerCase();
            
			//Adds some delay to make the chatbot seem more realistic
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
			System.out.println("<Vader>"+Bot.Bot(input));
		}while (!input.contains("bye"));*/
        
	}


	
	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("Welcome to ChatVader (enter 'Bye' to quit the program)");

		//If user clicks the send button
		if(ae.getActionCommand().equals("Send")){
			
			//Set input to JTextField's value
			String input = Jinput.getText();
			String output = Bot.Bot(input);
			
			try {
				Thread.sleep(750);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			display.setText(display.getText() + "<User> " +input+ "\n");
			try {
				Thread.sleep(750);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			display.setText(display.getText() + "<Vader> " +output+ "\n");
			
			
		}
		
	}

}
