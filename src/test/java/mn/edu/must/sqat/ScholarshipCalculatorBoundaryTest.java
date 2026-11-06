package mn.edu.must.sqat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/** Лаб 9, Хэсэг D: letterGrade-ийн зааг утгын тестүүд (спецификацаас). */
class ScholarshipCalculatorBoundaryTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    @ParameterizedTest(name = "letterGrade({0}) = {1}")
    @CsvSource({
        "0,     F",
        "59.99, F",
        "60,    D",
        "69.99, D",
        "70,    C",
        "79.99, C",
        "80,    B",
        "89.99, B",
        "90,    A",
        "100,   A"
    })
    void letterGrade_boundaries(double score, String expected) {
        assertEquals(expected, calc.letterGrade(score));
    }
}
