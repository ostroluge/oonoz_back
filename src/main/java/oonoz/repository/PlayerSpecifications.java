package oonoz.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import oonoz.domain.Player;

@Component
public class PlayerSpecifications {

	public static Specification<Player> isSupplier() {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.isTrue(root.get("isSupplier"));
			}

		};
	}
	
	public static Specification<Player> isPlayer() {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.isFalse(root.get("isSupplier"));
			}

		};
	}
	
	public static Specification<Player> mailStartWith(String searchString) {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.like(builder.lower(root.get("mail")),searchString+"%");
			}

		};
	}

	public static Specification<Player> usernameStartWith(String searchString) {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.like(builder.lower(root.get("username")),searchString+"%");
			}

		};
	}
	
	public static Specification<Player> lastnameStartWith(String searchString) {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.like(builder.lower(root.get("lastName")),searchString+"%");
			}

		};
	}
	
	public static Specification<Player> firstnameStartWith(String searchString) {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				
				return builder.like(builder.lower(root.get("firstName")),searchString+"%");
				
			}

		};
	}
	
	public static Specification<Player> isActive() {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.isTrue(root.get("isActive"));
			}

		};
	}
	
	public static Specification<Player> isUnactive() {
		return new Specification<Player>() {

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				return builder.isFalse(root.get("isActive"));
			}

		};
	}
	
	
	
	
	
	

}
