/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic.programming;

/**
 *
 * @author Asus
 */
public class DynamicProgramming {

    /**
     * @param args the command line arguments
     */
    static int tedadMahha = 5;
    static int[] taghazaha = {1, 2, 5, 3, 4};
    static int maxTolid = 6;
    static int maxAnbarDari = 3;
    static double HazineAnbarDari = 0.5;
    static double HazineSabeteTolid = 13;
    static double HazineTolidHarVahed = 4;
    static int MojoodiyeKalaDarEbteda = 0;
    static int MojoodiyeKalaDarEnteha = 0;
    static double[][][] JadvalHa = new double[tedadMahha][maxAnbarDari + 1][maxTolid + 1];
    static double[][] minsHazine = new double[tedadMahha][maxAnbarDari + 1];
    static int[][] minsTolid = new int[tedadMahha][maxAnbarDari + 1];

    public static void main(String[] args) {
        for (int i = 0; i < tedadMahha; i++) {
            for (int j = 0; j < maxAnbarDari + 1; j++) {
                minsHazine[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < tedadMahha; i++) {
            for (int j = 0; j < maxAnbarDari + 1; j++) {
                for (int k = 0; k < maxTolid + 1; k++) {
                    JadvalHa[i][j][k] = -1;
                }
            }
        }
        for (int i = tedadMahha - 1; i >= 0; i--) {
            for (int j = 0; j < maxAnbarDari + 1; j++) {
                for (int k = 0; k < maxTolid + 1; k++) {
                    if (i == tedadMahha - 1) {
                        if (j + k == MojoodiyeKalaDarEnteha + taghazaha[i]) {
                            CalculatingEachCellOfTables(i, j, k);
                        }
                    } else {
                        if (i == 0) {
                            if (j == MojoodiyeKalaDarEbteda) {
                                if (j + k - taghazaha[i] >= 0 && j + k - taghazaha[i] <= maxAnbarDari) {
                                    CalculatingEachCellOfTables(i, j, k);
                                }
                            }
                        } else {
                            if (j + k - taghazaha[i] >= 0 && j + k - taghazaha[i] <= maxAnbarDari) {
                                CalculatingEachCellOfTables(i, j, k);
                            }
                        }
                    }

                }
            }
        }
        CalculatingTheResult();
    }

    public static void CalculatingEachCellOfTables(int i, int j, int k) {
        if (k != 0) {
            if (i != tedadMahha - 1) {
                JadvalHa[i][j][k] = JadvalHa[i][j][k] + HazineSabeteTolid + k * HazineTolidHarVahed;
            } else {
                JadvalHa[i][j][k] = HazineSabeteTolid + k * HazineTolidHarVahed;
            }
        } else {
            if (i != tedadMahha - 1) {
                JadvalHa[i][j][k] = JadvalHa[i][j][k] + k * HazineTolidHarVahed;
            } else {
                JadvalHa[i][j][k] = k * HazineTolidHarVahed;
            }
        }
        JadvalHa[i][j][k] = JadvalHa[i][j][k] + (j + k - taghazaha[i]) * HazineAnbarDari;
        if (JadvalHa[i][j][k] != -1 && JadvalHa[i][j][k] < minsHazine[i][j]) {
            minsHazine[i][j] = JadvalHa[i][j][k];
            minsTolid[i][j] = k;
        }
        if (i - 1 >= 0) {
            for (int satr = 0; satr < maxAnbarDari + 1; satr++) {
                for (int sotoon = 0; sotoon < maxTolid + 1; sotoon++) {
                    if (satr + sotoon - taghazaha[i - 1] == j) {
                        JadvalHa[i - 1][satr][sotoon] = minsHazine[i][j];
                    }
                }
            }
        }
    }

    public static void CalculatingTheResult() {
        int i = 0;
        int j = MojoodiyeKalaDarEbteda;
        while (i < tedadMahha) {
            int mah = i + 1;
            System.out.println("Tolid va hazine dar mahe " + mah + " :  " + minsTolid[i][j] + " , " + minsHazine[i][j]);
            j = j + minsTolid[i][j] - taghazaha[i];
            i++;
        }
    }
}
