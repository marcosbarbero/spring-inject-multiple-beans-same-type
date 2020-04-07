package com.marcosbarbero;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    static class Runner implements CommandLineRunner {

    	private final Map<String, Fruit> fruits;
		private final Fruit banana;
		private final Fruit strawberry;

		public Runner(Map<String, Fruit> fruits,
					  @Qualifier("banana") Fruit banana,
					  @Qualifier("strawberry") Fruit strawberry) {
			this.fruits = fruits;
			this.banana = banana;
			this.strawberry = strawberry;
		}

		@Override
		public void run(String... args) {
			fruits.keySet().forEach(System.out::println);
			System.out.println(banana);
			System.out.println(strawberry);
		}
	}

    @Configuration
    static class MultipleBeanDeclaration {

        @Bean
		Fruit banana() {
        	return new Fruit("banana");
		}

		@Bean
		Fruit strawberry() {
        	return new Fruit("strawberry");
		}
    }

    static class Fruit {
		private final String name;

        public Fruit(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

		@Override
		public String toString() {
			return "Fruit{" +
					"name='" + name + '\'' +
					'}';
		}
	}


}
