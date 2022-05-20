package com.restapi.scourt.utils;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public final class CoreUtils
/*    */ {
/*    */ 
/*    */   public static String lpad(String str, int len, String addStr) {
/* 40 */     String result = str;
/* 41 */     int templen = len - result.length();
/*    */ 
/* 43 */     for (int i = 0; i < templen; i++) {
/* 44 */       result = addStr + result;
/*    */     }
/*    */ 
/* 47 */     return result;
/*    */   }
/*    */ 
/*    */   public static String rpad(String str, int len, String addStr) {
/* 51 */     String result = str;
/* 52 */     int templen = len - result.length();
/*    */ 
/* 54 */     for (int i = 0; i < templen; i++) {
/* 55 */       result = result + addStr;
/*    */     }
/*    */ 
/* 58 */     return result;
/*    */   }
/*    */ 
/*    */   public static boolean isRequestMethod(HttpServletRequest request, String method) {
/* 62 */     String m = request.getMethod();
/* 63 */     return m.equalsIgnoreCase(method);
/*    */   }
/*    */ 
/*    */   public static String getRandom(int round)
/*    */   {
/* 68 */     Random random = new Random();
/* 69 */     StringBuffer buff = new StringBuffer();
/*    */ 
/* 71 */     for (int i = 0; i < round; i++) {
/* 72 */       int a1 = Math.abs(random.nextInt() % 10);
/* 73 */       buff.append(a1);
/*    */     }
/*    */ 
/* 76 */     return buff.toString();
/*    */   }
/*    */ 
/*    */   public static boolean isAjaxRequest(HttpServletRequest request) {
/* 80 */     String ajaxHeader = request.getHeader("x-requested-with");
/* 81 */     return "XMLHttpRequest".equals(ajaxHeader);
/*    */   }
/*    */ }

/* Location:           C:\2022_Project\ds\WEB-INF\classes\com.jar
 * Qualified Name:     wise.common.util.CoreUtils
 * JD-Core Version:    0.6.0
 */