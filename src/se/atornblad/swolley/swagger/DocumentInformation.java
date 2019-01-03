package se.atornblad.swolley.swagger;

public class DocumentInformation {
	private String description;
	private String version;
	private String title;
	private String termsOfService;
	//private Contact contact;
	private LicenseInformation license;
	
	public DocumentInformation() {
		
	}
	
	public DocumentInformation(String description, String version, String title, String termsOfService,
			LicenseInformation license) {
		this.description = description;
		this.version = version;
		this.title = title;
		this.termsOfService = termsOfService;
		this.license = license;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTermsOfService() {
		return termsOfService;
	}

	public void setTermsOfService(String termsOfService) {
		this.termsOfService = termsOfService;
	}

	public LicenseInformation getLicense() {
		return license;
	}

	public void setLicense(LicenseInformation license) {
		this.license = license;
	}
}
