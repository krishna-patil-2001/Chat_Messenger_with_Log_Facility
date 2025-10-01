import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class ChatClient
{
    public static void main(String Arg[]) throws Exception
    {
        System.out.println("Marvellous Client is ready to connect with server");

        Socket sobj = new Socket("localhost", 5100);
        System.out.println("Marvellous Client successfully connected with server");

        PrintStream out = new PrintStream(sobj.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(sobj.getInputStream()));
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        // Log file setup
        BufferedWriter logWriter = new BufferedWriter(new FileWriter("ClientChatLog.txt", true));

        System.out.println("----------------------------------------------------");
        System.out.println("Marvellous Chat messenger is ready to use");
        System.out.println("----------------------------------------------------");

        String strToServer, strFromServer;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (!(strToServer = console.readLine()).equalsIgnoreCase("end"))
        {
            out.println(strToServer);

            // Log client message
            String timeStamp = sdf.format(new Date());
            logWriter.write(timeStamp + " | Client: " + strToServer + "\n");
            logWriter.flush();

            strFromServer = in.readLine();
            System.out.println("Server says: " + strFromServer);

            // Log server message
            timeStamp = sdf.format(new Date());
            logWriter.write(timeStamp + " | Server: " + strFromServer + "\n");
            logWriter.flush();
        }

        logWriter.close();
        in.close();
        out.close();
        sobj.close();
    }
}
