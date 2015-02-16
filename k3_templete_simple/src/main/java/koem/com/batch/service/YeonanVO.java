package koem.com.batch.service;

public class YeonanVO {
/*
	해역명 / SEA_NAM / string /
	연안명 / CST_NAM / string /
	정점명 / STA_NAM / string /
	관측일 / OBS_DAT / string /
	수온(표층)(℃) / WTEMP_S / number / ℃
	수온(저층)(℃) / WTEMP_B / number / ℃
	염분(표층) / SALT_S / number /
	염분(저층) / SALT_B / number /
	수소이온농도(표층) / PH_S / number /
	수소이온농도(저층) / PH_B / number /
	용존산소(표층)(㎎/L) / DO_S / number / ㎎/L
	용존산소(저층)(㎎/L) / DO_B / number / ㎎/L
	화학산소요구량(표층)(㎎/L) / COD_S / number / ㎎/L
	화학산소요구량(저층)(㎎/L) / COD_B / number / ㎎/L
	암모니아질소(표층)(㎍/L) / NH4N_S / number / ㎍/L
	암모니아질소(저층)(㎍/L) / NH4N_B / number / ㎍/L
	아질산질소(표층)(㎍/L) / NO2N_S / number / ㎍/L
	아질산질소(저층)(㎍/L) / NO2N_B / number / ㎍/L
	질산질소(표층)(㎍/L) / NO3N_S / number / ㎍/L
	질산질소(저층)(㎍/L) / NO3N_B / number / ㎍/L
	용존무기질소(표층)(㎍/L) / DIN_S / number / ㎍/L
	용존무기질소(저층)(㎍/L) / DIN_B / number / ㎍/L
	총질소(표층)(㎍/L) / TN_S / number / ㎍/L
	총질소(저층)(㎍/L) / TN_B / number / ㎍/L
	용존무기인(표층)(㎍/L) / DIP_S / number / ㎍/L
	용존무기인(저층)(㎍/L) / DIP_B / number / ㎍/L
	총인(표층)(㎍/L) / TP_S / number / ㎍/L
	총인(저층)(㎍/L) / TP_B / number / ㎍/L
	규산규소(표층)(㎍/L) / SIO2_S / number / ㎍/L
	규산규소(저층)(㎍/L) / SIO2_B / number / ㎍/L
	부유물질(표층)(㎎/L) / SS_S / number / ㎎/L
	부유물질(저층)(㎎/L) / SS_B / number / ㎎/L
	클로로필a(표층)(㎍/L) / CHLA_S / number / ㎍/L
	클로로필a(저층)(㎍/L) / CHLA_B / number / ㎍/L
	유분(표층)(㎍/L) / SPM_S / number / ㎍/L
	유분(저층)(㎍/L) / SPM_B / number / ㎍/L
	투명도(m) / TRANS / number / m
	정점코드 / STA_CDE / string /
	날씨 / WEATHER / string /
	*/
	 private String SEA_NAM;
	 private String CST_NAM;
	 private String STA_NAM;
	 private String OBS_DAT;
	 private String WTEMP_S;
	 private String WTEMP_B;
	 private String SALT_S;
	 private String SALT_B;
	 private String PH_S;
	 private String PH_B;
	 private String DO_S;
	 private String DO_B;
	 private String COD_S;
	 private String COD_B;
	 private String NH4N_S;
	 private String NH4N_B;
	 private String NO2N_S;
	 private String NO2N_B;
	 private String NO3N_S;
	 private String NO3N_B;
	 private String DIN_S;
	 private String DIN_B;
	 private String TN_S;
	 private String TN_B;
	 private String DIP_S;
	 private String DIP_B;
	 private String TP_S;
	 private String TP_B;
	 private String SIO2_S;
	 private String SIO2_B;
	 private String SS_S;
	 private String SS_B;
	 private String CHLA_S;
	 private String CHLA_B;
	 private String SPM_S;
	 private String SPM_B;
	 private String TRANS;
	 private String STA_CDE;
	 private String WEATHER;
	 
	public String getSEA_NAM() {
		return SEA_NAM;
	}
	public void setSEA_NAM(String sEA_NAM) {
		SEA_NAM = sEA_NAM;
	}
	public String getCST_NAM() {
		return CST_NAM;
	}
	public void setCST_NAM(String cST_NAM) {
		CST_NAM = cST_NAM;
	}
	public String getSTA_NAM() {
		return STA_NAM;
	}
	public void setSTA_NAM(String sTA_NAM) {
		STA_NAM = sTA_NAM;
	}
	public String getOBS_DAT() {
		return OBS_DAT;
	}
	public void setOBS_DAT(String oBS_DAT) {
		OBS_DAT = oBS_DAT;
	}
	public String getWTEMP_S() {
		return WTEMP_S;
	}
	public void setWTEMP_S(String wTEMP_S) {
		WTEMP_S = wTEMP_S;
	}
	public String getWTEMP_B() {
		return WTEMP_B;
	}
	public void setWTEMP_B(String wTEMP_B) {
		WTEMP_B = wTEMP_B;
	}
	public String getSALT_S() {
		return SALT_S;
	}
	public void setSALT_S(String sALT_S) {
		SALT_S = sALT_S;
	}
	public String getSALT_B() {
		return SALT_B;
	}
	public void setSALT_B(String sALT_B) {
		SALT_B = sALT_B;
	}
	public String getPH_S() {
		return PH_S;
	}
	public void setPH_S(String pH_S) {
		PH_S = pH_S;
	}
	public String getPH_B() {
		return PH_B;
	}
	public void setPH_B(String pH_B) {
		PH_B = pH_B;
	}
	public String getDO_S() {
		return DO_S;
	}
	public void setDO_S(String dO_S) {
		DO_S = dO_S;
	}
	public String getDO_B() {
		return DO_B;
	}
	public void setDO_B(String dO_B) {
		DO_B = dO_B;
	}
	public String getCOD_S() {
		return COD_S;
	}
	public void setCOD_S(String cOD_S) {
		COD_S = cOD_S;
	}
	public String getCOD_B() {
		return COD_B;
	}
	public void setCOD_B(String cOD_B) {
		COD_B = cOD_B;
	}
	public String getNH4N_S() {
		return NH4N_S;
	}
	public void setNH4N_S(String nH4N_S) {
		NH4N_S = nH4N_S;
	}
	public String getNH4N_B() {
		return NH4N_B;
	}
	public void setNH4N_B(String nH4N_B) {
		NH4N_B = nH4N_B;
	}
	public String getNO2N_S() {
		return NO2N_S;
	}
	public void setNO2N_S(String nO2N_S) {
		NO2N_S = nO2N_S;
	}
	public String getNO2N_B() {
		return NO2N_B;
	}
	public void setNO2N_B(String nO2N_B) {
		NO2N_B = nO2N_B;
	}
	public String getNO3N_S() {
		return NO3N_S;
	}
	public void setNO3N_S(String nO3N_S) {
		NO3N_S = nO3N_S;
	}
	public String getNO3N_B() {
		return NO3N_B;
	}
	public void setNO3N_B(String nO3N_B) {
		NO3N_B = nO3N_B;
	}
	public String getDIN_S() {
		return DIN_S;
	}
	public void setDIN_S(String dIN_S) {
		DIN_S = dIN_S;
	}
	public String getDIN_B() {
		return DIN_B;
	}
	public void setDIN_B(String dIN_B) {
		DIN_B = dIN_B;
	}
	public String getTN_S() {
		return TN_S;
	}
	public void setTN_S(String tN_S) {
		TN_S = tN_S;
	}
	public String getTN_B() {
		return TN_B;
	}
	public void setTN_B(String tN_B) {
		TN_B = tN_B;
	}
	public String getDIP_S() {
		return DIP_S;
	}
	public void setDIP_S(String dIP_S) {
		DIP_S = dIP_S;
	}
	public String getDIP_B() {
		return DIP_B;
	}
	public void setDIP_B(String dIP_B) {
		DIP_B = dIP_B;
	}
	public String getTP_S() {
		return TP_S;
	}
	public void setTP_S(String tP_S) {
		TP_S = tP_S;
	}
	public String getTP_B() {
		return TP_B;
	}
	public void setTP_B(String tP_B) {
		TP_B = tP_B;
	}
	public String getSIO2_S() {
		return SIO2_S;
	}
	public void setSIO2_S(String sIO2_S) {
		SIO2_S = sIO2_S;
	}
	public String getSIO2_B() {
		return SIO2_B;
	}
	public void setSIO2_B(String sIO2_B) {
		SIO2_B = sIO2_B;
	}
	public String getSS_S() {
		return SS_S;
	}
	public void setSS_S(String sS_S) {
		SS_S = sS_S;
	}
	public String getSS_B() {
		return SS_B;
	}
	public void setSS_B(String sS_B) {
		SS_B = sS_B;
	}
	public String getCHLA_S() {
		return CHLA_S;
	}
	public void setCHLA_S(String cHLA_S) {
		CHLA_S = cHLA_S;
	}
	public String getCHLA_B() {
		return CHLA_B;
	}
	public void setCHLA_B(String cHLA_B) {
		CHLA_B = cHLA_B;
	}
	public String getSPM_S() {
		return SPM_S;
	}
	public void setSPM_S(String sPM_S) {
		SPM_S = sPM_S;
	}
	public String getSPM_B() {
		return SPM_B;
	}
	public void setSPM_B(String sPM_B) {
		SPM_B = sPM_B;
	}
	public String getTRANS() {
		return TRANS;
	}
	public void setTRANS(String tRANS) {
		TRANS = tRANS;
	}
	public String getSTA_CDE() {
		return STA_CDE;
	}
	public void setSTA_CDE(String sTA_CDE) {
		STA_CDE = sTA_CDE;
	}
	public String getWEATHER() {
		return WEATHER;
	}
	public void setWEATHER(String wEATHER) {
		WEATHER = wEATHER;
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
