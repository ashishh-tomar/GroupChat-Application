package group.chatting.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class UserThird extends JFrame implements ActionListener,Runnable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel p1;
	JTextArea t1;
	JButton b1;
	static JTextArea t2;
	static ServerSocket skt;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	BufferedWriter writer;
	BufferedReader reader;
	
	UserThird()
	{
		//Start of Panel Section
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,450,70);
		
		add(p1);
		
		//End of panel Section
		//Starting to set an image of cutom size
		
		
		JLabel l1=new JLabel();
		l1.setBounds(5,15,30,30);
		p1.add(l1);    //To add image above panel 
		ImageIcon icon=new ImageIcon(ClassLoader.getSystemResource("group/chatting/application/icons/3.png"));
		Image img=icon.getImage();
		Image newImg=img.getScaledInstance(l1.getWidth(), l1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image=new ImageIcon(newImg);
		l1.setIcon(image);
		
		//Applying Mouse Event to close on Back Arrow
		
		l1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ae)
			{
				System.exit(0);
			}
	
		});

		
		//Dp
		JLabel l2=new JLabel();
		l2.setBounds(50,10,50,50);
		p1.add(l2);    //To add image above panel 
		ImageIcon icon2=new ImageIcon(ClassLoader.getSystemResource("group/chatting/application/icons/group.png"));
		Image img2=icon2.getImage();
		Image newImg2=img2.getScaledInstance(l2.getWidth(), l2.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image2=new ImageIcon(newImg2);
		l2.setIcon(image2);
		
		
		//Name
		JLabel l3=new JLabel("Group");
		l3.setFont(new Font("SAN_SERIF",Font.BOLD,20));
		l3.setForeground(Color.WHITE);
		l3.setBounds(110,20,100,20);
		p1.add(l3);
		
		//Active Status
		JLabel l4=new JLabel("user1,user2,user4");
		l4.setFont(new Font("SAN_SERIF",Font.PLAIN,13));
		l4.setForeground(Color.WHITE);
		l4.setBounds(110,40,160,20);
		p1.add(l4);
	 
		
		// video Icon
				JLabel l5=new JLabel();
				l5.setBounds(320,18,28,28);
				p1.add(l5);    //To add image above panel 
				ImageIcon icon3=new ImageIcon(ClassLoader.getSystemResource("group/chatting/application/icons/video.png"));
				Image img3=icon3.getImage();
				Image newImg3=img3.getScaledInstance(l5.getWidth(), l5.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon image3=new ImageIcon(newImg3);
				l5.setIcon(image3);
				
				//
		
		// Phone Icon
		JLabel l6=new JLabel();
		l6.setBounds(360,18,28,28);
		p1.add(l6);    //To add image above panel 
		ImageIcon icon4=new ImageIcon(ClassLoader.getSystemResource("group/chatting/application/icons/phone.png"));
		Image img4=icon4.getImage();
		Image newImg4=img4.getScaledInstance(l6.getWidth(), l6.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image4=new ImageIcon(newImg4);
		l6.setIcon(image4);
		
		//
		//Three Dots
		JLabel l7=new JLabel();
		l7.setBounds(400,20,10,20);
		p1.add(l7);    //To add image above panel 
		ImageIcon icon5=new ImageIcon(ClassLoader.getSystemResource("group/chatting/application/icons/3icon.png"));
		Image img5=icon5.getImage();
		Image newImg5=img5.getScaledInstance(l7.getWidth(), l7.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image5=new ImageIcon(newImg5);
		l7.setIcon(image5);
		
		//
		
		
		//Text Area to write Mesage
		
		t1=new JTextArea();
		t1.setBounds(5,650,310,40);
		t1.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
		add(t1);
		
		//Send Button
		
		b1=new JButton("Send");
		b1.setBounds(320,650,120,40);
		b1.setBackground(new Color(7,94,84));
		b1.setForeground(Color.white);
		b1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		add(b1);
		b1.addActionListener(this);
		
		
		//Text Area for outgoing and incoming mssges
		
		t2=new JTextArea();
		t2.setBounds(5,75,440,570);
		//t2.setBackground(Color.pink);
		t2.setFont(new Font("SAN_SERIF",Font.PLAIN,25));
		t2.setEditable(false);
		
		
		add(t2);
		t2.setLineWrap(true); //This is bcz our text get cuts from sender side if its too long so to overcome that we have to use 2 following methos.
		t2.setWrapStyleWord(true);
		
		
		//End 
		
		setLayout(null);
		
		setSize(450,700);
		setLocation(960,200);
		
		
		setUndecorated(true);
		setVisible(true);
		
		try {
			@SuppressWarnings("resource")
			Socket socketClient=new Socket("localhost",4010); //for connection
			writer=new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			reader=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	//We have to implement this method because we have to override cause we are implementing 
	public void actionPerformed(ActionEvent ae)
	{
		//When clicking on send button some action has to be performed for thts we use this method
		
		try {
		String str="user3 : \n"+t1.getText();
		
		writer.write(str);
		writer.write("\n");
		
		writer.flush();
		
		}
		catch(Exception e)
		{
			t1.setText("");
			e.printStackTrace();
			
		}
		
		
	}
	
	public void run()
	{
		try {
			String msg="";
			while((msg=reader.readLine())!=null)
			{
				t2.append(msg);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		
		
		UserThird one=new UserThird();
		Thread thread=new Thread(one);
		thread.start();
		one.setVisible(true);
	}
}

