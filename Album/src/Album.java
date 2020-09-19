import java.util.ArrayList;
import java.util.Random;

public class Album {
    final private int totalFigurinhas;
    final private int quantFigurinhasPorPacotinho;
    private int totalFigurinhasRecebidos;
    private int FigurinhasRepetidas;
    private Figurinha[] album;
    private int[] albumrepetidos;


    public static final float PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR = 0.9f;  // 90%


    public Album(int totalFigurinhas, int quantFigurinhasPorPacotinho) {
        this.totalFigurinhas = totalFigurinhas;
        this.quantFigurinhasPorPacotinho = quantFigurinhasPorPacotinho;
        this.album = new Figurinha[totalFigurinhas + 1];
        this.albumrepetidos = new int[totalFigurinhas + 1];
        for (int i = 1; i < totalFigurinhas; i++) {
            album[i] = null;
        }
    }

    public void receberNovoPacotinho(Figurinha[] pacotinho) {
        for (Figurinha figurinha : pacotinho) {
            if (album[figurinha.getPosicao()] == null) {
                album[figurinha.getPosicao()] = figurinha;
            } else {
                this.FigurinhasRepetidas++;
                albumrepetidos[figurinha.getPosicao()] += 1;

            }
            this.totalFigurinhasRecebidos++;
        }
    }


    public int getTotalPacotinhosRecebidos() {
        return this.totalFigurinhasRecebidos/quantFigurinhasPorPacotinho;
    }

    /**
     * Termina de preencher o álbum, desde que ele já esteja preenchido além de certa fração
     * mínima definida em Album.PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR.
     *
     * Se o álbum não estiver ainda completo o suficiente para isso, este método simplesmente
     * não faz nada.
     */
    public void encomendarFigurinhasRestantes() {
        if(getQuantFigurinhasColadas()
                >= (PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR * totalFigurinhas)){
            for (int i = 1; i <= totalFigurinhas; i++){
                if(this.album[i] == null){
                    Figurinha figurinha = new Figurinha(i,
                            String.format("http://urlFakeDaFigurinha%d.jpg", i));
                    this.album[i] = figurinha;
                    this.totalFigurinhasRecebidos++;
                }
            }
        }
    }

    public boolean possuiFigurinhaColada(int posicao) {
        return !(this.album[posicao] == null);
    }

    public boolean possuiFigurinhaColada(Figurinha figurinha) {  // overload
        return possuiFigurinhaColada(figurinha.getPosicao());
    }

    public boolean possuiFigurinhaRepetida(int posicao) {
        return this.possuiFigurinhaColada(posicao) && this.albumrepetidos[posicao] > 1;
    }

    public boolean possuiFigurinhaRepetida(Figurinha figurinha) {  // overload
        return possuiFigurinhaRepetida(figurinha.getPosicao());
    }

    public int getQuantFigurinhasColadas() {
        return (this.totalFigurinhasRecebidos - this.getQuantFigurinhasRepetidas());
    }

    public int getQuantFigurinhasRepetidas() {
        return this.FigurinhasRepetidas;
    }

    public int getQuantFigurinhasFaltando() {
        return this.totalFigurinhas - this.getQuantFigurinhasColadas();
    }
}
