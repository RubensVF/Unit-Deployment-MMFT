package com.bbva.mmft.lib.r001.impl;

import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.mmft.dto.employes.EmployeDTO;

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

				LOGGER.info("Entrando al metodo executeInsert 2.0");

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
}
