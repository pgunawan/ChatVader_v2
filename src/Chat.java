import java.io.*;

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
		frame.setSize(580,500);
		frame.setLayout(new FlowLayout());
		//Set JFrame's default close operation to EXIT_ON_CLOSE
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Initialize JTextArea 
		display = new JTextArea();
		display = new JTextArea(25,43);
		//Set JTextArea so it cannot be edited
		display.setEditable(false);
		//Set JTA to wrap lines
		display.setLineWrap(true);
		display.setText("Welcome to ChatVader (type 'Bye' to quit)\n"
				+ "Use the command 'note: (YOUR NOTE HERE)' to save your note to DropBox\n\n");

		//Create new JScrollPane based on the JTA
		scroll = new JScrollPane(display);

		//Initialize JTextField
		Jinput = new JTextField(36);
		Jinput.setText("");
		Jinput.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				try {
					actionEnter(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

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

	public static void main(String[]args)throws IOException, ClassNotFoundException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Chat();
			}
		});
	}

	@Override
	public void actionPerformed (ActionEvent ae) {
		//If user clicks the send button
		if(ae.getActionCommand().equals("Send")){
			//Set input to JTextField's value
			String input = Jinput.getText();
			//Edit input to slash any special characters, leading/trailing spaces, etc.
			input=input.trim();
			input=input.toLowerCase();
			String tagged;
			try {
				tagged = tagger.tag(input); //POS tagger
				String output = Bot.Bot(tagged);
				Jinput.setText("");
				display.setText(display.getText() + "<User> " +input+ "\n");
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				display.setText(display.getText() + "<Vader>" + output + "\n");
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void actionEnter (KeyEvent ke) throws Exception {
		//If user press enter
		if(ke.getKeyCode()==KeyEvent.VK_ENTER){
			//Set input to JTextField's value
			String input = Jinput.getText();
			//Edit input to slash any special characters, leading/trailing spaces, etc.
			
			input=input.trim();
			input=input.toLowerCase();

			String output = Bot.Bot(input);
			Jinput.setText("");

			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			display.setText(display.getText() + "<User> " +input+ "\n");
			try {
				Thread.sleep(750);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			display.setText(display.getText() + "<Vader> " +output+ "\n");
		}
	}
}
