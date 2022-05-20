package com.restapi.scourt.utils;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class Configurator
/*     */ {
/*     */   private static Configurator instance;
/*     */   private String applicationContextConfigRealLocation;
/*  42 */   private Map<String, Object> configs = new HashMap();
/*     */ 
/*     */   public static Configurator getInstance()
/*     */   {
/*  48 */     if (instance == null) {
/*  49 */       instance = new Configurator();
/*     */     }
/*  51 */     return instance;
/*     */   }
/*     */ 
/*     */   public void load() throws Exception
/*     */   {
/*  56 */     Properties prop = new Properties();
/*  57 */     InputStream input = null;
/*     */     try
/*     */     {
/*  60 */       File propFile = new File(this.applicationContextConfigRealLocation);
/*     */ 
/*  62 */       if (!propFile.exists()) {
/*  63 */         propFile = new File(this.applicationContextConfigRealLocation);
/*     */       }
/*     */ 
/*  66 */       input = new FileInputStream(propFile);
/*     */ 
/*  68 */       prop.load(input);
/*     */     }
/*     */     catch (IOException ex) {
/*     */ 
/*  73 */       if (input != null)
/*     */         try {
/*  75 */           input.close();
/*     */         } catch (IOException e) {
/*  77 */           e.printStackTrace();
/*     */         }
/*     */     }
/*     */     finally
/*     */     {
/*  73 */       if (input != null) {
/*     */         try {
/*  75 */           input.close();
/*     */         } catch (IOException e) {
/*  77 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  82 */     Object[] keyset = prop.keySet().toArray();
/*  83 */     for (int x0 = 0; x0 < keyset.length; x0++)
/*     */     {
/*  85 */       this.configs.put((String)keyset[x0], prop.get(keyset[x0]));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/*  91 */     this.configs.clear();
/*     */   }
/*     */ 
/*     */   public Set<String> getConfigKeySet() {
/* 114 */     return this.configs.keySet();
/*     */   }
/*     */ 
/*     */   public Object getConfigObjectValue(String key) {
/* 118 */     return getConfigObjectValue(key, new Object());
/*     */   }
/*     */ 
/*     */   public Object getConfigObjectValue(String key, Object dephault)
/*     */   {
/*     */     Object value;
/* 122 */     if (!this.configs.containsKey(key)) {
/* 124 */       value = dephault;
/*     */     } else {
/* 126 */       value = this.configs.get(key);
/*     */     }
/* 128 */     return value;
/*     */   }
/*     */ 
/*     */   public String getConfig(String key) {
/* 132 */     return getConfig(key, "");
/*     */   }
/*     */ 
/*     */   public String getConfig(String key, String dephault)
/*     */   {
/*     */     String value;
/* 136 */     if (!this.configs.containsKey(key)) {
/* 138 */       value = dephault;
/*     */     } else {
/* 140 */       value = ((String)this.configs.get(key)).trim();
/*     */     }
/* 142 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getConfigBooleanValue(String key) {
/* 166 */     return getConfigBooleanValue(key, false);
/*     */   }
/*     */   public boolean getConfigBooleanValue(String key, boolean dephault) {
/* 169 */     Boolean boolValue = Boolean.valueOf(false);
/* 170 */     if (!this.configs.containsKey(key)) {
/* 172 */       boolValue = Boolean.valueOf(dephault);
/*     */     } else {
/* 174 */       String val = getConfig(key);
/* 175 */       boolValue = Boolean.valueOf(new Boolean(val).booleanValue());
/*     */     }
/* 177 */     return boolValue.booleanValue();
/*     */   }
/*     */ 
/*     */   public String getFullDomain(HttpServletRequest request) {
/* 196 */     String protocol = request.isSecure() ? "https://" : "http://";
/* 197 */     String domain = request.getServerName();
/* 198 */     int port = request.getServerPort();
/*     */ 
/* 200 */     String portStr = ":" + port;
/*     */ 
/* 202 */     return protocol + domain + portStr;
/*     */   }
/*     */ 
/*     */   public String getApplicationContextConfigRealLocation()
/*     */   {
/* 236 */     return this.applicationContextConfigRealLocation;
/*     */   }
/*     */ 
/*     */   public void setApplicationContextConfigRealLocation(String applicationContextConfigRealLocation) {
/* 240 */     this.applicationContextConfigRealLocation = applicationContextConfigRealLocation;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 244 */     return this.configs.toString();
/*     */   }
/*     */ }

/* Location:           C:\2022_Project\ds\WEB-INF\classes\com.jar
 * Qualified Name:     wise.context.config.Configurator
 * JD-Core Version:    0.6.0
 */