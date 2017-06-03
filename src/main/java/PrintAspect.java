import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PrintAspect
{
  @Before(value = "this(Tests)")
  public void forTests(JoinPoint jp)
  {
    Logger.getLogger("bs").info("aspected call from Tests: " + jp.getSignature());
  }

  @Before(value = "call(* load*(..))")
  public void forLoad(JoinPoint jp)
  {
    Logger.getLogger("bs").info("aspected load: " + jp.getSignature());
    Stream<StackTraceElement> stackTraceStream = Arrays.stream(Thread.currentThread().getStackTrace());
    stackTraceStream.forEach(frame -> {
      System.out.println("stack: " + frame);
    });
  }
  
  @Before("call(* print*(..)) && !cflow(this(PrintAspect))")
  /* cflow is a way to protect against recursion */
  public void print(JoinPoint jp)
  {
    System.out.print("before print: " + jp.getSignature());
    if (jp.getThis() != null)
      System.out.print(", this: " + jp.getThis().getClass());
    System.out.println();
  }
}
