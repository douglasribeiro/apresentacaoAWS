package com.apresentacaoaws.service.exception;

import java.util.Date;
import java.util.List;

public class ApiErrorList extends ApiError {
	
	private static final long serialVersionUID = 1L;

	private List<String> errors;
	
	public ApiErrorList(int code, String msg, Date date, List<String> errors) {
		super(code, msg, date);
		this.setErrors(errors);
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
