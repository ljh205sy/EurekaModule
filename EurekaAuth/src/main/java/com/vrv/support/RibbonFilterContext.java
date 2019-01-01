package com.vrv.support;

import java.util.Map;

/**
 * @author wh1107066
 *
 */
public interface RibbonFilterContext {
    RibbonFilterContext add(String key, String value);

    String get(String key);

    RibbonFilterContext remove(String key);

    Map<String, String> getAttributes();
}
