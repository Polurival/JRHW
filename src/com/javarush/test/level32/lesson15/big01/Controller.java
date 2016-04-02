package com.javarush.test.level32.lesson15.big01;

import javax.swing.*;
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
        createNewDocument();
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
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text)
    {
        resetDocument();
        Reader reader = new StringReader(text);
        try
        {
            new HTMLEditorKit().read(reader, document, 0);
        }
        catch (IOException | BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText()
    {
        Writer writer = new StringWriter();
        try
        {
            new HTMLEditorKit().write(writer, document, 0, document.getLength());
        }
        catch (IOException | BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    public void createNewDocument()
    {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument()
    {

    }

    public void saveDocument()
    {

    }

    public void saveDocumentAs()
    {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int result = fileChooser.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (Writer writer = new FileWriter(currentFile))
            {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            }
            catch (IOException | BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
        }
    }
}
