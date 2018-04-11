public class Ingresso extends Corridoio
{
    /**
     * Costruttore dell'ingresso, richiama il supercostruttore con un solo parametro
     * @param NUMERO_CORRIDOI_MAX   numero massimo di corridoi accessibili.
     */
    public Ingresso(final int NUMERO_CORRIDOI_MAX)
    {
        super(null);
        riempiAccessi(NUMERO_CORRIDOI_MAX);
    }

    /**
     * Override del metodo riempiAccessi, poichè l'ingresso deve
     * avere almeno N corridoi
     * @param n numero di accessi massimi da un corridoio
     */
    @Override
    protected void riempiAccessi(int n)
    {
        for(int i=0; i<n; i++)
            accessi.add(new Corridoio(this, n));
    }

    /**
     * richiama il metodo della superclasse ma cambio l'accesso in public.
     * @param uscitaNumero  numero che identifica il nodo pre-uscita
     */
    @Override
    public void aggiungiUscita(int uscitaNumero)
    {
        super.aggiungiUscita(uscitaNumero);
    }

}
