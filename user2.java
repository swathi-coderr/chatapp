import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.DataOutputStream;
import java.io.DataInputStream;
public class user2 extends Frame implements Runnable,ActionListener{
    TextField tf;
    TextArea ta;
    Button send;
    ServerSocket sc;
    Socket s;
    DataInputStream di;
    DataOutputStream dd;
    Thread c;
        user2(){
            tf=new TextField();
            ta=new TextArea();
            send=new Button("send");
            send.addActionListener(this);
            try{
            sc =new ServerSocket(12000);
            s=sc.accept();
            di=new DataInputStream(s.getInputStream());
            dd=new DataOutputStream(s.getOutputStream());
            }
            catch(Exception e){

            }
            add(tf);
            add(ta);
            add(send);


            c=new Thread(this);
            c.setDaemon(true);
            c.start();
           
            setSize(500,500);
            setTitle("user2");
            setLayout(new FlowLayout());
            setVisible(true);
            

        }
        public void actionPerformed(ActionEvent e){
            String msg=tf.getText();
            ta.append("uesr2:"+msg+"\n");
            tf.setText("");
              try{
            dd.writeUTF(msg);
            dd.flush();
              }
              catch(Exception ex){

              }

        }
        public static void main(String[] args){
            new user2();

        }
        public void run(){
            while(true){
                try{
                  String msg=  di.readUTF();
                  ta.append("user1:"+msg+"\n");

                }
                catch(Exception e){
                    
                }
            }
        }
}