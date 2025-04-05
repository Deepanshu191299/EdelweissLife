package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunicationDetails {

	@JsonProperty("la_details")
	private LaPrSpCommunicationDetails laDetails;

	@JsonProperty("proposer_details")
	private LaPrSpCommunicationDetails proposerDetails;

	@JsonProperty("spouse_details")
	private LaPrSpCommunicationDetails spouseDetails;

	public LaPrSpCommunicationDetails getLaDetails() {
		return laDetails;
	}

	public void setLaDetails(LaPrSpCommunicationDetails laDetails) {
		this.laDetails = laDetails;
	}

	public LaPrSpCommunicationDetails getProposerDetails() {
		return proposerDetails;
	}

	public void setProposerDetails(LaPrSpCommunicationDetails proposerDetails) {
		this.proposerDetails = proposerDetails;
	}

	public LaPrSpCommunicationDetails getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(LaPrSpCommunicationDetails spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	@Override
	public String toString() {
		return "CommunicationDetails [laDetails=" + laDetails + ", proposerDetails=" + proposerDetails
				+ ", spouseDetails=" + spouseDetails + "]";
	}

	public class LaPrSpCommunicationDetails {

		@JsonProperty("communication_details_id")
		private int communicationDetailsId;

		@JsonProperty("ca_address_line1")
		private String caAddressLine1;

		@JsonProperty("ca_address_line2")
		private String caAddressLine2;

		@JsonProperty("ca_address_line3")
		private String caAddressLine3;

		@JsonProperty("ca_address_line4")
		private String caAddressLine4;

		@JsonProperty("ca_city")
		private String caCity;

		@JsonProperty("ca_state")
		private String caState;

		@JsonProperty("ca_pincode")
		private String caPincode;

		@JsonProperty("is_ca_pa_same_yn")
		private String isCaPaSameYn;

		@JsonProperty("pa_address_line1")
		private String paAddressLine1;

		@JsonProperty("pa_address_line2")
		private String paAddressLine2;

		@JsonProperty("pa_address_line3")
		private String paAddressLine3;

		@JsonProperty("pa_address_line4")
		private String paAddressLine4;

		@JsonProperty("pa_city")
		private String paCity;

		@JsonProperty("pa_state")
		private String paState;

		@JsonProperty("pa_pincode")
		private String paPincode;

		@JsonProperty("correspondance_add")
		private String correspondanceAdd;

		@JsonProperty("mobile_number")
		private String mobileNumber;

		@JsonProperty("phone_number_office_std")
		private String phoneNumberOfficeStd;

		@JsonProperty("phone_number_office")
		private String phoneNumberOffice;

		@JsonProperty("phone_number_home_std")
		private String phoneNumberHomeStd;

		@JsonProperty("phone_number_home")
		private String phoneNumberHome;

		@JsonProperty("email_id")
		private String emailId;

		@JsonProperty("facebook_id")
		private String facebookId;

		@JsonProperty("linked_in_id")
		private String linkedInId;

		@JsonProperty("corporate_email_id")
		private String corporateEmailId;
		
		public int getCommunicationDetailsId() {
			return communicationDetailsId;
		}

		public void setCommunicationDetailsId(int communicationDetailsId) {
			this.communicationDetailsId = communicationDetailsId;
		}

		public String getCaAddressLine1() {
			return caAddressLine1;
		}

		public void setCaAddressLine1(String caAddressLine1) {
			this.caAddressLine1 = caAddressLine1;
		}

		public String getCaAddressLine2() {
			return caAddressLine2;
		}

		public void setCaAddressLine2(String caAddressLine2) {
			this.caAddressLine2 = caAddressLine2;
		}

		public String getCaAddressLine3() {
			return caAddressLine3;
		}

		public void setCaAddressLine3(String caAddressLine3) {
			this.caAddressLine3 = caAddressLine3;
		}

		public String getCaAddressLine4() {
			return caAddressLine4;
		}

		public void setCaAddressLine4(String caAddressLine4) {
			this.caAddressLine4 = caAddressLine4;
		}

		public String getCaCity() {
			return caCity;
		}

		public void setCaCity(String caCity) {
			this.caCity = caCity;
		}

		public String getCaState() {
			return caState;
		}

		public void setCaState(String caState) {
			this.caState = caState;
		}

		public String getCaPincode() {
			return caPincode;
		}

		public void setCaPincode(String caPincode) {
			this.caPincode = caPincode;
		}

		public String getIsCaPaSameYn() {
			return isCaPaSameYn;
		}

		public void setIsCaPaSameYn(String isCaPaSameYn) {
			this.isCaPaSameYn = isCaPaSameYn;
		}

		public String getPaAddressLine1() {
			return paAddressLine1;
		}

		public void setPaAddressLine1(String paAddressLine1) {
			this.paAddressLine1 = paAddressLine1;
		}

		public String getPaAddressLine2() {
			return paAddressLine2;
		}

		public void setPaAddressLine2(String paAddressLine2) {
			this.paAddressLine2 = paAddressLine2;
		}

		public String getPaAddressLine3() {
			return paAddressLine3;
		}

		public void setPaAddressLine3(String paAddressLine3) {
			this.paAddressLine3 = paAddressLine3;
		}

		public String getPaAddressLine4() {
			return paAddressLine4;
		}

		public void setPaAddressLine4(String paAddressLine4) {
			this.paAddressLine4 = paAddressLine4;
		}

		public String getPaCity() {
			return paCity;
		}

		public void setPaCity(String paCity) {
			this.paCity = paCity;
		}

		public String getPaState() {
			return paState;
		}

		public void setPaState(String paState) {
			this.paState = paState;
		}

		public String getPaPincode() {
			return paPincode;
		}

		public void setPaPincode(String paPincode) {
			this.paPincode = paPincode;
		}

		public String getCorrespondanceAdd() {
			return correspondanceAdd;
		}

		public void setCorrespondanceAdd(String correspondanceAdd) {
			this.correspondanceAdd = correspondanceAdd;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public String getPhoneNumberOfficeStd() {
			return phoneNumberOfficeStd;
		}

		public void setPhoneNumberOfficeStd(String phoneNumberOfficeStd) {
			this.phoneNumberOfficeStd = phoneNumberOfficeStd;
		}

		public String getPhoneNumberOffice() {
			return phoneNumberOffice;
		}

		public void setPhoneNumberOffice(String phoneNumberOffice) {
			this.phoneNumberOffice = phoneNumberOffice;
		}

		public String getPhoneNumberHomeStd() {
			return phoneNumberHomeStd;
		}

		public void setPhoneNumberHomeStd(String phoneNumberHomeStd) {
			this.phoneNumberHomeStd = phoneNumberHomeStd;
		}

		public String getPhoneNumberHome() {
			return phoneNumberHome;
		}

		public void setPhoneNumberHome(String phoneNumberHome) {
			this.phoneNumberHome = phoneNumberHome;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getFacebookId() {
			return facebookId;
		}

		public void setFacebookId(String facebookId) {
			this.facebookId = facebookId;
		}

		public String getLinkedInId() {
			return linkedInId;
		}

		public void setLinkedInId(String linkedInId) {
			this.linkedInId = linkedInId;
		}

		public String getCorporateEmailId() {
			return corporateEmailId;
		}

		public void setCorporateEmailId(String corporateEmailId) {
			this.corporateEmailId = corporateEmailId;
		}

		@Override
		public String toString() {
			return "LaPrSpCommunicationDetails [communicationDetailsId=" + communicationDetailsId + ", caAddressLine1="
					+ caAddressLine1 + ", caAddressLine2=" + caAddressLine2 + ", caAddressLine3=" + caAddressLine3
					+ ", caAddressLine4=" + caAddressLine4 + ", caCity=" + caCity + ", caState=" + caState
					+ ", caPincode=" + caPincode + ", isCaPaSameYn=" + isCaPaSameYn + ", paAddressLine1="
					+ paAddressLine1 + ", paAddressLine2=" + paAddressLine2 + ", paAddressLine3=" + paAddressLine3
					+ ", paAddressLine4=" + paAddressLine4 + ", paCity=" + paCity + ", paState=" + paState
					+ ", paPincode=" + paPincode + ", correspondanceAdd=" + correspondanceAdd + ", mobileNumber=" + mobileNumber
					+ ", phoneNumberOfficeStd=" + phoneNumberOfficeStd + ", phoneNumberOffice=" + phoneNumberOffice
					+ ", phoneNumberHomeStd=" + phoneNumberHomeStd + ", phoneNumberHome=" + phoneNumberHome
					+ ", emailId=" + emailId + ", facebookId=" + facebookId + ", linkedInId=" + linkedInId
					+ ", corporateEmailId=" + corporateEmailId + "]";
		}

	}

}
