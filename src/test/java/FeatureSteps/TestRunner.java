package FeatureSteps;

import TestBase.TestSetup;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "file:src/test/resources/Feature",
        glue = {"FeatureSteps"},
        plugin = {"pretty", "json:target/cucumber.json", "junit:target/JUNITReports/report.xml", "html:target/htmlCucumberReports/cucumber-reports.html", "json:target/JSONReports/report.json"},
        monochrome = true
)
public class TestRunner extends TestSetup {
}
