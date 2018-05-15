#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cukes.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import ${package}.Application;
import ${package}.cukes.TestBase;

/**
 * TestBase class used specifically for integration (build) tests. 
 * Contains all common methods/variables between the feature files for the 
 * integration phase only.
 */
@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class ITestBase extends TestBase {

}