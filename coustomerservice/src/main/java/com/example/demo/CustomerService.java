package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exception.ResourceNotFoundEx;

@Service
public class CustomerService implements UserDetailsService {
	@Autowired
	CustRepo custrepo;
	
	@Autowired 
	PasswordEncoder passwordEncoder;
	
	@Transactional                                        //applicable for only public function level and class level
	public Customer addCustomer(Customer customer) {
		String hashed=passwordEncoder.encode(customer.getPassword());
		customer.setPassword(hashed);
		System.out.println("added the customer");
		return custrepo.save(customer);
		
	}
//	public Customer getRegistory(Customer customer) {
//		return custrepo.save(customer);
//	}
	public List<Customer> getCustomer() {
		return custrepo.findAll();
	}
	public Customer getCustomersById(int custId){
		return custrepo.findById(custId).orElseThrow(()->new ResourceNotFoundEx("ID not found"));
	}
	public Customer updateCustomer( Customer customer) {
		Customer cust=custrepo.findById(customer.getCustId( )).orElseThrow(()->new ResourceNotFoundEx("ID not found"));
		cust.setCustName(customer.getCustName());
		cust.setCustAdd(customer.getCustAdd());
		cust.setUserName(customer.getUserName());
		 if (customer.getPassword() != null && !customer.getPassword().isBlank()) {
		        String hashed = passwordEncoder.encode(customer.getPassword());
		        cust.setPassword(hashed);
		    }

		return custrepo.save(cust);
	}
	public String deleteCustomer( int custId) {
		Customer cust=custrepo.findById(custId).orElseThrow(()->new ResourceNotFoundEx("ID not found"));
	    custrepo.delete(cust);
	    return "record deleted succesfuuly";
		
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Customer customer = custrepo.findByuserName(username)
	            .orElseThrow(() -> new UsernameNotFoundException("Customer not found: " + username));
	    return new UserPrincipal(customer);
}
}
