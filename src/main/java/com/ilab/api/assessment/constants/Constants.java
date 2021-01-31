package com.ilab.api.assessment.constants;

public class Constants {
	
	private Constants() {
		
	}

	public static final String EXTENTREPORTPATH = System.getProperty("user.dir")+"/ExtentReports/index.html";
	
	public static final String EXTENTCONFIGFILEPATH = System.getProperty("user.dir") +"/src/test/resources/extentreport.xml";

	public static final String RESPONSETXTPATH="./output/responses/";

	public static final String JSONFILE=System.getProperty("user.dir")+"/src/test/resources/newpet.json";
	public static final String DOGBASEURL = "https://dog.ceo/api";
	public static final String PETSTOREBASEURL = "https://petstore.swagger.io/v2/pet";

	//Endpoints
	public static final String GETRANDOMBREEDURL = "/breeds/image/random";
	public static final String DOGLISTURL = "/breeds/list/all";
	public static final String BULLDOGLISTURL = "/breed/bulldog/list";
	public static final String BULLDOGURL = "/breed/bulldog/";
	public static final String IMAGESURL = "/images";
	public static final String GETPETBYSTATUSURL = "/findByStatus?status=available";

	public static final String SUCCESS = "success";
	public static final String STATUS = "status";
	public static final String MESSAGE = "message";
	public static final String BULLDOG = "bulldog";



}
