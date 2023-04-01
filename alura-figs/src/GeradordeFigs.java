import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders.MarginBorder;

public class GeradordeFigs {
    

    private BufferedImage BufferedImage;

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{

        // leitura da imagem;
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg")); // método pegando arquivo já baixado
        //InputStream inputStream = new URL ("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json").openStream();
        BufferedImage imagemoriginal = ImageIO.read(inputStream);


        // cria nova imagem em memória com transparência e com tamanho novo;
        int largura = imagemoriginal.getWidth();
        int altura = imagemoriginal.getHeight();
        int novaAltura = altura + 200;
        int novaLargura = largura;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória);
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemoriginal, 0, 0, null);

        // configurar a fonte;
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setColor(Color.black);
        graphics.setFont(fonte);

        // escrever algo na nova imagem;
        String texto = "Teste de texto";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaotextoX = (largura - larguraTexto) / 2;
        graphics.drawString(texto, posicaotextoX, novaAltura - 100);


        // escrever a imagem em um arquivo;
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

  
}
