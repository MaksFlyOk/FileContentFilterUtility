import org.junit.jupiter.api.Test;
import ru.shift.utility.core.LineClassifier;
import ru.shift.utility.core.LineType;

import static org.junit.jupiter.api.Assertions.*;

public class LineClassifierTest {

  @Test
  public void testGetTypeInteger() {
    assertEquals(LineType.INTEGER, LineClassifier.getType("123"));
    assertEquals(LineType.INTEGER, LineClassifier.getType("-123"));
  }

  @Test
  public void testGetTypeFloat() {
    assertEquals(LineType.FLOAT, LineClassifier.getType("3.14"));
    assertEquals(LineType.FLOAT, LineClassifier.getType("-0.001"));
    assertEquals(LineType.FLOAT, LineClassifier.getType("1.3232E25"));
  }

  @Test
  public void testGetTypeString() {
    assertEquals(LineType.STRING, LineClassifier.getType("Lorem ipsum"));
    assertEquals(LineType.STRING, LineClassifier.getType("Пример"));
  }
}
