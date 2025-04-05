package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseData2 {

	@JsonProperty("Id")
	public String id;

	@JsonProperty("Status")
	public int status;

	@JsonProperty("Code")
	public Object code;

	@JsonProperty("Links")
	public Object links;

	@JsonProperty("Title")
	public String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getCode() {
		return code;
	}

	public void setCode(Object code) {
		this.code = code;
	}

	public Object getLinks() {
		return links;
	}

	public void setLinks(Object links) {
		this.links = links;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ResponseData2 [id=" + id + ", status=" + status + ", code=" + code + ", links=" + links + ", title="
				+ title + "]";
	}

}
