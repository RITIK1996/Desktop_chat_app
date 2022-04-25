import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.net.*;

class server{

    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    private Thread thread;
    // constructor..

    public server()
    {
        try{
     server=new ServerSocket(7777);
     System.out.println("server is ready to acept connection ");
     System.out.println("waiting.......");
       socket=server.accept();

      br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
       out=new PrintWriter(socket.getOutputStream());
 
       startReading();
       startWriting();
    }catch(Exception e){
        e.printStackTrace();
    }
}


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
              System.out.println("client terminated the chat");
              break;

          }
          System.out.println("client :"+msg);
        }
      }
       catch(Exception e){
       e.printStackTrace();
       }
    }; 
      new Thread(r1).start();
    
    }
    public void startWriting(){
    // data user se lega and then send kero clint ko
    Runnable r2=()->{
        System.out.println("writer started.....");
        while(true){
            try{
                BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                String content= br1.readLine();
             out.println(content);
             out.flush();
            }catch(Exception e){
                // todo handel  exception 
                e.printStackTrace();
            }
        }

    };
    new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("This is server .... going to start");
        new server();
    }
}