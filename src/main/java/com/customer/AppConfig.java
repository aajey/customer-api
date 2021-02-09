package com.customer;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.HttpSolrClientFactoryBean;


@Configuration
@EnableSolrRepositories(schemaCreationSupport = true)
public class AppConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	 /*@Bean
    public SolrClient solrClient() {
		 HttpSolrClientFactoryBean factory = new HttpSolrClientFactoryBean();
		 factory.setUrl(env.getProperty("solr.host"));
		 return factory.getSolrClient();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
	*/
}
