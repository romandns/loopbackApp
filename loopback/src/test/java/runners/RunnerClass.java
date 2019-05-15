package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features= "src/main/resources/feature/",
        format={"json:target/cucumber.json","html:target/site/cucumber-pretty"},
        glue = "api.steps")

public class RunnerClass {
    public static void main(String[] args) {

    }
}
