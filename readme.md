# API Framework


## Introduction
 
API Framework that does not require you to write code for almost all APIS. 

## Requirements

* Java 1.8
* Install maven.

## Configuration

* Clone repository.
* For generating key from google place [Click here](https://developers.google.com/places/web-service/get-api-key) and click on **GET A KEY** button:![alt text](https://i.imgur.com/OATTSD6.png)
* Go to src/test/resources/ExcelData
* Open Excel **googleplace** sheet and modify $$$$$$$ from parameters column to above generated key.


For more detail refer guidelines.

## Optional Configuration[Google drive support]

* Delete client_secret.json from src/main/resources.
* Turn on the [Drive api](https://developers.google.com/drive/v2/web/quickstart/java) (Step 1 only)
* Move client_secret.json to src/main/resources and give file name **client_secret.json**  
* Go to src/test/resources/ExcelData
* Upload excel file to your drive and open with google spreadsheet.
* Copy **excel id**:![alt text](https://i.imgur.com/5pOTkAz.png from spreadsheet) 
* Go to src/main/java/com/framework/constants >> Constants.java
* Search EXCELFILEPATH variable and change to above **excel id** and save file. 
* Modify/check your **Application Name** in com.framework.constants >> Constants.java >> APPLICATIONNAME(As per given in google drive)


## Run

* mvn clean compile test **or** Open testng.xml file and Run As TestNG Suite.
* If user do **Optional configuration** a new window will generate and select previously used email address.
* Click on **Allow** button.

## Reports

* Open Reports folder after running framework.
* Report will generate for each TestFlow.

![alt text](https://i.imgur.com/GQBHcIb.png)
	
## Guidelines

* #sheetName.1.id# -> Use for single value replacement.
* @sheetName.1.id@ -> Use for List replacement for only assert response.

### Run mode for TestFlow: 

Runmode helps for running particular TestFlow.

Test Id | Test Mode | Test Flow Name | Test Case Name
--- | --- | --- | ---
1 | Yes | Get Token for users | getAccessTokenForOpsUser
2 | | | getAccessTokenForWMGJ|
3 | No |Get To Be Paid Package| getToBePaidPackageGJLP |  

![alt text](https://i.imgur.com/xu7480I.png)

### Extract response value:

Extract values from the response and use for the next test cases.

Test Method and json path |
---|
**extractString**:$.responseData.X-Authorization-Token|
**extractNumber**:$.responseData.packages[0].amount|
**extractLong**:$.responseData.payment.lpTransaction.transactionDate |
**extractBoolean**:$.status |
**extractStringList**:$.responseData.payments[*].lpTransaction.status |

![alt text](https://i.imgur.com/BTI53hg.png)

### Extract dynamic value for TestCases.

Extract dynamic values for the below column:

* Test Url
* Test Input Json
* Test Headers
* Test Parameters
* Test Assert Response

For Example

* **Test Url**: /lppayment/#**sheetName.Test Id.path**#/    ------> **i.e** #googleplace.1.lng#
* **Test Assert Response**: 
	* #googleplace.14.reconciliationStatus#,to_be_paid
	* #googleplace.15.reconciliationStatus#,#googleplace.14.reconciliationStatus#**;**#googleplace.14.reconciliationStatus#,to_be_paid (Compare more than 2 values separated by **;**)
* **Test Assert Response List**:
	* @googleplace.24.status@,waiting_for_clearance;#googleplace.23.reconciliationStatus#,to_be_paid (Use @ @ means list replacement always first in assert column than # # single value replacement)
	
![alt text](https://i.imgur.com/pyIR5eD.png)	

### Schema validation for response.

* Add your schema under src/test/resources

Test Schema Name |
---|
accesstokensuccess.json|

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

* **Test Parameters**: ?createdFrom=#epoch.0.startdatetoday#&createdTo=#epoch.0.enddatetoday#