import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*
* @author Abdullah Ejaz
*
*
* */


public class Server extends Thread implements Runnable{


    public static String REGISTER_ACTION = "register";
    public static String SEARCH_ACTION = "search";
    public static final String SUCCESS = "success";

    private static Map<String, ModelPeer> peersMap = new HashMap<String, ModelPeer>();
    private Socket s;


    public Server(Socket s) {
        this.s = s;
    }

    public static Map<String, ModelPeer> getPeersMap() {
        return peersMap;
    }

    public static void setPeersMap(Map<String, ModelPeer> peersMap) {
        Server.peersMap = peersMap;
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public boolean registration(ModelPeer peer){

        //For calculating the time to register a peer
        final long startTime = System.currentTimeMillis();
        System.out.println("The peer id "  + peer.getId()+ " is getting registered by the Server.");
        boolean registered = false;
        ModelPeer tempPeer = peersMap.get(peer.getId());

        //Peer is registering file for the first time.

        if (tempPeer == null) {

            peersMap.put(peer.getId(), peer);
            registered = true;

        } else
             {

                 tempPeer.getFilesShared().addAll(peer.getFilesShared());
            peersMap.put(peer.getId(), tempPeer);
            registered = true;
        }

        final long endTime = System.currentTimeMillis();
        System.out.println("Server is done with registering peer id  " + peer.getId()+ " in "+(endTime - startTime)+ " milliseconds");
        return registered;
    }

/**********************************************************************************************************************************************************/

    // This method search for the file and return the list
    public List<ModelPeer> searching(String fileName) {
        final long startTime = System.currentTimeMillis();
        System.out.println("Searching for the file " + fileName);
        List<ModelPeer> listOfPeers = new ArrayList<ModelPeer>();

        for (Map.Entry<String, ModelPeer> entry : peersMap.entrySet()) {

            ModelPeer currentPeerModel = entry.getValue();

            // Searching file name
            if (currentPeerModel.getFilesShared().contains(fileName)) {

                listOfPeers.add(currentPeerModel);

            }

        }

        final long endTime = System.currentTimeMillis();
        System.out.println("Search completed for the file " + fileName+ " in  " +(endTime - startTime)+ " milliseconds");
        return listOfPeers;
    }


/**********************************************************************************************************************************************************/

    @Override
    public void run() {

        boolean connected = true;

       do {
           try{
               ObjectInputStream inp = new ObjectInputStream(s.getInputStream());
               ObjectOutputStream oup = new ObjectOutputStream(s.getOutputStream());
               Messages message = (Messages) inp.readObject();
               ResponseFromServer rfs = new ResponseFromServer();


                if (REGISTER_ACTION.equalsIgnoreCase(message.getActionPerformed())){
                    this.registration(message.getModel());

                }else if(SEARCH_ACTION.equalsIgnoreCase(message.getActionPerformed())){
                   List<ModelPeer> listOfPeers = this.searching(message.getFileSearch());
                    rfs.setPeerModelList(listOfPeers);
               }

               rfs.setStatus(SUCCESS);

                //Writing the content
                oup.writeObject(rfs);
                oup.flush();
                inp.close();
                connected = false;


           }
           catch(Exception ex){
               ex.printStackTrace();
           }

       } while(connected);

    }

/**********************************************************************************************************************************************************/

    public static void main(String args[]) throws Exception
    {
        //Creating a connection between server and the client
        ServerSocket ss = new ServerSocket(8899);


        while(true){
            System.out.println("Server waiting to connect to the peer");
            //connecting
            Socket ps = ss.accept();
            new Server(ps).start();
        }



    }
}
