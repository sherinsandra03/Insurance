package hexa.org.dao;

import java.util.List;

import hexa.org.entity.Policy;
import hexa.org.exception.PolicyNotFoundException;

public interface IPolicyService {
	
	boolean createPolicy(Policy policy);
	Policy getPolicy(int policyId) throws PolicyNotFoundException;
	List<Policy> getAllPolicies();
	boolean updatePolicy(Policy policy);
	boolean deletePolicy(int policyId) throws PolicyNotFoundException;

}