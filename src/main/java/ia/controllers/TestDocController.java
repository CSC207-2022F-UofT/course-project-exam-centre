package ia.controllers;

import java.io.File;

public class TestDocController implements SubmitDocumentController{

    @Override
    public void SubmitDocument(File file) {
        System.out.println("bruh");
    }
}
