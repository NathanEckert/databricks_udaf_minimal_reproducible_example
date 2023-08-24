/*
 * (C) ActiveViam 2023
 * ALL RIGHTS RESERVED. This material is the CONFIDENTIAL and PROPRIETARY
 * property of ActiveViam. Any unauthorized use,
 * reproduction or transfer of this material is strictly prohibited
 */

package com.activeviam.databricks.udaf;

import java.io.Serializable;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Aggregator;
import org.apache.spark.sql.functions;

public static class Average implements Serializable  {
	private long sum;
	private long count;

	public Average(long sum, long count) {
		this.sum = sum;
		this.count = count;
	}

	public long getSum() {
		return this.sum;
	}

	public long getCount() {
		return this.count;
	}

	public void setSum(long sum) {
		this.sum = sum;
	}

	public void setCount(long count) {
		this.count = count;
	}
}

public static class MyAverage extends Aggregator<Long, Average, Double> {

	// A zero value for this aggregation. Should satisfy the property that any b + zero = b
	public Average zero() {
		return new Average(0L, 0L);
	}

	// Combine two values to produce a new value. For performance, the function may modify `buffer`
	// and return it instead of constructing a new object
	public Average reduce(Average buffer, Long data) {
		long newSum = buffer.getSum() + data;
		long newCount = buffer.getCount() + 1;
		buffer.setSum(newSum);
		buffer.setCount(newCount);
		return buffer;
	}

	// Merge two intermediate values
	public Average merge(Average b1, Average b2) {
		long mergedSum = b1.getSum() + b2.getSum();
		long mergedCount = b1.getCount() + b2.getCount();
		b1.setSum(mergedSum);
		b1.setCount(mergedCount);
		return b1;
	}

	// Transform the output of the reduction
	public Double finish(Average reduction) {
		return ((double) reduction.getSum()) / reduction.getCount();
	}

	// The Encoder for the intermediate value type
	public Encoder<Average> bufferEncoder() {
		return Encoders.bean(Average.class);
	}

	// The Encoder for the final output value type
	public Encoder<Double> outputEncoder() {
		return Encoders.DOUBLE();
	}

}