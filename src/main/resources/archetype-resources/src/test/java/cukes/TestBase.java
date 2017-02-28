#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cukes;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * TestBase class contains all of the common methods/variables used 
 * between integration and functional tests (for all feature files).
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestBase {

}