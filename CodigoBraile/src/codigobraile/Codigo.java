package codigobraile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Codigo implements ICodigoBraile {

    private String Caracteres;
    private char[] arreglo;

    public Codigo(String ca) {
        this.Caracteres = ca;
    }

    public void setCaracteres(String car) {
        this.Caracteres = car;
    }

    public String getCaracteres() {
        return Caracteres;
    }

    public char[] getLetra() {
        arreglo = Caracteres.toCharArray();
        return arreglo;
    }

    public void ArchivoPdf() {

        Document pdf = new Document() {

        };
        try {
            PdfWriter.getInstance(pdf, new FileOutputStream("Traduccion.pdf"));
            pdf.open();
            Paragraph parrafo = new Paragraph("CÓDIGO BRAILE");
            parrafo.setAlignment(Element.ALIGN_CENTER);
            pdf.add(parrafo);
            int x = 25;
            int y = 600;

            String directorio = "C:\\Users\\HP\\Desktop\\CodigoBraile\\Imagenes\\";
            String lol = "";
            Paragraph parrafo2 = new Paragraph("\nEl codigo ingresado es: ");
            parrafo2.setAlignment(Element.ALIGN_CENTER);
            pdf.add(parrafo2);
            Paragraph parrafo3 = new Paragraph("\t\n\" " + Caracteres + "\t\" ");
            parrafo3.setAlignment(Element.ALIGN_CENTER);
            pdf.add(parrafo3);
            char[] caracte = getLetra();
            for (int i = 0; i < caracte.length; i++) {

                for (char m = 'A'; m <= 'Z'; m++) {
                    if (caracte[i] == m) {
                        String Arr = Character.toString(m);
                        Arr = Arr.toLowerCase();
                        lol = Arr + ".png";

                    }
                }

                for (char n = 'a'; n <= 'z'; n++) {
                    if (caracte[i] == n) {
                        lol = n + ".png";

                    }
                }
                for (char w = '0'; w <= '9'; w++) {
                    if (caracte[i] == w) {
                        lol = w + ".png";
                    }
                }

                if (Character.isUpperCase(caracte[i]) == true) {
                    Image ima = Image.getInstance(directorio + "mayu.png");
                    ima.setAbsolutePosition(x, y);
                    pdf.add(ima);
                    x = x + 50;
                }
                if (Character.isDigit(caracte[i]) == true) {
                    Image imo = Image.getInstance(directorio + "num.png");
                    imo.setAbsolutePosition(x, y);
                    pdf.add(imo);
                    x = x + 50;

                }

                if (caracte[i] == ' ') {
                    lol = "espacio.png";
                }

                if (caracte[i] == ',') {
                    lol = "coma.png";
                }

                if (caracte[i] == '¡' || caracte[i] == '!') {
                    lol = "admiracion.png";
                }

                if (caracte[i] == '¿' || caracte[i] == '?') {
                    lol = "interrogacion.png";
                }

                if (caracte[i] == '.') {
                    lol = "punto.png";
                }

                if (caracte[i] == ')') {
                    lol = "Parentecis cerrado.png";
                }
                if (caracte[i] == '(') {
                    lol = "Parentecis abierto.png";
                }
                if (caracte[i] == 'á') {
                    lol = "aAcento.png";
                }
                if (caracte[i] == 'é') {
                    lol = "eAcento.png";
                }
                if (caracte[i] == 'í') {
                    lol = "iAcento.png";
                }
                if (caracte[i] == 'ó') {
                    lol = "oAcento.png";
                }
                if (caracte[i] == 'ú') {
                    lol = "uAcento.png";
                }
                Image imag = Image.getInstance(directorio + lol);
                imag.setAbsolutePosition(x, y);
                pdf.add(imag);
                if (caracte[i] == ' ') {
                    y = y - 90;
                    x = 25;
                } else {
                    x = x + 50;
                }
            }
            pdf.close();
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(CodigoBraile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
