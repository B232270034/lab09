package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    // ---------- Part A: happy path ----------
    @Test
    void letterGrade_95_isA() {
        assertEquals("A", calc.letterGrade(95));
    }

    @Test
    void letterGrade_85_isB() {
        assertEquals("B", calc.letterGrade(85));
    }

    @Test
    void gpa_95_and_85_is_3_5() {
        assertEquals(3.5, calc.gpa(new double[]{95, 85}), 0.001);
    }

    @Test
    void scholarship_highGpaEnoughCredits_is100() {
        assertEquals(100, calc.scholarship(3.9, 16, false));
    }

    @Test
    void scholarship_goodGpa_is70() {
        assertEquals(70, calc.scholarship(3.5, 13, false));
    }

    // ---------- Part B: letterGrade ----------
    @Test
    void letterGrade_negative_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    @Test
    void letterGrade_over100_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    @Test
    void letterGrade_75_isC() {
        assertEquals("C", calc.letterGrade(75));
    }

    @Test
    void letterGrade_65_isD() {
        assertEquals("D", calc.letterGrade(65));
    }

    @Test
    void letterGrade_30_isF() {
        assertEquals("F", calc.letterGrade(30));
    }

    // ---------- Part B: gpa ----------
    @Test
    void gpa_null_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }

    @Test
    void gpa_empty_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{}));
    }

    @Test
    void gpa_mixedCDF_is_1() {
        // C=2, D=1, F=0 (default салаа) -> 3/3 = 1.0
        assertEquals(1.0, calc.gpa(new double[]{75, 65, 30}), 0.001);
    }

    // ---------- Part B: scholarship ----------
    @Test
    void scholarship_gpaNegative_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 10, false));
    }

    @Test
    void scholarship_gpaAbove4_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 10, false));
    }

    @Test
    void scholarship_creditsNegative_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.0, -1, false));
    }

    @Test
    void scholarship_violation_is0() {
        assertEquals(0, calc.scholarship(3.9, 16, true));
    }

    @Test
    void scholarship_highGpaLowCredits_falls_to70() {
        assertEquals(70, calc.scholarship(3.9, 14, false));
    }

    @Test
    void scholarship_midGpaLowCredits_falls_to50() {
        assertEquals(50, calc.scholarship(3.5, 11, false));
    }

    @Test
    void scholarship_lowGpa_is50() {
        assertEquals(50, calc.scholarship(3.1, 5, false));
    }

    @Test
    void scholarship_belowThreshold_is0() {
        assertEquals(0, calc.scholarship(2.9, 20, false));
    }

    // ---------- Part C: давталтын заагийн тестүүд (0 / 1 / 2+) ----------
    @Test
    void gpa_loopZero_emptyArray_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{}));
    }

    @Test
    void gpa_loopZero_null_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }

    @Test
    void gpa_loopOnce_single95_is4() {
        assertEquals(4.0, calc.gpa(new double[]{95}), 0.001);
    }

    @Test
    void gpa_loopMany_four_is2_5() {
        assertEquals(2.5, calc.gpa(new double[]{95, 85, 75, 65}), 0.001);
    }

    // ---------- Part C: давталтын заагийн тестүүд (0 / 1 / 2+) ----------
}
