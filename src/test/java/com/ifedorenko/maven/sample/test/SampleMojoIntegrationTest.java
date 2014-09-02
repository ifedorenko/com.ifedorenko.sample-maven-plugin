package com.ifedorenko.maven.sample.test;

import io.takari.maven.testing.TestResources;
import io.takari.maven.testing.executor.MavenExecutionResult;
import io.takari.maven.testing.executor.MavenInstallations;
import io.takari.maven.testing.executor.MavenRuntime;
import io.takari.maven.testing.executor.MavenVersions;
import io.takari.maven.testing.executor.MavenRuntime.VerifierRuntimeBuilder;
import io.takari.maven.testing.executor.junit.MavenJUnitTestRunner;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MavenJUnitTestRunner.class)
@MavenInstallations({"target/maven-installation/apache-maven-3.2.2"})
@MavenVersions({"3.0.5", "3.2.3"})
public class SampleMojoIntegrationTest {

  @Rule
  public final TestResources resources = new TestResources();

  public final MavenRuntime verifier;

  public SampleMojoIntegrationTest(VerifierRuntimeBuilder builder) throws Exception {
    this.verifier = builder.withCliOptions("-X").build();
  }

  @Test
  public void testBasic() throws Exception {
    File basedir = resources.getBasedir("basic-it");

    MavenExecutionResult result = verifier.forProject(basedir).execute("compile");

    result.assertErrorFreeLog();
    TestResources.assertFilesPresent(basedir, "target/sample/sample.txt");
  }

}
