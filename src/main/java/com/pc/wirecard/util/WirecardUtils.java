package com.pc.wirecard.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pc.wirecard.constant.WirecardConstants;
import com.pc.wirecard.model.MerchantNameAndDate;
import com.pc.wirecard.model.entity.MerchantInfo;
import com.pc.wirecard.model.internalreport.SheetOneInfo;
import com.pc.wirecard.model.poiji.RoctextInfo;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public class WirecardUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(WirecardUtils.class); 
	
	public static<T> List<T> convertToList(final Path excelFilePath, final Class<T> type) {	
		try (InputStream stream = new FileInputStream(excelFilePath.toFile())) {
			return Poiji.fromExcel(stream, PoijiExcelType.XLS, type);
		} catch (IOException e) {
			logger.error("Error in converting excel file to list:", e);
			return null;
		}			
	}
	
	public static void writeToExcel(final Workbook workbook, final Path destinationPath) throws IOException {
		Files.createDirectories(destinationPath.getParent());
		final FileOutputStream outputStream = new FileOutputStream(destinationPath.toFile());
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	public static MerchantNameAndDate extractMerchantNameAndDateFromFilename(final String filename) {
		final Pattern r = Pattern.compile(WirecardConstants.FILENAME_PATTERN);
		final Matcher m = r.matcher(filename);
		final MerchantNameAndDate md = new MerchantNameAndDate();
		if (m.find( )) {
			final String merchantName = m.group(1);
			final String date = m.group(2);
			md.setMerchantName(merchantName);
			md.setDate(date);
		} 
		return md;
	}
	
	public static void validateFilename(final String filename) {
		final Pattern r = Pattern.compile(WirecardConstants.FILENAME_PATTERN);
		final Matcher m = r.matcher(filename);
		if (!m.find( )) {
			throw new IllegalArgumentException("Invalid filename format");
		}		
	}
	
	public static String formatToWirecardFormat(BigDecimal d) {
		final DecimalFormat df = new DecimalFormat("#.##");
		if (d.compareTo(new BigDecimal(0)) == 0) {
			return "";
		} else {
			return df.format(d);
		}
	}
	
	public static BigDecimal roundUpTwoDecimalPlaces(BigDecimal d) {
		return d.setScale(2, RoundingMode.HALF_UP);		
	}
	
	public static boolean isZero(BigDecimal value) {
		return value.compareTo(WirecardConstants.ZERO) == 0;
	}
	
	public static BigDecimal getSgdPayment(final RoctextInfo rocInfo) {
		final BigDecimal baseAmount = rocInfo.getBaseAmt() == null ? new BigDecimal(0) : rocInfo.getBaseAmt();
		final BigDecimal mdrAmount = getMdrAmount(rocInfo);
		return baseAmount.subtract(mdrAmount);
	}
	
	public static BigDecimal getMdrAmount(final RoctextInfo rocInfo) {
		final BigDecimal baseAmount = rocInfo.getBaseAmt() == null ? new BigDecimal(0) : rocInfo.getBaseAmt();
		final BigDecimal comAmount = rocInfo.getoComAmt() == null ? new BigDecimal(0) : rocInfo.getoComAmt();
		final BigDecimal grossAmount = rocInfo.getoGrossAmt() == null ? new BigDecimal(0) : rocInfo.getoGrossAmt();
		BigDecimal mdrRate = new BigDecimal(0);
		try {
			mdrRate = comAmount.divide(grossAmount, 8, RoundingMode.HALF_UP);
		} catch (ArithmeticException e) {
			mdrRate = new BigDecimal(0);
		}
		return mdrRate.multiply(baseAmount);
	}
	
	public static BigDecimal getMerchantCommission(final RoctextInfo rocInfo, final MerchantInfo merchantInfo) {
		final String merchantCommissionRateString = merchantInfo != null ? merchantInfo.getDccCommission() : "0";
		final BigDecimal baseAmount = rocInfo.getBaseAmt() == null ? new BigDecimal(0) : rocInfo.getBaseAmt();
		final BigDecimal sgdAmount = WirecardConstants.CURRENCY_BASE.equals(rocInfo.getCcy()) ? new BigDecimal(0) : baseAmount;
		
		BigDecimal merchantCommissionRateBigDecimal = null;		
		try {
			merchantCommissionRateBigDecimal = new BigDecimal(merchantCommissionRateString).divide(new BigDecimal(100));
		} catch (Exception e) {
			merchantCommissionRateBigDecimal = new BigDecimal(0);
		}		
		return sgdAmount.multiply(merchantCommissionRateBigDecimal);
	}
	
	public static BigDecimal getMerchantCommission(final SheetOneInfo cellInfo, final MerchantInfo merchantInfo) {
		final String merchantCommissionRateString = merchantInfo != null ? merchantInfo.getDccCommission() : "0";
		BigDecimal merchantCommissionRateBigDecimal = null;		
		try {
			merchantCommissionRateBigDecimal = new BigDecimal(merchantCommissionRateString).divide(new BigDecimal(100));
		} catch (Exception e) {
			merchantCommissionRateBigDecimal = new BigDecimal(0);
		}		
		return cellInfo.getSgdAmount().multiply(merchantCommissionRateBigDecimal);
	}
	

}
