package oonoz.util;

public class FilteredSearch {
	
	private String usernameSearch;

	private String lastnameSearch;
	
	private String firstnameSearch;
	
	private String userStatus;
	
	private int pageNumber;
	
	private int pageSize;
	
	private String userActive;
		
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public String getUsernameSearch() {
		return usernameSearch;
	}

	public void setUsernameSearch(String usernameSearch) {
		this.usernameSearch = usernameSearch;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getUserActive() {
		return userActive;
	}

	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}

	public String getLastnameSearch() {
		return lastnameSearch;
	}

	public void setLastnameSearch(String lastnameSearch) {
		this.lastnameSearch = lastnameSearch;
	}

	public String getFirstnameSearch() {
		return firstnameSearch;
	}

	public void setFirstnameSearch(String firstnameSearch) {
		this.firstnameSearch = firstnameSearch;
	}

	
	
	
	

}
