package in.edelweiss.proposal.form.dropdown.model;

import java.util.ArrayList;
import java.util.List;

public class DropdownMaster {
    private Boolean status;
    private List<String> errors = new ArrayList<String>();
    private ResponseData responseData;
    
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public List<String> getErrors() {
        return errors;
    }
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    public ResponseData getResponseData() {
        return responseData;
    }
    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}