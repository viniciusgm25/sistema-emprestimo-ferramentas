package Controles;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Este filtro intercepta qualquer texto inserido em um componente e o converte
 * para MAIÚSCULAS.
 */
public class UpperCaseDocumentFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string,
            AttributeSet attr) throws BadLocationException {

        if (string != null) {
            string = string.toUpperCase(); // Converte o texto para maiúsculo
        }
        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
            AttributeSet attrs) throws BadLocationException {

        if (text != null) {
            text = text.toUpperCase(); // Converte o texto para maiúsculo
        }
        super.replace(fb, offset, length, text, attrs);
    }
}
