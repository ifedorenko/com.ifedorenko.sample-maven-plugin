package com.ifedorenko.maven.sample.test;

import io.takari.maven.testing.TestResources;
import io.takari.maven.testing.it.VerifierResult;
import io.takari.maven.testing.it.VerifierRuntime;
import io.takari.maven.testing.it.VerifierRuntime.VerifierRuntimeBuilder;
import io.takari.maven.testing.it.junit.MavenInstallations;
import io.takari.maven.testing.it.junit.MavenTestRunner;
import io.takari.maven.testing.it.junit.MavenVersions;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MavenTestRunner.class)
@MavenInstallations({"target/maven-installation/apache-maven-3.2.2"})
@MavenVersions({"3.0.5", "3.2.3"})
public class SampleMojoIntegrationTest {

  @Rule
  public final TestResources resources = new TestResources();

  public final VerifierRuntime verifier;

  public SampleMojoIntegrationTest(VerifierRuntimeBuilder builder) throws Exception {
    this.verifier = builder.withCliOptions("-X").build();
  }

  @Test
  public void testBasic() throws Exception {
    File basedir = resources.getBasedir("basic-it");

    VerifierResult result = verifier.forProject(basedir).execute("compile");

    result.assertErrorFreeLog();
    TestResources.assertFilesPresent(basedir, "target/sample/sample.txt");
  }

}
