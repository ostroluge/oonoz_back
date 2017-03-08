package oonoz.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import oonoz.domain.QCM;


/**
 * The Class QCMSpecifications.
 * 
 * Description :
 */
@Component
public class QCMSpecifications {
	
	/**
	 * Instantiates a new QCM specifications.
	 */
	public QCMSpecifications(){
		
	}
	
	/**
	 * QCM label start with.
	 *
	 * @param searchString the search string
	 * @return the specification
	 */
	public static Specification<QCM> labelStartWith(String searchString) {
		return new Specification<QCM>() {

			@Override
			public Predicate toPredicate(Root<QCM> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				
				return builder.like(builder.lower(root.get("name")),searchString+"%");
				
			}

		};
	}
	
	/**
	 * QCM with category.
	 *
	 * @param category the category
	 * @return the specification
	 */
	public static Specification<QCM> withCategory(String category) {
		return new Specification<QCM>() {

			@Override
			public Predicate toPredicate(Root<QCM> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				
				return builder.equal(builder.lower(root.get("category")), category);
				
			}

		};
	}
	
	/**
	 * QCM label start with.
	 *
	 * @param idTheme the id theme
	 * @return the specification
	 */
	public static Specification<QCM> withTheme(Long idTheme) {
		return new Specification<QCM>() {

			@Override
			public Predicate toPredicate(Root<QCM> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				
				return builder.equal(root.get("idTheme"), idTheme);
				
			}

		};
	}
	
	/**
	 * QCM label start with.
	 *
	 * @param idSubTheme the id sub theme
	 * @return the specification
	 */
	public static Specification<QCM> withSubTheme(Long idSubTheme) {
		return new Specification<QCM>() {

			@Override
			public Predicate toPredicate(Root<QCM> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
							
				Join join = root.join("subThemes");                   
                return builder.equal(join.get("id"),idSubTheme);
				
			}

		};
	}
	
	
	/**
	 * QCM price less or equal than.
	 *
	 * @param price the maximum price of QCM
	 * @return the specification
	 */
	public static Specification<QCM> withPriceLessOrEqualTo(float price) {
		return new Specification<QCM>() {

			@Override
			public Predicate toPredicate(Root<QCM> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				
				return builder.lessThanOrEqualTo(root.get("price"), price);
				
			}

		};
	}
	
	/**
	 * QCM with a gift.
	 *
	 * @return the specification
	 */
	public static Specification<QCM> hasGift() {
		return new Specification<QCM>() {

			@Override
			public Predicate toPredicate(Root<QCM> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				
				return builder.isNotNull(root.get("prizeName"));
				
			}

		};
	}
	
	
	/**
	 * Is validated.
	 *
	 * @return the specification
	 */
	public static Specification<QCM> isValidated() {
		return new Specification<QCM>() {

			@Override
			public Predicate toPredicate(Root<QCM> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.isTrue(root.get("isValidated"));
			}

		};
	}
	
	/**
	 * Is validated.
	 *
	 * @return the specification
	 */
	public static Specification<QCM> isFree() {
		return new Specification<QCM>() {

			@Override
			public Predicate toPredicate(Root<QCM> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.isTrue(root.get("isFree"));
			}

		};
	}

}
