package oonoz.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import oonoz.domain.Player;

/**
 * The Class PlayerSpecifications.
 * 
 * Description :
 */
@Component
public class PlayerSpecifications {

	private PlayerSpecifications(){
	}
	
	/**
	 * Is supplier.
	 *
	 * @return the specification
	 */
	public static Specification<Player> isSupplier() {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.isTrue(root.get("isSupplier"));
			}

		};
	}
	
	/**
	 * Is player.
	 *
	 * @return the specification
	 */
	public static Specification<Player> isPlayer() {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.isFalse(root.get("isSupplier"));
			}

		};
	}
	
	/**
	 * Mail start with.
	 *
	 * @param searchString the search string
	 * @return the specification
	 */
	public static Specification<Player> mailStartWith(String searchString) {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.like(builder.lower(root.get("mail")),searchString+"%");
			}

		};
	}

	/**
	 * Username start with.
	 *
	 * @param searchString the search string
	 * @return the specification
	 */
	public static Specification<Player> usernameStartWith(String searchString) {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.like(builder.lower(root.get("username")),searchString+"%");
			}

		};
	}
	
	/**
	 * Lastname start with.
	 *
	 * @param searchString the search string
	 * @return the specification
	 */
	public static Specification<Player> lastnameStartWith(String searchString) {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.like(builder.lower(root.get("lastName")),searchString+"%");
			}

		};
	}
	
	/**
	 * Firstname start with.
	 *
	 * @param searchString the search string
	 * @return the specification
	 */
	public static Specification<Player> firstnameStartWith(String searchString) {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				
				return builder.like(builder.lower(root.get("firstName")),searchString+"%");
				
			}

		};
	}
	
	/**
	 * Is active.
	 *
	 * @return the specification
	 */
	public static Specification<Player> isActive() {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.isTrue(root.get("isActive"));
			}

		};
	}
	
	/**
	 * Is unactive.
	 *
	 * @return the specification
	 */
	public static Specification<Player> isUnactive() {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.isFalse(root.get("isActive"));
			}

		};
	}

}
