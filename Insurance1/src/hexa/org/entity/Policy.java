package hexa.org.entity;

public class Policy {
	
	private int policyId;
	private String policyName;
	private String policyDetails;
	public Policy() {
		super();
	}
	public Policy(int policyId, String policyName, String policyDetails) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.policyDetails = policyDetails;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getPolicyDetails() {
		return policyDetails;
	}
	public void setPolicyDetails(String policyDetails) {
		this.policyDetails = policyDetails;
	}
	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", policyDetails=" + policyDetails + "]";
	}
}