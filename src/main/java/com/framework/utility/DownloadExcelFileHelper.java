/**
 * 
 */
package com.framework.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import com.framework.constants.Constants;
import com.framework.constants.Constants.DownloadExcelFileHelperConstants;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

/**
 * @author nikhil
 *
 */
public class DownloadExcelFileHelper {
	/*
	 * Application name.
	 */
	private static final String APPLICATION_NAME = DownloadExcelFileHelperConstants.APPLICATIONNAME.toString();

	/*
	 * Directory to store user credentials for this application.
	 */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			DownloadExcelFileHelperConstants.LOCATIONOFCREDENTIAL.toString());

	/*
	 * Global instance of the {@link FileDataStoreFactory}.
	 */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/*
	 * Global instance of the JSON factory.
	 */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/*
	 * Global instance of the HTTP transport.
	 */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/drive-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	public static void initialMethod() throws IOException {
		// Build a new authorized API client service.
		Drive service = getDriveService();
		/*
		 * File id for download.
		 */
		downloadFile(service, DownloadExcelFileHelperConstants.EXCELFILEPATH.toString());
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws IOException {
		// Load client secrets.
		InputStream in = DownloadExcelFileHelper.class
				.getResourceAsStream(DownloadExcelFileHelperConstants.CLIENTSECRETJSON.toString());
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 * Build and return an authorized Drive client service.
	 * 
	 * @return an authorized Drive client service
	 * @throws IOException
	 */
	public static Drive getDriveService() throws IOException {
		Credential credential = authorize();
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}

	/*
	 * download file helper.
	 * 
	 */
	private static void downloadFile(Drive service, String fileId) {

		try {
			File file = service.files().get(fileId).execute();
			System.out.println("Title: " + file.getName());
			System.out.println("Description: " + file.getDescription());
			System.out.println("MIME type: " + file.getMimeType());
			OutputStream outputStream = new FileOutputStream(new java.io.File(Constants.EXCEL_PATH));
			service.files().export(fileId, DownloadExcelFileHelperConstants.FILETYPE.toString())
					.executeMediaAndDownloadTo(outputStream);
			System.out.println(outputStream);
		} catch (IOException e) {
			System.out.println("An error occurred: " + e);
		} catch (Exception e) {
			System.out.println("An error occurred: " + e);
		}
	}
}
