package com.restapi.scourt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // getter 切疑 持失
@Setter // setter 切疑 持失
@NoArgsConstructor // default constructor 持失
@ToString
public class OlapVO {
	private String url;		// jdbc url
	private String uid;		// jdbc uid
	private String pwd;	// jdbc passwd 
}
