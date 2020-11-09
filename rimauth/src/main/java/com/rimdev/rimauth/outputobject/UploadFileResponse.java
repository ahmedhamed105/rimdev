package com.rimdev.rimauth.outputobject;

import com.rimdev.rimauth.entities.User;

public class UploadFileResponse {
	
	   private String fileName;
	   private String fileDownloadUri;
	   private String fileType;
	   private long size;
	   private int error;
	   private String error_message;


		public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, int error,
			String error_message) {
		super();
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
		this.error = error;
		this.error_message = error_message;
	}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileDownloadUri() {
			return fileDownloadUri;
		}

		public void setFileDownloadUri(String fileDownloadUri) {
			this.fileDownloadUri = fileDownloadUri;
		}

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}

		public long getSize() {
			return size;
		}

		public void setSize(long size) {
			this.size = size;
		}

		public int getError() {
			return error;
		}

		public void setError(int error) {
			this.error = error;
		}

		public String getError_message() {
			return error_message;
		}

		public void setError_message(String error_message) {
			this.error_message = error_message;
		}
		
		
	
    

}
