package com.bbva.mmft;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.mmft.dto.employes.EmployeDTO;
import java.util.List;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractMMFTT00101MXTransaction extends AbstractTransaction {

	public AbstractMMFTT00101MXTransaction(){
	}


	/**
	 * Return value for input parameter employeeIn
	 */
	protected EmployeDTO getEmployeein(){
		return (EmployeDTO)this.getParameter("employeeIn");
	}

	/**
	 * Return value for input parameter operation
	 */
	protected String getOperation(){
		return (String)this.getParameter("operation");
	}

	/**
	 * Set value for EmployeDTO output parameter employeOut
	 */
	protected void setEmployeout(final EmployeDTO field){
		this.addParameter("employeOut", field);
	}

	/**
	 * Set value for String output parameter message
	 */
	protected void setMessage(final String field){
		this.addParameter("message", field);
	}

	/**
	 * Set value for List<EmployeDTO> output parameter employeesListOut
	 */
	protected void setEmployeeslistout(final List<EmployeDTO> field){
		this.addParameter("employeesListOut", field);
	}
}
