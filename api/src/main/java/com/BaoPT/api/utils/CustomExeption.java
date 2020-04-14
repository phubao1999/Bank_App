/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.utils;

/**
 * [OVERVIEW] CustomExeption.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

public class CustomExeption extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public CustomExeption(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
