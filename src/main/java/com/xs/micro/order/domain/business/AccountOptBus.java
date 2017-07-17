/**
 * 
 */
package com.xs.micro.order.domain.business;

import com.xs.micro.order.domain.em.Sex;
import com.xs.micro.order.domain.pojo.model.TbAccountOne;

/**
 * 账户操作中心
 * @author zhangli
 * @date 2017年6月28日
 */
public interface AccountOptBus {
	/**
	 * 注册账户，注册成功后调用通知服务
	 * @param acctId 账号
	 * @param sex 性别
	 * @return
	 * @author zhangli 2017年6月28日
	 */
	TbAccountOne registAcctount(String acctId, Sex sex);
}
