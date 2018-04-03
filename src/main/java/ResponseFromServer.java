import java.io.Serializable;
import java.util.List;

public class ResponseFromServer implements Serializable{

    public static String status;
    public List<ModelPeer> peerModelList;

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        ResponseFromServer.status = status;
    }

    public List<ModelPeer> getPeerModelList() {
        return peerModelList;
    }

    public void setPeerModelList(List<ModelPeer> peerModelList) {
        this.peerModelList = peerModelList;
    }
}
