package com.kiyad.KiyadAutomationFramework;

import org.junit.runner.RunWith;
import io.cucumber.junit.*;


@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resource/featureFiles",
        tags=("@Smoke or @Regression"),
        //tags=("@Dev"),
        plugin= {"pretty", "html:target/cucumber.html"}

        )

public class TestRunner {

}
