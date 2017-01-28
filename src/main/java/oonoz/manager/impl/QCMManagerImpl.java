package oonoz.manager.impl;

import static oonoz.repository.QCMSpecifications.isFree;
import static oonoz.repository.QCMSpecifications.isValidated;
import static oonoz.repository.QCMSpecifications.labelStartWith;
import static oonoz.repository.QCMSpecifications.withCategory;
import static oonoz.repository.QCMSpecifications.withTheme;
import static oonoz.repository.QCMSpecifications.withSubTheme;
import static oonoz.repository.QCMSpecifications.withPriceLessOrEqualTo;
import static org.springframework.data.jpa.domain.Specifications.where;
import static oonoz.repository.QCMSpecifications.hasGift;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import oonoz.domain.QCM;
import oonoz.domain.SubTheme;
import oonoz.domain.Theme;
import oonoz.exception.QCMCreationException;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.QCMValidationException;
import oonoz.manager.QCMManager;
import oonoz.repository.QCMRepository;
import oonoz.repository.SupplierRepository;
import oonoz.repository.ThemeRepository;
import oonoz.util.QCMFilteredSearch;

/**
 * The Class QCMManagerImpl.
 */
@Component(value = "QCMManager")
public class QCMManagerImpl implements QCMManager {
	
	/** The player repository. */
	@Resource
	private QCMRepository qcmRepository;
	
	/** The theme repository. */
	@Resource
	private ThemeRepository themeRepository;
	
	/** The supplier repository. */
	@Resource
	private SupplierRepository supplierRepository;
	
	/**
	 * Gets the all the qcm.
	 *
	 * @return all the qcm
	 */
	public List<QCM> getAllQCMs(){
		return (List<QCM>) qcmRepository.findAll();
	}

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the qcm
	 * @throws QCMDoesNotExistException 
	 */
	public QCM findOne(long id) throws QCMDoesNotExistException {
		QCM qcm=qcmRepository.findOne(id);
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
		if(themeRepository.exists(qcm.getIdTheme()) && supplierRepository.exists(qcm.getIdSupplier())){
			checkThemeSubThemeAssociation(qcm.getTheme(),qcm.getSubThemes());
			qcm=qcmRepository.save(qcm);
			if(qcm==null){
	
				throw new QCMCreationException("Error during QCM creation !");
			}
			return qcm;
		}
		throw new QCMCreationException("The QCM theme or supplier does not exist !");
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(long id) {
		qcmRepository.delete(id);
	}
	
	/**
	 * Save.
	 *
	 * @param qcm the qcm
	 * @return the qcm
	 */
	public QCM save(QCM qcm) {
		return qcmRepository.save(qcm);
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
		
		QCM existingQCM = qcmRepository.findOne(id);
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
			return qcmRepository.save(qcm);
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
		return qcmRepository.findByIdSupplier(idSupplier);
	}

	/**
	 * Find supplier QCM by theme.
	 *
	 * @param theme the theme
	 * @return the list
	 */
	public List<QCM> findSupplierQCMByTheme(Theme theme) {
		return qcmRepository.findByTheme(theme);
	}
	
	
	
	public List<QCM> findByIdThemeAndIdSupplier(long idSupplier,long idTheme) throws QCMDoesNotExistException{
		List<QCM> QCMlist=qcmRepository.findByIdSupplierAndIdTheme(idTheme,idSupplier);
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
		return qcmRepository.findByIsValidatedTrueAndIsCompleteTrue();
	}
	
	/**
	 * Récupère tous les QCM non validés mais terminés.
	 * Fonction réservée à l'ADMIN
	 *
	 * @return the all QCM not validated
	 */
	public List<QCM> getAllNotValidatedQCM(){
		return qcmRepository.findByIsValidatedFalseAndIsCompleteTrue();
	}
	
	/**
	 * Validate QCM.
	 *
	 * @param id the id
	 * @throws QCMDoesNotExistException 
	 * @throws QCMValidationException 
	 */
	public void validateQCM(long id) throws QCMDoesNotExistException, QCMValidationException{
		if(qcmRepository.exists(id)){
			QCM qcmToValidate = qcmRepository.findOne(id);
			
			if(qcmToValidate.isValidated()){
				throw new QCMValidationException("The qcm is already validated");
			} else {
				qcmToValidate.setValidated(Boolean.TRUE);
				qcmRepository.save(qcmToValidate);
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
		
		QCM qcm=qcmRepository.findByName(qcmName);
		if(qcm!=null){
			return true;
		}
		return false;
	}
	
	public List<QCM> findsWithFilter(QCMFilteredSearch filteredSearch){
		
		Specification<QCM> spec = null;
		
		if (filteredSearch.getLabelSearch() != null && !"".equals(filteredSearch.getLabelSearch())) {
			spec = where(labelStartWith(filteredSearch.getLabelSearch().toLowerCase()));
		}
		if (filteredSearch.isFree() != null && filteredSearch.isFree()) {
			spec = where(spec).and(isFree());
		}
		if (filteredSearch.getCategory() != null && !"".equals(filteredSearch.getCategory())) {
			spec = where(spec).and(withCategory(filteredSearch.getCategory()));
		}
		if (filteredSearch.getIdTheme()!=null && filteredSearch.getIdTheme() != 0 ) {
			spec = where(spec).and(withTheme(filteredSearch.getIdTheme()));
		}
		
		if (filteredSearch.getIdSubTheme()!=null && filteredSearch.getIdSubTheme() != 0 ) {
			spec = where(spec).and(withSubTheme(filteredSearch.getIdSubTheme()));
		}
		if (filteredSearch.getMaxPrice()!=null && filteredSearch.getMaxPrice()> 0 ) {
			spec = where(spec).and(withPriceLessOrEqualTo(filteredSearch.getMaxPrice()));
		}
		if (filteredSearch.isHasGift()!=null && filteredSearch.isHasGift() ) {
			spec = where(spec).and(hasGift());
		}
		
		/**Only validated QCM**/
		spec=where(spec).and(isValidated());
		return qcmRepository.findAll(spec);
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
