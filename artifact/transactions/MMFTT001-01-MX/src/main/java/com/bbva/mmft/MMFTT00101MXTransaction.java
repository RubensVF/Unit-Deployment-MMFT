package com.bbva.mmft;

import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.mmft.dto.employes.EmployeDTO;
import com.bbva.mmft.lib.r001.MMFTR001;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transaction employes
 *
 */
public class MMFTT00101MXTransaction extends AbstractMMFTT00101MXTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(MMFTT00101MXTransaction.class);
	private static final String EMAIL_REGEX = "^(.+)@(.+)$";
	private static final String PHONE_REGEX = "^\\d{10}$";
	private static final String RFC_REGEX = "^[a-zA-Z]{3,4}(\\\\d{6})((\\\\D|\\\\d){2,3})?$";

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		MMFTR001 mmftR001 = this.getServiceLibrary(MMFTR001.class);
		// TODO - Implementation of business logic
		LOGGER.info("Entrando a la transaccion");
		String operation = getOperation();
		EmployeDTO employe = getEmployeein();
		switch(operation) {
			case "INSERT":
				LOGGER.info("Init insert");
				String res = validate(employe);
				if (res == null) {
					mmftR001.executeInsert(employe);
					setEmployeout(employe);
					LOGGER.info("Finish insert");
				} else
					setMessage(res);
				break;
			case "UPDATE":
				LOGGER.info("Init insert");
				String resU = validate(employe);
				if (resU == null) {
					mmftR001.executeInsert(employe);
					setEmployeout(employe);
					LOGGER.info("Finish insert");
				} else
					setMessage(resU);
				break;
			case "DELETE":
				LOGGER.info("Init delete");
				mmftR001.executeDeleteById(employe);
				LOGGER.info("Finish delete");
				break;
			case "GET BY NAME":
				LOGGER.info("Init getByName");
				if(mmftR001.executeGetByName(employe) == null)
					setMessage("No results found");
				else
					setEmployeout(mmftR001.executeGetByName(employe));
				LOGGER.info("Finish getByName");
				break;
			default:
				setMessage("Invalid operation");
				break;
		}
		
		Advice advice = getAdvice();
		if (advice != null && "MMFT01317007".equals(advice.getCode())) {
			setSeverity(Severity.ENR);
		} else {
			setSeverity(Severity.OK);
		}
	}

	public static String validate(EmployeDTO employe) {
		
		
		if (!Pattern.compile(EMAIL_REGEX).matcher(employe.getEmail()).matches()) {
			LOGGER.info("Invalid email: {}", employe.getEmail());
			return "Invalid email";
		}
		if (!Pattern.compile(PHONE_REGEX).matcher(employe.getPhone()).matches()) {
			LOGGER.info("Invalid phone: {}", employe.getPhone());
			return "Invalid phone";
		}
		if (!Pattern.compile(RFC_REGEX).matcher(employe.getRfc()).matches()) {
			LOGGER.info("Invalid rfc: {}", employe.getRfc());
			return "Invalid RFC";
		}
		return null;
	}

}
