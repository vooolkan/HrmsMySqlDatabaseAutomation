package com.hrms.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/Database.feature",
		glue = "com.hrms.DbSteps",
		dryRun=false,
		plugin = {"pretty", "json:target/jSon/cucumber.json", "html:target/html/cucumber-dbTesting"},
		monochrome = true,
		tags = {"@dbTesting"}		
		)

public class DbRunner {

}
