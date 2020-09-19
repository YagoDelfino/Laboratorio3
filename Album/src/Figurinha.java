import java.util.Random;

public class Figurinha {
    private String urlImagem;
    private int posicao;

    public Figurinha(int posicao, String urlImagem) {
        this.urlImagem = urlImagem;
        this.posicao = posicao;
    }

    /**
     * Indica a posição, no álbum, que esta figurinha deve ocupar.
     *
     * @return Um int dizendo a posição da figurinha
     */
    public int getPosicao() {
        return this.posicao;
    }

    /**
     * Retorna a URL de onde a imagem associada a esta figurinha deverá ser baixada.
     *
     * @return uma String com o endereço desejado
     */
    public String getUrlImagem() {
        return this.urlImagem;
    }


}
