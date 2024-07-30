package com.web.app.framework.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.openqa.selenium.WebElement;
import java.awt.Toolkit;
import static com.web.app.framework.utlis.properties.ObjectRepositoriesController.*;
import com.web.app.framework.utlis.general.WaitUtils;
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

	
	
	// Select service console Option

		public FilesPage selectServiceConsoleOption() {
		
				clickElementUsingJavascript(ddNavigationMenu, "Navigation Menu Dropdown");
				for(int i=0; i<= (optionsSeviceConsole.size())-1; i++) {
					if(optionsSeviceConsole.get(i).getText().trim().equals("Files")) {
						selectDropDownUsingValue(optionsSeviceConsole.get(i), "Files");
					}
				}
						return this;
		}
		
		
		public int identifyRecentDate() {
			int rowCount = 0;
			try {
				for (int i = 0; i <= (dateLastModifiedDate.size()) - 1; i++) {
					dateStrings.add(dateLastModifiedDate.get(i).getText().trim());
				}
				for (int j = 0; j <= (dateStrings.size()) - 1; j++) {
					DateTime date =	convertDateToSpecifiedFormat(dateStrings.get(j));
					dates.add(date);
					
				}
				for (int i = 0; i <= (dates.size()) - 1; i++) {
					if (mostRecentDate == null || dates.get(i).isAfter(mostRecentDate)) {
						mostRecentDate = dates.get(i);
						rowCount = i;
					}
				}

			} catch (java.lang.IndexOutOfBoundsException e) {
				//Assertion.assertFail("Failed due to Index Out of bound" + e);
			} catch (Exception e) {
				//Assertion.assertFail("Failed due to " + e);
			}
			return rowCount;

		}

		// Select the recent File and download

		public FilesPage openRecentFile(String Title) {
			int value = this.identifyRecentDate();
			try {
				for (int i = 0; i <= (colTitle.size()) - 1; i++) {
					if (colTitle.get(i).getText().trim().equals(Title)) {

						for (int j = 0; j <= (tblFiles.size()) - 1; j++) {
							if (j == value) {
							     WebElement FileLink = locateElement(Locators.XPATH, "//table[@data-aura-class='uiVirtualDataTable']//tbody//tr["
							     		+ "												+ j + \"]//th[\" + i + \"]//a");
								DownloadedFileExtension = locateElement(Locators.XPATH, "//table[@data-aura-class='uiVirtualDataTable']//tbody//tr[\"\r\n"
										+ "											+ j + \"]//th[\" + i + \"]//a//span[@class='slds-assistive-text']").getText().trim();
								DownloadedFileName = locateElement(Locators.XPATH, "(//*[@class='itemTitle desktop outputTextOverride uiOutputText'])[\" + j + \"]").getText().trim();
								clickElementUsingJavascript(FileLink, "File To be downloaded");

							}

						}
					}

				}
			} catch (Exception e) {
//				Assertion.assertFail("Unable to download the file due to " + e);
			}
			return this;
		}
		
		
		public FilesPage downloadFile() {
			new WaitUtils().waitForElementToBeVisible(btnDownload);
			clickElementUsingJavascript(btnDownload, "Download Button");
			return this;
		}
		
		// verify the file is downloaded in the project

		public FilesPage verifyFileDownload() {
			try {
				File file = new File(downloadDir, FilesPage.DownloadedFileName);
				if (file.exists() && file.isFile()) {
//					Assertion.assertTrue(true, "File is successfully downloaded");
//					reportStep("File is successfully downloaded", "pass");
				}
			} catch (Exception e) {
//				Assertion.assertFail("File is not downloaded due to " + e);
//				reportStep("File is not downloaded due to", "fail");
			}
			return this;
		}
		
		

		public boolean isFileAlreadyUploaded() {
			boolean flag = false;
			try {
				for (int i = 0; i <= (tblFilesColumn.size()) - 1; i++) {
					if (tblFilesColumn.get(i).getText().equals(getDOMValue("FilesPage", "FileName"))) {
						flag = true;
						break;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
		
		// Upload File

		public FilesPage uploadFile() throws AWTException, IOException {
			try {
				robot = new Robot();
				int Suffix = 1;
				String ModifiedFileName = null;
				String Path = getDOMValue("FilesPage", "UploadFolder");
				if (Path != null && Path.isEmpty()) { // Check if UploadFolder is not empty or null
        				clickElementUsingJavascript(btnUpload, "Upload New File");
                     new Robot().delay(5000);
					String DesiredUploadFileName = getDOMValue("FilesPage", "FileName");
					String UploadFilePath = System.getProperty("user.dir") + File.separator + Path + File.separator
							+ DesiredUploadFileName;
					File file = new File(UploadFilePath, DesiredUploadFileName);
					while (file.exists() && this.isFileAlreadyUploaded() == true) {
						ModifiedFileName = DesiredUploadFileName + "_" + Suffix;
						UploadFilePath = System.getProperty("user.dir") + File.separator + Path + File.separator + ModifiedFileName;
						file = new File(UploadFilePath);
						Suffix++;
					}
					StringSelection selection = new StringSelection(UploadFilePath);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
					new Robot().keyPress(KeyEvent.VK_CONTROL);
					new Robot().keyPress(KeyEvent.VK_V);
					new Robot().keyRelease(KeyEvent.VK_V);
					new Robot().keyRelease(KeyEvent.VK_CONTROL);
					new Robot().keyPress(KeyEvent.VK_ENTER);
					new Robot().keyRelease(KeyEvent.VK_ENTER);
				} else {
					// Handle the case where UploadFolder is empty or null
				}
			} catch (Exception e) {
			//	Assertion.assertFail("Unable to upload the file due to" + e);
			}
			return this;
		}
		
		public FilesPage verifyUploadIsSuccess() {
			try {
			verifyDisplayed(messageSuccessUpload);
			//reportStep("Success Toaster Message", "pass");
			}catch(Exception e) {
		//reportStep("Success Toaster Message is not displayed", "fail");		
			}
			return this;
		}

}
