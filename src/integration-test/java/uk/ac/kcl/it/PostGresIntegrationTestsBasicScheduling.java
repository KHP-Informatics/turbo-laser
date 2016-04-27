/* 
 * Copyright 2016 King's College London, Richard Jackson <richgjackson@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.kcl.it;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import uk.ac.kcl.batch.*;

import static junit.framework.TestCase.assertNotNull;

/**
 *
 * @author rich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("uk.ac.kcl.it")
@TestPropertySource({
        "classpath:postgres_test_config_basic.properties",
        "classpath:jms.properties",
        "classpath:concurrency.properties",
        "classpath:postgres_db.properties",
        "classpath:elasticsearch.properties",
        "classpath:jobAndStep.properties"})
@ContextConfiguration(classes = {
        JobConfiguration.class,
        BatchConfigurer.class,
        GateConfiguration.class,
        DbLineFixerConfiguration.class,
        PostGresTestUtils.class,
        ScheduledJobConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
public class PostGresIntegrationTestsBasicScheduling {

    final static Logger logger = Logger.getLogger(PostGresIntegrationTestsBasicScheduling.class);

    @Autowired
    PostGresTestUtils utils;
    @Test
    public void postgresGatePipelineTest() {
        utils.createBasicInputTable();
        utils.createBasicOutputTable();
        utils.insertDataIntoBasicTable();
        utils.initPostGresJobRepository();
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
