package oonoz.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import oonoz.domain.QCM;


@Component
public class QCMSpecifications {
	
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
	 * @param searchString the search string
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
	 * @param searchString the search string
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
