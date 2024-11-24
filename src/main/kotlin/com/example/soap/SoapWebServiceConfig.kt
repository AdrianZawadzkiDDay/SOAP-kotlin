package com.example.soap

import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.ws.config.annotation.EnableWs
import org.springframework.ws.transport.http.MessageDispatcherServlet
import org.springframework.xml.xsd.SimpleXsdSchema
import org.springframework.xml.xsd.XsdSchema
import org.springframework.context.ApplicationContext
import org.springframework.core.io.ClassPathResource

@EnableWs
@Configuration
class SoapWebServiceConfig(private val applicationContext: ApplicationContext) {

    @Bean
    fun messageDispatcherServlet(): ServletRegistrationBean<MessageDispatcherServlet> {
        val servlet = MessageDispatcherServlet()
        servlet.setApplicationContext(applicationContext)
        servlet.isTransformWsdlLocations = true
        return ServletRegistrationBean(servlet, "/ws/*")
    }

    @Bean
    fun schema(): XsdSchema {
        return SimpleXsdSchema(ClassPathResource("wsdl/SimpleService.xsd"))
    }

}