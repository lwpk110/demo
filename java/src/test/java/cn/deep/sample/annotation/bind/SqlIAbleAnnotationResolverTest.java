package cn.deep.sample.annotation.bind;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration
@ActiveProfiles("test")
class SqlIAbleAnnotationResolverTest {

    @Autowired
    private TestBean testBean;

    @Test
    public void testSqlInjectionAnnotation_allCheckedFieldArrCorrect_shouldPass() {
        testBean.invokeWithSome("user1", "123456", "1234");
    }

    @Test
    void testSqlInjection_sqlInjectionAnnotationHasSomeCheckedField_shouldCheckSome() {
        Assertions.assertThrows(
                Exception.class,
                () -> testBean.invokeWithSome("u'_incorrect ", "123456 ", "test1")
        );
    }

    @Test
    void testSqlInjection_sqlInjectionAnnotationHasEmptyValidatedField_shouldCheckAll() {
        Assertions.assertThrows(
                Exception.class,
                () -> testBean.invokeWithAll("test_u", "exec ", "test")
        );
    }

    @Test
    void testSqlInjection_sqlInjectionAnnotationHasStringListField_shouldCheckList() {
        Assertions.assertThrows(
                Exception.class,
                () -> testBean.invokeWithCollection("test_u", Arrays.asList("p1","select "))
        );

        testBean.invokeWithCollection("test_u", Arrays.asList("p1","p2"));
    }
    @Test
    void testSqlInjection_sqlInjectionAnnotationHasDto_shouldCheckDto() {
        Assertions.assertThrows(
                Exception.class,
                () -> testBean.invokeWithCheckDto("test_u", new SqlIDto("u1", "select ",
                        Collections.emptyList()) )
        );

        testBean.invokeWithCheckDto("test_u", new SqlIDto("u1", "t1", Collections.emptyList()) );
    }
    @Configuration
    @EnableAspectJAutoProxy
    static class TestConfig {
        @Bean
        public SqlInjectionAnnotationResolver sqlInjectionAnnotationResolver() {
            return new SqlInjectionAnnotationResolver();
        }

        @Bean
        public TestBean testBean() {
            return new TestBean();
        }
    }

    static class TestBean {
        @SqlIAble(fields = {"userName", "password"})
        void invokeWithSome(String userName, String password, String scope) {
        }

        @SqlIAble
        void invokeWithAll(String userName, String password, String scope) {
        }

        @SqlIAble(fields = {"userName", "tabs"})
        void invokeWithCollection(String userName, List<String> tabs) {}

        @SqlIAble(fields =  {"dto"})
        void invokeWithCheckDto(String userName, SqlIDto dto){}
    }

    static class SqlIDto{
        private String user;
        @SqlIField
        private String table;
        @SqlIField
        private List<String> fields;

        public SqlIDto() {
        }

        public SqlIDto(String user, String table, List<String> fields) {
            this.user = user;
            this.table = table;
            this.fields = fields;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public List<String> getFields() {
            return fields;
        }

        public void setFields(List<String> fields) {
            this.fields = fields;
        }
    }

}