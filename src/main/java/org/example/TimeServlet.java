package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    private static final String DEFAULT_TIMEZONE = "UTC";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String timezone = req.getParameter("timezone");

        if (Objects.isNull(timezone)) {
            timezone = DEFAULT_TIMEZONE;
        }

        ZoneId zoneId = ZoneId.of(timezone);
        ZonedDateTime now = ZonedDateTime.now(zoneId);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        resp.getWriter().write(dtf.format(now));
    }
}
