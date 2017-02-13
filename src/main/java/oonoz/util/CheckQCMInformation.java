package oonoz.util;

import org.springframework.stereotype.Component;
import oonoz.exception.WrongInformationException;

// TODO: Auto-generated Javadoc
/**
 * The Class CheckQCMInformation.
 */
@Component
public class CheckQCMInformation {

	/**
	 * Check name.
	 *
	 * @param name
	 *            the name
	 * @throws WrongInformationException
	 *             the wrong information exception
	 */
	public void checkName(String name) throws WrongInformationException {
		if (name == null || name.length() < 3 || name.length() > 40) {
			throw new WrongInformationException("The name of the QCM is not valid!");
		}
	}

	/**
	 * Check description.
	 *
	 * @param description
	 *            the description
	 * @throws WrongInformationException
	 *             the wrong information exception
	 */
	public void checkDescription(String description) throws WrongInformationException {
		if (description == null || description.length() < 4 || description.length() > 150) {
			throw new WrongInformationException("The description of the QCM is not valid!");
		}
	}

	/**
	 * Check category.
	 *
	 * @param category
	 *            the category
	 * @throws WrongInformationException
	 *             the wrong information exception
	 */
	public void checkCategory(String category) throws WrongInformationException {
		if (category == null || category.length() < 3 || category.length() > 10
				|| (!"formatif".equals(category) && !"sommatif".equals(category))) {
			throw new WrongInformationException("The category of the QCM is not valid!");
		}
	}

	/**
	 * Check id theme.
	 *
	 * @param idTheme
	 *            the id theme
	 * @throws WrongInformationException
	 *             the wrong information exception
	 */
	public void checkIdTheme(Long idTheme) throws WrongInformationException {

		if (idTheme == null || idTheme == 0) {
			throw new WrongInformationException("The QCM must own a theme !");
		}
	}

	/**
	 * Check prize name.
	 *
	 * @param prizeName the prize name
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkPrizeName(String prizeName) throws WrongInformationException {

		if (prizeName == null || prizeName.length() < 4 || prizeName.length() > 50) {
			throw new WrongInformationException("The QCM prize name is not valid !");
		}
	}

	/**
	 * Check prize description.
	 *
	 * @param prizeDescription the prize description
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkPrizeDescription(String prizeDescription) throws WrongInformationException {

		if (prizeDescription == null || prizeDescription.length() < 4 || prizeDescription.length() > 150) {
			throw new WrongInformationException("The QCM prize description is not valid !");
		}
	}
	
	/**
	 * Check minimal score.
	 *
	 * @param minimalScore the minimal score
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkMinimalScore(int minimalScore) throws WrongInformationException {
		if (minimalScore < 0 || minimalScore > 20) {
			throw new WrongInformationException("The QCM minimal score must be contained between 0 and 20 !");
		}
	}
	
	/**
	 * Check price.
	 *
	 * @param price the price
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkPrice(float price) throws WrongInformationException {
		if (price < 0 || price > 9999999) {
			throw new WrongInformationException("The QCM price must be contained between 0 and 9999999 !");
		}
	}

}
