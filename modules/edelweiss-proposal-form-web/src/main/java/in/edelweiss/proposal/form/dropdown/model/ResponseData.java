package in.edelweiss.proposal.form.dropdown.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {
   
	private List<Master> master = new ArrayList<Master>();
   
    public List<Master> getMaster() {
        return master;
    }
    public void setMaster(List<Master> master) {
        this.master = master;
    }
}
