package in.edelweiss.tokio.common.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"items"})
public class CustomerLeadEnquiryDetails {

	
	@JsonProperty("items")
	private List<Item> items;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public CustomerLeadEnquiryDetails() {
	}

	/**
	 *
	 * @param lastPage
	 * @param pageSize
	 * @param page
	 * @param totalCount
	 * @param actions
	 * @param items
	 * @param facets
	 */
	public CustomerLeadEnquiryDetails(List<Item> items) {
		super();
		this.items = items;
	}

	

	@JsonProperty("items")
	public List<Item> getItems() {
		return items;
	}

	@JsonProperty("items")
	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(CustomerLeadEnquiryDetails.class.getName()).append('@')
				.append(Integer.toHexString(System.identityHashCode(this))).append('[');
		
		sb.append("items");
		sb.append('=');
		sb.append(((this.items == null) ? "<null>" : this.items));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}