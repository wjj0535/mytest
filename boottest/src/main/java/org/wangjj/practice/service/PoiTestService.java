package org.wangjj.practice.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PoiTestService {
    public void readXls() throws IOException {
        InputStream is = new FileInputStream("C:\\Users\\wjj19\\Desktop\\瑞达鑫副本-6分误差622.xls.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            BigDecimal shuie = new BigDecimal("0");
            // 循环行Row
            for (int rowNum = 2; rowNum <= 149; rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    HSSFCell amountCell = hssfRow.getCell(4);
                    HSSFCell hanUnitPriceCell = hssfRow.getCell(5);
                    HSSFCell hanPriceCell = hssfRow.getCell(6);
                    HSSFCell taxRateCell = hssfRow.getCell(7);
                    HSSFCell totalPriceCell = hssfRow.getCell(8);

                    BigDecimal amount = new BigDecimal(amountCell.toString());
                    BigDecimal taxRate = new BigDecimal(taxRateCell.toString());
                    BigDecimal totalPrice = new BigDecimal(totalPriceCell.toString());

                    BigDecimal fansuanShuie0 = calculateTax(totalPrice, taxRate, "");

                    shuie = shuie.add(fansuanShuie0);
//                    if (rowNum == 110) {
//                        int s = 0;
//                    }
//                    BigDecimal hanUnitPrice = totalPrice.divide(amount, 0, BigDecimal.ROUND_HALF_UP);
//                    BigDecimal noHanUnitPrice = hanUnitPrice.divide(taxRate.add(new BigDecimal(1)), 14, BigDecimal.ROUND_HALF_UP);
//                    BigDecimal fansuanXiaoshoue = noHanUnitPrice.multiply(amount);
//                    BigDecimal fansuanShuie = totalPrice.subtract(fansuanXiaoshoue);
//
//                    BigDecimal weicha = fansuanShuie.subtract(fansuanShuie0);
//                    System.out.println(rowNum+": "+weicha.abs().toPlainString());
//
//                    if (isSingleLineError(weicha)) {
//                    }
                }
            }
            System.out.println(shuie);
        }
    }
    private boolean isSingleLineError(BigDecimal errorTax) {
        BigDecimal absErrorTax = errorTax.abs();
        return absErrorTax.compareTo(new BigDecimal("0.01")) > 0;
    }
    private boolean isTotalError(BigDecimal errorTax) {
        BigDecimal absErrorTax = errorTax.abs();
        return absErrorTax.compareTo(new BigDecimal("0.06")) > 0;
    }
    private BigDecimal calculateTax(BigDecimal cargoPrice, BigDecimal cargoTaxRate, String includeTax) {
        // 不含税金额 = 含税金额 - 含税金额/(1 + 税率)
        BigDecimal divideNum = new BigDecimal(1).add(cargoTaxRate);
        BigDecimal calculateTax = cargoPrice.multiply(cargoTaxRate)
                .divide(divideNum, 3, RoundingMode.HALF_UP);
        BigDecimal unIncludeTaxPrice = cargoPrice.subtract(calculateTax);
        // 计算税额 = 不含税金额 * 税率
        return unIncludeTaxPrice.multiply(cargoTaxRate).setScale(2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) throws IOException {
        PoiTestService test = new PoiTestService();
        test.readXls();
    }
}
