#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cukes.functional;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * RunCukes class used for running functional (deployment) 
 * cucumber tests. 
 * 
 * Needs to be referenced directly from the
 * bamboo plan as the SureFire plugin will not pick this up
 * as a Junit test (because of naming structure - intended). 
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/",
        tags = {"@FunctionalTests"}
)
public class RunCukesFunctional {}