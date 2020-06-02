package pl.jazapp.app;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@WebServlet("average")
public class AverageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("text/plain");

        var writer = resp.getWriter();

        try {
            var getNumbers = req.getParameter("numbers");
            String[] numbers = getNumbers.split(",");
            List<Integer> numbersList = new ArrayList<>();

            for (String number : numbers) {
                numbersList.add(Integer.parseInt(number.trim()));
            }

            OptionalDouble average = numbersList.stream()
                    .mapToDouble(a -> a)
                    .average();

            BigDecimal bdAverage = BigDecimal.valueOf(average.getAsDouble());
            BigDecimal result = bdAverage.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();

            writer.println("Average equals: " + result);
        }
        catch (NullPointerException | NumberFormatException e) {
            writer.println("Please put parameters.");
        }
    }
}
