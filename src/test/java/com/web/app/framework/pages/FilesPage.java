package com.web.app.framework.pages;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.openqa.selenium.WebElement;

import static com.web.app.framework.utlis.properties.ObjectRepositoriesController.*;
import com.web.app.selenium.api.design.Locators;
import com.web.app.testng.api.TestNGHooks;

public class FilesPage extends TestNGHooks {

	public static String FileName = null;

	public static List<String> dateStrings = null;
	DateTime mostRecentDate = null;

	private static String DownloadedFileExtension = null;

	private static String DownloadedFileName = null;

	Robot robot;

	public FilesPage() {
		dateStrings = new ArrayList<String>();
	}

	public static String getDownloadedFileName() {
		return DownloadedFileName;

	}

	public static String getDownloadedFileExtension() {
		return DownloadedFileExtension;

	}

	WebElement ddNavigationMenu = locateElement(Locators.XPATH, getDOMValue("FilesPage", "NavigationMenu.dropdown"));

	List<WebElement> optionsSeviceConsole = locateElements(Locators.XPATH,
			getDOMValue("FilesPage", "SeviceConsole.options"));

	WebElement tblHeaderLastModified = locateElement(Locators.XPATH,
			getDOMValue("FilesPage", "HeaderLastModified.table"));

	List<WebElement> dateLastModifiedDate = locateElements(Locators.XPATH,
			getDOMValue("FilesPage", "LastModifiedDate.date"));

	List<WebElement> colTitle = locateElements(Locators.XPATH, getDOMValue("FilesPage", "Title.column"));

	List<WebElement> tblFiles = locateElements(Locators.XPATH, getDOMValue("FilesPage", "Files.table"));

	List<WebElement> tblFilesColumn = locateElements(Locators.XPATH, getDOMValue("FilesPage", "FilesColumn.table"));

	WebElement linkPublic = locateElement(Locators.XPATH, getDOMValue("FilesPage", "Public.link"));

	WebElement toggleExpirationDate = locateElement(Locators.XPATH, getDOMValue("FilesPage", "ExpirationDate.toggle"));

	WebElement btnCreateLink = locateElement(Locators.XPATH, getDOMValue("FilesPage", "CreateLink.button"));

	WebElement btnCreate = locateElement(Locators.XPATH, getDOMValue("FilesPage", "Create.button"));

	WebElement btnCopyLink = locateElement(Locators.XPATH, getDOMValue("FilesPage", "CopyLink.button"));

	WebElement txtReadOnlyPublicUrl = locateElement(Locators.XPATH, getDOMValue("FilesPage", "ReadOnlyPublicUrl.text"));

	WebElement btnClosePopup = locateElement(Locators.XPATH, getDOMValue("FilesPage", "ClosePopup.button"));

	WebElement btnDownload = locateElement(Locators.XPATH, getDOMValue("FilesPage", "Download.button"));

	WebElement btnShare = locateElement(Locators.XPATH, getDOMValue("FilesPage", "Share.button"));

	WebElement txtSearchPeople = locateElement(Locators.XPATH, getDOMValue("FilesPage", "SearchPeople.textbox"));

	WebElement modalPeopleResults = locateElement(Locators.XPATH, getDOMValue("FilesPage", "PeopleResults.modal"));

	List<WebElement> tblpeopleResults = locateElements(Locators.XPATH, getDOMValue("FilesPage", "peopleResults.table"));

	WebElement errorMessageCannotShare = locateElement(Locators.XPATH,
			getDOMValue("FilesPage", "errorMessageCannotShare.popup"));

	WebElement deleteShareWith = locateElement(Locators.XPATH, getDOMValue("FilesPage", "deleteShareWith.button"));

	WebElement txtAddMessage = locateElement(Locators.XPATH, getDOMValue("FilesPage", "AddMessage.textbox"));

	WebElement successMessageShare = locateElement(Locators.XPATH, getDOMValue("FilesPage", "MessageShare.popup"));

	WebElement btnUpload = locateElement(Locators.XPATH, getDOMValue("FilesPage", "Upload.button"));

	WebElement messageSuccessUpload = locateElement(Locators.XPATH, getDOMValue("FilesPage", "SuccessUpload.popup"));

	WebElement messageFileUpload = locateElement(Locators.XPATH, getDOMValue("FilesPage", "FileUpload.popup"));

	WebElement btnDone = locateElement(Locators.XPATH, getDOMValue("FilesPage", "Done.button"));

	List<WebElement> ddAction = locateElements(Locators.XPATH, getDOMValue("FilesPage", "Action.dropdown"));

	List<WebElement> listActionOptions = locateElements(Locators.XPATH,
			getDOMValue("FilesPage", "ActionOptions.options"));

	WebElement labelFileName = locateElement(Locators.XPATH, getDOMValue("FilesPage", "FileName.label"));

	WebElement lblFileExtension = locateElement(Locators.XPATH, getDOMValue("FilesPage", "FileExtension.label"));

	WebElement btnCloseTestCase = locateElement(Locators.XPATH, getDOMValue("FilesPage", "CloseTestCase.button"));

	WebElement hdrDeletePopup = locateElement(Locators.XPATH, getDOMValue("FilesPage", "DeletePopup.header"));

	WebElement btnDelete = locateElement(Locators.XPATH, getDOMValue("FilesPage", "Delete.button"));

	WebElement messageSuccessDelete = locateElement(Locators.XPATH, getDOMValue("FilesPage", "SuccessDelete.popup"));

}
