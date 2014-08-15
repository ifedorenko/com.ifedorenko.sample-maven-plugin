package com.ifedorenko.maven.sample.test;

import io.takari.maven.testing.it.MavenVersions;
import io.takari.maven.testing.it.VerifierRuntime;

import org.apache.maven.plugin.testing.resources.TestResources;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public abstract class AbstractMojoIntegrationTest {

  @Rule
  public final TestResources resources;

  public final VerifierRuntime verifier;

  protected AbstractMojoIntegrationTest(String mavenVersion) throws Exception {
    this.resources = new TestResources("src/test/projects", "target/it/" + mavenVersion + "/");
    this.verifier = VerifierRuntime.builder(mavenVersion).withCliOptions("-X").build();
  }

  @Parameters(name = "maven-{0}")
  public static Iterable<Object[]> mavenVersions() {
    return MavenVersions.asJunitParameters("3.0.5", "3.2.1", "3.2.2");
  }

}
