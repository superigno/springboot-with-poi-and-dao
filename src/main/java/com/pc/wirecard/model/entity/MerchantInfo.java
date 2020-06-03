package com.pc.wirecard.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Entity
@Table(name = "merchant")
public class MerchantInfo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String name;	
	private String displayTradingName;
	private String bankMerchantId;
	private String thirdpartyMerchantId;
	private String originator;
	private String dccCommission;
	private Integer vanId;
	private Integer integrated;
	private String streetLine1;
	private String streetLine2;
	private String city;
	private String state;
	private String postcode;
	private String country;
	private String phone;
	private String fax;
	private String contactPerson;
	private String contactEmail;
	private String baseCurrency;
	private String rateCategory;
	private String rateCategoryMcp;
	private Integer organizationId;
	private String merchantBatchId;
	private String description;
	private Integer createdBy;
	private String createdByName;
	private Integer lastModifiedBy;
	private String lastModifiedByName;
	private String clientId;
	private String currencyWhitelist;
	private String currencyBlacklist;
	private String dccCardtypeList;
	private String merchantType;
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDisplayTradingName() {
		return displayTradingName;
	}
	public String getBankMerchantId() {
		return bankMerchantId;
	}
	public String getThirdpartyMerchantId() {
		return thirdpartyMerchantId;
	}
	public String getOriginator() {
		return originator;
	}
	public String getDccCommission() {
		return dccCommission;
	}
	public Integer getVanId() {
		return vanId;
	}
	public Integer getIntegrated() {
		return integrated;
	}
	public String getStreetLine1() {
		return streetLine1;
	}
	public String getStreetLine2() {
		return streetLine2;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPostcode() {
		return postcode;
	}
	public String getCountry() {
		return country;
	}
	public String getPhone() {
		return phone;
	}
	public String getFax() {
		return fax;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public String getRateCategory() {
		return rateCategory;
	}
	public String getRateCategoryMcp() {
		return rateCategoryMcp;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public String getMerchantBatchId() {
		return merchantBatchId;
	}
	public String getDescription() {
		return description;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public Integer getLastModifiedBy() {
		return lastModifiedBy;
	}
	public String getLastModifiedByName() {
		return lastModifiedByName;
	}
	public String getClientId() {
		return clientId;
	}
	public String getCurrencyWhitelist() {
		return currencyWhitelist;
	}
	public String getCurrencyBlacklist() {
		return currencyBlacklist;
	}
	public String getDccCardtypeList() {
		return dccCardtypeList;
	}
	public String getMerchantType() {
		return merchantType;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDisplayTradingName(String displayTradingName) {
		this.displayTradingName = displayTradingName;
	}
	public void setBankMerchantId(String bankMerchantId) {
		this.bankMerchantId = bankMerchantId;
	}
	public void setThirdpartyMerchantId(String thirdpartyMerchantId) {
		this.thirdpartyMerchantId = thirdpartyMerchantId;
	}
	public void setOriginator(String originator) {
		this.originator = originator;
	}
	public void setDccCommission(String dccCommission) {
		this.dccCommission = dccCommission;
	}
	public void setVanId(Integer vanId) {
		this.vanId = vanId;
	}
	public void setIntegrated(Integer integrated) {
		this.integrated = integrated;
	}
	public void setStreetLine1(String streetLine1) {
		this.streetLine1 = streetLine1;
	}
	public void setStreetLine2(String streetLine2) {
		this.streetLine2 = streetLine2;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public void setRateCategory(String rateCategory) {
		this.rateCategory = rateCategory;
	}
	public void setRateCategoryMcp(String rateCategoryMcp) {
		this.rateCategoryMcp = rateCategoryMcp;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public void setMerchantBatchId(String merchantBatchId) {
		this.merchantBatchId = merchantBatchId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public void setLastModifiedBy(Integer lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public void setLastModifiedByName(String lastModifiedByName) {
		this.lastModifiedByName = lastModifiedByName;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public void setCurrencyWhitelist(String currencyWhitelist) {
		this.currencyWhitelist = currencyWhitelist;
	}
	public void setCurrencyBlacklist(String currencyBlacklist) {
		this.currencyBlacklist = currencyBlacklist;
	}
	public void setDccCardtypeList(String dccCardtypeList) {
		this.dccCardtypeList = dccCardtypeList;
	}
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	
	@Override
	public String toString() {
		return "MerchantInfo [id=" + id + ", name=" + name + ", displayTradingName=" + displayTradingName
				+ ", bankMerchantId=" + bankMerchantId + ", thirdpartyMerchantId=" + thirdpartyMerchantId
				+ ", originator=" + originator + ", dccCommission=" + dccCommission + ", vanId=" + vanId
				+ ", integrated=" + integrated + ", streetLine1=" + streetLine1 + ", streetLine2=" + streetLine2
				+ ", city=" + city + ", state=" + state + ", postcode=" + postcode + ", country=" + country + ", phone="
				+ phone + ", fax=" + fax + ", contactPerson=" + contactPerson + ", contactEmail=" + contactEmail
				+ ", baseCurrency=" + baseCurrency + ", rateCategory=" + rateCategory + ", rateCategoryMcp="
				+ rateCategoryMcp + ", organizationId=" + organizationId + ", merchantBatchId=" + merchantBatchId
				+ ", description=" + description + ", createdBy=" + createdBy + ", createdByName=" + createdByName
				+ ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedByName=" + lastModifiedByName + ", clientId="
				+ clientId + ", currencyWhitelist=" + currencyWhitelist + ", currencyBlacklist=" + currencyBlacklist
				+ ", dccCardtypeList=" + dccCardtypeList + ", merchantType=" + merchantType + "]";
	}
	
}
