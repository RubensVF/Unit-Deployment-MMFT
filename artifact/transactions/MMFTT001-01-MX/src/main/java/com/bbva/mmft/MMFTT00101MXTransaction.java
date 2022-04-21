package com.bbva.mmft;

import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.mmft.dto.employes.EmployeDTO;
import com.bbva.mmft.lib.r001.MMFTR001;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transaction employes
 *
 */
public class MMFTT00101MXTransaction extends AbstractMMFTT00101MXTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(MMFTT00101MXTransaction.class);
	private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	private static final String PHONE_REGEX = "^\\d{10}$";
	private static final String RFC_REGEX = "^[a-zA-Z]{3,4}(\\d{6})((\\D|\\d){2,3})?$";

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		MMFTR001 mmftR001 = this.getServiceLibrary(MMFTR001.class);
		// TODO - Implementation of business logic
		LOGGER.info("Entrando a la transaccio 2.0");
		String operation = getOperation();
		EmployeDTO employe = getEmployeein();
		switch (operation) {
		case "INSERT":
			LOGGER.info("Init insert 2.0");
			String res = validate(employe);
			if (res.equals("OK")) {
				mmftR001.executeInsert(employe);
				setEmployeout(employe);
				LOGGER.info("Finish insert");
			} else
				setMessage(res);
			break;
		case "UPDATE":
			LOGGER.info("Init Update 2.0");
			String resU = validate(employe);
			if (resU.equals("OK")) {
				mmftR001.executeUpdate(employe);
				setEmployeout(employe);
				LOGGER.info("Finish Update");
			} else
				setMessage(resU);
			break;
		case "DELETE":
			LOGGER.info("Init delete 2.0");
			int result = mmftR001.executeDeleteById(employe);
			//Si el resultado es 1 se elimino correctamente;
			if(result>0)
				setMessage("Succesfully delete");
			else
				setMessage("Error deleting");
			LOGGER.info("Finish delete");
			break;
		case "GET BY NAME":
			LOGGER.info("Init getByName 2.0");
			if (mmftR001.executeGetByName(employe) == null)
				setMessage("No results found");
			else
				setEmployeout(mmftR001.executeGetByName(employe));
			LOGGER.info("Finish getByName");
			break;
		case "GET ALL":
			LOGGER.info("Init getAll 2.0");
			setEmployeeslistout(mmftR001.executeGetAll());
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
		if (employe.getName() == null || employe.getName().trim().isEmpty()) {
			return "Name field required";
		}

		if (employe.getNumber() == 0) {
			return "Number field required";
		}

		if (employe.getEmail() != null)
			if (!Pattern.compile(EMAIL_REGEX,Pattern.CASE_INSENSITIVE).matcher(employe.getEmail()).matches()) {
				LOGGER.info("Invalid email: {}", employe.getEmail());
				return "Invalid email";
			}
		if (employe.getPhone() != null)
			if (!Pattern.compile(PHONE_REGEX).matcher(employe.getPhone()).matches()) {
				LOGGER.info("Invalid phone: {}", employe.getPhone());
				return "Invalid phone";
			}
		if (employe.getRfc() != null)
			if (!Pattern.compile(RFC_REGEX).matcher(employe.getRfc()).matches()) {
				LOGGER.info("Invalid rfc : {}", Pattern.compile(RFC_REGEX).matcher(employe.getRfc()).matches());
				return "Invalid RFC ?";
			}
		return "OK";
	}

}
