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
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pc.wirecard.constant.WirecardConstants;
import com.pc.wirecard.core.SourceFile;
import com.pc.wirecard.model.MerchantNameAndDate;
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
		if (d.compareTo(BigDecimal.ZERO) == 0) {
			return "";
		} else {
			return df.format(d);
		}
	}
	
	public static BigDecimal roundUpTwoDecimalPlaces(BigDecimal d) {
		return d.setScale(WirecardConstants.BIGDECIMAL_ROUNDING_SCALE, RoundingMode.HALF_UP);		
	}
	
	public static boolean isZero(BigDecimal value) {
		return value.compareTo(BigDecimal.ZERO) == 0;
	}
	
	public static BigDecimal getSgdPayment(final BigDecimal comAmount, final BigDecimal grossAmount, BigDecimal sgdAmount) {
		sgdAmount = sgdAmount == null ? BigDecimal.ZERO : sgdAmount;
		final BigDecimal mdrAmount = getMdrAmount(comAmount, grossAmount, sgdAmount);
		return sgdAmount.subtract(mdrAmount);
	}
	
	public static BigDecimal getMdrAmount(BigDecimal comAmount, BigDecimal grossAmount, BigDecimal sgdAmount) {
		sgdAmount = sgdAmount == null ? BigDecimal.ZERO : sgdAmount;
		comAmount = comAmount == null ? BigDecimal.ZERO : comAmount;
		grossAmount = grossAmount == null ? BigDecimal.ZERO : grossAmount;
		BigDecimal mdrRate = null;
		try {
			mdrRate = comAmount.divide(grossAmount, WirecardConstants.BIGDECIMAL_QUOTIENT_SCALE, RoundingMode.HALF_UP);
		} catch (ArithmeticException e) {
			mdrRate = BigDecimal.ZERO;
		}
		return mdrRate.multiply(sgdAmount);
	}
	
	public static BigDecimal getMerchantCommission(final BigDecimal sgdAmount, final BigDecimal merchantCommissionRate) {
		BigDecimal mcr = null;
		try {
			mcr = merchantCommissionRate.divide(new BigDecimal(100));
		} catch (Exception e) {
			mcr = BigDecimal.ZERO;
		}
		return sgdAmount.multiply(mcr);
	}
	
	public static void validateDccWithEmptyBaseAmount(final SourceFile<RoctextInfo> sourceFile) throws IOException {
		final List<RoctextInfo> list = sourceFile.asList().stream().filter(item -> (!WirecardConstants.CURRENCY_BASE.equals(item.getCcy()) && (item.getBaseAmt() == null || WirecardUtils.isZero(item.getBaseAmt())))).collect(Collectors.toList());
		final StringBuilder sb = new StringBuilder();
		
		sb.append("The ff rows have DCC with an empty SGD Amount:");
		sb.append(System.lineSeparator());
		
		list.forEach(info -> {
			sb.append("Row "+(info.getRowIndex()+1)+": [ORG: "+info.getOrg()+", MERCHANTID: "+info.getMerchantId()+", ACQREFNUM: "+info.getAcqRefNum()+"]");
			sb.append(System.lineSeparator());
		});
		
		if (list.size() > 0) {
			throw new IOException(sb.toString());
		}
	}	

	public static BigDecimal returnAsNegative(BigDecimal num) {
		if (num.signum() == -1) {
			return num;
		} else {
			return num.negate();
		}
	}
	
}
