# Property Management in Spring Boot

**Leon Widdershoven**

## About Me

## This talk

- Using properties
- Property sources: defining values
- Adding a custom property source
- Debugging properties

## Using properties

- @Value
- @ConfigurationProperties
    - Lombok
- @ConditionalOnProperty
- Special property: @Profile


## What to use

- prefer @ConfigurationProperties to @Value
    - Eases testing
    - easier property reuse
    - more readable as related props are together
    - Goes great with constructor injection!
    - which is great with Lombok @AllArgsConstructor
- @Configuration instead of @Component/@Service/@Repository
  when you need those conditional on a property or profile.
  Common for "drivers" which have connection pools.
- Lombok instead of "generate getters and setters"
  as that adds code without value, distracting from your unique contribution    
    
## Property Sources

- hardcoded
- Computed, based on Environment
- Default property file
- Property file based on profile
- EnvironmentPostProcessors / BeanFactoryPostProcessor based sources
  (e.g. spring boot modules for aws, gcp, azure, ...., database, config server)
  
- Format properties or yaml; allows placeholders
- actuator env endpoint