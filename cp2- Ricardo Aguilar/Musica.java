public class Musica {
    String titulo;
    String artista;
    int duracaoSegundos;
    String genero;

    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
        this.genero = genero;
    }

    public void exibir() {
        System.out.println(titulo + " - " + artista + " (" + getDuracaoFormatada() + ") [" + genero + "]");
    }

    public String getDuracaoFormatada() {
        int min = duracaoSegundos / 60;
        int seg = duracaoSegundos % 60;
        return String.format("%d:%02d", min, seg);
    }

    public boolean contemTitulo(String busca) {
        return titulo.toLowerCase().contains(busca.toLowerCase());
    }
}