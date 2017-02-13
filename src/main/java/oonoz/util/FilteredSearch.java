package oonoz.util;

public class FilteredSearch {
	
	private String usernameSearch;

	private String lastnameSearch;
	
	private String firstnameSearch;
	
	private String mailSearch;
	
	private int pageNumber;
	
	private int pageSize;
	
	private boolean userActive;
	
	private boolean userInactive;
	
	private boolean playerStatus;
	
	private boolean supplierStatus;
	
	private boolean supplierAccountValid;
	
	private boolean supplierAccountNotValid;
		
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


	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
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

	public boolean isUserActive() {
		return userActive;
	}

	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}

	public boolean isUserInactive() {
		return userInactive;
	}

	public void setUserInactive(boolean userInactive) {
		this.userInactive = userInactive;
	}

	public String getMailSearch() {
		return mailSearch;
	}

	public void setMailSearch(String mailSearch) {
		this.mailSearch = mailSearch;
	}

	public boolean isPlayerStatus() {
		return playerStatus;
	}

	public void setPlayerStatus(boolean playerStatus) {
		this.playerStatus = playerStatus;
	}

	public boolean isSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(boolean supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public boolean isSupplierAccountValid() {
		return supplierAccountValid;
	}

	public void setSupplierAccountValid(boolean supplierAccountValid) {
		this.supplierAccountValid = supplierAccountValid;
	}

	public boolean isSupplierAccountNotValid() {
		return supplierAccountNotValid;
	}

	public void setSupplierAccountNotValid(boolean supplierAccountNotValid) {
		this.supplierAccountNotValid = supplierAccountNotValid;
	}

	
	
	
	
	
	

}
