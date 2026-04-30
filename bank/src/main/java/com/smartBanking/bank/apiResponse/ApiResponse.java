package com.smartBanking.bank.apiResponse;

import java.time.LocalDateTime;

//import com.smartBanking.bank.User.Dto.UserResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
	
private String message;
	
	private T data;
	
	private boolean success;
	
	private int status ;
	
	private LocalDateTime timestamp;
	

	
	
	public ApiResponse(String message, T data, boolean success, int status) {
		super();
		this.message = message;
		this.data = data;
		this.success = success;
		this.status = status;
		this.timestamp = LocalDateTime.now();
	}


	public ApiResponse() {
		
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	

	

}
