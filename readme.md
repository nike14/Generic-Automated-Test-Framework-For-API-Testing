# API Framework


## Introduction
 
API Framework that does not require you to write code for almost all APIS. 

## Requirements

* Java 1.8
* Install maven.

## Configuration

* Clone repository.
* Delete client_secret.json from src/main/resources.
* Turn on the [Drive api](https://developers.google.com/drive/v2/web/quickstart/java) (Step 1 only)
* Move client_secret.json to src/main/resources and give file name **client_secret.json**  
* Go to src/test/resources/ExcelData
* Upload excel file to your drive and open with google spreadsheet.
* For generating key from google place [Click here](https://developers.google.com/places/web-service/get-api-key) and click on **GET A KEY** button.
* Open Drive Excel and modify $$$$$$$ from parameters column to above generated key.
* Copy **excel id** from spreadsheet URL e.g **1eXH2bB1KiQB7lB6zNq67gyiURbdsfGgsiwq0efqrqXc** from URL "https://docs.google.com/spreadsheets/d/1eXH2bB1KiQB7lB6zNq67gyiURbdsfGgsiwq0efqrqXc/edit#gid=391694237"
* Go to src/main/java/com/framework/constants >> Constants.java
* Search EXCELFILEPATH variable and change to above **excel id** and save file. 
* Modify/check your **Application Name** in com.framework.constants >> Constants.java >> APPLICATIONNAME(As per given in google drive)

For more detail refer guidelines.

## Run

* mvn clean compile test **or** Open testng.xml file and Run As TestNG Suite.
* After running above command one new window will generate and select previously used email address.
* Click on **Allow** button.

## Reports

* Open Reports folder after running framework.
* Report will generate for each TestFlow.
	
## Guidelines

* #1.id# -> Use for single value replacement.
* @1.id@ -> Use for List replacement for only assert response.

### Run mode for TestFlow: 

Runmode helps for running particular TestFlow.

TCID | RunMode | TFNameAndDesc | TCName
--- | --- | --- | ---
1 | Yes | Get Token for users | getAccessTokenForOpsUser
2 | | | getAccessTokenForWMGJ|
3 | No |Get To Be Paid Package| getToBePaidPackageGJLP |  

### Extract dynamic value for TestCases.

Extract dynamic values for the below column:

* URL
* Input Json
* Headers
* Parameters
* Assert Response

For Example

* **URL**: /lppayment/#18.id#/
* **Assert Response**: 
	* #17.reconciliationStatus#,to_be_paid
	* #17.reconciliationStatus#,#15.reconciliationStatus#**;**#17.reconciliationStatus#,to_be_paid (Compare more than 2 values separed by **;**)
* **Assert Response List**:
	* @24.status@,waiting_for_clearance;#23.reconciliationStatus#,to_be_paid (Use @ @ means list replacement first in assert column than # # single value replacement)
	
### Schema validation for response.

* Add your schema under src/test/resources

output |
---|
accesstokensuccess.json|

### Extract response value:

Extract values from the response and use for the next test cases.

Method and json path |
---|
**extractString**:$.responseData.X-Authorization-Token|
**extractNumber**:$.responseData.packages[0].amount|
**extractLong**:$.responseData.payment.lpTransaction.transactionDate |
**extractBoolean**:$.status |

####  Epoch Date for parameters:

**Extract dynamic values for below data**:

* startdatetoday
* enddatetoday
* startdateyesterday
* enddateyesterday
* startdatethisweek
* startdatelastsevendays
* startdatethismonth
* startdatelastmonth
* enddatelastmonth
* startdatecustomrange

For Example

* **Parameters**: ?createdFrom=#0.startdatetoday#&createdTo=#0.enddatetoday#