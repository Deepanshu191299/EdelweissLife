package in.edelweiss.tokio.common.model;

import java.util.Map;

public class Field {

	private String hidden;
	private String fieldReference;
	private String label;
	private String placeHolder;
	private String required;
	private String text;
	private String type;
	private String errorMessage;
	private Map<String, String> optionValues;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public String getFieldReference() {
		return fieldReference;
	}
	public void setFieldReference(String fieldReference) {
		this.fieldReference = fieldReference;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getPlaceHolder() {
		return placeHolder;
	}
	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Map<String, String> getOptionValues() {
		return optionValues;
	}
	public void setOptionValues(Map<String, String> optionValues) {
		this.optionValues = optionValues;
	}
	@Override
	public String toString() {
		return "Field [hidden=" + hidden + ", fieldReference=" + fieldReference + ", label=" + label + ", placeHolder="
				+ placeHolder + ", required=" + required + ", text=" + text + ", type=" + type + ", errorMessage="
				+ errorMessage + ", optionValues=" + optionValues + "]";
	}

}
