package in.edelweiss.proposal.form.constants;

import java.util.Date;

/**
 * This class contains constants used in Proposal Form.
 *
 * @author Abhijit AA
 */
public class ProposalFormConstants {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private ProposalFormConstants() {

	}
	
	public static final long CURRENT_TIME_STAMP = new Date().getTime();
	
	public static final String TITLE_MASTER = "Title";
	public static final String GENDER_MASTER = "Gender";
	public static final String MARITAL_STATUS_MASTER = "Marital Status";
	public static final String NATIONALITY_MASTER = "Nationality";
	public static final String AGE_PROOF_MASTER = "Age Proof";
	public static final String EDUCATION_MASTER = "Education";
	public static final String EMPLOYEMENT_TYPE_MASTER = "Employement Type";
	public static final String INCOME_PROOF_MASTER = "Income Proof";
	public static final String ADDRESS_PROOF_MASTER = "Address Proof";
	public static final String IDENTITY_PROOF_MASTER = "Identity Proof";
	public static final String COURSE_DETAIL_EXTREF_CODE = "COURSE_DETAILS";
	
	public static final String EKYC_DETAILS_BY_TXN_ID = "/o/c/ekycdetailses?search=";

}
