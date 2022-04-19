package com.bbva.mmft;

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
	}

}
