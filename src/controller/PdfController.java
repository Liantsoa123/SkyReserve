package controller;

import mg.noobframework.annotation.*;
import mg.noobframework.modelview.Modelview;

@Controller
public class PdfController {

    @Get
    @Url("/downloadReservationPdf")
    @AuthMethod("CLIENT")
    public Modelview downloadReservationPdf(@RequestParam("reservationId") int reservationId) {
        Modelview mv = new Modelview();
        String url = "http://localhost:8080/api/pdf/reservation/" + reservationId;
        mv.setUrl(url);
        mv.setSendRedirect(true);
        return mv;
    }
}