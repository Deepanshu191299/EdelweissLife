package in.edelweiss.proposal.form.dropdown.model;

import java.util.ArrayList;
import java.util.List;

public class Master {
    
	private String type;
    private List<Data> data = new ArrayList<Data>();
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<Data> getData() {
        return data;
    }
    public void setData(List<Data> data) {
        this.data = data;
    }
}
