package com.bbva.mmft;

import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.mmft.dto.employes.EmployeDTO;
import com.bbva.mmft.lib.r001.MMFTR001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transaction employes
 *
 */
public class MMFTT00101MXTransaction extends AbstractMMFTT00101MXTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(MMFTT00101MXTransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		MMFTR001 mmftR001 = this.getServiceLibrary(MMFTR001.class);
		// TODO - Implementation of business logic
		LOGGER.info("Entrando a la transaccion");
		String tipo = getTipo();
		EmployeDTO employe = getEmployeein();
		
		if("0".equals(tipo)) {
			LOGGER.info("Init insert");
			mmftR001.executeInsert(employe);
			setEmployeout(employe);
			LOGGER.info("Finish insert");
		}
		
		
		Advice advice = getAdvice();
		if(advice != null && "MMFT01317007".equals(advice.getCode())) {
			setSeverity(Severity.ENR);
		}
		else {
			setSeverity(Severity.OK);
		}
	}

}
