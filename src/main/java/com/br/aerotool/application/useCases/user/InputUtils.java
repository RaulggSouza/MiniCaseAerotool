package com.br.aerotool.application.useCases.user;

import java.util.Map;
import java.util.Map.Entry;

public class InputUtils {
    @SafeVarargs
    static void notBlank(Entry<String, String>... fields){
        for (Entry<String, String> entry : fields){
            String name =  entry.getKey();
            String value = entry.getValue();
            if (value == null || value.isBlank()){
                throw new IllegalArgumentException(name+" must not be null or blank");
            }
        }
    }
}
