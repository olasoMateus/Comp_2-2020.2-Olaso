import org.junit.Before;
import org.junit.Test;
import sun.misc.FileURLMapper;

import static org.junit.Assert.*;

public class AlbumTest {

    private Album<Figurinha> albumFigurinhas;
    private Repositorio<Figurinha> repositorioFigurinhas;
    private Figurinha figurinhaGenerica = new Figurinha(0, "Blah");
    private Selo seloGenerico = new Selo(0, "Blah");//criados para fazer as instancias de repositório

    private static final int TAMANHO_DO_ALBUM = 200;
    private static final int ITENS_POR_PACOTE = 3;

    @Before  // roda antes de cada teste
    public void setUp() {
        this.repositorioFigurinhas = new Repositorio<Figurinha>("album_copa2014", TAMANHO_DO_ALBUM,
                figurinhaGenerica);
        this.albumFigurinhas = new Album<Figurinha>(repositorioFigurinhas, ITENS_POR_PACOTE);
    }

    private void popularAlbum(int[] posicoesDesejadas, Colecionavel tipo, Album album) {
        String tipoAnalisado = tipo.getClass().getName();
        String tipoDoAlbum = album.getTipoDoRepositorio();
        if(tipoAnalisado == "Figurinha" && tipoDoAlbum == tipoAnalisado){
            Pacotinho<Figurinha> pacote = new Pacotinho<Figurinha>(this.repositorioFigurinhas, posicoesDesejadas);
            album.receberNovoPacotinho(pacote);
        }
        else if(tipoAnalisado == "Selo" && tipoDoAlbum == tipoAnalisado){
            Pacotinho<Selo> pacote = new Pacotinho<Selo>(this.repositorioFigurinhas, posicoesDesejadas);
            album.receberNovoPacotinho(pacote);
        }
        else{
            System.out.println("Tipo do colecionavel ou do album invalido invalido");
        }
    }

    @Test
    public void testarPossuiFigurinhaParaFigurinhasExistentes() {
        popularAlbum(new int[] {1, 2, 3}, figurinhaGenerica, albumFigurinhas);

        for (int i = 1; i <= 3; i++) {
            assertTrue("Figurinhas já inseridas devem ser encontradas",
                    this.albumFigurinhas.possuiItemColado(i));
        }
    }

    @Test
    public void testarPossuiFigurinhaParaFigurinhasAusentes() {
        popularAlbum(new int[] {1, 2, 3}, figurinhaGenerica, albumFigurinhas);

        assertFalse("Não devemos encontrar no álbum figurinhas nunca inseridas",
                this.albumFigurinhas.possuiItemColado(4));
        assertFalse("Não devemos encontrar figurinhas de posições não-positivas",
                this.albumFigurinhas.possuiItemColado(-390));
        assertFalse("Não devemos encontrar figurinhas maiores do que o tamanho",
                this.albumFigurinhas.possuiItemColado(TAMANHO_DO_ALBUM + 1));
    }

    @Test
    public void testarPossuiRepetidaParaFigurinhaRepetida() {
        popularAlbum(new int[] {1, 2, 3}, figurinhaGenerica, albumFigurinhas);
        assertFalse(this.albumFigurinhas.possuiItemRepetido(2));  // sanity check

        popularAlbum(new int[] {2, 8, 9}, figurinhaGenerica, albumFigurinhas);
        assertTrue(this.albumFigurinhas.possuiItemRepetido(2));
    }

    @Test
    public void testarGetTamanhoAlbum() {
        assertEquals(TAMANHO_DO_ALBUM, this.albumFigurinhas.getTamanho());
    }

    @Test
    public void testarGetContFigurinhas() {
        popularAlbum(new int[] {1, 2, 3}, figurinhaGenerica, albumFigurinhas);
        assertEquals(3, this.albumFigurinhas.getQuantItensColados());

        // vou agora abrir outro pacotinho com as mesmas figurinhas
        // e verificar que o álbum terá ainda 3 figurinhas

        popularAlbum(new int[] {1, 2, 3}, figurinhaGenerica, albumFigurinhas);
        assertEquals(3, this.albumFigurinhas.getQuantItensColados());

        //vou agora abrir mais um pacote com figurinhas diferentes
        // e verificar se o albúm as adiciona corretamente

        popularAlbum(new int[] {4, 5, 6}, figurinhaGenerica, albumFigurinhas);
        assertEquals(6, this.albumFigurinhas.getQuantItensColados());
    }

    @Test
    public void testarGetQuantasFaltam() {
        popularAlbum(new int[] {1, 2, 3}, figurinhaGenerica, albumFigurinhas);
        assertEquals(TAMANHO_DO_ALBUM - 3,
                this.albumFigurinhas.getQuantItensFaltantes());
    }

    @Test
    public void testarAutoCompletar() {
        albumFigurinhas.autoCompletar();
        assertEquals("Não deve ser possível auto-completar um álbum que esteja vazio",
                TAMANHO_DO_ALBUM, albumFigurinhas.getQuantItensFaltantes());

        // agora vamos adicionar pacotinhos aleatórios até o álbum ficar quase cheio

        int minimoFigurinhasColadasParaAutoCompletar =
                (int) (TAMANHO_DO_ALBUM * Album.PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100f);

        while (albumFigurinhas.getQuantItensColados() < minimoFigurinhasColadasParaAutoCompletar) {
            Pacotinho novoPacotinho = new Pacotinho(
                    this.repositorioFigurinhas, ITENS_POR_PACOTE);  // aleatório
            albumFigurinhas.receberNovoPacotinho(novoPacotinho);
        }

        // sanity check
        assertTrue(albumFigurinhas.getQuantItensFaltantes() > 0);


        albumFigurinhas.autoCompletar();

        assertEquals("O álbum deve estar completo após uma chamada válida ao auto-completar " +
                        "(isto é, após o percentual mínimo de figurinhas já ter sido obtido)",
                0, albumFigurinhas.getQuantItensFaltantes());  // álbum completo!
    }

    @Test
    public void testarGetItemColado() {
        popularAlbum(new int[] {1, 2, 3}, figurinhaGenerica, albumFigurinhas);
        Colecionavel colecionavel = albumFigurinhas.getItemColado(2);

        assertNotNull(colecionavel);

        assertEquals(2, colecionavel.getPosicao());

        assertNull(albumFigurinhas.getItemColado(4));
    }

    @Test
    public void testarRejeicaoPacotinhosDeTamanhoErrado() {
        popularAlbum(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8}, figurinhaGenerica, albumFigurinhas);

        assertEquals("Pacotes de tamanho distinto do informado na construção " +
                        "do álbum devem ser rejeitados",
                0, albumFigurinhas.getQuantItensColados());
    }

    @Test
    public void testarInstanciasDoAlbum(){
        //Instancia para figurinhas já criada em setUp, então criamos uma de selos
        Repositorio<Selo> repositorioDeSelos = new Repositorio<Selo>("selosDoBrasil", 27,
                new Selo(0, "Blah"));
        Album<Selo> livroDeSelos = new Album<Selo>(repositorioDeSelos, ITENS_POR_PACOTE);
        popularAlbum(new int[]{1,2,3}, seloGenerico, livroDeSelos);

        Selo selo = livroDeSelos.getItemColado(2);

        assertNotNull(selo);

        assertEquals("Verifica se a posição está correta",2, selo.getPosicao());

        assertNull("Verifica se a posição 5 está realmente vazia",livroDeSelos.getItemColado(5));

        popularAlbum(new int[] {1, 2, 3}, seloGenerico, livroDeSelos);

        assertEquals(3, livroDeSelos.getQuantItensColados());


    }

}