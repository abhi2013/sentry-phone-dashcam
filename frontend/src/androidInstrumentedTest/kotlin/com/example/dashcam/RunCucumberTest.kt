package com.example.dashcam

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["features"],
    glue = ["com.example.dashcam.steps"],
    plugin = ["pretty"]
)
class RunCucumberTest
