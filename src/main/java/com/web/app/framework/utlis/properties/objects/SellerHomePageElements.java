package com.web.app.framework.utlis.properties.objects;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public interface SellerHomePageElements extends Config {

	@Key(value = "Options.tab")
	String options();

	@Key(value = "New.button")
	String New();

	@Key(value = "NewAccount.title")
	String NewAccount();

	@Key(value = "AccountName.text")
	String AccountName();

	@Key(value = "Ownership.dropdown")
	String OwnershipdropDown();

	@Key(value = "OwnerShip.options")
	String OwnershipOptions();

	@Key(value = "Save.button")
	String Save();

	@Key(value = "Option.tab")
	String Option();

	@Key(value = "SearchAccount.text")
	String SearchAccount();
	
	@Key(value= "AccountView.table")
	String AccountView();

	@Key(value = "Message.text")
	String Message();

	@Key(value = "ActionDropdown.dropdown")
	String ActionDropdown();

	@Key(value = "Edit.button")
	String Edit();

	@Key(value = "EditAccount.popup")
	String EditAccount();

	@Key(value = "PhoneNo.text")
	String PhoneNo();

	@Key(value = "AccountType.dropdown")
	String AccountTypedropDown();

	@Key(value = "AccountType.options")
	String AccountTypeOptions();

	@Key(value = "Industry.dropdown")
	String IndustrydropDown();

	@Key(value = "Industry.options")
	String IndustryOptions();

	@Key(value = "BillingStreet.text")
	String BillingStreet();

	@Key(value = "BillingCity.text")
	String BillingCity();

	@Key(value = "BillingZipCode.text")
	String BillingZipCode();

	@Key(value = "BillingProvince.text")
	String BillingProvince();

	@Key(value = "BillingCountry.text")
	String BillingCountry();

	@Key(value = "ShippingStreet.text")
	String ShippingStreet();

	@Key(value = "ShippingCity.text")
	String ShippingCity();

	@Key(value = "ShippingZipCode.text")
	String ShippingZipCode();

	@Key(value = "ShippingProvince.text")
	String ShippingProvince();

	@Key(value = "ShippingCountry.text")
	String ShippingCountry();

	@Key(value = "CustomerPriority.dropdown")
	String CustomerPrioritydropDown();

	@Key(value = "SLA.dropdown")
	String SLAdropDown();

	@Key(value = "CustomerPriority.options")
	String CustomerPriorityOptions();

	@Key(value = "SLA.options")
	String SLAOptions();

	@Key(value = "Active.dropdown")
	String ActivedropDown();

	@Key(value = "Active.options")
	String ActiveOptions();

	@Key(value = "Upsell.dropdown")
	String UpselldropDown();

	@Key(value = "Upsell.options")
	String UpsellOptions();

	@Key(value = "PhoneNoValue.grid")
	String PhoneNoValue();

}
