import java.net.Socket;
import java.util.logging.Handler;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicSplitPaneUI.BasicHorizontalLayoutManager;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class client extends JFrame {


    Socket socket;
    BufferedReader br;
    PrintWriter out;
    // declear component // 
    private JLabel heading = new JLabel("Client Area");
    private JTextArea messageArea = new JTextArea();
    private JTextField messageInput = new JTextField();
    private Font font = new Font("Roboto",Font.PLAIN,20);


// constructor  
    public client(){
        try{
            System.out.println("sending request to server");
        socket=new Socket("127.0.0.1",7777);
        System.out.println("connection is done ");
        
        // br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // out=new PrintWriter(socket.getOutputStream());
        createGUI();
        hangleEvent();
        // startReading();
        // startWriting();


        }catch(Exception e){

        }
    }
private void hangleEvent() {
    messageInput.addKeyListener(new KeyListener(){

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            System.out.println("key relesed"+e.getKeyCode());
            if(e.getKeyCode()==10){
                System.out.println("you hava press enter button");
            }
            
        }

    });

    
    }
private void createGUI() {
    // write   gui code .
    this.setTitle("Client Message [End]");
    this.setSize(600,700);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // codeing for component //

    heading.setFont(font);
    messageArea.setFont(font);
    messageInput.setFont(font);
    

    heading.setIcon(new ImageIcon("images1.jpg"));
    heading.setHorizontalAlignment(SwingConstants.CENTER);
    heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    this.setVisible(true);



    // frame ka layout  ... 

    this.setLayout(new BorderLayout());
    // adding the component 
    this.add(heading,BorderLayout.NORTH);
    this.add(messageArea,BorderLayout.CENTER);
    this.add(messageInput,BorderLayout.SOUTH);

    this.add(heading,BorderLayout.NORTH);


    
    }
    // method 
    public void startReading(){
        // thread red ker k data rahaga   
        Runnable r1=()->{
        System.out.println("reader start ..");
        try{
        while(true)
        {
            
            String msg = br.readLine();
            if(msg.equals("exit"))
            {
                System.out.println("server terminated the chat");
                break;
  
            }
            System.out.println("server :"+msg);
          
        }
    }catch(Exception e){
        e.printStackTrace();
    }
        }; 
        new Thread(r1).start();
      }

// method 
      public void startWriting(){
        // data user se lega and then send kero clint ko
        Runnable r2=()->{
            System.out.println("writer started.....");
            try{
            while(true){
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    String content= br1.readLine();
                 out.println(content);
                 out.flush();
                }
            }catch(Exception e){
                // todo handel  exception 
                e.printStackTrace();
            }
    
        };
        new Thread(r2).start();
        }
    public static void main(String[] args) {
    System.out.println("This is client side ");

    new client();
   
    }

}
