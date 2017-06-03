import java.util.logging.Logger;
import org.junit.Test;

public class Tests
{
  Logger logger;

  public Tests()
  {
    logger = Logger.getLogger("bs");
  }

  @Test
  public void testAspect()
  {
    System.out.println("remember to start jvm with -javaagent:pathto/aspectjweaver.jar");
    System.out.println("this print will be aspected");
  }
}