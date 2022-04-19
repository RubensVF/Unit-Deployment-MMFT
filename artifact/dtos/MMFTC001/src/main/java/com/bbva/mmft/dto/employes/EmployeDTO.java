package com.bbva.mmft.dto.employes;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The EmployeDTO class...
 */
public class EmployeDTO implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	/* Attributes section for the DTO */

	private int number;
	private String name;
	private String department;
	private String rfc;
	private String email;
	private String phone;
	private String address;
	private Date date;
	private int status;
	private float salary;
	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		final EmployeDTO rhs = (EmployeDTO) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj))
					.append(number, rhs.number)
					.append(name, rhs.name)
					.append(department, rhs.department)
					.append(rfc, rhs.rfc)
					.append(email, rhs.email)
					.append(phone, rhs.phone)
					.append(address, rhs.address)
					.append(status, rhs.status)
					.append(salary, rhs.salary)
					.isEquals();
	}

	/**
	 * Returns a hash code value for the object.
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.number)
			.append(this.name)
			.append(this.department)
			.append(this.rfc)
			.append(this.email)
			.append(this.phone)
			.append(this.address)
			.append(this.status)
			.append(this.salary)
			.toHashCode();
	}

	/**
	 * Returns a string representation of the object.
	 * It is important to OBFUSCATE the attributes that are sensitive to show in the representation.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("number", number)
			.append("name", name)
			.append("department",department)
			.append("rfc",rfc)
			.append("email",email)
			.append("phone",phone)
			.append("address",address)
			.append("status",status)
			.append("salary",salary)
			.toString();
	}
}
