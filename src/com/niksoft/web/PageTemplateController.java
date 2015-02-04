package com.niksoft.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class PageTemplateController  implements Serializable {

	private static final long serialVersionUID = 2774372430137219820L;

	public String getTemplate(){
		switch(AuthController.getUserRole(FacesContext.getCurrentInstance())){
		case "manager":
			return "/WEB-INF/templates/securedadmin.xhtml";
		case "vendors":
			return "/WEB-INF/templates/securedvendor.xhtml";
		default: 
			return "/WEB-INF/templates/secureduser.xhtml";
		}
	}
}
