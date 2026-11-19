package com.example.demo;


import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CustId;

    private String custName;

    private String CustAdd;
    
    private String userName;
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

    public Customer() {
    }

    public int getCustId() {
        return CustId;
    }

    public void setCustId(int custId) {
        CustId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAdd() {
        return CustAdd;
    }

    public void setCustAdd(String custAdd) {
        CustAdd = custAdd;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustId=" + CustId +
                ", custName='" + custName + '\'' +
                ", CustAdd='" + CustAdd + '\'' +
                ", userName='" + userName + '\'' +   // <-- make sure this line exists
                '}';
    }
}