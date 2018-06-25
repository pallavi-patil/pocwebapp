package com.capgemini.pocwebapp.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class PathHelper {

  /**
   * @param req HttpServletRequest
   * @return the path that we want to test path matches against
   */
  public static String getPath(HttpServletRequest req) {
    // I think we'll typically want to check against the actual URI requested
    // However, if we don't have it then fall back to the servletPath
    String requestUri = req.getRequestURI();
    return StringUtils.isEmpty(requestUri) ? req.getServletPath() : requestUri;
  }

  public static boolean isAdminPath(String path) {
    return path != null && path.toLowerCase().startsWith(PathConstants.ADMIN_PATH + "/");
  }

  public static boolean isAdminPath(HttpServletRequest req) {
    return isAdminPath(getPath(req));
  }

  public static boolean isServicePath(String path) {
    return path != null && path.toLowerCase().startsWith(PathConstants.RDC_SERVICE_PATH + "/");
  }

  public static boolean isServicePath(HttpServletRequest req) {
    return isServicePath(getPath(req));
  }

   /**
   * Similar to above for facility, but only expects a registry in the path, and does a replacement instead of appending.
   * @param registryUrlName
   * @param url
   * @return
   */
  public static String replaceRegistryPath(String registryUrlName, String url){
    return replacePath(url, PathConstants.REGISTRY_VAR, registryUrlName);
  }


  /**
   * Similar to above for facility, but does a replacement instead of appending.
   * @param registryUrlName
   * @param url
   * @return
   */
  public static String replaceFacilityPath(String registryUrlName, String facilityId,
      String url) {
    return replacePath(replaceRegistryPath(registryUrlName,url),PathConstants.FACILITY_VAR , facilityId);
  }

  /**
   * Util for doing {constant} -> {replacement}
   * @param constant The value you want to replace, will be wrapped in {}
   * @param url The whole URL you want to
   * @param replacement What you want to replace with
   * @return
   */
  public static String replacePath(String url, String constant, String replacement){
    return url.replace("{" + constant + "}", replacement);
  }
}
