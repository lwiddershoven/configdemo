package nl.leonw.demo.configdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

// I do not use @SpringBootTest because I need a separate configuration for each individual test.

class AValueAnnotationTest {


    @Test
    void undefined_properties_of_primitive_types_fail_on_unboxing() {
        try {
            createApplicationContextRunner()
                    .withConfiguration(AutoConfigurations.of(AValueAnnotationConfiguration.class))
                    .run(context -> assertNotNull(context.getBean(AValueAnnotation.class)));
            fail("Loading should have thrown an exception");
        } catch (IllegalStateException e) {
            assertTrue(e.getCause().getMessage().contains("For input string: \"${a.intValue}\""));
        }
    }

    @Test
    void defined_properties_are_converted_to_the_correct_type() {
        createApplicationContextRunner()
                .withPropertyValues("a.intValue=10")
                .withConfiguration(AutoConfigurations.of(AValueAnnotationConfiguration.class))
                .run(context -> assertEquals(10, context.getBean(AValueAnnotation.class).getIntValue()));
    }

    @Test
    void undefined_reference_type_properties_are_null() {
        createApplicationContextRunner()
//                .withPropertyValues("a.value=10")
                .withPropertyValues("a.intValue=10") // to prevent auto unboxing to break it
                .withConfiguration(AutoConfigurations.of(AValueAnnotationConfiguration.class))
                .run(context -> System.out.println(context.getBean(AValueAnnotation.class).getValue()));
    }




    private ApplicationContextRunner createApplicationContextRunner() {
        return new ApplicationContextRunner()
                .withSystemProperties("spring.profiles.active=a");
    }


    @Configuration
    @Import(AValueAnnotation.class)
    static class AValueAnnotationConfiguration {

    }
}