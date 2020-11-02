package nl.leonw.demo.configdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("a") // I don't want this component if I just run the app because the properties are not configured
@Component // Let Spring instantiate and thus autowire it
public class AValueAnnotation {

    @Value("${a.value}")
    private String value;

    @Value("${a.defaultValue:defaultValue}")
    private String defaultValue;

    @Value("${a.intValue}")
    private int intValue;


    // Generated getters and setters

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }
}
