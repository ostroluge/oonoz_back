package oonoz.util;

import org.springframework.stereotype.Component;

import oonoz.exception.WrongInformationException;

/**
 * The Class CheckQCMInformation.
 */
@Component
public class CheckQCMInformation {

	/**
	 * Check name.
	 *
	 * @param name the name
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkName(String name) throws WrongInformationException {
		if (name == null || name.length() < 3 || name.length() > 40) {
			throw new WrongInformationException("The name of the QCM is not valid!");
		}
	}

	/**
	 * Check description.
	 *
	 * @param description the description
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkDescription(String description) throws WrongInformationException {
		if (description == null || description.length() < 4 || description.length() > 150) {
			throw new WrongInformationException("The description of the QCM is not valid!");
		}
	}

	/**
	 * Check category.
	 *
	 * @param category the category
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkCategory(String category) throws WrongInformationException {
		if (category == null || category.length() < 3 || category.length() > 10 || (!category.equals("formatif") && !category.equals("sommatif"))) {
			throw new WrongInformationException("The category of the QCM is not valid!");
		}
	}
}
