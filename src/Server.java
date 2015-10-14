import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;

public class Server implements Runnable{

	public int PORT = 80;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			while(true){
				System.out.println("Listen--");
				Socket socket = serverSocket.accept();
				System.out.println("accept");
				try {
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					DataInputStream in = new DataInputStream(socket.getInputStream());
					String user = in.readUTF();
					String pswd = in.readUTF();
					System.out.print("receive---->"+user+"---"+pswd);
					if(user.equals("123")&&pswd.equals("123")){
						out.writeBoolean(true);
					}else{
						out.writeBoolean(false);
					}
					out.writeUTF("server info");
					out.close();
					in.close();
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("服务器异常："+e2.getMessage());
				}finally {
					socket.close();
					System.out.println("close");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ParseException, IOException, SQLException {
		// TODO Auto-generated method stub
		//创建监听线程
		Thread scuServerThread=new Thread(new Server());
		scuServerThread.start();
	}
}
