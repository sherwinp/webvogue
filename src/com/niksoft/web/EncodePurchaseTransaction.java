package com.niksoft.web;

import javax.servlet.http.HttpServletRequest;

public class EncodePurchaseTransaction extends com.d_project.qrcode.web.QRCodeServlet {

	private static final long serialVersionUID = -2579138647756208845L;

	@Override
	public String EncodeData(HttpServletRequest request) {
		String data = request.getParameter("d");
		return String.format("%s://%s:%s%s/%s/%s;jsessionid=%s", request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath(), "api/pay", data, request.getSession(false) != null ? request.getSession(false).getId() : "");
	}

}
