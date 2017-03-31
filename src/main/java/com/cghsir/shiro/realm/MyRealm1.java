package com.cghsir.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * 自定义 shiro安全数据源
 * @author Administrator
 *
 */
public class MyRealm1 implements Realm{

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String)token.getPrincipal();//得到用户名
		String password = new String((char[])token.getCredentials());//得到密码
		if(!"zhang".equals(username)){
			throw new UnknownAccountException();//用户名错误
		}
		if(!"1234".equals(password)){
			throw new IncorrectCredentialsException();//密码错误
		}
		//如果身份认证成功，返回一个AuthenticationInfo实现
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	/**
	 * 返回一个唯一的Realm名字
	 */
	public String getName() {
		return "myrealm1";
	}

	/**
	 * 判断此Realm是否支持此Token
	 */
	public boolean supports(AuthenticationToken token) {
		//仅支持UsernamePasswordToken类型的token
		return token instanceof UsernamePasswordToken;
	}

}
