package mastermindf;

/**
 * Cette classe permet d'afficher des messages de d�bogage sur la bonne sortie
 *
 * @author Jordan
 */
public class Logger
{

    public static void write(int minLogLevelNeeded, int currentLogLevel, String msg)
      {
        if (currentLogLevel >= minLogLevelNeeded) {
            System.out.println("**LOG** : " + msg);
        }
      }
}
