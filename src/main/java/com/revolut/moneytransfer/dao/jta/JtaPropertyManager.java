package com.revolut.moneytransfer.dao.jta;

import com.arjuna.ats.jta.common.JTAEnvironmentBean;
import com.arjuna.ats.jta.common.jtaPropertyManager;
import com.arjuna.common.internal.util.propertyservice.BeanPopulator;
/**
 * Property manager wrapper for the JTA module.
 * @see jtaPropertyManager
 */
public class JtaPropertyManager
{
    public static JTAEnvironmentBean getJTAEnvironmentBean()
    {
        return BeanPopulator.getDefaultInstance(JTAEnvironmentBean.class);
    }
}