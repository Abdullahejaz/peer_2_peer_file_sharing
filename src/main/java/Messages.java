import java.io.Serializable;

public class Messages implements Serializable {


    private ModelPeer model;
    private String fileSearch;
    private String actionPerformed;

    public ModelPeer getModel() {
        return model;
    }

    public void setModel(ModelPeer model) {
        this.model = model;
    }

    public String getFileSearch() {
        return fileSearch;
    }

    public void setFileSearch(String fileSearch) {
        this.fileSearch = fileSearch;
    }

    public String getActionPerformed() {
        return actionPerformed;
    }

    public void setActionPerformed(String actionPerformed) {
        this.actionPerformed = actionPerformed;
    }
}
