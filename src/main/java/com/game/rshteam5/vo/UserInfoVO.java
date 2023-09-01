package com.game.rshteam5.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoVO {
	private int uiNum;
	private String uiName;
	private String uiId;
	private String uiPwd;
	private String token;
}
