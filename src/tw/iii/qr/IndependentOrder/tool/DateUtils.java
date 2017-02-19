package tw.iii.qr.IndependentOrder.tool;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 一個有用的時間工具。
 * @author Tim Wang
 */
public final class DateUtils {
	private DateUtils() {}
	static {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		reusedCalendar = cal;
	}
	private static Calendar reusedCalendar;
	private static final Calendar getInternalCalendar() {return (Calendar)reusedCalendar.clone();}
	private static final void _setCalField(Calendar cal, int field, int value) {cal.set(field, value);}
	private static final void _setCalYear(Calendar cal, int year)              {_setCalField(cal, Calendar.YEAR,          year);}
	private static final void _setCalMonth(Calendar cal, int month)            {_setCalField(cal, Calendar.MONTH,         month);}
	private static final void _setCalDate(Calendar cal, int date)              {_setCalField(cal, Calendar.DATE,          date);}
	private static final void _setCalHour(Calendar cal, int hour)              {_setCalField(cal, Calendar.HOUR_OF_DAY,   hour);}
	private static final void _setCalMinute(Calendar cal, int minute)          {_setCalField(cal, Calendar.MINUTE,        minute);}
	private static final void _setCalSecond(Calendar cal, int second)          {_setCalField(cal, Calendar.SECOND,        second);}
	private static final int  _getCalYear(Calendar cal)      {return cal.get(Calendar.YEAR);}
	private static final int  _getCalMonth(Calendar cal)     {return cal.get(Calendar.MONTH);}
	private static final int  _getCalDate(Calendar cal)      {return cal.get(Calendar.DATE);}
	private static final int  _getCalDayOfWeek(Calendar cal) {return cal.get(Calendar.DAY_OF_WEEK);}
	private static final int  _getCalHour(Calendar cal)      {return cal.get(Calendar.HOUR_OF_DAY);}
	private static final int  _getCalMinute(Calendar cal)    {return cal.get(Calendar.MINUTE);}
	private static final int  _getCalSecond(Calendar cal)    {return cal.get(Calendar.SECOND);}
	private static final void _addCalField(Calendar cal, int field, int value) {cal.add(field, value);}
//	private static final void _addCalYear  (Calendar cal, int year)             {_addCalField(cal, Calendar.YEAR,   year);}
//	private static final void _addCalMonth (Calendar cal, int month)            {_addCalField(cal, Calendar.MONTH,  month);}
//	private static final void _addCalDate  (Calendar cal, int date)             {_addCalField(cal, Calendar.DATE,   date);}
//	private static final void _addCalHour  (Calendar cal, int hour)             {_addCalField(cal, Calendar.HOUR_OF_DAY, hour);}
//	private static final void _addCalMinute(Calendar cal, int minute)           {_addCalField(cal, Calendar.MINUTE, minute);}
//	private static final void _addCalSecond(Calendar cal, int second)           {_addCalField(cal, Calendar.SECOND, second);}
	private static final Calendar initCalWith(Date date) {
		Calendar cal;
		if(date!=null) {
			cal = getInternalCalendar();
			cal.setTimeInMillis(date.getTime());
		} else {
			cal = Calendar.getInstance();
		}
		return cal;
	}
	private static final Date operateCalendar(Date date, int field, int value) {Calendar cal = initCalWith(date);_addCalField(cal, field, value);return cal.getTime();}
	
	
	private static final Calendar clearTime(Calendar cal) {
		if(cal==null) cal = getInternalCalendar();
		int year  = _getCalYear(cal),
			month = _getCalMonth(cal),
			date  = _getCalDate(cal);
			cal.clear();
			cal.set(year, month, date);
		return cal;
	}
	
	/**
	 * 將String轉為Date。<br/>
	 * 轉為Date時，會將String中的數字依序轉為年-&gt;月-&gt;日-&gt;時-&gt;分-&gt;秒。
	 * @param date
	 * 日期字串。年若是小於等於1000，會視為民國年。
	 * @return
	 * Date
	 */
	
	
	public enum FormatType {DATE, DATE_TIME, DATE_TIME_SEC, TIME, TIME_SEC}
	
	
	
	
 
 
	
	 
	/**
	 * 清除時間，只剩下年。
	 * @param date
	 * @return
	 * 只含有年的時間。
	 */
	public static final Date clearAfterYear(Date date) {
		Calendar cal = initCalWith(date);
		int year = _getCalYear(cal);
		cal.clear();
		_setCalYear(cal, year);
		return cal.getTime();
	}
	
	/**
	 * 清除時間，只剩下年月。
	 * @param date
	 * @return
	 * 只含有年月的時間。
	 */
	public static final Date clearAfterMonth(Date date) {
		Calendar cal = initCalWith(date);
		int year  = _getCalYear(cal),
			month = _getCalMonth(cal);
		cal.clear();
		_setCalYear(cal, year);
		_setCalMonth(cal, month);
		return cal.getTime();
	}
	
	/**
	 * 清除時間，只剩下年月日。
	 * @param date
	 * @return
	 * 只含有年月日的時間。
	 */
	public static final Date clearTime(Date date) {
		Calendar cal = initCalWith(date);
		clearTime(cal);
		return cal.getTime();
	}
	
	/**
	 * 將一個日期的時間變為最大。(23:59:59.999)
	 * @param cal
	 * @return
	 */
	private static final Calendar maxTime(Calendar cal) {
		if(cal==null) cal = getInternalCalendar();
		_setCalHour(cal, 23);
		_setCalMinute(cal, 59);
		_setCalSecond(cal, 59);
		_setCalField(cal, Calendar.MILLISECOND, 999);
		return cal;
	}
	
	/**
	 * 將一個日期的時間變為最大。(23:59:59.999)
	 * @param date
	 * @return
	 */
	public static final Date maxTime(Date date) {return maxTime(initCalWith(date)).getTime();}
	
	/**
	 * 取得下一天。
	 * @param date
	 * @return
	 * 回傳下一天。
	 */
	public static final Date nextDate(Date date) {return operateCalendar(date, Calendar.DATE,  1);}
	
	/**
	 * 取得前一天。
	 * @param date
	 * @return
	 * 回傳前一天。
	 */
	public static final Date previousDate(Date date) {return operateCalendar(date, Calendar.DATE, -1);}
	
	/**
	 * 對小時偏移。
	 * @param date
	 * @param hour
	 * @return
	 * 偏移後的時間。
	 */
	public static final Date offsetByHour(Date date, int hour) {return operateCalendar(date, Calendar.HOUR_OF_DAY, hour);}
	
	/**
	 * 對日期偏移。
	 * @param date
	 * @param day
	 * @return
	 * 偏移後的時間。
	 */
	public static final Date offsetByDay(Date date, int day) {return operateCalendar(date, Calendar.DATE, day);}
	
	/**
	 * 對週做偏移。
	 * @param date
	 * @param week
	 * @return
	 * 偏移後的時間。
	 */
	public static final Date offsetByWeek(Date date, int week)  {return operateCalendar(date, Calendar.WEEK_OF_YEAR, week);}
	
	/**
	 * 對月做偏移。
	 * @param date
	 * @param month
	 * @return
	 * 偏移後的時間。
	 */
	public static final Date offsetByMonth(Date date, int month) {return operateCalendar(date, Calendar.MONTH, month);}
	
	/**
	 * 對年做偏移。
	 * @param date
	 * @param year
	 * @return
	 * 偏移後的時間。
	 */
	public static final Date offsetByYear(Date date, int year)  {return operateCalendar(date, Calendar.YEAR, year);}

	/**
	 * 兩日期之間差了多少。(aDateBigThenDate1 - date1)
	 * @param aDateBigThenDate1
	 * @param date1
	 * @return
	 * 差距的毫秒。
	 */
	public static final long diff(Date aDateBigThenDate1, Date date1) {
		Date date2 = aDateBigThenDate1;
		if(date1==null) {
			if(date2==null) return 0L;
			return date2.getTime();
		} else {
			if(date2==null) return date1.getTime();
			return date2.getTime()-date1.getTime();
		}
	}
	/**
	 * 兩個日期差了幾天
	 * return 天
	 * */
	public static final long diffDays(Date startDate, Date endDate){
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		return diffDays;
	}
	
	/**
	 * 兩個日期差了幾天
	 * return 天
	 * */
	public static final long diffHours(Date startDate, Date endDate){
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		long diffTime = endTime - startTime;
		long diffHours = diffTime / (1000 * 60 * 60);
		return diffHours;
	}
	
	
	
	/**
	 * 兩日期之間差了多少。
	 * @param date1
	 * @param date2
	 * @return
	 * 差距的毫秒。(絕對值)
	 */
	public static final long diffAbs(Date date1, Date date2) {return Math.abs(diff(date1, date2));}
	
	public enum TimeType {DATE, WEEK, MONTH, YEAR}
	
	/**
	 * 取得某日期至某日期後幾日之間的日期。
	 * @param beginDate
	 * 開始日期(會忽略日期中的時分秒)
	 * @param afterDay
	 * 後幾日
	 * @return
	 * 某日期至指定天數間的日期。
	 */
	public static final Iterable<Date> each(Date beginDate, int afterDay) {return each(beginDate, operateCalendar(beginDate, Calendar.DATE, afterDay), TimeType.DATE, 1);}
	
	/**
	 * 取得起訖日期間的日期。
	 * @param beginDate
	 * 開始日期(會忽略日期中的時分秒)
	 * @param endDate
	 * 結束日期(會忽略日期中的時分秒)
	 * @return
	 * 訖日期間的日期。
	 */
	public static final Iterable<Date> each(Date beginDate, Date endDate) {return each(beginDate, endDate, TimeType.DATE, 1);}
	
	/**
	 * 依據選擇的時間類型及增加值取得起迄日期間的日期。
	 * @param beginDate
	 * 開始日期
	 * @param endDate
	 * 結束日期
	 * @param increamentType
	 * 每次要增加的時間類型
	 * @param incrementValue
	 * 每次增加多少
	 * @return
	 * 計算後訖日期間的日期。
	 */
	public static final Iterable<Date> each(final Date beginDate, final Date endDate, final TimeType increamentType, final int incrementValue) {
		return new Iterable<Date>() {
			@Override
			public Iterator<Date> iterator() {
				return new Iterator<Date>() {
					private Calendar cal = getInternalCalendar();
					private Date _endDate;
					private Date tmp;
					private int calendarField;
					
					{
						if(beginDate!=null
						&& endDate!=null
						&& increamentType!=null
						&& incrementValue>0
						&& endDate.getTime()>beginDate.getTime()) {
							switch (increamentType) {
								case DATE:
									calendarField = Calendar.DATE;
									cal.setTime(clearTime(beginDate));
									_endDate = clearTime(endDate);
									break;
								case WEEK:
									calendarField = Calendar.WEEK_OF_YEAR;
									cal.setTime(clearTime(beginDate));
									_endDate = clearTime(endDate);
									break;
								case MONTH:
									calendarField = Calendar.MONTH;
									cal.setTime(clearAfterMonth(beginDate));
									_endDate = clearAfterMonth(endDate);
									break;
								case YEAR:
									calendarField = Calendar.YEAR;
									cal.setTime(clearAfterYear(beginDate));
									_endDate = clearAfterYear(endDate);
									break;
								default:
									calendarField = Calendar.DATE;
									cal.setTime(clearTime(beginDate));
									_endDate = clearTime(endDate);
									break;
								}							
						}
					}
					
					@Override
					public void remove() {}
					@Override
					public Date next() {
						tmp = cal.getTime();
						cal.add(calendarField, incrementValue);
						return tmp;
					}
					@Override
					public boolean hasNext() {return cal==null?false:cal.getTime().getTime()<=_endDate.getTime();}
				};
			}			
		};
	}
	
	/**
	 * 取得指定日期的頭尾日期。(不含時間)
	 * @param date
	 * @param timeType
	 * @return
	 */
	public static final Date[] getBetween(Date date, TimeType timeType) {
		Date[] dates = new Date[2];
		Calendar cal = initCalWith(date);
		clearTime(cal);
		switch (timeType) {
			case DATE:
				dates[0] = cal.getTime();
				dates[1] = cal.getTime();
				break;
			case WEEK:
				_addCalField(cal, Calendar.DATE, Calendar.SUNDAY-_getCalDayOfWeek(cal));
				dates[0] = cal.getTime();
				_addCalField(cal, Calendar.DATE, 6);
				dates[1] = cal.getTime();
				break;
			case MONTH:
				_setCalDate(cal, 1);
				dates[0] = cal.getTime();
				_setCalDate(cal, cal.getActualMaximum(Calendar.DATE));
				dates[1] = cal.getTime();
				break;
			case YEAR:
				_setCalDate(cal, 1);
				_setCalMonth(cal, Calendar.JANUARY);
				dates[0] = cal.getTime();
				_setCalMonth(cal, Calendar.DECEMBER);
				_setCalDate(cal, 31);
				dates[1] = cal.getTime();
				break;
		}
		return dates;
	}
	
	/**
	 * 取得指定日期的頭尾日期。(含時間)
	 * @param date
	 * @param timeType
	 * @return
	 */
	public static final Date[] getBetweenWithTime(Date date, TimeType timeType) {
		Date[] dates = getBetween(date, timeType);
		dates[1] = maxTime(dates[1]);
		return dates;
	}
	
	public static void main(String[] args) {
		Calendar begin = Calendar.getInstance();
		begin.setTime(new Date());
		begin.add(Calendar.MINUTE, -1);
		
		Calendar end = Calendar.getInstance();
		end.setTime(begin.getTime());
		end.add(Calendar.HOUR_OF_DAY, -24);
		System.out.println(end.getTime());
	}
	public static int getAge(Date birthday) {
		if(birthday==null) return 0;
		
		int age = 0;
		
		Calendar nowCal = Calendar.getInstance();
		Calendar birCal = Calendar.getInstance();
		birCal.setTime(birthday);
		
		age = nowCal.get(Calendar.YEAR)-birCal.get(Calendar.YEAR);
		if(nowCal.get(Calendar.MONTH)<birCal.get(Calendar.MONTH)
		|| (nowCal.get(Calendar.MONTH)==birCal.get(Calendar.MONTH) && nowCal.get(Calendar.DAY_OF_MONTH)<birCal.get(Calendar.DAY_OF_MONTH))) {
			--age;
		}
		
		return age;
	}
	
	public static String formatDuring(Date begin,Date end){
		return formatDuring(end.getTime() - begin.getTime());
	}
	
	public static String formatDuringWithDayOrHourMinute(Date begin,Date end){
		return formatDuringWithDayOrHourMinute(end.getTime() - begin.getTime());
	}
	
	public static String formatDuringWithHtml(Date begin,Date end,String cssClass){
		return formatDuringWithHtml(end.getTime() - begin.getTime(),cssClass);
	}
	public static String formatDuring(long mss) {
		long days 	 = mss / (1000 * 60 * 60 * 24);
		long hours 	 = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		return Math.abs(days) + "天  " + Math.abs(hours) + "小時  " + Math.abs(minutes) + "分 " + Math.abs(seconds) + "秒 ";
	}
	
	public static String formatDuringWithDayOrHourMinute(long mss) {
		long days 	 = mss / (1000 * 60 * 60 * 24);
		long hours 	 = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		if(days >=1){
			return Math.abs(days) + "天 ";
		}else{
			return Math.abs(hours) + "h" + Math.abs(minutes) + "'";
		}
	}
	
	
	
	
	public static Date parseyyyyMMddhhmmssToDate(String date){
		Integer year = Integer.valueOf(date.substring(0, 4));
		Integer month = Integer.valueOf(date.substring(4, 6));
		Integer day = Integer.valueOf(date.substring(6, 8));
		Integer hour = Integer.valueOf(date.substring(8, 10));
		Integer min = Integer.valueOf(date.substring(10, 12));
		Integer sec = Integer.valueOf(date.substring(12, 14));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND ,sec);
		return cal.getTime();
	}
	
	public static Date getFirstDateOfQueryMonth(int month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH,	month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getFirstDateOfQueryYear(int year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,	year);
		cal.set(Calendar.MONTH,	0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getFirstDateOfThisMonth(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getLastDateOfThisMonth(){
		Calendar cal = Calendar.getInstance();
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, lastDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static String getChineseMonth(Date date){
		Map<String, String> aMap = new HashMap<String, String>();
        aMap.put("01","一月");
        aMap.put("02","二月");
        aMap.put("03","三月");
        aMap.put("04","四月");
        aMap.put("05","五月");
        aMap.put("06","六月");
        aMap.put("07","七月");
        aMap.put("08","八月");
        aMap.put("09","九月");
        aMap.put("10","十月");
        aMap.put("11","十一月");
        aMap.put("12","十二月");
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String month = aMap.get(cal.get(Calendar.MONTH));
        return month;
       
	}
	
	public static String formatDuringWithHtml(long mss,String cssClass) {
		long days 	 = mss / (1000 * 60 * 60 * 24);
		long hours 	 = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		boolean showDays 	= true;
		boolean showHours 	= false;
		boolean showMinutes = false;
		boolean showSeconds = false;
		StringBuilder sb = new StringBuilder();
		if(Math.abs(days) > 0 || showDays){
			sb.append("<span class='").append(cssClass).append("'>").append(Math.abs(days)).append("</span>天");
			showHours 	= true;
			showMinutes = true;
			showSeconds = true;
		}
		if(Math.abs(hours) > 0 || showHours){
			sb.append("<span class='").append(cssClass).append("'>").append(Math.abs(hours)).append("</span>小時");
			showMinutes = true;
			showSeconds = true;
		}
		if(Math.abs(minutes) > 0 || showMinutes){
			sb.append("<span class='").append(cssClass).append("'>").append(Math.abs(minutes)).append("</span>分");
			showSeconds = true;
		}
		if(Math.abs(seconds) > 0 || showSeconds){
			sb.append("<span class='").append(cssClass).append("'>").append(Math.abs(seconds)).append("</span>秒");
		}
		
		return sb.toString();
	}
	
	public static boolean isThisMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDay = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date lastDay = cal.getTime();
		
		if(date.after(firstDay) && date.before(lastDay)){
			return true;
		}
		return false;
	}
	

}
