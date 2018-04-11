import java.util.Random;
import java.util.Scanner;

public class Labirinto
{
    /**
     * corridoio corrente
     */
    private Corridoio currentCorridoio;
    /**
     * identificativo del corridoio pre-uscita
     */
    private int uscitaNumero;

    /**
     * Costruttore del labirinto.
     * Crea un ingresso, sceglie un nodo random come pre-uscita, aggiunge l'uscita
     * @param NUMERO_CORRIDOI_MAX
     */
    public Labirinto(final int NUMERO_CORRIDOI_MAX)
    {
        currentCorridoio = new Ingresso(NUMERO_CORRIDOI_MAX);
        uscitaNumero = new Random().nextInt(Corridoio.getNumeroCorridoiTotali() - NUMERO_CORRIDOI_MAX)+NUMERO_CORRIDOI_MAX;
        currentCorridoio.aggiungiUscita(uscitaNumero);
    }

    /**
     * Metodo runnable che permette di giocare
     */
    public void entraEGioca()
    {
        Scanner scanner = new Scanner(System.in);
        while(!(currentCorridoio instanceof Uscita))
        {
            try {
                System.out.println(currentCorridoio.corridoiMenu());
                char k = scanner.nextLine().toLowerCase().charAt(0);
                if(k=='b' && !(currentCorridoio instanceof Ingresso))
                    currentCorridoio = currentCorridoio.getCorridoioPrecedente();
                else if (k!='b') currentCorridoio = currentCorridoio.nextCorridoio(Character.getNumericValue(k));
            }
            catch (IndexOutOfBoundsException e) {continue;}
        }
        System.out.println("Bravissimo! Sei uscito dal labirinto!");
    }

}
