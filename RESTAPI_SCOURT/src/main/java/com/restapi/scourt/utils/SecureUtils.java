package com.restapi.scourt.utils;
/*     */ import java.security.MessageDigest;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;

/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;

/*     */ import org.bouncycastle.util.encoders.Base64;
/*     */ 
/*     */ public final class SecureUtils
/*     */ {
/*     */   public static Object secure(Object param)
/*     */   {
/*  40 */     if (param == null) {
/*  41 */       param = "";
/*     */     }
/*  43 */     else if ((param instanceof String)) {
/*  44 */       String paramStr = (String)param;
/*     */ 
/*  46 */       paramStr = paramStr.replaceAll("&", "&amp;");
/*  47 */       paramStr = paramStr.replaceAll("\"", "&quot;");
/*  48 */       paramStr = paramStr.replaceAll("<", "&lt;");
/*  49 */       paramStr = paramStr.replaceAll(">", "&gt;");
/*  50 */       paramStr = paramStr.replaceAll("'", "&#39;");
/*     */ 
/*  53 */       paramStr = paramStr.replaceAll("%2F", "");
/*     */ 
/*  55 */       param = paramStr;
/*     */     }
/*     */ 
/*  59 */     return param;
/*     */   }
/*     */ 
/*     */   public static String unsecure(String secured) {
/*  63 */     if (secured == null) {
/*  64 */       secured = "";
/*     */     } else {
/*  66 */       secured = secured.replaceAll("&lt;", "<");
/*  67 */       secured = secured.replaceAll("&gt;", ">");
/*  68 */       secured = secured.replaceAll("&amp;", "&");
/*  69 */       secured = secured.replaceAll("&quot;", "\"");
/*  70 */       secured = secured.replaceAll("&#39;", "'");
/*     */     }
/*     */ 
/*  73 */     return secured;
/*     */   }
/*     */ 
/*     */   public static String getParameter(HttpServletRequest request, String param) {
/*  77 */     return getParameter(request, param, "");
/*     */   }
/*     */ 
/*     */   public static String getParameter(HttpServletRequest request, String param, String dephault)
/*     */   {
/*  82 */     Map securedParams = getParameters(request);
/*     */     String value;
/*  84 */     if (securedParams.containsKey(param)) {
/*  85 */       value = ((String[])securedParams.get(param))[0];
/*  86 */       value = value.trim();
/*  87 */       if ((dephault == null) && ("".equals(value)))
/*  88 */         value = null;
/*     */     }
/*     */     else {
/*  91 */       value = dephault;
/*     */     }
/*     */ 
/*  94 */     return value;
/*     */   }
/*     */ 
/*     */   public static Map<String, String[]> getParameters(HttpServletRequest request)
/*     */   {
/* 105 */     String key = null;
/* 106 */     String[] values = null;
/* 107 */     Map securedParamMap = new HashMap();
/*     */ 
/* 110 */     Enumeration keyset = request.getParameterNames();
/*     */ 
/* 112 */     while (keyset.hasMoreElements()) {
/* 113 */       key = (String)keyset.nextElement();
/* 114 */       values = request.getParameterValues(key);
/*     */ 
/* 116 */       String[] securedParamValues = new String[values.length];
/*     */ 
/* 118 */       for (int i = 0; i < securedParamValues.length; i++) {
/* 119 */         securedParamValues[i] = ((String)secure(values[i]));
/*     */       }
/*     */ 
/* 123 */       securedParamMap.put(key, securedParamValues);
/*     */     }
/*     */ 
/* 126 */     return securedParamMap;
/*     */   }
/*     */ 
/*     */   public static void addHeader(HttpServletResponse response, String headerName, String headerValue) {
/*     */   }
/*     */ 
/*     */   public static String getHeader(HttpServletRequest request, String headerName) {
/* 169 */     return getHeader(request, headerName, "");
/*     */   }
/*     */ 
/*     */   public static String getHeader(HttpServletRequest request, String headerName, String dephault) {
/* 173 */     String header = request.getHeader(headerName);
/* 174 */     header = (String)secure(header);
/*     */ 
/* 176 */     if ((header == null) || ("".equals(header))) {
/* 177 */       header = dephault;
/*     */     }
/*     */ 
/* 180 */     return header;
/*     */   }
/*     */ 
/*     */   public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, int maxAge) {
/*     */   }
/*     */ 
/*     */   public static void addCookie(HttpServletResponse response, Cookie cookie) {
/*     */   }
/*     */ 
/*     */   public static String encode(String qwer, int round) {
/* 190 */     qwer = qwer + CoreUtils.getRandom(3);
/*     */ 
/* 192 */     for (int index = 0; index < round; index++) {
/* 193 */       qwer = new String(Base64.encode(qwer.getBytes()));
/*     */     }
/*     */ 
/* 196 */     String t1 = qwer.substring(0, 4);
/* 197 */     String t2 = qwer.substring(4);
/*     */ 
/* 199 */     String[] caret = { "p", "v", "o", "@", "b", "q", "3", "$", "n", "0" };
/* 200 */     int chip = Integer.valueOf(CoreUtils.getRandom(1)).intValue();
/*     */ 
/* 202 */     qwer = t1 + caret[chip] + t2;
/* 203 */     qwer = new String(Base64.encode(qwer.getBytes()));
/*     */ 
/* 205 */     return qwer;
/*     */   }
/*     */ 
/*     */   public static String decode(String qwer, int round) {
/* 209 */     qwer = new String(Base64.decode(qwer.getBytes()));
/*     */ 
/* 211 */     String t1 = qwer.substring(0, 4);
/* 212 */     String t2 = qwer.substring(5);
/*     */ 
/* 214 */     qwer = t1 + t2;
/*     */ 
/* 216 */     for (int index = 0; index < round; index++) {
/* 217 */       qwer = new String(Base64.decode(qwer.getBytes()));
/*     */     }
/*     */ 
/* 220 */     qwer = qwer.substring(0, qwer.length() - 3);
/*     */ 
/* 222 */     return qwer;
/*     */   }
/*     */ 
/*     */   public static String sha256(String plain) throws Exception {
/* 226 */     MessageDigest digest = MessageDigest.getInstance("SHA-256");
/* 227 */     byte[] hash = digest.digest(plain.getBytes(Configurator.getInstance().getConfig("encoding")));
/*     */ 
/* 230 */     return new String(Base64.encode(hash));
/*     */   }
/*     */ 
/*     */   public static String encSeed(String key, String plain)
/*     */     throws Exception
/*     */   {
/* 248 */     key = decode(key, 5);
/*     */ 
/* 250 */     int[] seedKey = SEED.getSeedRoundKey(key);
/* 251 */     String cipher = SEED.getSeedEncrypt(plain, seedKey);
/*     */ 
/* 253 */     return cipher;
/*     */   }
/*     */ 
/*     */   public static String decSeed(String key, String cipher) throws Exception {
/* 257 */     key = decode(key, 5);
/*     */ 
/* 259 */     int[] seedKey = SEED.getSeedRoundKey(key);
/* 260 */     String palin = SEED.getSeedDecrypt(cipher, seedKey);
/*     */ 
/* 262 */     return palin;
/*     */   }
/*     */ 
/*     */   public static void main(String[] s) throws Exception {
/* 266 */     String key = "VmxSS0BNRlV4YkZoVGJrNXFVbTFTVmxsVVRrTldWbXhYWVVaT2JHSkhlREZaYTFacllWWmFWV0pGVmxWV2JFcFVWbGQ0UzFOR1VsbGFSbFpPVmxaVk1WWlZXa1pQVmtKU1VGUXdQUT09";
/* 268 */     String url = "jdbc:tibero:thin:@140.5.1.60:8629:dwvrf";
/* 269 */     String uid = "wisebir4";
/* 270 */     String pwd = "wisebir41012";
/* 271 */     System.out.println("jdbc.repository.url=" + encSeed(key, url));
/* 272 */     System.out.println("jdbc.repository.username=" + encSeed(key, uid));
/* 273 */     System.out.println("jdbc.repository.password=" + encSeed(key, pwd));
			
/*     */   }
/*     */ }

/* Location:           C:\2022_Project\ds\WEB-INF\classes\com.jar
 * Qualified Name:     wise.common.secure.SecureUtils
 * JD-Core Version:    0.6.0
 */