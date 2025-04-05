package in.edelweiss.tokio.common.model;

import java.util.List;

public class FormView {

	private String title;
	private List<Field> fields;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	@Override
	public String toString() {
		return "FormView [title=" + title + ", fields=" + fields + "]";
	}
}
