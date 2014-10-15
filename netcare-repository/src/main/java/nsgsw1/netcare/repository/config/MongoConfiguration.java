package nsgsw1.netcare.repository.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.WriteConcern;

@Configuration
@EnableAspectJAutoProxy
@EnableMongoRepositories(basePackages = "nsgsw1.netcare.repository")
@PropertySource({ "classpath:/META-INF/spring/persistence-mongodb.properties" })
public class MongoConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public MongoFactoryBean mongo() {
		MongoFactoryBean mongo = new MongoFactoryBean();
		mongo.setHost(env.getProperty("db.host"));
		mongo.setPort(env.getProperty("db.port", Integer.class, 27017));
		mongo.setWriteConcern(WriteConcern.SAFE);
		return mongo;
//		final Mongo mongoStrict = new Mongo(new MongoURI(
//				"mongodb://127.0.0.1:27018,127.0.0.1:27019,127.0.0.1:27020"));
//		mongoStrict.setWriteConcern(WriteConcern.REPLICAS_SAFE);
//		return new SimpleMongoDbFactory(mongoStrict, "mydb");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongo().getObject(),
				env.getProperty("db.name"));
		return mongoTemplate;
	}
	
	@Bean
	public RespositoryInterceptor interceptor() {
		return new RespositoryInterceptor();
	}
}
