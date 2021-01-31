package com.ilab.api.assessment.tests.testrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resource/features"},
        glue = {"stepdef"},
        plugin = {"pretty","html:target/cucumber.json"})
public class TestRunner {
}
