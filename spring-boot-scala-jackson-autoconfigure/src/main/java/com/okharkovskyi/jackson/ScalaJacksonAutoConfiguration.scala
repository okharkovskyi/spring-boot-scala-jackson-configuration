package com.okharkovskyi.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.boot.autoconfigure.condition.{ConditionalOnMissingBean, ConditionalOnProperty}
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@Configuration
@ConditionalOnProperty(name = Array("scala.jackson.serialization.enabled"), havingValue = "true", matchIfMissing = true)
class ScalaJacksonAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  def defaultScalaModule: DefaultScalaModule = new DefaultScalaModule

  @Bean
  @ConditionalOnMissingBean
  def mappingJackson2HttpMessageConverter(objectMapper: ObjectMapper, defaultScalaModule: DefaultScalaModule) = {
    objectMapper.registerModule(defaultScalaModule)
    new MappingJackson2HttpMessageConverter(objectMapper)
  }
}