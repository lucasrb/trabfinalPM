package br.uniriotec.svg;

import java.io.*;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.w3c.dom.Document;

public class DOMRasterizer {

	/**
	 * Código para capturar um documento SVG de um arquivo existente através de uma URI
	 * {@link https://stackoverflow.com/questions/26027313/how-to-load-and-parse-svg-documents}
	 */
    public Document createDocument(String uri) throws IOException {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory( parser );
        Document document = factory.createDocument( uri );
        
        return document;
    }

    /**
     * {@link https://xmlgraphics.apache.org/batik/using/transcoder.html}
     */
    public void save(Document document) throws Exception {
        JPEGTranscoder t = new JPEGTranscoder();
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
                             new Float(.8));
        TranscoderInput input = new TranscoderInput(document);
        OutputStream ostream = new FileOutputStream("out.jpg");
        TranscoderOutput output = new TranscoderOutput(ostream);
        t.transcode(input, output);
        ostream.flush();
        ostream.close();
    }
}