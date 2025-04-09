package hexa.org.main;

import java.util.List;
import java.util.Scanner;

import hexa.org.dao.InsuranceServiceImpl;
import hexa.org.entity.Policy;
import hexa.org.exception.PolicyNotFoundException;


public class MainModule {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		InsuranceServiceImpl service = new InsuranceServiceImpl();
		
		while(true) {
			System.out.println(".........Insurance System..........");
			System.out.println("1. Create Policy");
			System.out.println("2. Get Policy By Id");
			System.out.println("3. Get All Policies");
			System.out.println("4. Update Policy");
			System.out.println("5. delete Policy");
			System.out.println("6. Exit");
			System.out.println("Enter Option: ");
			int option=sc.nextInt();
			
			switch(option) {
			case 1 -> {
				System.out.println("Enter policyId: ");
				int pid = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter Policy name: ");
				String name = sc.nextLine();
				System.out.print("Enter Policy details: ");
				String details = sc.nextLine();
                Policy policy = new Policy(pid, name, details);
                if(service.createPolicy(policy)) {
                	System.out.println("Policy created successfully.");
                }else {
                	System.out.println("Policy creation failed");
                }
			}
			case 2 -> {
				System.out.println("Enter policyId:");
				int id=sc.nextInt();
				try {
					Policy result=service.getPolicy(id);
					System.out.println(result);
				}catch(PolicyNotFoundException pe) {
					System.out.println(pe.getMessage());
				}
			}
			case 3 -> {
				List<Policy> policies = service.getAllPolicies();
				if(policies.isEmpty()) {
					System.out.println("No policies found.");
				}else {
					for(Policy p: policies) {
						System.out.println(p);
					}
				}
			}
			case 4 -> {
				System.out.print("Enter policyId to update: ");
                int upid = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter New Policy name: ");
                String newName = sc.nextLine();
                System.out.print("Enter New Policy details: ");
                String newDetails = sc.nextLine();
                Policy updatedPolicy = new Policy(upid, newName, newDetails);
                if (service.updatePolicy(updatedPolicy)) {
                    System.out.println("Policy updated successfully.");
                } else {
                    System.out.println("Update failed.");
                }
				
				
			}
			case 5 -> {
				System.out.print("Enter PolicyId to delete: ");
                int delid = sc.nextInt();
                try {
                    if (service.deletePolicy(delid)) {
                        System.out.println("Policy deleted successfully.");
                    }
                } catch (PolicyNotFoundException e) {
                    System.out.println(e.getMessage());
                }
			}
			case 6 -> {
				System.out.println("Exited...");
				sc.close();
				System.exit(0);
			}
			default -> {
				System.out.println("Invalid option");
			}
			}
		}

	}

}