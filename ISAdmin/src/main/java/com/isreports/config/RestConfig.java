package com.isreports.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class RestConfig {

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(getMessageConverters());
		return restTemplate;
	}

	@Bean
	public List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(getMappingJacksonHttpMessageConverter());
		return messageConverters;
	}

	@Bean
	public static MappingJackson2HttpMessageConverter getMappingJacksonHttpMessageConverter() {
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();

		MediaType jsonMediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
		MediaType htmlMediaType = new MediaType("text", "html", Charset.forName("UTF-8"));

		supportedMediaTypes.add(jsonMediaType);
		supportedMediaTypes.add(htmlMediaType);

		MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		jacksonConverter.setSupportedMediaTypes(supportedMediaTypes);
		return jacksonConverter;

	}
}
