package com.bbva.mmft.lib.r001;

import java.util.List;

import com.bbva.mmft.dto.employes.EmployeDTO;

/**
 * The  interface MMFTR001 class...
 */
public interface MMFTR001 {

	/**
	 * The execute method...
	 */
	public EmployeDTO executeInsert(EmployeDTO employe);
	public EmployeDTO executeUpdate(EmployeDTO employe);
	public int executeDeleteById(EmployeDTO employe);
	public EmployeDTO executeGetByName(EmployeDTO employe);
	public List<EmployeDTO> executeGetAll();

}
