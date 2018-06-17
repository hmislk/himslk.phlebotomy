/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smapling;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.prefs.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author buddh
 */
public class Prefs {

    private static final String URL = "url";
    private static final String PRINTER = "printer";
    
    private static String urlValue = "";
    private static String username;
    private static String password;
    private static String departmentName;
    private static Long departmentId;
    private static String institutionName;
    private static Long institutionId;
    private static String usersName;
    private static Long usersId;
    private static boolean login;
    private static String printer;

    public static void loadPrefs() {
        System.out.println("load prefs");
        Preferences prefs = Preferences.userNodeForPackage(Prefs.class);
        urlValue = prefs.get(URL, urlValue);
        printer = prefs.get(PRINTER, printer);
    }

    public static void savePrefs() {
        System.out.println("save prefs");
        Preferences prefs = Preferences.userNodeForPackage(Prefs.class);
        prefs.put(URL, getUrlValue());
        prefs.put(PRINTER, getPrinter());
    }

    public static void getDataFromResponse(String response) {
        String patternStart = "#{";
        String patternEnd = "}";
        String regexString = Pattern.quote(patternStart) + "(.*?)" + Pattern.quote(patternEnd);
        String text = response;
        Pattern p = Pattern.compile(regexString);
        Matcher m = p.matcher(text);
        List<String> strBlocks = new ArrayList<>();
        while (m.find()) {
            String block = m.group(1);
            if (!block.trim().equals("")) {

                if (block.contains("|")) {
                    String[] blockParts = block.split("\\|");
                    for (int i = 0; i < blockParts.length; i++) {
                        System.out.println("blockParts[i] = " + blockParts[i]);
                        String[] parameterValueSet = blockParts[i].split("=");
                        if (parameterValueSet.length == 2) {
                            String para = parameterValueSet[0];
                            String paraVal = parameterValueSet[1];
                            switch (para) {
                                case "Login":
                                    if (paraVal.equals("1")) {
                                        login = true;
                                    } else if (paraVal.equals("0")) {
                                        login = false;
                                        return;
                                    }
                                    break;
                                case "Department":
                                    departmentName = paraVal;
                                    break;
                                case "DepartmentId":
                                    departmentId = stringToLong(paraVal);
                                    break;
                                case "Institution":
                                    institutionName = paraVal;
                                    break;
                                case "InstitutionId":
                                    institutionId = stringToLong(paraVal);
                                    break;
                                case "User":
                                    username = paraVal;
                                    break;
                                case "UserId":
                                    usersId = stringToLong(paraVal);
                                    break;
                            }
                        }
                    }

                }

            }
        }
    }

    public static Long stringToLong(String str) {
        try {
            Long n = Long.parseLong(str);
            return n;
        } catch (Exception e) {
            return null;
        }
    }

    public static String executePost(String targetURL, Map<String, Object> parameters) {
        HttpURLConnection connection = null;
        if (parameters != null && !parameters.isEmpty()) {
            targetURL += "?";
        }
        Set s = parameters.entrySet();
        Iterator it = s.iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            Object pVal = m.getValue();
            String pPara = (String) m.getKey();
            targetURL += pPara + "=" + pVal.toString() + "&";
        }
        if (parameters != null && !parameters.isEmpty()) {
            targetURL += "last=true";
        }
        
        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setUseCaches(false);
            connection.setDoOutput(true);
            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
//            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static String getUrlValue() {
        if(urlValue==null){
            urlValue = "";
        }
        return urlValue;
    }

    public static void setUrlValue(String urlValue) {
        Prefs.urlValue = urlValue;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String aUsername) {
        username = aUsername;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String aPassword) {
        password = aPassword;
    }

    public static String getDepartmentName() {
        return departmentName;
    }

    public static void setDepartmentName(String aDepartmentName) {
        departmentName = aDepartmentName;
    }

    public static Long getDepartmentId() {
        return departmentId;
    }

    public static void setDepartmentId(Long aDepartmentId) {
        departmentId = aDepartmentId;
    }

    public static String getInstitutionName() {
        return institutionName;
    }

    public static void setInstitutionName(String aInstitutionName) {
        institutionName = aInstitutionName;
    }

    public static Long getInstitutionId() {
        return institutionId;
    }

    public static void setInstitutionId(Long aInstitutionId) {
        institutionId = aInstitutionId;
    }

    public static String getUsersName() {
        return usersName;
    }

    public static void setUsersName(String aUsersName) {
        usersName = aUsersName;
    }

    public static Long getUsersId() {
        return usersId;
    }

    public static void setUsersId(Long aUsersId) {
        usersId = aUsersId;
    }

    public static boolean isLogin() {
        return login;
    }

    public static void setLogin(boolean aLogin) {
        login = aLogin;
    }

    public static String getPrinter() {
        if(printer==null){
            printer = "";
        }
        return printer;
    }

    public static void setPrinter(String aPrinter) {
        printer = aPrinter;
    }

    
    
    
}
