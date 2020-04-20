/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * [OVERVIEW] ReadProperties.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/20      (VNEXT) BaoPT       Create new
*/
public class ReadProperties {

    public static Properties readProperties(String source) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(source));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
