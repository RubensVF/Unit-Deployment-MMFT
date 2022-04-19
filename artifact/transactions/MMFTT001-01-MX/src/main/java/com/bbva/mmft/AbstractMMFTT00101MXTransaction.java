package com.bbva.mmft;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.mmft.dto.employes.EmployeDTO;

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
	 * Return value for input parameter tipo
	 */
	protected String getTipo(){
		return (String)this.getParameter("tipo");
	}

	/**
	 * Set value for EmployeDTO output parameter employeOut
	 */
	protected void setEmployeout(final EmployeDTO field){
		this.addParameter("employeOut", field);
	}
}
