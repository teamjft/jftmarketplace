package com.jft.market.api.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement(name = "status")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseStatus {

	public static final ResponseStatus SUCCESS = new ResponseStatus("success", HttpStatus.OK);
	public static final ResponseStatus ERROR = new ResponseStatus("error", HttpStatus.INTERNAL_SERVER_ERROR);
	public static final ResponseStatus FAILURE = new ResponseStatus("failure", HttpStatus.BAD_REQUEST);

	private ResponseStatus(String message, HttpStatus httpStatus) {
		status = message;
		response = new ResponseEntity<ResponseStatus>(this, httpStatus);
	}

	@JsonIgnore
	private ResponseEntity<ResponseStatus> response;

	private String status;

	public ResponseEntity<ResponseStatus> getResponse() {
		return response;
	}

	public String getStatus() {
		return status;
	}
}
