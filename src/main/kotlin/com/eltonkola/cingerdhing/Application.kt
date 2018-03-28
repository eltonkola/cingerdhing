package com.eltonkola.cingerdhing

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration(exclude = arrayOf(RepositoryRestMvcAutoConfiguration::class))
//@EnableWebMvc
//@EnableJpaRepositories
//@EnableAutoConfiguration
//@ComponentScan(basePackages = arrayOf("com.eltonkola.cingerdhing"))
class Application{

//
//fun main(args: Array<String>) {
//    runApplication<Application>(*args)
//}

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }

}
