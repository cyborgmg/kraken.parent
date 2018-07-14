package com.peixeurbano.kraken.entity.imutable;

import java.io.Serializable;

import com.peixeurbano.kraken.entity.sqlserver.CmpComponentConfig;


public class ListCmpComponent implements Serializable {
	
	private static final long serialVersionUID = 8139473568830952677L;
	
	private CmpComponentConfig HOURLOCK;
	private CmpComponentConfig MINUTELOCK;
	private CmpComponentConfig HOURUNLOCK;
	private CmpComponentConfig MINUTEUNLOCK;
	private CmpComponentConfig HOURRELOAD;
	private CmpComponentConfig MINUTERELOAD;

	public CmpComponentConfig getHOURLOCK() {
		return this.HOURLOCK;
	}
	public void setHOURLOCK(final CmpComponentConfig hOURLOCK) {
		this.HOURLOCK = hOURLOCK;
	}
	public CmpComponentConfig getMINUTELOCK() {
		return this.MINUTELOCK;
	}
	public void setMINUTELOCK(final CmpComponentConfig mINUTELOCK) {
		this.MINUTELOCK = mINUTELOCK;
	}
	public CmpComponentConfig getHOURUNLOCK() {
		return this.HOURUNLOCK;
	}
	public void setHOURUNLOCK(final CmpComponentConfig hOURUNLOCK) {
		this.HOURUNLOCK = hOURUNLOCK;
	}
	public CmpComponentConfig getMINUTEUNLOCK() {
		return this.MINUTEUNLOCK;
	}
	public void setMINUTEUNLOCK(final CmpComponentConfig mINUTEUNLOCK) {
		this.MINUTEUNLOCK = mINUTEUNLOCK;
	}
	public CmpComponentConfig getHOURRELOAD() {
		return this.HOURRELOAD;
	}
	public void setHOURRELOAD(final CmpComponentConfig hOURRELOAD) {
		this.HOURRELOAD = hOURRELOAD;
	}
	public CmpComponentConfig getMINUTERELOAD() {
		return this.MINUTERELOAD;
	}
	public void setMINUTERELOAD(final CmpComponentConfig mINUTERELOAD) {
		this.MINUTERELOAD = mINUTERELOAD;
	}
			
}
