package oonoz.util;

import org.springframework.stereotype.Component;

import oonoz.exception.WrongInformationException;

/**
 * The Class CheckQuestionInformation.
 */
@Component
public class CheckQuestionInformation {

	/**
	 * Check title.
	 *
	 * @param title the title
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkTitle(String title) throws WrongInformationException {
		if (title == null || title.length() < 4 || title.length() > 200) {
			throw new WrongInformationException("The title of the question is not valid!");
		}
	}
	
	/**
	 * Check answer.
	 *
	 * @param answer the answer
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkAnswer(String answer) throws WrongInformationException {
		if (answer == null || answer.length() < 2 || answer.length() > 50) {
			throw new WrongInformationException("The answer of the question is not valid!");
		}
	}
	
	/**
	 * Check proposition 1.
	 *
	 * @param proposition1 the proposition 1
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkProposition1(String proposition1) throws WrongInformationException {
		if (proposition1 == null || proposition1.length() < 2 || proposition1.length() > 50) {
			throw new WrongInformationException("The proposition1 of the question is not valid!");
		}
	}

	/**
	 * Check proposition 2.
	 *
	 * @param proposition2 the proposition 2
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkProposition2(String proposition2) throws WrongInformationException {
		if (proposition2 == null || proposition2.length() < 2 || proposition2.length() > 50) {
			throw new WrongInformationException("The proposition2 of the question is not valid!");
		}
	}
	
	/**
	 * Check proposition 3.
	 *
	 * @param proposition3 the proposition 3
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkProposition3(String proposition3) throws WrongInformationException {
		if (proposition3 == null || proposition3.length() < 2 || proposition3.length() > 50) {
			throw new WrongInformationException("The proposition3 of the question is not valid!");
		}
	}
}
