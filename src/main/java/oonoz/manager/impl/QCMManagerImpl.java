package oonoz.manager.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import oonoz.domain.QCM;
import oonoz.domain.SubTheme;
import oonoz.domain.Theme;
import oonoz.exception.QCMCreationException;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.QCMValidationException;
import oonoz.manager.QCMManager;
import oonoz.repository.QCMRepository;
import oonoz.repository.ThemeRepository;

/**
 * The Class QCMManagerImpl.
 */
@Component(value = "QCMManager")
public class QCMManagerImpl implements QCMManager {
	
	/** The player repository. */
	@Resource
	private QCMRepository QCMRepository;
	
	/** The theme repository. */
	@Resource
	private ThemeRepository themeRepository;
	
	/**
	 * Gets the all the qcm.
	 *
	 * @return all the qcm
	 */
	public List<QCM> getAllQCMs(){
		return (List<QCM>) QCMRepository.findAll();
	}

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the qcm
	 * @throws QCMDoesNotExistException 
	 */
	public QCM findOne(long id) throws QCMDoesNotExistException {
		QCM qcm=QCMRepository.findOne(id);
		if(qcm==null){
			throw new QCMDoesNotExistException("The QCM does not exist !");
		}
		return qcm;
	}
	
	/**
	 * Post QCM.
	 *
	 * @param qcm the qcm
	 * @return the qcm
	 * @throws QCMCreationException 
	 * @throws QCMValidationException 
	 */
	public QCM postQCM(QCM qcm) throws QCMCreationException {
		qcmExist(qcm.getName());
		checkThemeSubThemeAssociation(qcm.getTheme(),qcm.getSubThemes());
		qcm=QCMRepository.save(qcm);
		if(qcm==null){
			throw new QCMCreationException("Error during QCM creation !");
		}
		return QCMRepository.save(qcm);
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(long id) {
		QCMRepository.delete(id);
	}
	
	/**
	 * Save.
	 *
	 * @param qcm the qcm
	 * @return the qcm
	 */
	public QCM save(QCM qcm) {
		return QCMRepository.save(qcm);
	}
	
	/**
	 * Update the qcm.
	 *
	 * @param id the id
	 * @param qcm the qcm
	 * @return the qcm
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 * @throws QCMCreationException 
	 * @throws QCMValidationException 
	 */
	public QCM update(long id, QCM qcm) throws QCMDoesNotExistException, QCMCreationException {
		
		QCM existingQCM = QCMRepository.findOne(id);
		if (existingQCM != null) {
			qcm.setQuestions(existingQCM.getQuestions());
			qcm.setSupplier(existingQCM.getSupplier());
			if (existingQCM.getIdTheme() == qcm.getIdTheme()) {
				qcm.setTheme(existingQCM.getTheme());
			} else {
				Theme newTheme = themeRepository.findOne(qcm.getIdTheme());
				qcm.setTheme(newTheme);
				
			}
			checkThemeSubThemeAssociation(qcm.getTheme(),qcm.getSubThemes());
			return QCMRepository.save(qcm);
		} else {
			throw new QCMDoesNotExistException("The qcm does not exist");
		}
	}
	

	/**
	 * Find supplier questions.
	 *
	 * @param idSupplier the id supplier
	 * @return the list
	 */
	public List<QCM> findSupplierQuestions(long idSupplier) {
		return QCMRepository.findByIdSupplier(idSupplier);
	}

	/**
	 * Find supplier QCM by theme.
	 *
	 * @param theme the theme
	 * @return the list
	 */
	public List<QCM> findSupplierQCMByTheme(Theme theme) {
		return QCMRepository.findByTheme(theme);
	}
	
	public List<QCM> findByIdThemeAndIdSupplier(long idSupplier,long idTheme) throws QCMDoesNotExistException{
		List<QCM> QCMlist=QCMRepository.findByIdSupplierAndIdTheme(idSupplier,idTheme);
		if(QCMlist==null){
			throw new QCMDoesNotExistException("They are no QCM associated with this supplier or theme !");
		}
		return QCMlist;
	}

	/**
	 * Récupère tous les QCM validés et terminés.
	 * Fonction réservée à l'ADMIN
	 *
	 * @return the all validated QCM
	 */
	public List<QCM> getAllValidatedQCM(){
		return QCMRepository.findByIsValidatedTrueAndIsCompleteTrue();
	}
	
	/**
	 * Récupère tous les QCM non validés mais terminés.
	 * Fonction réservée à l'ADMIN
	 *
	 * @return the all QCM not validated
	 */
	public List<QCM> getAllNotValidatedQCM(){
		return QCMRepository.findByIsValidatedFalseAndIsCompleteTrue();
	}
	
	/**
	 * Validate QCM.
	 *
	 * @param id the id
	 * @throws QCMDoesNotExistException 
	 * @throws QCMValidationException 
	 */
	public void validateQCM(long id) throws QCMDoesNotExistException, QCMValidationException{
		if(QCMRepository.exists(id)){
			QCM qcmToValidate = QCMRepository.findOne(id);
			
			if(qcmToValidate.isValidated()){
				throw new QCMValidationException("The qcm is already validated");
			} else {
				qcmToValidate.setValidated(Boolean.TRUE);
				QCMRepository.save(qcmToValidate);
			}
		} else {
			throw new QCMDoesNotExistException("The qcm does not exist");
		}
	}
	
	/**
	 * Check if QCM name already exist.
	 * @param qcmName
	 * @return
	 * 		true, if exist
	 * 		false, if not
	 */
	public boolean qcmExist(String qcmName){
		
		QCM qcm=QCMRepository.findByName(qcmName);
		if(qcm!=null){
			return true;
		}
		return false;
	}
	
	/**
	 * Check if sub-themes are own by the theme.
	 * @param theme
	 * @param subthemes
	 * @return
	 * @throws QCMValidationException 
	 */
	private void checkThemeSubThemeAssociation(Theme theme,List<SubTheme> subthemes) throws QCMCreationException{
		boolean isContained=false;
		if(theme!=null && subthemes!=null){
			for(SubTheme st:subthemes){
				for(SubTheme stt:theme.getSubThemes()){
					if(stt.getId()==st.getId())
						isContained=true;						
				}
				if(!isContained){
					throw new QCMCreationException("Subthemes are not own by this Theme");
				}
				isContained=false;
			}		
		}
		
	}
}
