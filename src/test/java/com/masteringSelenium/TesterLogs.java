package com.masteringSelenium;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.LoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TesterLogs {
	protected Logger Log = LogManager.getLogger(TesterLogs.class.getName());
	@BeforeMethod(alwaysRun = true)
	public void driversetup(Method method) {
		config1(method);

	}
	
	public void config2(Method method) {
		final ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
	    builder.setStatusLevel(Level.ERROR);
	    builder.setConfigurationName("BuilderTest");
	    builder.add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
	            .addAttribute("level", Level.DEBUG));
	   
	    final AppenderComponentBuilder consoleAppender = builder.newAppender("Stdout", "CONSOLE").addAttribute("target",
	            ConsoleAppender.Target.SYSTEM_OUT);
	    consoleAppender.add(builder.newLayout("PatternLayout").
	            addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
//	    consoleAppender.add(builder.newFilter("MarkerFilter", Filter.Result.DENY,
//	            Filter.Result.NEUTRAL).addAttribute("marker", "FLOW"));
	    builder.add(consoleAppender);
	  
	    
	    final AppenderComponentBuilder fileAppender = builder.newAppender("trace-log", "RollingFile")
	    		.addAttribute("fileName", "logs/"+method.getName()+".log")
		.addAttribute("filePattern", "logs/"+method.getName()+"rolling-%d{MM-dd-yy}.log")
	    		 .addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable")
		.addAttribute("append", false);
	            
//	    fileAppender.add(builder.newLayout("PatternLayout").
//	            addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
	    
	    builder.add(fileAppender);
	    
//	    builder.add(builder.newLogger("trace.log", Level.DEBUG).
//	            add(builder.newAppenderRef("Stdout")).
//	            add(builder.newAppenderRef("trace-log")).
//	            addAttribute("additivity", false));
	    
	    builder.add(builder.newLogger("org.apache.logging.log4j", Level.DEBUG).
	            add(builder.newAppenderRef("Stdout")).
	            add(builder.newAppenderRef("trace-log")).
	            addAttribute("additivity", false));
	    
	    builder.add(builder.newRootLogger(Level.ERROR).add(builder.newAppenderRef("Stdout")));
	    
	    try {
			builder.writeXmlConfiguration(System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Configurator.initialize(builder.build());
	}
	
	public void config1(Method method) {
		ConfigurationBuilder<BuiltConfiguration> builder
		= ConfigurationBuilderFactory.newConfigurationBuilder();

		AppenderComponentBuilder console 
		= builder.newAppender("Stdout", "CONSOLE").addAttribute("target",
	            ConsoleAppender.Target.SYSTEM_OUT);
		
		
	    console.add(builder.newLayout("PatternLayout").
	            addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
		
		builder.add(console);

		ComponentBuilder triggeringPolicies = builder.newComponent("Policies")
				.addComponent(builder.newComponent("TimeBasedTriggeringPolicy")
						.addAttribute("interval", "1")
						.addAttribute("modulate", true));


		AppenderComponentBuilder rollingFile 
		= builder.newAppender("trace-log", "RollingFile");
		rollingFile.addAttribute("fileName", "logs/"+method.getName()+".log");
		rollingFile.addAttribute("filePattern", "logs/"+method.getName()+"rolling-%d{MM-dd-yy}.log");
		rollingFile.addAttribute("append", false);
		rollingFile.addComponent(triggeringPolicies);
		rollingFile.add(builder.newLayout("PatternLayout").
	            addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
		
		builder.add(rollingFile);

		LayoutComponentBuilder standard 
		= builder.newLayout("PatternLayout");
		standard.addAttribute("pattern", "[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n");

		console.add(standard);
		//		file.add(standard);
		rollingFile.add(standard);

		RootLoggerComponentBuilder rootLogger 
		= builder.newRootLogger(Level.INFO);
		rootLogger.add(builder.newAppenderRef("Stdout"));
		rootLogger.addAttribute("additivity", false);
		builder.add(rootLogger);

		LoggerComponentBuilder logger = builder.newLogger("com.masteringSelenium", Level.DEBUG);
		logger.add(builder.newAppenderRef("trace-log"));
		logger.add(builder.newAppenderRef("Stdout"));
		logger.addAttribute("additivity", false);
		logger.addAttribute("append", false);
		builder.add(logger);


		rollingFile.addComponent(triggeringPolicies);

		try {
			builder.writeXmlConfiguration(System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Configurator.initialize(builder.build());
	}

//	@Test
	public void test112() throws IOException {
	LogManager.getLogger().info("**************WOWing BY");

	}

}
