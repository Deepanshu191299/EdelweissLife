package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomizeFeaturesCard {

	@JsonProperty("sectionTagLine")
	private String sectionTagLine;
	
	@JsonProperty("sectionSubTitle")
	private String sectionSubTitle;
	
	@JsonProperty("productCode")
	private String productCode;

	@JsonProperty("sectionTitle")
	private String sectionTitle;
	
	@JsonProperty("shortNote")
	private String shortNote;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("content")
	private String content;

	@JsonProperty("cfcNestedData")
	private List<CFCNestedData> cfcNestedData;
	
	public String getSectionTagLine() {
		return sectionTagLine;
	}

	public void setSectionTagLine(String sectionTagLine) {
		this.sectionTagLine = sectionTagLine;
	}

	public String getSectionSubTitle() {
		return sectionSubTitle;
	}

	public void setSectionSubTitle(String sectionSubTitle) {
		this.sectionSubTitle = sectionSubTitle;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSectionTitle() {
		return sectionTitle;
	}

	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	public String getShortNote() {
		return shortNote;
	}

	public void setShortNote(String shortNote) {
		this.shortNote = shortNote;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<CFCNestedData> getCfcNestedData() {
		return cfcNestedData;
	}

	public void setCfcNestedData(List<CFCNestedData> cfcNestedData) {
		this.cfcNestedData = cfcNestedData;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CFCNestedData {

		@JsonProperty("cfcContent")
		private String cfcContent;

		@JsonProperty("cfcTitle")
		private String cfcTitle;

		@JsonProperty("Image")
		private FeatureImage featureImage;

		@JsonProperty("enterTabId")
		private String enterTabId;

		public String getCfcContent() {
			return cfcContent;
		}

		public void setCfcContent(String cfcContent) {
			this.cfcContent = cfcContent;
		}

		public String getCfcTitle() {
			return cfcTitle;
		}

		public void setCfcTitle(String cfcTitle) {
			this.cfcTitle = cfcTitle;
		}

		public FeatureImage getFeatureImage() {
			return featureImage;
		}

		public void setFeatureImage(FeatureImage featureImage) {
			this.featureImage = featureImage;
		}

		public String getEnterTabId() {
			return enterTabId;
		}

		public void setEnterTabId(String enterTabId) {
			this.enterTabId = enterTabId;
		}
		
		

		@Override
		public String toString() {
			return "CFCNestedData [cfcContent=" + cfcContent + ", cfcTitle=" + cfcTitle + ", featureImage="
					+ featureImage + ", enterTabId=" + enterTabId + "]";
		}
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class FeatureImage {

		@JsonProperty("groupId")
		private String groupid;

		@JsonProperty("alt")
		private String alt;

		@JsonProperty("name")
		private String name;

		@JsonProperty("width")
		private String width;

		@JsonProperty("description")
		private String description;

		@JsonProperty("title")
		private String title;

		@JsonProperty("type")
		private String type;

		@JsonProperty("uuid")
		private String uuid;

		@JsonProperty("fileEntryId")
		private String fileEntryId;

		@JsonProperty("resourcePrimKey")
		private String resourcePrimKey;

		@JsonProperty("url")
		private String url;

		@JsonProperty("height")
		private String height;

		public String getGroupid() {
			return groupid;
		}

		public void setGroupid(String groupid) {
			this.groupid = groupid;
		}

		public String getAlt() {
			return alt;
		}

		public void setAlt(String alt) {
			this.alt = alt;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getWidth() {
			return width;
		}

		public void setWidth(String width) {
			this.width = width;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getFileEntryId() {
			return fileEntryId;
		}

		public void setFileEntryId(String fileEntryId) {
			this.fileEntryId = fileEntryId;
		}

		public String getResourcePrimKey() {
			return resourcePrimKey;
		}

		public void setResourcePrimKey(String resourcePrimKey) {
			this.resourcePrimKey = resourcePrimKey;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getHeight() {
			return height;
		}

		public void setHeight(String height) {
			this.height = height;
		}

		@Override
		public String toString() {
			return "FeatureImage [groupid=" + groupid + ", alt=" + alt + ", name=" + name + ", width=" + width
					+ ", description=" + description + ", title=" + title + ", type=" + type + ", uuid=" + uuid
					+ ", fileEntryId=" + fileEntryId + ", resourcePrimKey=" + resourcePrimKey + ", url=" + url
					+ ", height=" + height + "]";
		}
		
	}

	@Override
	public String toString() {
		return "CustomizeFeaturesCard [sectionTagLine=" + sectionTagLine + ", sectionSubTitle=" + sectionSubTitle
				+ ", productCode=" + productCode + ", sectionTitle=" + sectionTitle + ", shortNote=" + shortNote
				+ ", title=" + title + ", content=" + content + ", cfcNestedData=" + cfcNestedData + "]";
	}
}