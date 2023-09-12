/*
 * (C) ActiveViam 2023
 * ALL RIGHTS RESERVED. This material is the CONFIDENTIAL and PROPRIETARY
 * property of ActiveViam. Any unauthorized use,
 * reproduction or transfer of this material is strictly prohibited
 */

package com.activiam.databricks.sparkudaf;

import java.io.Serializable;

public class Average implements Serializable {
	private long sum;
	private long count;

	public Average(long sum, long count) {
		this.sum = sum;
		this.count = count;
	}

	public long getSum() {
		return this.sum;
	}

	public void setSum(long sum) {
		this.sum = sum;
	}

	public long getCount() {
		return this.count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
