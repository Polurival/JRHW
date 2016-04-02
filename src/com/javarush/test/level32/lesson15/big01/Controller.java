package com.javarush.test.level32.lesson15.big01;

import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by
 * Polurival on 02.04.2016.
 */
public class Controller
{
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public HTMLDocument getDocument()
    {
        return document;
    }

    public Controller(View view)
    {
        this.view = view;
    }

    public void init()
    {

    }

    public static void main(String[] args)
    {
        View view = new View();
        Controller controller = new Controller(view);

        view.setController(controller);

        view.init();
        controller.init();
    }

    public void exit()
    {
        System.exit(0);
    }

    public void resetDocument()
    {
        if (document != null)
        {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        document = (HTMLDocument) htmlEditorKit.createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text)
    {
        resetDocument();
        Reader reader = new StringReader(text);
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try
        {
            htmlEditorKit.read(reader, document, 0);
        }
        catch (IOException | BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText()
    {
        Writer writer = new StringWriter();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try
        {
            htmlEditorKit.write(writer, document, 0, document.getLength());
        }
        catch (IOException | BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }
}
