package com.capgemini.pocwebapp.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public final class PathConstants {
  //
  // common paths used by various web controller classes
  // this module is needed by both console-web and console-uploader,
  // so it has been moved into console-services.
  //

  //NOTE: Before you update this list, consider placing the directory under /public/...
  public static final Collection<String> PUBLIC_PATHS = Collections.unmodifiableList(Arrays
          .asList("/healthcheck", "/registry_maintenance", "/enterprise_maintenance", "/jawr",
              "/resources", "/login/forgot", "/rcservices/validateAccount", "/getGAScript",
              "/errors", "/public"));

  public static final String ADMIN_PATH = "/admin";

  public static final String ADMIN_ACCOUNT_PATH = ADMIN_PATH + "/account";
  public static final String ADMIN_ACCOUNTS_LOAD_PATH = ADMIN_ACCOUNT_PATH + "/load";
  public static final String ADMIN_ACCOUNT_DETAILS_PATH = ADMIN_ACCOUNT_PATH + "/details";
  public static final String ADMIN_ACCOUNT_SAVE_PATH = ADMIN_ACCOUNT_DETAILS_PATH + "/save";
  public static final String ADMIN_ACCOUNT_LOAD_PATH = ADMIN_ACCOUNT_DETAILS_PATH + "/load";

  public static final String ADMIN_FACILITY_PATH = ADMIN_PATH + "/facility";
  public static final String ADMIN_FACILITIES_LOAD_PATH = ADMIN_FACILITY_PATH + "/load";
  public static final String ADMIN_FACILITY_DETAILS_PATH = ADMIN_FACILITY_PATH + "/details";
  public static final String ADMIN_FACILITY_SAVE_PATH = ADMIN_FACILITY_DETAILS_PATH + "/save";
  public static final String ADMIN_FACILITY_LOAD_PATH = ADMIN_FACILITY_DETAILS_PATH + "/load";

  public static final String ADMIN_TOOLS_PATH = ADMIN_PATH + "/tools";
  public static final String ADMIN_TOOLS_MENU_PATH = ADMIN_PATH + "/";

  public static final String MY_ACCOUNT_ADM_PATH = ADMIN_PATH + "/myaccount";

  public static final String REGISTRY_VAR = "registry";
  public static final String FACILITY_VAR = "facilityId";

  public static final String WSAPI_PATH = "/wsapi";
  public static final String CDT_API_PATH = WSAPI_PATH + "/cdt";
  public static final String GEO_API_PATH = WSAPI_PATH + "/geo";
  public static final String TENANT_API_PATH = WSAPI_PATH + "/tenant";

    public static final String AGREEMENT_PART = "/agreement/";
    public static final String AGREEMENT_TYPE = "agreementType";

  public static final String DEFAULT_ACCESS_DENIED_PATH = "/in";
  public static final String DEFAULT_MISSING_PATH = "/errors/404";
  public static final String DEFAULT_ERROR_PATH = "/errors/error";

  public static final String RDC_SERVICE_PATH = "/rcservices";
  public static final String RDC_ACCOUNT_SERVICE_PATH = RDC_SERVICE_PATH + "/account/";
  public static final String RDC_ACCOUNT_SERVICE_SAVE_PATH = "/saveAccount";
  public static final String RDC_ACCOUNT_SERVICE_LOAD_PATH = "/loadAccount";


  public static final String RDC_FACILITY_SERVICE_PATH = RDC_SERVICE_PATH + "/facility/";
  public static final String RDC_FACILITY_SERVICE_SAVE_PATH = "/saveFacility";
  public static final String RDC_FACILITY_SERVICE_LOAD_PATH = "/loadFacility";

  public static final String RDC_FORM_SERVICE_PATH = RDC_SERVICE_PATH + "/caseform/";
  public static final String RDC_CASE_SERVICE_SAVE_PATH = "/saveForm/{facilityId}/{registryName}";
  public static final String RDC_CASE_SERVICE_LOAD_PATH = "/loadForm/{facilityId}/{registryName}";
  public static final String RDC_FACILITY_FORM_SERVICE_PATH = RDC_SERVICE_PATH + "/facilityform/";
  public static final String RDC_FAC_FORM_SERVICE_SAVE_PATH = "/saveFacilityForm/{facilityId}/{registryName}";
  public static final String RDC_FAC_FORM_SERVICE_LOAD_PATH = "/loadFacilityForm/{facilityId}/{registryName}";


  private PathConstants() {
  }

  /**
   * Convenience for getting whether something is public.
   *
   * @param path
   * @return
   */
  public static boolean isPublicPath(String path) {
    boolean isPublic = false;

    for (String publicPath : PathConstants.PUBLIC_PATHS) {
      if (path.startsWith(publicPath)) {
        isPublic = true;
        break;
      }
    }

    return isPublic;
  }

  /**
   * Used by interceptors, but I put it here so we don't have an extra class that we don't need.
   *
   * @param path
   * @return
   */
  public static boolean isResetPassword(String path) {
    return path.contains("passwordexpired") || path.contains("changepassword/update");
  }
  
}
