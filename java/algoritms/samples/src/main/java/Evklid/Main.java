package Evklid;

/**
 * Created by alex on 26.01.17.
 */
public class Main {

    public static void main(String[] args) {

    }

    /**
     * Вычисляем наибольший общий делитель двух целых, неотрицательных чисел
     * 
     * @param p число
     * @param q число
     * @return НОД
     */
    public static int gcd(int p, int q) {

        if (q == 0)
            return p;
        int r = p % q;
        return gcd(q, r);
    }
}
