package com.bbva.mmft.lib.r001.impl;

import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.mmft.dto.employes.EmployeDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The MMFTR001Impl class...
 */
public class MMFTR001Impl extends MMFTR001Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(MMFTR001Impl.class);

	/**
	 * The execute method...
	 */
	@Override
	public EmployeDTO executeInsert(EmployeDTO employe) {
		// TODO Auto-generated method stub

				LOGGER.info("Entrando al metodo executeInsert");

				int result = 0;

				try {

					result = this.jdbcUtils.update("insert",employe.getNumber(),employe.getName(),employe.getDepartment(),employe.getRfc(),
							employe.getEmail(),employe.getPhone(),employe.getAddress(),new java.sql.Timestamp(employe.getDate().getTime()),employe.getStatus(),employe.getSalary());

				} catch (DuplicateKeyException e) {

					LOGGER.error("Ocurrio un problema se duplico el key en la tabla employees");
					addAdvice("MNEO01317004");
				}

				catch (TimeoutException e) {

					LOGGER.error("Ocurrio un problema se excepdio el tiempo para insertar en la tabla employees ");
					addAdvice("MNEO01317005");
				}

				LOGGER.info("El resultado del insert es {}", result);

				return employe;

	}

	@Override
	public EmployeDTO executeUpdate(EmployeDTO employe) {
		// TODO Auto-generated method stub

		LOGGER.info("Entrando al metodo executeUpdate");

		int result = 0;

		try {

			Map<String,Object> params = new HashMap<>();
			params.put("number", employe.getNumber());
			params.put("name", employe.getName());
			params.put("department", employe.getDepartment());
			params.put("rfc", employe.getRfc());
			params.put("email", employe.getEmail());
			params.put("phone", employe.getPhone());
			params.put("address", employe.getAddress());
			params.put("date", new java.sql.Timestamp(employe.getDate().getTime()));
			params.put("status", employe.getStatus());
			params.put("salary", employe.getSalary());
			
			result = this.jdbcUtils.update("updateEmployee",params);

		} catch (DuplicateKeyException e) {

			LOGGER.error("Ocurrio un problema se duplico el key en la tabla customers");
			addAdvice("MNEO01317004");
		}

		catch (TimeoutException e) {

			LOGGER.error("Ocurrio un problema se excepdio el tiempo para actualizar en la tabla customers ");
			addAdvice("MNEO01317005");
		}

		LOGGER.info("El resultado del update es {}", result);

		return employe;
	}

	@Override
	public void executeDeleteById(EmployeDTO employe) {
		LOGGER.info("Entrando al metodo executeDeleteById");

		int result = 0;

		try {
			Map<String, Object> params = new HashMap<>();
			params.put("number", employe.getNumber());
			result = this.jdbcUtils.update("delete", params);
		} catch (TimeoutException e) {
			LOGGER.error("Ocurrio un problema se excepdio el tiempo para actualizar en la tabla customers ");
			addAdvice("MNEO01317005");
		}
		LOGGER.info("El resultado del delete es {}", result);
	}

	@Override
	public EmployeDTO executeGetByName(EmployeDTO employe) {
		 LOGGER.info("Entrando al metodo executeGetByName");
	     
	     EmployeDTO employeRes= new EmployeDTO();
	     
			try {
				
				Map<String, Object> params = new HashMap<>();
				params.put("name", employe.getName());
				
				List<Map<String, Object >> lista=new ArrayList<Map<String,Object>>();
				
				lista = this.jdbcUtils.queryForList("getByName", params);
				
				if(lista.isEmpty()){
					return null;
				}
				
				employeRes.setNumber(Integer.parseInt(lista.get(0).get("EMPLOYEE_NUMBER").toString()));
				employeRes.setName(lista.get(0).get("EMPLOYEE_NAME").toString());
				employeRes.setDepartment(lista.get(0).get("EMPLOYEE_DEPARTMENT").toString());
				employeRes.setRfc(lista.get(0).get("EMPLOYEE_RFC").toString());
				employeRes.setEmail(lista.get(0).get("EMPLOYEE_EMAIL").toString());
				employeRes.setPhone(lista.get(0).get("EMPLOYEE_PHONE").toString());
				employeRes.setAddress(lista.get(0).get("EMPLOYEE_ADDRESS").toString());
				employeRes.setStatus(Integer.parseInt(lista.get(0).get("EMPLOYEE_STATUS").toString()));
				employeRes.setSalary(Float.parseFloat(lista.get(0).get("SALARY").toString()));
				/**
				 * Convert string database to date java
				 */
				String dateString = lista.get(0).get("EMPLOYEE_REGISTRATION_DATE").toString();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
			    LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
			    Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
			    employeRes.setDate(date);
			    
			}catch (NoResultException e) {
				LOGGER.error("Ocurrio un problema al obtener el customer en BD");
				addAdvice("MNEO01317008");
			}
			
			LOGGER.info("Saliendo del metodo executeGetByName");
			
			return employeRes;
		
	}
}
