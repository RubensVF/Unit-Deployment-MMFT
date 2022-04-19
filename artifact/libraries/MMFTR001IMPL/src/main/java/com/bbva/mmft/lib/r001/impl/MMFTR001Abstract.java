package com.bbva.mmft.lib.r001.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.mmft.lib.r001.MMFTR001;

/**
 * This class automatically defines the libraries and utilities that it will use.
 */
public abstract class MMFTR001Abstract extends AbstractLibrary implements MMFTR001 {

	protected ApplicationConfigurationService applicationConfigurationService;


	/**
	* @param applicationConfigurationService the this.applicationConfigurationService to set
	*/
	public void setApplicationConfigurationService(ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

}