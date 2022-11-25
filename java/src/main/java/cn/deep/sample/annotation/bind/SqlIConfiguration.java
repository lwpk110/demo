package cn.deep.sample.annotation.bind;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class SqlIConfiguration {
    @Bean
    public SqlInjectionAnnotationResolver sqlInjectionAnnotationResolver(){
        return new SqlInjectionAnnotationResolver();
    }
}
