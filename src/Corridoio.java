import java.util.ArrayList;
import java.util.Random;

public class Corridoio
{

    /**
     * Link al corridoio precedente
     */
    protected Corridoio corridoioPrecedente;
    /**
     * numero identificativo di ogni corridoio
     */
    protected int numeroCorridoio;
    /**
     * lista contenente gli accessi/uscite accessibili da un corridoio
     */
    protected ArrayList<Corridoio> accessi;
    /**
     * numero corridoi totali, viene utilizzato come "bound" nella generazione
     * casuale dell'uscita del labirinto
     */
    protected static int numeroCorridoiTotali;

    /**
     * Costruttore che inizializza il corridoio:
     * <ul>
     *  <li>Linkando il corridoio precedente</li>
     *  <li>Inizializzando una lista vuota di accessi</li>
     *  <li>Assegnando un numero identificativo al corridoio</li>
     *  <li>Incrementando il campo statico che tiene conto dei corridoi totali</li>
     * </ul>
     *
     * @param corridoioPrecedente   link al corridoio precedente
     */
    public Corridoio(Corridoio corridoioPrecedente)
    {
        this.corridoioPrecedente = corridoioPrecedente;
        this.accessi = new ArrayList<>();
        numeroCorridoio = numeroCorridoiTotali++;
    }

    /**
     * costruttore che richiama il precedente, ma tiene conto del numero massimo
     * di corridoi accessibili da generare all'interno di un corridoio.
     * Viene richiamata la funzione riempiAccessi per generare i corridoi in modo "ricorsivo"
     * @param corridoioPrecedente   link al corridoio precedente
     * @param NUMERO_CORRIDOI_MAX   numero massimo di accessi in un corridoio
     */
    public Corridoio(Corridoio corridoioPrecedente, final int NUMERO_CORRIDOI_MAX)
    {
        this(corridoioPrecedente);
        riempiAccessi(NUMERO_CORRIDOI_MAX);
    }


    //getter

    /**
     * @return corridoioPrecedente
     */
    public Corridoio getCorridoioPrecedente() {return corridoioPrecedente; }

    /**
     *
     * @return numeroCorridoio
     */
    public int getNumeroCorridoio() {return numeroCorridoio; }

    /**
     *
     * @return lista di accessi
     */
    public ArrayList<Corridoio> getAccessi() {return accessi; }

    /**
     *
     * @return numeroCorridoiTotali
     */
    public static int getNumeroCorridoiTotali() {return numeroCorridoiTotali; }

    /**
     * Genera in modo ricorsivo "l'albero" che dà struttura
     * al labirinto.
     * @param n numero di accessi massimi da un corridoio
     */
    protected void riempiAccessi(int n)
    {
        for(int k=1; k<=new Random().nextInt(n); k++)
            accessi.add(new Corridoio(this, n));
    }


    /**
     * Ritorna una stringa di stampa della lista di corridoi accessibili.
     * @return  Menu dei corridoi
     */
    public String corridoiMenu()
    {
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for(Corridoio c: accessi)
            sb.append(k++).append(") ").append(c).append('\n');
        sb.append("b) torna indietro \n");
        return sb.toString();
    }

    /**
     * Passa al k-esimo corridoio della lista accessi
     * @param k
     * @return  corridoio successivo
     */
    public Corridoio nextCorridoio(int k) {return accessi.get(k); }

    /**
     * Aggiunge un uscita al labirinto. Viene chiamato solo da un oggetto
     * di tipo Ingresso in modo public e da questo, in modo ricorsivo viene
     * richiamato dagli altri Corridoi
     * @param uscitaNumero  numero che identifica il nodo pre-uscita
     */
    protected void aggiungiUscita(int uscitaNumero)
    {
        if(this.getNumeroCorridoio() == uscitaNumero)
        {
            this.getAccessi().add(new Uscita(this));
            return;
        }
        for(Corridoio c: this.getAccessi())
            c.aggiungiUscita(uscitaNumero);
    }

    /**
     * metodo che determina se il corridoio e' un'uscita
     */
    public boolean isUscita() {return this instanceof Uscita;}
}
