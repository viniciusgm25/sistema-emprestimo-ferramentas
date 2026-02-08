package Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class GerenciadorArquivos {

    // Define o nome da pasta onde os arquivos serão salvos
    private static final String PASTA_SISTEMA = "imagens_sistema";

    // Método para copiar um arquivo selecionado para dentro da pasta do sistema
    public static String copiar(File arquivoOrigem, String prefixo) {
        try {
            // Garante que a pasta do sistema exista
            Path pastaDestino = Paths.get(PASTA_SISTEMA);
            if (!Files.exists(pastaDestino)) {
                Files.createDirectories(pastaDestino);
            }

            // Pega a extensão do arquivo original (ex: ".jpg")
            String nomeOriginal = arquivoOrigem.getName();
            String extensao = "";
            int i = nomeOriginal.lastIndexOf('.');
            if (i > 0) {
                extensao = nomeOriginal.substring(i);
            }

            // Cria um nome de arquivo temporário único
            String nomeArquivoTemp = prefixo + "_temp_" + System.currentTimeMillis() + extensao;

            // Define o caminho completo de destino
            Path caminhoDestino = pastaDestino.resolve(nomeArquivoTemp);

            // Copia o arquivo
            Files.copy(arquivoOrigem.toPath(), caminhoDestino, StandardCopyOption.REPLACE_EXISTING);

            // Retorna o caminho relativo para ser salvo temporariamente
            return caminhoDestino.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Retorna nulo se houver erro
        }
    }

    // Método para renomear um arquivo temporário para seu nome final (usando o ID)
    public static String renomear(String caminhoArquivoTemp, String prefixo, int id) {
        if (caminhoArquivoTemp == null || !caminhoArquivoTemp.contains("_temp_")) {
            return caminhoArquivoTemp; // Não faz nada se não for um arquivo temporário
        }

        try {
            Path arquivoOrigem = Paths.get(caminhoArquivoTemp);

            // Pega a extensão
            String nomeOriginal = arquivoOrigem.getFileName().toString();
            String extensao = "";
            int i = nomeOriginal.lastIndexOf('.');
            if (i > 0) {
                extensao = nomeOriginal.substring(i);
            }

            // Cria o nome final do arquivo (ex: "produto_15.jpg")
            String nomeFinal = prefixo + "_" + id + extensao;

            // Resolve o novo caminho e renomeia o arquivo
            Path caminhoFinal = arquivoOrigem.resolveSibling(nomeFinal);
            Files.move(arquivoOrigem, caminhoFinal, StandardCopyOption.REPLACE_EXISTING);

            // Retorna o novo caminho relativo final
            return caminhoFinal.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return caminhoArquivoTemp; // Se der erro, mantém o nome temporário
        }
    }
}
