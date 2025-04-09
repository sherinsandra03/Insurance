package hexa.org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hexa.org.entity.Policy;
import hexa.org.exception.PolicyNotFoundException;
import hexa.org.util.DBConnection;

public class InsuranceServiceImpl implements IPolicyService {
	
	private Connection con;

	public InsuranceServiceImpl() {
		super();
		con = DBConnection.getConnection();

	}

	List<Policy> policyList = new ArrayList<>();
	
	@Override
	public boolean createPolicy(Policy policy) {
		boolean flag=false;
		try {
			PreparedStatement ps = con.prepareStatement("insert into policy(policyId,policyName,policyDetails) values(?,?,?)");
			ps.setInt(1, policy.getPolicyId());
			ps.setString(2, policy.getPolicyName());
			ps.setString(3,policy.getPolicyDetails());
			ps.executeUpdate();
			flag=true;
			}catch(SQLException se) {
				System.out.println("Error while inserting policy");
				se.printStackTrace();
			}
		return flag;
	}

	@Override
	public Policy getPolicy(int policyId) throws PolicyNotFoundException {
		Policy policy=null;
		try {
			PreparedStatement ps=con.prepareStatement("select * from policy where policyId=?");
			ps.setInt(1, policyId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				policy = new Policy(rs.getInt("PolicyId"),rs.getString("policyName"),rs.getString("policyDetails"));
			}
			else {
				throw new PolicyNotFoundException("Policy not found");
			}
		}catch(SQLException se) {
			System.out.println("Error while getting policy details");
			se.printStackTrace();
		}
		return policy;
	}

	@Override
	public List<Policy> getAllPolicies() {
		Policy policy=null;
		try {
			PreparedStatement ps=con.prepareStatement("select * from policy");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				policy = new Policy(rs.getInt("policyId"),rs.getString("policyName"), rs.getString("policyDetails"));
				policyList.add(policy);
			}
		}catch(SQLException se) {
			System.out.println("Error while getting all policies");
			se.printStackTrace();
		}
		return policyList;
	}

	@Override
	public boolean updatePolicy(Policy policy) {
		boolean flag=false;
		try {
			PreparedStatement ps=con.prepareStatement("update policy set policyName=?, policyDetails=? where policyId=?");
			ps.setString(1, policy.getPolicyName());
			ps.setString(2, policy.getPolicyDetails());
			ps.setInt(3, policy.getPolicyId());
			ps.executeUpdate();
			flag=true;
		}catch(SQLException se) {
			System.out.println("Error while updating policy");
			se.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deletePolicy(int policyId) throws PolicyNotFoundException {
		boolean flag=false;
		try {
			PreparedStatement ps=con.prepareStatement("delete from policy where policyId=?");
			ps.setInt(1, policyId);
			int rows=ps.executeUpdate();
			if(rows==0) {
				throw new PolicyNotFoundException("Policy not found");
			}
			flag=true;
		}catch(SQLException se) {
			System.out.println("Error while deleting policy");
			se.printStackTrace();
			
		}
		return flag;
	}
	

}