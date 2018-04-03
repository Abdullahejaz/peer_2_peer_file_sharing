import java.io.Serializable;
import java.util.List;

public class ModelPeer implements Serializable {

    private String peer_id;
    public static final long serialVersionUID = 1L;
    private String ipAddress;
    private String portNumber;
    private List<String> FilesShared;
    private String pathOfDirectory;

    public ModelPeer(String ipAddress, String portNumber, List<String>filesList, String directory){
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
        this.FilesShared = filesList;
        this.pathOfDirectory = directory;
        this.peer_id = ipAddress+" -- "+portNumber;

    }

    public String getId() {
        return peer_id;
    }

    public void setId(String id) {
        this.peer_id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public List<String> getFilesShared() {
        return FilesShared;
    }

    public void setFilesShared(List<String> filesShared) {
        FilesShared = filesShared;
    }

    public String getPathOfDirectory() {
        return pathOfDirectory;
    }

    public void setPathofDirectory(String pathOfDirectory) {
        this.pathOfDirectory = pathOfDirectory;
    }


}

