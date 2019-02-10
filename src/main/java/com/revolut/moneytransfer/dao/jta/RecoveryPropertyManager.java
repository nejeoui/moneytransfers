package com.revolut.moneytransfer.dao.jta;

import com.arjuna.ats.arjuna.common.RecoveryEnvironmentBean;
import com.arjuna.ats.arjuna.common.recoveryPropertyManager;
import com.arjuna.common.internal.util.propertyservice.BeanPopulator;
/**
* Property manager wrapper for the recovery system.
* @see recoveryPropertyManager
*/
public class RecoveryPropertyManager
{
   public static RecoveryEnvironmentBean getRecoveryEnvironmentBean()
   {
       return BeanPopulator.getDefaultInstance(RecoveryEnvironmentBean.class);
   }
}
