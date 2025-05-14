package controller;

import mg.noobframework.annotation.*;
import mg.noobframework.modelview.Modelview;
import utils.PropertiesLoader;

@Controller
public class PdfController {

    @Get
    @Url("/downloadReservationPdf")
    @AuthMethod("CLIENT")
    public Modelview downloadReservationPdf(@RequestParam("reservationId") int reservationId) {
        Modelview mv = new Modelview();
        String url = PropertiesLoader.getProperty("pdf.api.url") + reservationId;
        mv.setUrl(url);
        mv.setSendRedirect(true);
        return mv;
    }
}