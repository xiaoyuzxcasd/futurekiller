package com.baicai.futurekiller.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class RandomUtil {

	public static final Random random = new Random();

	public static int getRandomIndex(final Integer[] rates) {
		return getRandomIndex(rates, 100);
	}

	public static int getRandomIndex(final int[] rates) {
		return getRandomIndex(rates, 100);
	}

	public static int getRandomIndex(final Integer[] rates, final int base) {
		int sumRate = 0;
		int randomRate = random.nextInt(base);
		for (int i = 0; i < rates.length; i++) {
			sumRate += rates[i];
			if (randomRate < sumRate)
				return i;

		}
		return 0;
	}

	public static int getRandomIndex(final int[] rates, final int base) {
		int sumRate = 0;
		int randomRate = random.nextInt(base);
		for (int i = 0; i < rates.length; i++) {
			sumRate += rates[i];
			if (randomRate < sumRate)
				return i;

		}
		return 0;
	}

	public static boolean randomHit(int rate) {
		return randomHit(rate, 100);
	}

	public static boolean randomHit(int rate, final int base) {
		return random.nextInt(base) < rate;
	}

	/**
	 * 左闭右开
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int randomBetween(int begin, int end) {
		if (begin >= end) {
			return begin;
		} else {
			return random.nextInt(end - begin) + begin;
		}
	}

	/**
	 * 左闭右开
	 * @param begin
	 * @param end
	 * @return
	 */
	public static float randomBetween(float begin, float end) {
		if (begin >= end) {
			return begin;
		} else {
			return random.nextFloat() * (end - begin) + begin;
		}
	}

	/**
	 * 左闭右开
	 * @param begin
	 * @param end
	 * @return
	 */
	public static double randomBetween(double begin, double end) {
		if (begin >= end) {
			return begin;
		} else {
			return random.nextDouble() * (end - begin) + begin;
		}
	}

	/**
	 * 有 n 个小球 m 个盒子, 每个小球等概率 (概率 = 1 / 当前可放入的盒子总数) 放入某个盒子中, 某个盒子满了则剔除掉
	 * 
	 * @param ball
	 *            小球数
	 * @param boxMaxArr
	 *            每个盒子的最大值, 数组长度是盒子总数
	 * @return 存放随机结果的数组, 数组长度是盒子总数
	 */
	public static int[] randomBallInBox(int ball, int[] boxMaxArr) {
		int[] resultBoxArr = new int[boxMaxArr.length];
		randomBallInBox(ball, boxMaxArr, resultBoxArr, random);
		return resultBoxArr;
	}

	/**
	 * 有 n 个小球 m 个盒子, 每个小球等概率 (概率 = 1 / 当前可放入的盒子总数) 放入某个盒子中, 某个盒子满了则剔除掉
	 * 
	 * @param ball
	 *            小球数
	 * @param boxMaxArr
	 *            每个盒子的最大值, 数组长度是盒子总数
	 * @param resultBoxArr
	 *            存放随机结果的数组, 数组长度是盒子总数
	 * @param random
	 *            随机工具
	 */
	public static void randomBallInBox(int ball, int[] boxMaxArr, int[] resultBoxArr, Random random) {
		boolean[] full = new boolean[boxMaxArr.length];
		int total = boxMaxArr.length;
		for (int i = 0; i < ball; i++) {
			int rIndex = random.nextInt(total);
			int index = -1;
			int j = 0;
			for (; j < boxMaxArr.length; j++) {
				if (!full[j]) {
					index++;
					if (rIndex == index) {
						break;
					}
				}
			}
			resultBoxArr[j]++;
			if (boxMaxArr[j] == resultBoxArr[j]) {
				full[j] = true;
				total--;
			}
		}
	}

	/**
	 * 从一个左闭右开的范围内随机抽取指定个数的值
	 * 
	 * @param begin
	 *            范围起始 (包含此值)
	 * @param end
	 *            范围结束 (不包含此值)
	 * @param count
	 *            返回值个数
	 */
	public static int[] randomSelect(int begin, int end, int count) {
		int[] arr = new int[end - begin];
		for (int i = begin; end > i; i++) {
			arr[i - begin] = i;
		}
		if (count < arr.length) {
			shuffle(arr, count);
			arr = Arrays.copyOf(arr, count);
		}
		return arr;
	}

	/** 从一个数组内随机抽取一个值 */
	public static int randomSelect(final int[] arr) {
		int index = randomBetween(0, arr.length);
		return arr[index];
	}

	/** 从一个数组内随机抽取一个值 */
	public static <T> T randomSelect(final T[] arr) {
		int index = randomBetween(0, arr.length);
		return arr[index];
	}

	/**
	 * 从一个数组内按概率随机抽取一个值
	 * 
	 * @param arr
	 *            数组, <b>长度必须不比概率短</b>
	 * @param rates
	 *            概率
	 */
	public static int randomSelect(final int[] arr, final int[] rates) {
		final int base = sum(rates);
		int index = getRandomIndex(rates, base);
		return arr[index];
	}
	
	private static int sum(int[] arr) {
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}
		return sum;
	}

	/**
	 * 从一个数组内按概率随机抽取一个值
	 * 
	 * @param <T>
	 *            数组元素的类型
	 * @param arr
	 *            数组, <b>长度必须不比概率短</b>
	 * @param rates
	 *            概率
	 */
	public static <T> T randomSelect(final T[] arr, final int[] rates) {
		final int base = sum(rates);
		int index = getRandomIndex(rates, base);
		return arr[index];
	}

	/**
	 * 从一个数组内随机抽取指定个数的值
	 * 
	 * @param arr
	 *            数组
	 * @param count
	 *            返回值个数
	 */
	public static int[] randomSelect(int[] arr, int count) {
		int[] resArr = Arrays.copyOf(arr, arr.length);
		if (count < arr.length) {
			shuffle(resArr, count);
			resArr = Arrays.copyOf(resArr, count);
		}
		return resArr;
	}

	/**
	 * 从一个可迭代的数据结构中随机抽取一个值
	 * 
	 * @param <T>
	 *            可迭代的数据结构的元素类型
	 * @param coll
	 *            可迭代的数据结构
	 */
	public static <T> T randomSelect(Iterable<T> coll) {
		Iterator<T> itr = coll.iterator();
		if (!itr.hasNext()) {
			return null;
		}
		T res = itr.next();
		int i = 1;
		for (; itr.hasNext();) {
			T obj = itr.next();
			if (randomHit(1, 1 + i)) {
				res = obj;
			}
			i++;
		}
		return res;
	}

	/**
	 * 从一个可迭代的数据结构中随机抽取指定个数的值
	 * 
	 * @param <T>
	 *            可迭代的数据结构的元素类型
	 * @param coll
	 *            可迭代的数据结构
	 * @param count
	 *            返回值个数
	 */
	public static <T> ArrayList<T> randomSelect(Iterable<T> coll, int count) {
		Iterator<T> itr = coll.iterator();
		ArrayList<T> res = new ArrayList<T>(count);
		int i = 0;
		for (; count > i && itr.hasNext(); i++) {
			res.add(itr.next());
		}
		for (; itr.hasNext();) {
			T obj = itr.next();
			int swapIndex = randomBetween(0, 1 + i);
			if (count > swapIndex) {
				res.set(swapIndex, obj);
			}
			i++;
		}
		return res;
	}

	/**
	 * 把一个数组打乱
	 * 
	 * @param srcArr
	 *            原始数组, 执行完后所有元素已经打乱
	 */
	public static void shuffle(int[] srcArr) {
		shuffle(srcArr, srcArr.length);
	}

	/**
	 * 把一个数组的前 n 位打乱
	 * 
	 * @param srcArr
	 *            原始数组, 执行完后前 n 位是随机得到的值, 后 (长度-n) 位是其余的值
	 * @param count
	 *            指定前 n 位
	 */
	public static void shuffle(int[] srcArr, int count) {
		for (int i = 0; count > i; i++) {
			int swapIndex = randomBetween(i, srcArr.length);
			if (i != swapIndex) {
				int swap = srcArr[swapIndex];
				srcArr[swapIndex] = srcArr[i];
				srcArr[i] = swap;
			}
		}
	}

	/**
	 * 把一个数组打乱
	 * 
	 * @param srcList
	 *            原始数组, 执行完后所有元素已经打乱
	 */
	public static <T> void shuffle(ArrayList<T> srcList) {
		shuffle(srcList, srcList.size());
	}

	/**
	 * 把一个数组的前 n 位打乱
	 * 
	 * @param srcList
	 *            原始数组, 执行完后前 n 位是随机得到的值, 后 (长度-n) 位是其余的值
	 * @param count
	 *            指定前 n 位
	 */
	public static <T> void shuffle(ArrayList<T> srcList, int count) {
		int len = srcList.size();
		for (int i = 0; count > i; i++) {
			int swapIndex = randomBetween(i, len);
			if (i != swapIndex) {
				T swap = srcList.get(swapIndex);
				srcList.set(swapIndex, srcList.get(i));
				srcList.set(i, swap);
			}
		}
	}

}
