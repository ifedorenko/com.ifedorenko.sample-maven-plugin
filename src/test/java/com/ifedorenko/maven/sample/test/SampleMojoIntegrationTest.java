package com.ifedorenko.maven.sample.test;

import io.takari.maven.testing.TestResources;
import io.takari.maven.testing.it.VerifierResult;

import java.io.File;

import org.junit.Test;

public class SampleMojoIntegrationTest extends AbstractMojoIntegrationTest {

  public SampleMojoIntegrationTest(String version) throws Exception {
    super(version);
  }

  @Test
  public void testBasic() throws Exception {
    File basedir = resources.getBasedir("basic-it");

    VerifierResult result = verifier.forProject(basedir).execute("compile");

    result.assertErrorFreeLog();
    TestResources.assertFilesPresent(basedir, "target/sample/sample.txt");
  }

}
