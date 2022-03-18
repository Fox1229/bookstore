package com.atsk.util;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;

/**
 * @author Ly
 * @date 2021-07-08 10:46
 */
public class WebUtil {

    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt, int defaultValue) {
        try {
            String trim = strInt.trim();
            return Integer.parseInt(trim);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }


    public static double parseDouble(String strDouble, double defaultValue) {
        try {
            String trim = strDouble.trim();
            return Double.parseDouble(trim);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
