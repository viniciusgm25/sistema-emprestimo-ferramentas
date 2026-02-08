package Utils;

import Controles.Emprestimo;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Classe responsável por gerar contratos de locação em PDF e HTML.
 */
public class GeradorContrato {

    /**
     * Gera o contrato de locação em PDF.
     */
    public static void gerar(Emprestimo emprestimo, String caminhoArquivo) throws FileNotFoundException, IOException {
        PdfWriter writer = new PdfWriter(caminhoArquivo);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.setMargins(50, 50, 50, 50);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Título
        Paragraph titulo = new Paragraph("CONTRATO DE LOCAÇÃO DE EQUIPAMENTO");
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setBold();
        titulo.setFontSize(16);
        titulo.setMarginBottom(20);
        document.add(titulo);

        // Identificação do cliente
        String identificadorCliente = emprestimo.getPessoa().getTipoPessoa() == 'F'
                ? "CPF: " + emprestimo.getPessoa().getCpf()
                : "CNPJ: " + emprestimo.getPessoa().getCnpj();

        // Corpo inicial
        Paragraph p1 = new Paragraph(String.format(
                "Pelo presente instrumento, de um lado a sua empresa NOME DA EMPRESA AQUI, "
                + "CNPJ XX.XXX.XXX/XXXX-XX, doravante denominada LOCADORA, e de outro lado o(a) Sr(a). %s, "
                + "portador(a) do documento %s, residente no endereço %s, nº %s, doravante denominado(a) LOCATÁRIO(A), "
                + "celebram o presente contrato de locação.",
                emprestimo.getPessoa().getNome(), identificadorCliente,
                emprestimo.getPessoa().getEndereco(), emprestimo.getPessoa().getNumero()
        ));
        p1.setTextAlignment(TextAlignment.JUSTIFIED);
        p1.setFontSize(12);
        p1.setMarginBottom(15);
        document.add(p1);

        // Cláusula 1
        Paragraph c1Titulo = new Paragraph("1. DO OBJETO DA LOCAÇÃO");
        c1Titulo.setBold();
        c1Titulo.setMarginTop(15);
        document.add(c1Titulo);

        Paragraph c1Texto = new Paragraph(String.format("""
                                                        O objeto do presente contrato \u00e9 a loca\u00e7\u00e3o do seguinte equipamento:
                                                        - Produto: %s
                                                        - Marca: %s
                                                        - C\u00f3digo Original: %s""",
                emprestimo.getProduto().getNome(),
                emprestimo.getProduto().getMarca(),
                emprestimo.getProduto().getCodigoOriginal()
        ));
        c1Texto.setTextAlignment(TextAlignment.JUSTIFIED);
        document.add(c1Texto);

        // Cláusula 2
        Paragraph c2Titulo = new Paragraph("2. DO PRAZO E VALOR");
        c2Titulo.setBold();
        c2Titulo.setMarginTop(15);
        document.add(c2Titulo);

        Paragraph c2Texto = new Paragraph(String.format(
                "A presente locação terá o prazo de vigência de %s até %s. "
                + "O valor total pago pela locação foi de R$ %.2f.",
                sdf.format(emprestimo.getDataEmprestimo()),
                sdf.format(emprestimo.getDataPrevistaDevolucao()),
                emprestimo.getValorTotalCobrado()
        ));
        c2Texto.setTextAlignment(TextAlignment.JUSTIFIED);
        document.add(c2Texto);

        // Cláusula 3
        Paragraph c3Titulo = new Paragraph("3. DA DEVOLUÇÃO E MULTA POR ATRASO");
        c3Titulo.setBold();
        c3Titulo.setMarginTop(15);
        document.add(c3Titulo);

        Paragraph c3Texto = new Paragraph(
                "O LOCATÁRIO(A) se compromete a devolver o equipamento na data estipulada. "
                + "Em caso de não devolução na data acordada, será cobrada uma multa correspondente "
                + "ao valor da diária (R$ " + emprestimo.getProduto().getValorDiaria() + ") por cada dia de atraso, "
                + "acrescida de uma taxa de 20% sobre o valor total do contrato por quebra de acordo."
        );
        c3Texto.setTextAlignment(TextAlignment.JUSTIFIED);
        document.add(c3Texto);

        // Assinaturas
        Paragraph assinaturaLocadora = new Paragraph("\n\n\n_______________________________________\nAssinatura da LOCADORA");
        assinaturaLocadora.setTextAlignment(TextAlignment.CENTER);
        document.add(assinaturaLocadora);

        Paragraph assinaturaLocatario = new Paragraph("\n\n\n_______________________________________\nAssinatura do(a) LOCATÁRIO(A)");
        assinaturaLocatario.setTextAlignment(TextAlignment.CENTER);
        document.add(assinaturaLocatario);

        document.close();
    }

    /**
     * Gera o contrato de locação em formato HTML.
     */
    public static String gerarHtml(Emprestimo emprestimo) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String identificadorCliente = emprestimo.getPessoa().getTipoPessoa() == 'F'
                ? "CPF: " + emprestimo.getPessoa().getCpf()
                : "CNPJ: " + emprestimo.getPessoa().getCnpj();

        StringBuilder sb = new StringBuilder();
        sb.append("""
            <html>
            <head>
            <style>
            body { font-family: Arial, sans-serif; padding: 40px; line-height: 1.6; }
            h1 { text-align: center; font-size: 20px; margin-bottom: 20px; }
            h2 { font-size: 16px; margin-top: 25px; }
            p { text-align: justify; font-size: 13px; }
            div.assinatura { text-align: center; margin-top: 40px; }
            </style>
            </head>
            <body>
        """);

        sb.append("<h1>CONTRATO DE LOCAÇÃO DE EQUIPAMENTO</h1>");
        sb.append("<p>Pelo presente instrumento, de um lado a sua empresa <b>NOME DA EMPRESA AQUI</b>, "
                + "CNPJ XX.XXX.XXX/XXXX-XX, doravante denominada <b>LOCADORA</b>, e de outro lado o(a) Sr(a). <b>")
                .append(emprestimo.getPessoa().getNome())
                .append("</b>, portador(a) do documento ")
                .append(identificadorCliente)
                .append(", residente no endereço ")
                .append(emprestimo.getPessoa().getEndereco())
                .append(", nº ")
                .append(emprestimo.getPessoa().getNumero())
                .append(", doravante denominado(a) <b>LOCATÁRIO(A)</b>, celebram o presente contrato de locação.</p>");

        sb.append("<h2>1. DO OBJETO DA LOCAÇÃO</h2>");
        sb.append("<p>O objeto do presente contrato é a locação do seguinte equipamento:<br>");
        sb.append("- Produto: <b>").append(emprestimo.getProduto().getNome()).append("</b><br>");
        sb.append("- Marca: ").append(emprestimo.getProduto().getMarca()).append("<br>");
        sb.append("- Código Original: ").append(emprestimo.getProduto().getCodigoOriginal()).append("</p>");

        sb.append("<h2>2. DO PRAZO E VALOR</h2>");
        sb.append(String.format("<p>A presente locação terá o prazo de vigência de <b>%s</b> até <b>%s</b>. "
                + "O valor total pago pela locação foi de <b>R$ %.2f</b>.</p>",
                sdf.format(emprestimo.getDataEmprestimo()),
                sdf.format(emprestimo.getDataPrevistaDevolucao()),
                emprestimo.getValorTotalCobrado()));

        sb.append("<h2>3. DA DEVOLUÇÃO E MULTA POR ATRASO</h2>");
        sb.append("<p>O LOCATÁRIO(A) se compromete a devolver o equipamento na data estipulada. "
                + "Em caso de não devolução na data acordada, será cobrada uma multa correspondente ao valor da diária "
                + "(<b>R$ ").append(emprestimo.getProduto().getValorDiaria())
                .append("</b>) por cada dia de atraso, acrescida de uma taxa de 20% sobre o valor total do contrato por quebra de acordo.</p>");

        sb.append("<div class='assinatura'>_______________________________________<br>Assinatura da LOCADORA</div>");
        sb.append("<div class='assinatura'>_______________________________________<br>Assinatura do(a) LOCATÁRIO(A)</div>");

        sb.append("</body></html>");
        return sb.toString();
    }
}
