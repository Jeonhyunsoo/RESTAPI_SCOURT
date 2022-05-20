package com.restapi.scourt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // getter �ڵ� ����
@Setter // setter �ڵ� ����
@NoArgsConstructor // default constructor ����
@ToString
public class OlapVO {
	private String url;		// jdbc url
	private String uid;		// jdbc uid
	private String pwd;	// jdbc passwd 
}
