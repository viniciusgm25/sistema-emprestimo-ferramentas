package Utils;

import Controles.NotaFiscal;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class GeradorNotaFiscal {

    // --- DADOS DA SUA EMPRESA (PREENCHA AQUI) ---
    private static final String NOME_EMPRESA = "Sua Empresa de Locação LTDA";
    private static final String CNPJ_EMPRESA = "XX.XXX.XXX/0001-XX";
    private static final String ENDERECO_EMPRESA = "Rua Principal, 123 - Centro, Marechal Cândido Rondon - PR";
    private static final String TELEFONE_EMPRESA = "(45) 99999-8888";

    // --- MÉTODO PARA GERAR O PDF ---
    public static void gerarPdf(NotaFiscal nf, String caminhoArquivo) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(caminhoArquivo);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(30, 30, 30, 30);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // --- CABEÇALHO DA NOTA ---
        Table cabecalho = new Table(UnitValue.createPercentArray(new float[]{70, 30})).useAllAvailableWidth();

        // Coluna da Esquerda (Dados da Empresa)
        Table dadosEmpresa = new Table(1).useAllAvailableWidth().setBorder(null);
        dadosEmpresa.addCell(new Paragraph(NOME_EMPRESA).setBold().setFontSize(14).setBorder(null));
        dadosEmpresa.addCell(new Paragraph("CNPJ: " + CNPJ_EMPRESA).setFontSize(9).setBorder(null));
        dadosEmpresa.addCell(new Paragraph(ENDERECO_EMPRESA).setFontSize(9).setBorder(null));
        dadosEmpresa.addCell(new Paragraph("Telefone: " + TELEFONE_EMPRESA).setFontSize(9).setBorder(null));
        cabecalho.addCell(dadosEmpresa.setBorder(null));

        // Coluna da Direita (Número da NF)
        Table dadosNf = new Table(1).useAllAvailableWidth().setBorder(null);
        dadosNf.addCell(new Paragraph("NOTA FISCAL DE SERVIÇO").setBold().setTextAlignment(TextAlignment.CENTER).setBorder(null));
        dadosNf.addCell(new Paragraph("Nº " + String.format("%09d", nf.getNumeroNf())).setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER).setBorder(null));
        dadosNf.addCell(new Paragraph("Emissão: " + sdf.format(nf.getDataEmissao())).setFontSize(8).setTextAlignment(TextAlignment.CENTER).setBorder(null));
        cabecalho.addCell(dadosNf.setBorder(null));
        document.add(cabecalho);

        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------"));

        // --- DADOS DO CLIENTE ---
        document.add(new Paragraph("CLIENTE (TOMADOR DO SERVIÇO)").setBold().setMarginTop(10));
        document.add(new Paragraph("Nome: " + nf.getCodEmprestimo().getPessoa().getNome()));
        String docCliente = nf.getCodEmprestimo().getPessoa().getTipoPessoa() == 'F' ? "CPF: " : "CNPJ: ";
        docCliente += nf.getCodEmprestimo().getPessoa().getTipoPessoa() == 'F' ? nf.getCodEmprestimo().getPessoa().getCpf() : nf.getCodEmprestimo().getPessoa().getCnpj();
        document.add(new Paragraph(docCliente));
        document.add(new Paragraph("Endereço: " + nf.getCodEmprestimo().getPessoa().getEndereco() + ", " + nf.getCodEmprestimo().getPessoa().getNumero()));

        // --- DESCRIÇÃO DOS SERVIÇOS ---
        document.add(new Paragraph("DESCRIÇÃO DOS SERVIÇOS").setBold().setMarginTop(15));
        Table tabelaServicos = new Table(UnitValue.createPercentArray(new float[]{80, 20})).useAllAvailableWidth().setMarginTop(5);
        tabelaServicos.addHeaderCell(new Paragraph("Descrição").setBold());
        tabelaServicos.addHeaderCell(new Paragraph("Valor (R$)").setBold().setTextAlignment(TextAlignment.RIGHT));

        tabelaServicos.addCell(new Paragraph(nf.getDescricaoServico()));
        tabelaServicos.addCell(new Paragraph(String.format("%.2f", nf.getValorServico())).setTextAlignment(TextAlignment.RIGHT));
        document.add(tabelaServicos);

        // --- VALOR TOTAL ---
        document.add(new Paragraph("VALOR TOTAL DA NOTA: R$ " + String.format("%.2f", nf.getValorServico()))
                .setBold().setFontSize(14).setTextAlignment(TextAlignment.RIGHT).setMarginTop(20));

        document.close();
    }
}
