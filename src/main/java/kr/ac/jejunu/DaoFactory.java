package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

//spring에서 bean을 등록하기 위해 필요한 어노테이션
@Configuration
@EnableJpaRepositories(basePackages = "kr.ac.jejunu", entityManagerFactoryRef = "entityManagerFactoryBean")
public class DaoFactory {

    //환경변수 셋팅
    @Value("${db.driver}")
    private String className;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String url;

//    className = "com.mysql.cj.jdbc.Driver";
//    username = "jeju";
//    password = "jejupw";
//    url = "jdbc:mysql://localhost/jeju?characterEncoding=utf-8&serverTimezone=UTC";

//    @Bean
//    public UserDao userDao() throws ClassNotFoundException {
//        return new UserDao(jdbcTemplate());
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate() throws ClassNotFoundException {
//        return new JdbcTemplate(dataSource());
//    }

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        //dependency에 mysql은 runtimeonly로 되어 있다
        //하지만, 자바 파일에서 mysql을 연결하기 위해서는 class.forname이 필요.
        //class.forname은 컴파일 시 mysql을 연결 할 수 있게 한다.
        SimpleDriverDataSource dataSource =
                new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("kr.ac.jejunu");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); //hibernate를 쓰겠다.
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect","or.hibernate.dialect.MySQLDialect"); //mysql을 쓰겠다.
        entityManagerFactoryBean.setJpaProperties(properties);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
