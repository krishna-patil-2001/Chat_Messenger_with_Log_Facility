import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class ChatServer
{
    public static void main(String Arg[]) throws Exception
    {
        ServerSocket ssobj = new ServerSocket(5100);
        System.out.println("Marvellous server is waiting at port number 5100");
        
        Socket sobj = ssobj.accept();
        System.out.println("Marvellous Server successfully connected with the client");

        PrintStream out = new PrintStream(sobj.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(sobj.getInputStream()));
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        // Log file setup
        BufferedWriter logWriter = new BufferedWriter(new FileWriter("ServerChatLog.txt", true)); 

        System.out.println("----------------------------------------------------");
        System.out.println("Marvellous Chat messenger is ready to use");
        System.out.println("----------------------------------------------------");

        String strFromClient, strToClient;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while ((strFromClient = in.readLine()) != null)
        {
            String timeStamp = sdf.format(new Date());
            System.out.println("Client says : " + strFromClient);

            // Write to log
            logWriter.write(timeStamp + " | Client: " + strFromClient + "\n");
            logWriter.flush();

            System.out.print("Enter message for client: ");
            strToClient = console.readLine();
            out.println(strToClient);

            // Write to log
            timeStamp = sdf.format(new Date());
            logWriter.write(timeStamp + " | Server: " + strToClient + "\n");
            logWriter.flush();
        }

        logWriter.close();
        in.close();
        out.close();
        sobj.close();
        ssobj.close();
    }
}
