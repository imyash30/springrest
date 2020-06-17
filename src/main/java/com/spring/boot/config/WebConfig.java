package com.spring.boot.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.boot.config.AppProperties;
import com.spring.boot.config.JpaConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spring.boot.*")
@Import({JpaConfig.class, AppProperties.class})
public class WebConfig implements WebMvcConfigurer{

	@Autowired	private AppProperties props;
	
	@Bean(name="dataSource")
    public DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(props.getProperty("database.url"));
        dataSource.setDriverClassName(props.getProperty("database.driverclassname"));
        dataSource.setUsername(props.getProperty("database.username"));
        dataSource.setPassword(props.getProperty("database.password"));
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setMaxActive(500);
        dataSource.setMaxIdle(100);
        return dataSource;
    }
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", props.getProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", props.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", props.getProperty("hibernate.dialect"));
		return properties;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.spring.boot.entity" });
		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(getHibernateProperties());
		return em;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		/*final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Converter<String, Date> strToDt = new Converter<String, Date>() {
			@Override
			public Date convert(MappingContext<String, Date> context) {
				if(context.getSource() == null)
					return null;

				Date date = null;
				try {
					date = sdf.parse(context.getSource());
				} catch (ParseException e) { }
				return date;
			}
		};

		Converter<Date, String> dtToStr = new Converter<Date, String>() {
			@Override
			public String convert(MappingContext<Date, String> context) {
				if(context.getSource() == null)
					return null;

				return sdf.format(context.getSource());
			}
		};

		PropertyMap<VolunteerDto, Volunteer> volunteerMap = new PropertyMap<VolunteerDto, Volunteer>() {
			@Override
			protected void configure() {
				map().setTokenId(source.getTokenId());
			}
		};
		
		modelMapper.addConverter(strToDt);
		modelMapper.addConverter(dtToStr);*/
//		modelMapper.addMappings(volunteerMap);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
